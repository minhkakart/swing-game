package com.minhkakart.swinggame.entities;

import com.minhkakart.swinggame.MainApplication;
import com.minhkakart.swinggame.enums.*;
import com.minhkakart.swinggame.interfaces.Drawable;
import com.minhkakart.swinggame.manager.AnimationManager;
import com.minhkakart.swinggame.manager.PlayerStateManager;
import com.minhkakart.swinggame.model.GameCamera;
import com.minhkakart.swinggame.model.ImagePart;
import com.minhkakart.swinggame.model.MapTeleport;

import java.awt.*;
import java.util.List;

@SuppressWarnings({"CallToPrintStackTrace", "BusyWait"})
public class Player implements Drawable {
    public static final int PLAYER_DRAW_AREA_WIDTH = 29;
    public static final int PLAYER_DRAW_AREA_HEIGHT = 36;

    private final Object moveLock = new Object();
    private final Object directionLock = new Object();

    private final PlayerStateManager stateManager;
    private final AnimationManager animationManager;

    private final Point position;
    private final Point boundLeftCorner;
    private Direction direction;

    private GameCamera camera;

    private Thread moveRightThread = null;
    private Thread moveLeftThread = null;
    private Thread jumpThread = null;


    public Player() {
        this.position = new Point(57, 261);
        this.stateManager = new PlayerStateManager();
        this.animationManager = new AnimationManager(stateManager);
        this.boundLeftCorner = ((Point) position.clone());

        this.boundLeftCorner.translate(-(PLAYER_DRAW_AREA_WIDTH / 2), -PLAYER_DRAW_AREA_HEIGHT + 2);
        this.direction = Direction.RIGHT;
        this.stateManager.push(PlayerState.STANDING);
        this.animationManager.notifyPlayerStateChange();
        this.animationManager.start();
    }

    public synchronized void moveLeft() {
        if (moveLeftThread != null && moveLeftThread.isAlive()) {
            return;
        }

        if (stateManager.contains(PlayerState.RUNNING)) {
            return;
        }

        synchronized (directionLock) {
            stateManager.push(PlayerState.RUNNING);
            direction = Direction.LEFT;
        }

        animationManager.notifyPlayerStateChange();

        moveLeftThread = new Thread(() -> {
            while (stateManager.contains(PlayerState.RUNNING)) {
                int playerPosRow = position.y / 24;
                int playerPosCol = position.x / 24;
                int mapCollider;
                int moveX = -1;
                int moveY = 0;
                if (direction == Direction.LEFT) {
                    int colliderCol = Math.max(playerPosCol - 1, 0);
                    try {
                        mapCollider = camera.getMapLayer().getMapColliders()[playerPosRow][colliderCol];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        mapCollider = MapCollider.NONE.getValue();
                    }
                    if (mapCollider == MapCollider.LEFT.getValue() || mapCollider == MapCollider.UP_LEFT.getValue() || mapCollider == MapCollider.DOWN_LEFT.getValue() || mapCollider == MapCollider.All.getValue()) {
                        if (position.x + moveX < MapAssetPart.PART_WIDTH * playerPosCol + PLAYER_DRAW_AREA_WIDTH / 3) {
                            moveX = 0;
                        }
                    }
                }

                moving(moveX, moveY);
                fall();

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            moveLeftThread = null;

        });
        moveLeftThread.start();

    }

    public synchronized void moveRight() {
        if (moveRightThread != null && moveRightThread.isAlive()) {
            return;
        }

        if (stateManager.contains(PlayerState.RUNNING)) {
            return;
        }

        synchronized (directionLock) {
            stateManager.push(PlayerState.RUNNING);
            direction = Direction.RIGHT;
        }

        animationManager.notifyPlayerStateChange();

        moveRightThread = new Thread(() -> {
            while (stateManager.contains(PlayerState.RUNNING)) {
                int playerPosRow = position.y / 24;
                int playerPosCol = position.x / 24;
                int mapCollider;
                int moveX = 1;
                int moveY = 0;
                if (direction == Direction.RIGHT) {
                    int colliderCol = Math.min(playerPosCol + 1, camera.getMapLayer().getMapName().getMapCol() - 1);
                    try {
                        mapCollider = camera.getMapLayer().getMapColliders()[playerPosRow][colliderCol];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        mapCollider = MapCollider.NONE.getValue();
                    }
                    if (mapCollider == MapCollider.RIGHT.getValue() || mapCollider == MapCollider.UP_RIGHT.getValue() || mapCollider == MapCollider.DOWN_RIGHT.getValue() || mapCollider == MapCollider.All.getValue()) {
                        if (position.x + moveX > MapAssetPart.PART_WIDTH * (playerPosCol + 1) - PLAYER_DRAW_AREA_WIDTH / 3) {
                            moveX = 0;
                        }
                    }
                }

                moving(moveX, moveY);
                fall();

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            moveRightThread = null;


        });
        moveRightThread.start();
    }

    public void jump() {
        PlayerState currentState = stateManager.getCurrentState();
        if (currentState == PlayerState.JUMPING || currentState == PlayerState.FALLING) {
            return;
        }
        stateManager.push(PlayerState.JUMPING);

        animationManager.notifyPlayerStateChange();

        // Total time for jump: 644ms
        jumpThread = new Thread(() -> {
            int sleepTime = 6;
            for (int i = 0; i <= 38; i++) {
                int moveX = 0;
                int moveY = -2;

                // Current position of player in the map grid
                int playerPosRow = position.y / 24;
                int playerPosCol = position.x / 24;


                // Get the row of the map grid that the player's head is in
                int colliderRow = Math.max(playerPosRow - 2, 0);
                // Get the map collider in the map grid
                int mapCollider = camera.getMapLayer().getMapColliders()[colliderRow][playerPosCol];

                // Check if the player's head is colliding with the ceiling
                if (mapCollider == MapCollider.UP.getValue() || mapCollider == MapCollider.UP_LEFT.getValue() || mapCollider == MapCollider.UP_RIGHT.getValue() || mapCollider == MapCollider.All.getValue()) {
                    if (position.y + moveY - (Player.PLAYER_DRAW_AREA_HEIGHT - 6) < MapAssetPart.PART_HEIGHT * colliderRow + 24) {
                        break;
                    }
                }

                moving(moveX, moveY);

                if (i >= 30) {
                    sleepTime = 20;
                } else if (i >= 20) {
                    sleepTime = 12;
                }
                if (i == 38) {
                    sleepTime += 204;
                }
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            jumpThread = null;
            stateManager.remove(PlayerState.JUMPING);
            animationManager.notifyPlayerStateChange();
            fall();

        });
        jumpThread.start();
    }

    public void fall() {
        int moveYCheck = 2;
        int playerPosRowCheck = position.y / 24;
        int playerPosColCheck = position.x / 24;
        int mapColliderCheck;

        int colliderRowCheck = Math.min(playerPosRowCheck + 1, camera.getMapLayer().getMapName().getMapRow() - 1);
        mapColliderCheck = camera.getMapLayer().getMapColliders()[colliderRowCheck][playerPosColCheck];
        if (mapColliderCheck == MapCollider.DOWN.getValue() || mapColliderCheck == MapCollider.DOWN_LEFT.getValue() || mapColliderCheck == MapCollider.DOWN_RIGHT.getValue() || mapColliderCheck == MapCollider.All.getValue()) {
            if (position.y + moveYCheck > MapAssetPart.PART_HEIGHT * (playerPosRowCheck + 1)) {
                return;
            }
        }

        PlayerState currentState = stateManager.getCurrentState();

        if (currentState == PlayerState.JUMPING || currentState == PlayerState.FALLING) {
            return;
        }
        stateManager.push(PlayerState.FALLING);
        animationManager.notifyPlayerStateChange();

        Thread fallThread = new Thread(() -> {
            int sleepTime = 30;
            int count = 0;
            while (true) {
                int moveX = 0;
                int moveY = 2;
                int playerPosRow = position.y / 24;
                int playerPosCol = position.x / 24;
                int mapCollider;

                int colliderRow = Math.min(playerPosRow + 1, camera.getMapLayer().getMapName().getMapRow() - 1);
                mapCollider = camera.getMapLayer().getMapColliders()[colliderRow][playerPosCol];
                if (mapCollider == MapCollider.DOWN.getValue() || mapCollider == MapCollider.DOWN_LEFT.getValue() || mapCollider == MapCollider.DOWN_RIGHT.getValue() || mapCollider == MapCollider.All.getValue()) {
                    if (position.y + moveY > MapAssetPart.PART_HEIGHT * (playerPosRow + 1)) {
                        break;
                    }
                }

                moving(moveX, moveY);

                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                count++;
                if (count < 7) {
                    sleepTime -= 1;
                } else if (count < 18) {
                    sleepTime -= 2;
                } else {
                    sleepTime -= 3;
                }

                if (sleepTime < 5) {
                    sleepTime = 5;
                }
            }
            stateManager.remove(PlayerState.FALLING);
            animationManager.notifyPlayerStateChange();

        });
        fallThread.start();

    }

    private void checkTeleport() {
        Point playerPos = getPosition();
        MapTeleport[] mapTeleports = camera.getMapLayer().getMapName().getMapTeleports();
        for (MapTeleport teleport : mapTeleports) {
            if (teleport.getGate().contains(playerPos)) {
                camera.getMapLayer().loadMap(teleport.getDestinationMap());
                Point teleportPos = teleport.getDestinationTeleport().getPlayerPosition();
                position.setLocation(teleportPos);
                MainApplication.changeBackground(teleport.getDestinationMap().getBackgroundPlace());

                boundLeftCorner.setLocation(position.x - PLAYER_DRAW_AREA_WIDTH / 2, position.y - PLAYER_DRAW_AREA_HEIGHT + 2);
                camera.setPosition(position);
                System.out.println("Teleporting to " + teleport.getDestinationMap() + " at " + teleport.getDestinationTeleport().getTeleportPosition());
            }
        }
    }

    private void moving(int dx, int dy) {
        if (this.stateManager.getCurrentState() == PlayerState.DEAD) {
            return;
        }
        synchronized (moveLock) {
            position.translate(dx, dy);
            boundLeftCorner.translate(dx, dy);
            camera.setPosition(position);
        }
        checkTeleport();
//        System.out.println("Player position: " + position + " State: " + stateManager.getCurrentState());
    }

    public void setCamera(GameCamera camera) {
        this.camera = camera;
    }

    public Point getPosition() {
        return position;
    }

    public void removeState(PlayerState playerState) {
        stateManager.remove(playerState);
        animationManager.notifyPlayerStateChange();
    }

    @Override
    public void draw(Graphics2D g2d, Point position, boolean isFlipped) {
        if (camera == null) return;

        boolean flipped = direction == Direction.LEFT;

        List<PlayerPart> parts = animationManager.getListPart();
        for (PlayerPart part : parts) {
            new ImagePart(part).draw(g2d, new Point(boundLeftCorner.x - camera.getPosition().x, boundLeftCorner.y - camera.getPosition().y), flipped);
        }
    }
}

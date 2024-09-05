package com.minhkakart.swinggame.entities;

import com.minhkakart.swinggame.enums.Direction;
import com.minhkakart.swinggame.enums.MapCollider;
import com.minhkakart.swinggame.enums.PlayerPart;
import com.minhkakart.swinggame.enums.PlayerState;
import com.minhkakart.swinggame.interfaces.Drawable;
import com.minhkakart.swinggame.model.GameCamera;
import com.minhkakart.swinggame.model.ImagePart;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class Player implements Drawable {
    public static final int PLAYER_DRAW_AREA_WIDTH = 22;
    public static final int PLAYER_DRAW_AREA_HEIGHT = 35;

    private final Point position;
    private final Point boundLeftCorner;
    private Direction direction;
    private PlayerState state;
    private final ArrayList<ImagePart> heads;
    private final ArrayList<ImagePart> bodies;
    private final ArrayList<ImagePart> legs;

    private final Object moveLock = new Object();
    private final Object directionLock = new Object();

    private GameCamera camera;

    private Thread moveRightThread = null;
    private Thread moveLeftThread = null;

    private int animationIndex = 0;
    private boolean isAnimating = false;

    private final Stack<PlayerState> stateStack = new Stack<>();

    public Player() {
        this.position = new Point(240, 215);
        this.boundLeftCorner = ((Point) position.clone());
        boundLeftCorner.translate(-(PLAYER_DRAW_AREA_WIDTH / 2), -PLAYER_DRAW_AREA_HEIGHT + 2);
        this.direction = Direction.RIGHT;
        this.state = PlayerState.STANDING;
        stateStack.push(PlayerState.STANDING);
        heads = new ArrayList<>();
        bodies = new ArrayList<>();
        legs = new ArrayList<>();

        heads.add(new ImagePart(PlayerPart.HEAD_STAND));
        heads.add(new ImagePart(PlayerPart.HEAD_RUN_1));
        heads.add(new ImagePart(PlayerPart.HEAD_RUN_2));
        heads.add(new ImagePart(PlayerPart.HEAD_RUN_3));
        heads.add(new ImagePart(PlayerPart.HEAD_RUN_4));
        heads.add(new ImagePart(PlayerPart.HEAD_RUN_5));

        bodies.add(new ImagePart(PlayerPart.BODY_STAND));
        bodies.add(new ImagePart(PlayerPart.BODY_RUN_1));
        bodies.add(new ImagePart(PlayerPart.BODY_RUN_2));
        bodies.add(new ImagePart(PlayerPart.BODY_RUN_3));
        bodies.add(new ImagePart(PlayerPart.BODY_RUN_4));
        bodies.add(new ImagePart(PlayerPart.BODY_RUN_5));

        legs.add(new ImagePart(PlayerPart.LEG_STAND));
        legs.add(new ImagePart(PlayerPart.LEG_RUN_1));
        legs.add(new ImagePart(PlayerPart.LEG_RUN_2));
        legs.add(new ImagePart(PlayerPart.LEG_RUN_3));
        legs.add(new ImagePart(PlayerPart.LEG_RUN_4));
        legs.add(new ImagePart(PlayerPart.LEG_RUN_5));
    }

    private synchronized boolean isAnimating() {
        return isAnimating;
    }

    private synchronized void setAnimating(boolean animating) {
        isAnimating = animating;
    }

    private void playAnimation(){
        if (isAnimating()){
            return;
        }
        setAnimating(true);
        new Thread(() -> {
            while (isAnimating()){
                animationIndex++;
                if (animationIndex == 6){
                    animationIndex = 1;
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            animationIndex = 0;
        }).start();
    }

    public void stopAnimation(){
        setAnimating(false);
    }

    public synchronized void moveLeft() {
        if (moveLeftThread != null && moveLeftThread.isAlive()) {
            return;
        }

        if (state == PlayerState.RUNNING && direction == Direction.RIGHT) {
            return;
        }

        if (state != PlayerState.RUNNING) {
            synchronized (directionLock) {
                state = PlayerState.RUNNING;
                direction = Direction.LEFT;
            }
        }

        playAnimation();

        moveLeftThread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (state == PlayerState.STANDING) {
                    break;
                }
                int playerPosRow = position.y / 24;
                int playerPosCol = position.x / 24;
                int mapCollider = -1;
                int moveX = -2;
                int moveY = 0;
                if (direction == Direction.LEFT) {
                    int colliderCol = Math.max(playerPosCol - 1, 0);
                    mapCollider = camera.getMapLayer().getMapColliders()[playerPosRow][colliderCol];
                    if (mapCollider == MapCollider.LEFT.getValue() || mapCollider == MapCollider.UP_LEFT.getValue() || mapCollider == MapCollider.DOWN_LEFT.getValue() || mapCollider == MapCollider.All.getValue()) {
                        if (position.x + moveX < MapAssetPart.PART_WIDTH * playerPosCol + 8) {
                            moveX = 0;
                        }
                    }
                }

                moving(moveX, moveY);
                fall();

                try {
                    Thread.sleep(15);
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

        if (state == PlayerState.RUNNING && direction == Direction.LEFT) {
            return;
        }

        if (state != PlayerState.RUNNING) {
            synchronized (directionLock) {
                state = PlayerState.RUNNING;
                direction = Direction.RIGHT;
            }
        }

        playAnimation();

        moveRightThread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (state == PlayerState.STANDING) {
                    break;
                }
                int playerPosRow = position.y / 24;
                int playerPosCol = position.x / 24;
                int mapCollider = -1;
                int moveX = 2;
                int moveY = 0;
                if (direction == Direction.RIGHT) {
                    int colliderCol = Math.min(playerPosCol + 1, camera.getMapLayer().getMapName().getMapCol() - 1);
                    mapCollider = camera.getMapLayer().getMapColliders()[playerPosRow][colliderCol];
                    if (mapCollider == MapCollider.RIGHT.getValue() || mapCollider == MapCollider.UP_RIGHT.getValue() || mapCollider == MapCollider.DOWN_RIGHT.getValue() || mapCollider == MapCollider.All.getValue()) {
                        if (position.x + moveX > MapAssetPart.PART_WIDTH * (playerPosCol + 1) - 8) {
                            moveX = 0;
                        }
                    }
                }

                moving(moveX, moveY);
                fall();

                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            moveRightThread = null;

        });
        moveRightThread.start();
    }

    public void jump() {
        PlayerState currentState = stateStack.peek();
        if (currentState == PlayerState.JUMPING || currentState == PlayerState.FALLING) {
            return;
        }
        stateStack.push(PlayerState.JUMPING);

        new Thread(() -> {
            int sleepTime = 8;
            for (int i = 0; i < 38; i++) {
                int moveX = 0;
                int moveY = -2;

                moving(moveX, moveY);

                if (i >= 24) {
                    sleepTime = 20;
                } else if (i >= 20) {
                    sleepTime = 12;
                }
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            stateStack.pop();
            fall();

        }).start();
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

        PlayerState currentState = stateStack.peek();
        if (currentState == PlayerState.JUMPING || currentState == PlayerState.FALLING) {
            return;
        }
        stateStack.push(PlayerState.FALLING);

        new Thread(() -> {
            int sleepTime = 30;
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

                sleepTime -= 2;
                if (sleepTime < 8) {
                    sleepTime = 8;
                }
            }
            stateStack.pop();

        }).start();

    }


    private void moving(int dx, int dy) {
        synchronized (moveLock) {
            position.translate(dx, dy);
            boundLeftCorner.translate(dx, dy);
            camera.setPosition(position);
        }
    }

    public PlayerState getState() {
        return state;
    }

    public synchronized void setState(PlayerState state) {
        this.state = state;
    }

    public void setCamera(GameCamera camera) {
        this.camera = camera;
    }

    public Point getPosition() {
        return position;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (camera == null) return;
        ImagePart head = heads.get(animationIndex);
        ImagePart body = bodies.get(animationIndex);
        ImagePart leg = legs.get(animationIndex);
        boolean isFlipped = direction == Direction.LEFT;
        head.draw(g2d, new Point(boundLeftCorner.x - camera.getPosition().x, boundLeftCorner.y - camera.getPosition().y), isFlipped);
        leg.draw(g2d, new Point(boundLeftCorner.x - camera.getPosition().x, boundLeftCorner.y - camera.getPosition().y), isFlipped);
        body.draw(g2d, new Point(boundLeftCorner.x - camera.getPosition().x, boundLeftCorner.y - camera.getPosition().y), isFlipped);
    }

    @Override
    public void draw(Graphics2D g2d, boolean isFlipped) {

    }

    @Override
    public void draw(Graphics2D g2d, Point position) {

    }

    @Override
    public void draw(Graphics2D g2d, Point position, boolean isFlipped) {

    }
}

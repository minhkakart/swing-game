package com.minhkakart.swinggame.ui.layer;

import com.minhkakart.swinggame.MainApplication;
import com.minhkakart.swinggame.entities.MapAssetPart;
import com.minhkakart.swinggame.enums.GameLayerDepth;
import com.minhkakart.swinggame.enums.MapData;
import com.minhkakart.swinggame.manager.ResourceManager;
import com.minhkakart.swinggame.model.*;
import com.minhkakart.swinggame.supports.StringFormatter;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class MapLayer extends GameLayer {
    private int[][][] mapAssets;
    private int[][] mapColliders;
    private Point prevPoint = new Point();
    private final Point translatePoint = new Point(0, 0);
    private double scale = 1.0;

    private MapData mapData;
    private int nLayer;
    private int row;
    private int col;

    private GameCamera camera;

    public MapLayer(MapData mapData, GameLayerDepth depth) {
        super(depth);
        this.mapData = mapData;
        loadMap(mapData);
    }

    public GameCamera getCamera() {
        return camera;
    }

    public int[][] getMapColliders() {
        return mapColliders;
    }

    public void setCamera(GameCamera camera) {
        this.camera = camera;
    }

    public MapData getMapName() {
        return mapData;
    }

    public synchronized void translate(int x, int y) {
        translatePoint.x += x;
        translatePoint.y += y;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public synchronized void loadMap(MapData mapData) {
        this.mapData = mapData;
        Thread loadAssetThread = new Thread(() -> {
            InputStream inputStream = null;
            BufferedReader bufferedReader = null;
            try {
                inputStream = Files.newInputStream(Paths.get(ResourceManager.getMapAssetDataPath(mapData.getMapName())));
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                nLayer = mapData.getMapLayerCount();
                row = mapData.getMapRow();
                col = mapData.getMapCol();
                mapAssets = new int[row][col][nLayer];
                for (int layer = 0; layer < nLayer; layer++) {
                    bufferedReader.readLine();
                    for (int i = 0; i < row; i++) {
                        String[] values = StringFormatter.removeUnnecessarySpace(bufferedReader.readLine()).split(" ");
                        for (int j = 0; j < values.length; j++) {
                            int value = Integer.parseInt(values[j]);
                            mapAssets[i][j][layer] = value;
                        }
                    }
                }
/*

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    System.out.print(String.format("%4d ", mapAssets[i][j]));
                }
                System.out.println();
            }
*/

            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread loadColliderThread = new Thread(() -> {
            InputStream inputStream = null;
            BufferedReader bufferedReader = null;
            try {
                inputStream = Files.newInputStream(Paths.get(ResourceManager.getMapColliderDataPath(mapData.getMapName())));
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                row = mapData.getMapRow();
                col = mapData.getMapCol();
                mapColliders = new int[row][col];
                for (int i = 0; i < row; i++) {
                    String[] values = StringFormatter.removeUnnecessarySpace(bufferedReader.readLine()).split(" ");
                    for (int j = 0; j < values.length; j++) {
                        int value = Integer.parseInt(values[j]);
                        mapColliders[i][j] = value;
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread loadTeleportThread = new Thread(() -> {
            InputStream inputStream = null;
            BufferedReader bufferedReader = null;
            try {
                MapTeleport[] mapTeleports = mapData.getMapTeleports();
                int nTeleport = mapData.getTeleportCount();
                inputStream = Files.newInputStream(Paths.get(ResourceManager.getMapTeleportDataPath(mapData.getMapName())));
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                for (int i = 0; i < nTeleport; i++) {
                    String[] values = StringFormatter.removeUnnecessarySpace(bufferedReader.readLine()).split(" ");
//                    int x = Integer.parseInt(values[0]);
//                    int y = Integer.parseInt(values[1]);
//                    int directionValue = Integer.parseInt(values[2]);
//                    Direction direction = Direction.values()[directionValue];
//                    mapTeleports.add(new MapTeleport(new Point(x, y), direction));

                    mapTeleports[i].setMapOwner(mapData);

                    String destinationMapName = values[0];
                    int teleportIndex = Integer.parseInt(values[1]);

                    MapData destinationMap;
                    switch (destinationMapName) {
                        case "TONE":
                            destinationMap = MapData.TONE;
                            break;
                        case "ICHIDAI":
                            destinationMap = MapData.ICHIDAI;
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid destination map name");
                    }

                    mapTeleports[i].setDestinationMap(destinationMap);
                    mapTeleports[i].setDestinationTeleport(destinationMap.getMapTeleports()[teleportIndex]);

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        loadAssetThread.start();
        loadColliderThread.start();
        loadTeleportThread.start();
        try {
            loadAssetThread.join();
            loadColliderThread.join();
            loadTeleportThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void draw(Graphics2D g2d) {
        if (mapAssets == null) {
            return;
        }
        g2d.scale(scale, scale);
        g2d.translate(translatePoint.x, translatePoint.y);
        for (int layer = 0; layer < nLayer; layer++) {
            for (int currentRow = 0; currentRow < row; currentRow++) {
                for (int currentCol = 0; currentCol < col; currentCol++) {
                    int id = mapAssets[currentRow][currentCol][layer];
                    if (id == 0) {
                        continue;
                    }

                    Optional<MapAssetPart> mapAssetPart = ResourceManager.getMapAssetPart(id);
                    if (mapAssetPart.isPresent()) {
                        MapAssetPart part = mapAssetPart.get();
                        Image image = part.getPartImage().getImage();
                        Point sourceStartPoint = part.getStartPoint();
                        Point sourceEndPoint = part.getEndPoints();
                        int dx1 = currentCol * MapAssetPart.PART_WIDTH - camera.getPosition().x;
                        int dy1 = currentRow * MapAssetPart.PART_HEIGHT - camera.getPosition().y;
                        int dx2 = dx1 + MapAssetPart.PART_WIDTH;
                        int dy2 = dy1 + MapAssetPart.PART_HEIGHT;

                        g2d.drawImage(image, dx1, dy1, dx2, dy2, sourceStartPoint.x, sourceStartPoint.y, sourceEndPoint.x, sourceEndPoint.y, null);
                    }

                }
            }
        }

        for (MapTeleport mapTeleport : mapData.getMapTeleports()) {
            int positionX = mapTeleport.getTeleportPosition().x - camera.getPosition().x;
            int positionY = mapTeleport.getTeleportPosition().y - camera.getPosition().y;
            mapTeleport.draw(g2d, new Point(positionX, positionY));
        }

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

    /**
     * Define the input event for the map layer
     */
    private final GameInputListener inputListener = new GameInputListener(1) {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            int notches = e.getWheelRotation();
            if (notches < 0) {
                scale += 0.1;
            } else {
                scale -= 0.1;
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point currentPoint = e.getPoint();
            translatePoint.x += currentPoint.x - prevPoint.x;
            translatePoint.y += currentPoint.y - prevPoint.y;
            prevPoint = currentPoint;
            if (translatePoint.x > 0) {
                translatePoint.x = 0;
            }
            if (translatePoint.y > 0) {
                translatePoint.y = 0;
            }
            if (translatePoint.x < MainApplication.WIDTH - col * MapAssetPart.PART_WIDTH) {
                translatePoint.x = MainApplication.WIDTH - col * MapAssetPart.PART_WIDTH;
            }
            if (translatePoint.y < MainApplication.HEIGHT - row * MapAssetPart.PART_HEIGHT) {
                translatePoint.y = MainApplication.HEIGHT - row * MapAssetPart.PART_HEIGHT;
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            prevPoint = e.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };

    public GameInputListener getInputListener() {
        return inputListener;
    }

}

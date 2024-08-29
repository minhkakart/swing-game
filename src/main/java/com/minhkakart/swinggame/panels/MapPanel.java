package com.minhkakart.swinggame.panels;

import com.minhkakart.swinggame.MainApplication;
import com.minhkakart.swinggame.entities.MapAssetPart;
import com.minhkakart.swinggame.enums.MapName;
import com.minhkakart.swinggame.manager.ResourceManager;
import com.minhkakart.swinggame.supports.StringFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class MapPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

    private int[][][] mapAssets;
    private Point prevPoint = new Point();
    private final Point translatePoint = new Point(0, 0);
    private double scale = 1.0;

    private int nLayer;
    private int row;
    private int col;

    public MapPanel() {
        setOpaque(true);
        setBackground(new Color(0, 0, 0, 0f));
        setPreferredSize(new Dimension(MainApplication.WIDTH, MainApplication.HEIGHT));
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        addKeyListener(this);

    }

    public MapPanel(MapName mapName) {
        this();
        loadMapAsset(mapName);
    }

    public synchronized void translate(int x, int y) {
        translatePoint.x += x;
        translatePoint.y += y;
    }

    public void loadMapAsset(MapName mapName) {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String line;
        try {
            inputStream = Files.newInputStream(Paths.get(ResourceManager.getMapDataPath(mapName.getMapName())));
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            line = bufferedReader.readLine();
            nLayer = Integer.parseInt(line);
            line = bufferedReader.readLine();
            row = Integer.parseInt(line);
            line = bufferedReader.readLine();
            col = Integer.parseInt(line);
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
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (mapAssets == null) {
            return;
        }
        Graphics2D g2d = (Graphics2D) g;
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
                        g2d.drawImage(image, currentCol * MapAssetPart.PART_WIDTH, currentRow * MapAssetPart.PART_HEIGHT, (currentCol + 1) * MapAssetPart.PART_WIDTH, (currentRow + 1) * MapAssetPart.PART_HEIGHT, sourceStartPoint.x, sourceStartPoint.y, sourceEndPoint.x, sourceEndPoint.y, this);
                    }

                }
            }
        }
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
    public void mouseDragged(MouseEvent e) {
        Point currentPoint = e.getPoint();
        translatePoint.x += currentPoint.x - prevPoint.x;
        translatePoint.y += currentPoint.y - prevPoint.y;
        prevPoint = currentPoint;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

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
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                translatePoint.y += 4;
                break;
            case KeyEvent.VK_DOWN:
                translatePoint.y -= 4;
                break;
            case KeyEvent.VK_LEFT:
                translatePoint.x += 4;
                break;
            case KeyEvent.VK_RIGHT:
                translatePoint.x -= 4;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
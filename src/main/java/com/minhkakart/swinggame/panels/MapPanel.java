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

public class MapPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

    private int[][] mapAssets;
    private Point prevPoint = new Point();
    private final Point translatePoint = new Point(0, 0);
    private double scale = 1.0;

    public MapPanel() {
        setOpaque(true);
        setBackground(new Color(0, 0, 0, 0f));
        setPreferredSize(new Dimension(MainApplication.WIDTH, MainApplication.HEIGHT));
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);

        loadMapAsset(MapName.TONE);
    }

    public void loadMapAsset(MapName mapName) {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;

        try {
            inputStream = Files.newInputStream(Paths.get(ResourceManager.getMapDataPath(mapName.getMapName())));
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();
            int row = Integer.parseInt(line);
            line = bufferedReader.readLine();
            int col = Integer.parseInt(line);
            mapAssets = new int[row][col];
            for (int i = 0; i < row; i++) {
                String[] values = StringFormatter.removeUnnecessarySpace(bufferedReader.readLine()).split(" ");
                for (int j = 0; j < values.length; j++) {
                    int value = Integer.parseInt(values[j]);
                    mapAssets[i][j] = value;
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
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(scale, scale);
        g2d.translate(translatePoint.x, translatePoint.y);
        for (int i = 0; i < mapAssets.length; i++) {
            for (int j = 0; j < mapAssets[i].length; j++) {
                int id = mapAssets[i][j];
                if (id == 0) {
                    continue;
                }

                Optional<MapAssetPart> mapAssetPart = ResourceManager.getMapAssetPart(id);
                if (mapAssetPart.isPresent()) {
                    MapAssetPart part = mapAssetPart.get();
                    Image image = part.getPartImage().getImage();
                    Point sourceStartPoint = part.getStartPoint();
                    Point sourceEndPoint = part.getEndPoints();
                    g2d.drawImage(image, j * MapAssetPart.PART_WIDTH, i * MapAssetPart.PART_HEIGHT, (j + 1) * MapAssetPart.PART_WIDTH, (i + 1) * MapAssetPart.PART_HEIGHT, sourceStartPoint.x, sourceStartPoint.y, sourceEndPoint.x, sourceEndPoint.y, null);
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
        repaint();
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
        repaint();
    }
}
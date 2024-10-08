package com.minhkakart.swinggame;

import com.minhkakart.swinggame.enums.BackgroundPlace;
import com.minhkakart.swinggame.enums.GameLayerDepth;
import com.minhkakart.swinggame.enums.MapData;
import com.minhkakart.swinggame.model.GameCamera;
import com.minhkakart.swinggame.panels.LogInPanel;
import com.minhkakart.swinggame.ui.layer.BackgroundLayer;
import com.minhkakart.swinggame.ui.layer.MapLayer;
import com.minhkakart.swinggame.ui.layer.PlayerLayer;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainApplication extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 320;

    private static final MapLayer mapLayer = new MapLayer(MapData.TONE, GameLayerDepth.MAP);
    private static final BackgroundLayer backgroundLayer = new BackgroundLayer(GameLayerDepth.BACKGROUND);
    private static final PlayerLayer playerLayer = new PlayerLayer(GameLayerDepth.PLAYER);
    private static final GameCamera camera = new GameCamera(playerLayer, mapLayer);
    private static final GamePanel gamePanel = new GamePanel();

    public MainApplication() {
        setTitle("Swing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Exit");
                System.exit(0);
            }
        });

        GamePanel gamePanel = getGamePanel();

        add(gamePanel);

        pack();
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

    }

    public static void changeBackground(BackgroundPlace backgroundPlace) {
        backgroundLayer.setBackGround(backgroundPlace);
        gamePanel.setBackground(backgroundPlace.getColor());
    }

    private static GamePanel getGamePanel() {

        backgroundLayer.setBackGround(mapLayer.getMapName().getBackgroundPlace());

        playerLayer.getPlayer().setCamera(camera);
        mapLayer.setCamera(camera);

        gamePanel.setBounds(0, 0, WIDTH, HEIGHT);
        gamePanel.setBackground(backgroundLayer.getBackground().getBackgroundPlace().getColor());

        gamePanel.addGameLayer(backgroundLayer);
        gamePanel.addGameLayer(mapLayer);
        gamePanel.addGameLayer(playerLayer);

        gamePanel.addGameInputListener(playerLayer.getInputListener());
        gamePanel.addGameInputListener(mapLayer.getInputListener());

        playerLayer.getPlayer().fall();

        LogInPanel logInPanel = new LogInPanel();
        logInPanel.getChangeBackground().addActionListener(e -> {
            BackgroundPlace[] bgPlaces = BackgroundPlace.values();
            int index = (int) (Math.random() * bgPlaces.length);
            backgroundLayer.setBackGround(bgPlaces[index]);
            gamePanel.setBackground(backgroundLayer.getBackground().getBackgroundPlace().getColor());
        });

//        gamePanel.add(logInPanel);

        return gamePanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApplication::new);
    }
}

package com.minhkakart.swinggame;

import com.minhkakart.swinggame.enums.MapName;
import com.minhkakart.swinggame.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainApplication extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 320;

    public MainApplication() {
        setTitle("Swing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });


        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        layeredPane.setOpaque(true);
        
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setBounds(0, 0, WIDTH, HEIGHT);

        MapPanel mapPanel = new MapPanel(MapName.ICHIDAI);
        mapPanel.setBounds(0, 1, WIDTH, HEIGHT);

        PlayerPanel playerPanel = new PlayerPanel();
        playerPanel.setBounds(1, 0, WIDTH, HEIGHT);

        ControlPanel controlPanel = new ControlPanel();
        controlPanel.setBounds(1, 1, WIDTH, HEIGHT);
        controlPanel.addKeyListener(mapPanel);

        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(mapPanel, new Integer(1));
        layeredPane.add(playerPanel, new Integer(2));
        layeredPane.add(controlPanel, new Integer(3));

        add(layeredPane);

        pack();
        setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApplication::new);
    }
}

package com.minhkakart.swinggame;

import com.minhkakart.swinggame.panels.BackgroundPanel;
import com.minhkakart.swinggame.panels.LogInPanel;
import com.minhkakart.swinggame.panels.MapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainApplication extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

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

        LogInPanel logInPanel = new LogInPanel();
        logInPanel.setBounds( -1, -1, WIDTH, HEIGHT);

        MapPanel mapPanel = new MapPanel();
        mapPanel.setBounds(1, 1, WIDTH, HEIGHT);

        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
//        layeredPane.add(logInPanel, new Integer(1));
        layeredPane.add(mapPanel, new Integer(2));


        add(layeredPane);

        pack();
        setLocationRelativeTo(null);

        logInPanel.getExitButton().addActionListener(e -> System.exit(0));
        logInPanel.getChangeBackground().addActionListener(e -> backgroundPanel.changeBackGround());


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApplication::new);
    }
}

package com.minhkakart.swinggame.panels;

import com.minhkakart.swinggame.MainApplication;
import com.minhkakart.swinggame.entities.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {

    private Player player = new Player();

    public PlayerPanel() {
        setOpaque(true);
        setBackground(new Color(0, 0, 0, 0f));
        setPreferredSize(new Dimension(MainApplication.WIDTH, MainApplication.HEIGHT));
        setFocusable(true);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        player.draw(g2d);
    }
}

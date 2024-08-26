package com.minhkakart.swinggame.panels;

import com.minhkakart.swinggame.entities.Background;
import com.minhkakart.swinggame.enums.BackgroundPlace;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private final Background background;

    public BackgroundPanel() {
        setOpaque(true);
        setBackground(new Color(32, 195, 245));
        background = new Background(BackgroundPlace.VILLAGE);

        new Timer(1000 / 50, e -> repaint()).start();

        background.animate();
    }

    public void changeBackGround(){
        BackgroundPlace place = background.getBackgroundPlace();
        if (place == BackgroundPlace.VILLAGE) {
            background.changeBackground(BackgroundPlace.SNOW);
        } else {
            background.changeBackground(BackgroundPlace.VILLAGE);
        }
    }

    @Override
    public void paint(java.awt.Graphics g) {
        super.paint(g);
        background.draw((java.awt.Graphics2D) g);
    }

}

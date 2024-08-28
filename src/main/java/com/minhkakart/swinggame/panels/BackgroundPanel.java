package com.minhkakart.swinggame.panels;

import com.minhkakart.swinggame.entities.Background;
import com.minhkakart.swinggame.enums.BackgroundPlace;

import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import java.util.stream.Collectors;

public class BackgroundPanel extends JPanel {
    private final Background background;

    public BackgroundPanel() {
        setOpaque(true);

        BackgroundPlace[] places = BackgroundPlace.values();
        Random random = new Random();
        int index = random.nextInt(places.length);

        background = new Background(places[index]);
        setBackground(background.getBackgroundPlace().getColor());

        new Timer(1000 / 50, e -> repaint()).start();

        background.animate();
    }

    public void changeBackGround(){
        BackgroundPlace[] places = BackgroundPlace.values();
        List<BackgroundPlace> backgroundPlaces = Arrays.stream(places).collect(Collectors.toList());
        backgroundPlaces.remove(background.getBackgroundPlace());

        Random random = new Random();
        int index = random.nextInt(backgroundPlaces.size());

        background.changeBackground(backgroundPlaces.get(index));
        setBackground(background.getBackgroundPlace().getColor());
    }

    @Override
    public void paint(java.awt.Graphics g) {
        super.paint(g);
        background.draw((java.awt.Graphics2D) g);
    }

}

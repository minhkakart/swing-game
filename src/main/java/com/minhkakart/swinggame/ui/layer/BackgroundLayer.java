package com.minhkakart.swinggame.ui.layer;

import com.minhkakart.swinggame.entities.Background;
import com.minhkakart.swinggame.enums.BackgroundPlace;
import com.minhkakart.swinggame.enums.GameLayerDepth;

import java.awt.*;
import java.util.Random;

public class BackgroundLayer extends GameLayer {
    private final Background background;
    public BackgroundLayer(GameLayerDepth depth) {
        super(depth);

        BackgroundPlace[] places = BackgroundPlace.values();
        Random random = new Random();
        int index = random.nextInt(places.length);
        background = new Background(places[index]);
        background.animate();
    }

    public Background getBackground() {
        return background;
    }


    public void setBackGround(BackgroundPlace place) {
        background.changeBackground(place);
    }

    @Override
    public void draw(Graphics2D g2d, Point position, boolean isFlipped) {
        background.draw(g2d, position, isFlipped);
    }

}

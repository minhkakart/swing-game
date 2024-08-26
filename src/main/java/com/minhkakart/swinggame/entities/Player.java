package com.minhkakart.swinggame.entities;

import com.minhkakart.swinggame.enums.Direction;
import com.minhkakart.swinggame.interfaces.Drawable;
import com.minhkakart.swinggame.manager.AnimationManager;
import com.minhkakart.swinggame.manager.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player implements Drawable {
    private final int ID;
    private final AnimationManager animationManager;
    private final Point position = new Point(0, 0);

    public Player() {
        this.ID = new Random().nextInt();
        animationManager = new AnimationManager();
    }

    public int getID() {
        return ID;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position.setLocation(position);
    }

    public void playAnimation() {
        animationManager.playAnimation();
    }

    public void stopAnimation() {
        animationManager.stopAnimation();
    }

    public void setDirection(Direction direction) {
        animationManager.setDirection(direction);
    }

    @Override
    public void draw(Graphics2D g) {
        animationManager.draw(g, position);

    }
}

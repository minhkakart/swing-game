package com.minhkakart.swinggame.entities;

import com.minhkakart.swinggame.enums.Direction;
import com.minhkakart.swinggame.enums.PlayerPart;
import com.minhkakart.swinggame.enums.PlayerState;
import com.minhkakart.swinggame.interfaces.Drawable;
import com.minhkakart.swinggame.manager.AnimationManager;
import com.minhkakart.swinggame.manager.ResourceManager;
import com.minhkakart.swinggame.model.ImagePart;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player implements Drawable {

    private Point position;
    private Point boundLeftCorner;
    private Direction direction;
    private PlayerState state;
    private ImagePart head = new ImagePart(PlayerPart.HEAD_STAND);
    private ImagePart body = new ImagePart(PlayerPart.BODY_STAND);
    private ImagePart leg = new ImagePart(PlayerPart.LEG_STAND);

    public Player() {
        this.position = new Point(240, 218);
        this.boundLeftCorner = ((Point) position.clone());
        boundLeftCorner.translate(-11, -35);
        this.direction = Direction.RIGHT;
        this.state = PlayerState.STANDING;
    }

    @Override
    public void draw(Graphics2D g) {
        head.draw(g, boundLeftCorner);
        body.draw(g, boundLeftCorner);
        leg.draw(g, boundLeftCorner);
    }

    @Override
    public void draw(Graphics2D g, Point position) {

    }
}

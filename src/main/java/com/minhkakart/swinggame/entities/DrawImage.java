package com.minhkakart.swinggame.entities;

import com.minhkakart.swinggame.interfaces.Drawable;

import javax.swing.*;
import java.awt.*;

public class DrawImage implements Drawable {
    private ImageIcon image;
    private Point position;

    public DrawImage(ImageIcon image, Point position) {
        this.image = image;
        this.position = position;
    }

    public ImageIcon getImageIcon() {
        return image;
    }

    public Image getDrawImage() {
        return image.getImage();
    }

    public synchronized void setImageIcon(ImageIcon image) {
        this.image = image;
    }

    public Point getPosition() {
        return position;
    }

    public synchronized void setPosition(Point position) {
        this.position = position;
    }

    public int getImageWidth() {
        return getImageIcon().getIconWidth();
    }

    public int getImageHeight() {
        return getImageIcon().getIconHeight();
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(image.getImage(), position.x, position.y, null);
    }

    @Override
    public void draw(Graphics2D g, Point position) {

    }
}

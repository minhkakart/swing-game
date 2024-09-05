package com.minhkakart.swinggame.interfaces;

import java.awt.*;

public interface Drawable {
    void draw(Graphics2D g2d);
    void draw(Graphics2D g2d, boolean isFlipped);
    void draw(Graphics2D g2d, Point position);
    void draw(Graphics2D g2d, Point position, boolean isFlipped);
}

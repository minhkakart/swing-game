package com.minhkakart.swinggame.model;

import com.minhkakart.swinggame.enums.Direction;
import com.minhkakart.swinggame.manager.ResourceManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class MapArrow {
    private static final BufferedImage bufferedImage;
    private static final int WIDTH = 13;
    private static final int HEIGHT = 16;
    private static final int SOURCE_X = 146;
    private static final int SOURCE_Y = 233;

    static {
        try {
            bufferedImage = ImageIO.read(new File(ResourceManager.getImagePath("Big4.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void draw(Graphics2D g2d, Point position, Direction direction) {
        BufferedImage sub = bufferedImage.getSubimage(SOURCE_X, SOURCE_Y, WIDTH, HEIGHT);
        AffineTransform transform = new AffineTransform();
        if (direction == Direction.LEFT) {
            transform.scale(-1, 1);
            transform.translate(-position.x, position.y);
        } else if (direction == Direction.UP) {
            transform.rotate(Math.toRadians(-90));
            transform.translate(-position.y, position.x);
        } else if (direction == Direction.DOWN) {
            transform.rotate(Math.toRadians(90));
            transform.translate(position.y, -position.x);
        } else {
            transform.translate(position.x, position.y);
        }
        g2d.drawImage(sub, transform, null);
    }
}

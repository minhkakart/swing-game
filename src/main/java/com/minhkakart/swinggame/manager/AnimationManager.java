package com.minhkakart.swinggame.manager;

import com.minhkakart.swinggame.enums.Direction;

import javax.swing.*;
import java.awt.*;

public class AnimationManager {
    private final ImageIcon moveImage;

    private int imageIndex = 0;
    private Direction direction = Direction.RIGHT;

    private boolean isAnimating = false;

    public AnimationManager() {
        moveImage = new ImageIcon(ResourceManager.getImagePath("move.png"));
    }

    public void playAnimation() {
        if (isAnimating) {
            return;
        }

        new Thread(() -> {
            setAnimating(true);
            int imageIndex = 1;
            while (isAnimating) {
                setImageIndex(imageIndex);
                if (imageIndex == 5) {
                    imageIndex = 1;
                } else {
                    imageIndex++;
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public synchronized void stopAnimation() {
        setImageIndex(0);
        setAnimating(false);
    }

    public synchronized void setAnimating(boolean isAnimating) {
        this.isAnimating = isAnimating;
    }

    private synchronized void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }

    public synchronized void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void draw(Graphics2D g, Point position) {
        int dx1 = (int) position.getX();
        int dy1 = (int) position.getY();
        int dx2 = (int) position.getX() + 50;
        int dy2 = (int) position.getY() + moveImage.getIconHeight();
        int sx1 = (imageIndex) * 50;
        int sy1 = 0;
        int sx2 = (imageIndex + 1) * 50;
        int sy2 = moveImage.getIconHeight();

        if (direction == Direction.LEFT) {
            int dx1Temp = dx1;
            dx1 = dx2;
            dx2 = dx1Temp;
        }

        g.drawImage(moveImage.getImage(), dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
    }
}

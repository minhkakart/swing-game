package com.minhkakart.swinggame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameCanvas extends Canvas implements KeyListener, Transparency {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;

    private int x = 50;
    private int y = 50;
    private int dx = 10;
    private int dy = 10;
    private boolean isRightDirection = true;
    private boolean isDownDirection = true;

    public GameCanvas() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        new Thread(() -> {
            while (true) {
                if (isRightDirection) {
                    x++;
                } else {
                    x--;
                }

                if (isDownDirection) {
                    y++;
                } else {
                    y--;
                }

                if (x + dx > WIDTH || x <= 0) {
                    isRightDirection = !isRightDirection;
                }

                if (y + dy > HEIGHT || y <= 0) {
                    isDownDirection = !isDownDirection;
                }

                repaint();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public GameCanvas(Color backgroundColor) {
        this();
        setBackground(backgroundColor);
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, dx, dy);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            x -= 2;
        } else if (key == KeyEvent.VK_RIGHT) {
            x += 2;
        } else if (key == KeyEvent.VK_UP) {
            y -= 2;
        } else if (key == KeyEvent.VK_DOWN) {
            y += 2;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public int getTransparency() {
        return TRANSLUCENT;
    }
}

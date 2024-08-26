package com.minhkakart.swinggame;

import com.minhkakart.swinggame.entities.Player;
import com.minhkakart.swinggame.enums.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements KeyListener, MouseMotionListener, MouseListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private final Player player = new Player();

    public GamePanel() {
        setOpaque(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();

        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        new Thread(() -> {
            while (true) {
                repaint();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public GamePanel(Color backgroundColor) {
        this();
        setBackground(backgroundColor);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        player.draw(g2d);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            player.playAnimation();
            player.setPosition(new Point((int) player.getPosition().getX(), (int) player.getPosition().getY() - 1));
        } else if (keyCode == KeyEvent.VK_DOWN) {
            player.setPosition(new Point((int) player.getPosition().getX(), (int) player.getPosition().getY() + 1));
        } else if (keyCode == KeyEvent.VK_LEFT) {
            player.setDirection(Direction.LEFT);
            player.playAnimation();
            player.setPosition(new Point((int) player.getPosition().getX() - 1, (int) player.getPosition().getY()));
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            player.setDirection(Direction.RIGHT);
            player.playAnimation();
            player.setPosition(new Point((int) player.getPosition().getX() + 1, (int) player.getPosition().getY()));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.stopAnimation();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        System.out.println("Mouse pressed: " + e.getX() + ", " + e.getY());
        player.setPosition(new Point(e.getX() - 25, e.getY() - 35));
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse dragged: " + e.getX() + ", " + e.getY());
        player.setPosition(new Point(e.getX() - 25, e.getY() - 35));
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

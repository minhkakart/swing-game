package com.minhkakart.swinggame;

import com.minhkakart.swinggame.model.GameInputListener;
import com.minhkakart.swinggame.ui.layer.GameLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
    private final ArrayList<GameLayer> gameLayers = new ArrayList<>();
    private final ArrayList<GameInputListener> gameInputListeners = new ArrayList<>();

    public GamePanel() {
        setOpaque(true);
        setPreferredSize(new Dimension(MainApplication.WIDTH, MainApplication.HEIGHT));
        setFocusable(true);

        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);

        new Timer(1000 / 60, e -> repaint()).start();
    }

    public void addGameLayer(GameLayer gameLayer) {
        gameLayers.add(gameLayer);
        gameLayers.sort(GameLayer::compareTo);
    }

    public void addGameInputListener(GameInputListener gameInputListener) {
        gameInputListeners.add(gameInputListener);
        gameInputListeners.sort(GameInputListener::compareTo);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        for (GameLayer gameLayer : gameLayers) {
            gameLayer.draw(g2d);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (gameInputListeners.isEmpty()) return;
        int largestPriority = gameInputListeners.get(0).getPriority();
        for (GameInputListener gameInputListener : gameInputListeners) {
            if (gameInputListener.getPriority() == largestPriority) {
                gameInputListener.keyTyped(e);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameInputListeners.isEmpty()) return;
        int largestPriority = gameInputListeners.get(0).getPriority();
        for (GameInputListener gameInputListener : gameInputListeners) {
            if (gameInputListener.getPriority() == largestPriority) {
                gameInputListener.keyPressed(e);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (gameInputListeners.isEmpty()) return;
        int largestPriority = gameInputListeners.get(0).getPriority();
        for (GameInputListener gameInputListener : gameInputListeners) {
            if (gameInputListener.getPriority() == largestPriority) {
                gameInputListener.keyReleased(e);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (gameInputListeners.isEmpty()) return;
        int largestPriority = gameInputListeners.get(0).getPriority();
        for (GameInputListener gameInputListener : gameInputListeners) {
            if (gameInputListener.getPriority() == largestPriority) {
                gameInputListener.mouseClicked(e);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (gameInputListeners.isEmpty()) return;
        int largestPriority = gameInputListeners.get(0).getPriority();
        for (GameInputListener gameInputListener : gameInputListeners) {
            if (gameInputListener.getPriority() == largestPriority) {
                gameInputListener.mousePressed(e);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (gameInputListeners.isEmpty()) return;
        int largestPriority = gameInputListeners.get(0).getPriority();
        for (GameInputListener gameInputListener : gameInputListeners) {
            if (gameInputListener.getPriority() == largestPriority) {
                gameInputListener.mouseReleased(e);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (gameInputListeners.isEmpty()) return;
        int largestPriority = gameInputListeners.get(0).getPriority();
        for (GameInputListener gameInputListener : gameInputListeners) {
            if (gameInputListener.getPriority() == largestPriority) {
                gameInputListener.mouseEntered(e);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (gameInputListeners.isEmpty()) return;
        int largestPriority = gameInputListeners.get(0).getPriority();
        for (GameInputListener gameInputListener : gameInputListeners) {
            if (gameInputListener.getPriority() == largestPriority) {
                gameInputListener.mouseExited(e);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (gameInputListeners.isEmpty()) return;
        int largestPriority = gameInputListeners.get(0).getPriority();
        for (GameInputListener gameInputListener : gameInputListeners) {
            if (gameInputListener.getPriority() == largestPriority) {
                gameInputListener.mouseDragged(e);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (gameInputListeners.isEmpty()) return;
        int largestPriority = gameInputListeners.get(0).getPriority();
        for (GameInputListener gameInputListener : gameInputListeners) {
            if (gameInputListener.getPriority() == largestPriority) {
                gameInputListener.mouseMoved(e);
            }
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (gameInputListeners.isEmpty()) return;
        int largestPriority = gameInputListeners.get(0).getPriority();
        for (GameInputListener gameInputListener : gameInputListeners) {
            if (gameInputListener.getPriority() == largestPriority) {
                gameInputListener.mouseWheelMoved(e);
            }
        }
    }
}

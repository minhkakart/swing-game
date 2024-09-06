package com.minhkakart.swinggame.ui.layer;

import com.minhkakart.swinggame.entities.Player;
import com.minhkakart.swinggame.enums.GameLayerDepth;
import com.minhkakart.swinggame.enums.PlayerState;
import com.minhkakart.swinggame.model.GameInputListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PlayerLayer extends GameLayer {
    private final Player player = new Player();

    private final Set<Integer> pressedKeys = Collections.synchronizedSet(new HashSet<>());

    public PlayerLayer(GameLayerDepth depth) {
        super(depth);

        new Timer(100, (e) -> playKeyEvent()).start();
    }

    public Player getPlayer() {
        return player;
    }

    private void playKeyEvent() {
        for (int key : pressedKeys) {
            switch (key) {
                case KeyEvent.VK_LEFT:
                    player.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    player.moveRight();
                    break;
                case KeyEvent.VK_UP:
                    player.jump();
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        player.draw(g2d);
    }

    @Override
    public void draw(Graphics2D g2d, boolean isFlipped) {

    }

    @Override
    public void draw(Graphics2D g2d, Point position) {

    }

    @Override
    public void draw(Graphics2D g2d, Point position, boolean isFlipped) {

    }

    private final GameInputListener inputListener = new GameInputListener(1) {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (!pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                        pressedKeys.add(KeyEvent.VK_LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (!pressedKeys.contains(KeyEvent.VK_LEFT)) {
                        pressedKeys.add(KeyEvent.VK_RIGHT);
                    }
                    break;
                case KeyEvent.VK_UP:
                    pressedKeys.add(KeyEvent.VK_UP);
                    break;
            }
            playKeyEvent();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            pressedKeys.remove(e.getKeyCode());
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_RIGHT:
                    player.stopAnimation();
                    player.setState(PlayerState.STANDING);
                    break;
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

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

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

        }
    };

    public GameInputListener getInputListener() {
        return inputListener;
    }

}

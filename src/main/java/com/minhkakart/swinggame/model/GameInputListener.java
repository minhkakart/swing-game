package com.minhkakart.swinggame.model;

import javax.swing.event.MouseInputListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelListener;

public abstract class GameInputListener implements KeyListener, MouseInputListener, MouseWheelListener, Comparable<GameInputListener> {
    private final int priority;

    public GameInputListener(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    // Sort in descending order of priority by default
    @Override
    public int compareTo(GameInputListener o) {
        return o.getPriority() - this.getPriority();
    }
}

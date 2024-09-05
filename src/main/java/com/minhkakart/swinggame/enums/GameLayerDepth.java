package com.minhkakart.swinggame.enums;

public enum GameLayerDepth {
    BACKGROUND(0),
    MAP(1),
    PLAYER(2),
    CONTROL(3);

    private final int depth;

    GameLayerDepth(int depth) {
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }
}

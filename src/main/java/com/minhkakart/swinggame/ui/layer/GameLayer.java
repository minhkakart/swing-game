package com.minhkakart.swinggame.ui.layer;

import com.minhkakart.swinggame.enums.GameLayerDepth;
import com.minhkakart.swinggame.interfaces.Drawable;

public abstract class GameLayer implements Comparable<GameLayer>, Drawable {
    private final GameLayerDepth depth;

    public GameLayer(GameLayerDepth depth) {
        this.depth = depth;
    }

    // Sort in ascending order of depth by default
    @Override
    public int compareTo(GameLayer o) {
        return Integer.compare(this.depth.getDepth(), o.depth.getDepth());
    }
}

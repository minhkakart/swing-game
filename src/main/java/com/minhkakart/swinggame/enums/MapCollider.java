package com.minhkakart.swinggame.enums;

/**
 * Enum for map collider
 * NONE: No collider
 * LEFT: Stop player from moving left
 * RIGHT: Stop player from moving right
 * UP: Stop player from moving up
 * DOWN: Stop player from moving down
 * All: Stop player from moving in all directions
 */
public enum MapCollider {
    NONE(0),
    LEFT(1),
    RIGHT(2),
    UP(3),
    DOWN(4),
    UP_LEFT(5),
    UP_RIGHT(6),
    DOWN_LEFT(7),
    DOWN_RIGHT(8),
    All(9);

    private final int value;

    MapCollider(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

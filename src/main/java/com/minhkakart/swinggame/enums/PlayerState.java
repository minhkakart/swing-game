package com.minhkakart.swinggame.enums;

public enum PlayerState {
    STANDING(1, 0),
    RUNNING(5, 50),
    JUMPING(5, 200),
    FALLING(1, 0),
    BEING_HIT(1, 0),
    DEAD(1, 0);

    /**
     * The number of animation images
     */
    private final int ANIMATION_COUNT;

    /**
     * The speed of animation in milliseconds
     */
    private final int ANIMATION_SPEED;

    PlayerState(int animationCount, int animationSpeed) {
        ANIMATION_COUNT = animationCount;
        ANIMATION_SPEED = animationSpeed;
    }

    PlayerState() {
        ANIMATION_COUNT = 1;
        ANIMATION_SPEED = 1;
    }

    public int getAnimationCount() {
        return ANIMATION_COUNT;
    }

    public int getAnimationSpeed() {
        return ANIMATION_SPEED;
    }
}

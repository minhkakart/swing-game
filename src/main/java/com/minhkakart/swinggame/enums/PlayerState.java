package com.minhkakart.swinggame.enums;

public enum PlayerState {
    STANDING(0),
    RUNNING(1),
    JUMPING(2),
    FALLING(2),
    ATTACKING(2),
    BEING_HIT(3),
    DEAD(100);

    private final int priority;

    PlayerState(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

}
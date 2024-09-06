package com.minhkakart.swinggame.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum JumpAnimationStage {
    ONE(new PlayerPart[]{PlayerPart.HEAD_JUMP, PlayerPart.LEG_JUMP, PlayerPart.BODY_JUMP}, 200),
    TWO(new PlayerPart[]{PlayerPart.ROLL_1}, 74),
    THREE(new PlayerPart[]{PlayerPart.ROLL_2}, 74),
    FOUR(new PlayerPart[]{PlayerPart.ROLL_3}, 74),
    FIVE(new PlayerPart[]{PlayerPart.ROLL_4}, 74),
    SIX(new PlayerPart[]{PlayerPart.ROLL_1}, 74),
    SEVEN(new PlayerPart[]{PlayerPart.ROLL_2}, 74);

    private final List<PlayerPart> listPart;
    private final int duration;

    JumpAnimationStage(PlayerPart[] listPart, int duration) {
        this.listPart = Arrays.stream(listPart).collect(Collectors.toList());
        this.duration = duration;
    }

    public List<PlayerPart> getListPart() {
        return listPart;
    }

    public int getDuration() {
        return duration;
    }

    public String toString() {
        return this.name();
    }
}

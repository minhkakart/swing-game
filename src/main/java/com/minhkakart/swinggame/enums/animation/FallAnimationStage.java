package com.minhkakart.swinggame.enums.animation;

import com.minhkakart.swinggame.enums.PlayerPart;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum FallAnimationStage {
    STAGE_1(new PlayerPart[]{PlayerPart.HEAD_FALL, PlayerPart.LEG_FALL, PlayerPart.BODY_FALL}, 100);

    private final List<PlayerPart> listPart;
    private final int duration;

    FallAnimationStage(PlayerPart[] listPart, int duration) {
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

package com.minhkakart.swinggame.enums.animation;

import com.minhkakart.swinggame.enums.PlayerPart;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum StandAnimationStage {
    STAGE_0(new PlayerPart[]{PlayerPart.HEAD_STAND_0, PlayerPart.LEG_STAND, PlayerPart.BODY_STAND_0}, 400),
    STAGE_1(new PlayerPart[]{PlayerPart.HEAD_STAND_1, PlayerPart.LEG_STAND, PlayerPart.BODY_STAND_1}, 400);

    private final List<PlayerPart> listPart;
    private final int duration;

    StandAnimationStage(PlayerPart[] listPart, int duration) {
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

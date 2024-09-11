package com.minhkakart.swinggame.enums.animation;

import com.minhkakart.swinggame.enums.PlayerPart;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum RunAnimationStage {
    STAGE_1(new PlayerPart[]{PlayerPart.HEAD_RUN_1, PlayerPart.LEG_RUN_1, PlayerPart.BODY_RUN_1}, 50),
    STAGE_2(new PlayerPart[]{PlayerPart.HEAD_RUN_2, PlayerPart.LEG_RUN_2, PlayerPart.BODY_RUN_2}, 50),
    STAGE_3(new PlayerPart[]{PlayerPart.HEAD_RUN_3, PlayerPart.LEG_RUN_3, PlayerPart.BODY_RUN_3}, 50),
    STAGE_4(new PlayerPart[]{PlayerPart.HEAD_RUN_4, PlayerPart.LEG_RUN_4, PlayerPart.BODY_RUN_4}, 50),
    STAGE_5(new PlayerPart[]{PlayerPart.HEAD_RUN_5, PlayerPart.LEG_RUN_5, PlayerPart.BODY_RUN_5}, 50);

    private final List<PlayerPart> listPart;
    private final int duration;

    RunAnimationStage(PlayerPart[] listPart, int duration) {
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

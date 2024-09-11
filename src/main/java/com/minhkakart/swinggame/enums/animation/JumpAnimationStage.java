package com.minhkakart.swinggame.enums.animation;

import com.minhkakart.swinggame.enums.PlayerPart;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum JumpAnimationStage {
    STAGE_1(new PlayerPart[]{PlayerPart.HEAD_JUMP, PlayerPart.LEG_JUMP, PlayerPart.BODY_JUMP}, 200),
    STAGE_2(new PlayerPart[]{PlayerPart.ROLL_1}, 74),
    STAGE_3(new PlayerPart[]{PlayerPart.ROLL_2}, 74),
    STAGE_4(new PlayerPart[]{PlayerPart.ROLL_3}, 74),
    STAGE_5(new PlayerPart[]{PlayerPart.ROLL_4}, 74),
    STAGE_6(new PlayerPart[]{PlayerPart.ROLL_1}, 74),
    STAGE_7(new PlayerPart[]{PlayerPart.ROLL_2}, 74);

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

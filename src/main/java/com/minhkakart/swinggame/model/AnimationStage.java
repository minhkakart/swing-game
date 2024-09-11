package com.minhkakart.swinggame.model;

import com.minhkakart.swinggame.enums.PlayerState;

public class AnimationStage {
    private PlayerState playerState;

    public AnimationStage(PlayerState playerState) {
        this.playerState = playerState;
    }


    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }
}

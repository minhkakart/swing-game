package com.minhkakart.swinggame.manager;

import com.minhkakart.swinggame.enums.PlayerPart;
import com.minhkakart.swinggame.enums.PlayerState;
import com.minhkakart.swinggame.enums.animation.FallAnimationStage;
import com.minhkakart.swinggame.enums.animation.JumpAnimationStage;
import com.minhkakart.swinggame.enums.animation.RunAnimationStage;
import com.minhkakart.swinggame.enums.animation.StandAnimationStage;

import java.util.List;

public class AnimationManager implements Runnable {
    private final PlayerStateManager playerStateManager;
    private final Thread animationThread;
    private int currentStage;
    private List<PlayerPart> listPart;
    private boolean isNotified;
    private PlayerState playerState;

    private final Object lock = new Object();

    public AnimationManager(PlayerStateManager playerStateManager) {
        this.playerStateManager = playerStateManager;
        this.animationThread = new Thread(this);
        this.currentStage = 0;
        this.isNotified = false;
    }

    public void start() {
        animationThread.start();
    }

    public void notifyPlayerStateChange() {
        synchronized (lock) {
            if (playerState == null || playerState != playerStateManager.getCurrentState()) {
                playerState = playerStateManager.getCurrentState();
                setCurrentStage(0);
                changeNotified();
            }
        }
    }

    public List<PlayerPart> getListPart() {
        synchronized (lock) {
            return listPart;
        }
    }

    private void setListPart(List<PlayerPart> listPart) {
        synchronized (lock) {
            this.listPart = listPart;
        }
    }

    private void setCurrentStage(int currentStage) {
        synchronized (lock) {
            this.currentStage = currentStage;
        }
    }

    public int getCurrentStage() {
        synchronized (lock) {
            return currentStage;
        }
    }

    public boolean isNotified() {
        synchronized (lock) {
            return isNotified;
        }
    }

    public void changeNotified() {
        synchronized (lock) {
            isNotified = !isNotified;
        }
    }

    @SuppressWarnings({"BusyWait", "CallToPrintStackTrace", "InfiniteLoopStatement"})
    @Override
    public void run() {
        boolean isNotified = isNotified();
        while (true) {
            PlayerState playerState = playerStateManager.getCurrentState();
            int stageCount;
            int duration;
            synchronized (lock) {
                switch (playerState) {
                    case FALLING:
                        setListPart(FallAnimationStage.values()[getCurrentStage()].getListPart());
                        stageCount = FallAnimationStage.values().length;
                        duration = FallAnimationStage.values()[getCurrentStage()].getDuration();
                        break;
                    case JUMPING:
                        setListPart(JumpAnimationStage.values()[getCurrentStage()].getListPart());
                        stageCount = JumpAnimationStage.values().length;
                        duration = JumpAnimationStage.values()[getCurrentStage()].getDuration();
                        break;
                    case RUNNING:
                        setListPart(RunAnimationStage.values()[getCurrentStage()].getListPart());
                        stageCount = RunAnimationStage.values().length;
                        duration = RunAnimationStage.values()[getCurrentStage()].getDuration();
                        break;
                    case STANDING:
                    default:
                        setListPart(StandAnimationStage.values()[getCurrentStage()].getListPart());
                        stageCount = StandAnimationStage.values().length;
                        duration = StandAnimationStage.values()[getCurrentStage()].getDuration();
                        break;
                }
                setCurrentStage((getCurrentStage() + 1) % stageCount);
            }

            for (int i = 0; i < duration/10; i++) {
                if (isNotified != isNotified()) {
                    isNotified = isNotified();
                    break;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if ((duration/10) * 10 < duration) {
                try {
                    Thread.sleep(duration - (duration/10) * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
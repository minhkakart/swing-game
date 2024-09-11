package com.minhkakart.swinggame.manager;

import com.minhkakart.swinggame.enums.PlayerState;

import java.util.LinkedList;


public class PlayerStateManager {
    private final LinkedList<PlayerState> playerStates;

    public PlayerStateManager() {
        playerStates = new LinkedList<>();
    }

    public synchronized void push(PlayerState playerState) {
        if (playerStates.contains(playerState)) {
            return;
        }

        if (playerStates.size() > 1 && playerState.getPriority() == playerStates.getFirst().getPriority()) {
            playerStates.removeFirst();
            playerStates.addFirst(playerState);
            return;
        }

        playerStates.add(playerState);
        playerStates.sort((o1, o2) -> o2.getPriority() - o1.getPriority());
    }

    public synchronized PlayerState getCurrentState() {
        return playerStates.getFirst();
    }

    public synchronized boolean contains(PlayerState playerState) {
        return playerStates.contains(playerState);
    }

    public synchronized void remove(PlayerState playerState) {
        playerStates.remove(playerState);
    }

}

package com.minhkakart.swinggame.enums;

public enum MapName {
    TONE("tone.txt");

    private final String mapName;

    MapName(String mapName) {
        this.mapName = mapName;
    }

    public String getMapName() {
        return mapName;
    }

}

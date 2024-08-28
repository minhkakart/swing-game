package com.minhkakart.swinggame.enums;

@SuppressWarnings("SpellCheckingInspection")
public enum MapName {
    TONE("tone.txt"),
    ICHIDAI("ichidai.txt");

    private final String mapName;

    MapName(String mapName) {
        this.mapName = mapName;
    }

    public String getMapName() {
        return mapName;
    }

}

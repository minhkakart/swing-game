package com.minhkakart.swinggame.enums;

@SuppressWarnings("SpellCheckingInspection")
public enum MapName {
    TONE("tone.txt", 19, 118, 2, BackgroundPlace.COUNTRY),
    ICHIDAI("ichidai.txt", 85, 30, 2, BackgroundPlace.DESERT),;

    private final String mapName;
    private final int mapRow;
    private final int mapCol;
    private final int mapLayerCount;
    private final BackgroundPlace backgroundPlace;

    MapName(String mapName, int mapRow, int mapCol, int mapLayerCount, BackgroundPlace backgroundPlace) {
        this.mapName = mapName;
        this.mapRow = mapRow;
        this.mapCol = mapCol;
        this.mapLayerCount = mapLayerCount;
        this.backgroundPlace = backgroundPlace;
    }

    public String getMapName() {
        return mapName;
    }

    public int getMapRow() {
        return mapRow;
    }

    public int getMapCol() {
        return mapCol;
    }

    public int getMapLayerCount() {
        return mapLayerCount;
    }

    public BackgroundPlace getBackgroundPlace() {
        return backgroundPlace;
    }
}

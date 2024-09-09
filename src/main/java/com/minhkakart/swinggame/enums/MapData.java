package com.minhkakart.swinggame.enums;

import com.minhkakart.swinggame.model.MapTeleport;

import java.awt.*;

@SuppressWarnings("SpellCheckingInspection")
public enum MapData {
    TONE("tone.txt", 23, 120, 2, BackgroundPlace.COUNTRY, 2, new MapTeleport[]{
            new MapTeleport(new Point(36, 232), new Point(57, 263), Direction.LEFT),
            new MapTeleport(new Point(2810, 122), new Point(2817, 167), Direction.UP),
    }),
    ICHIDAI("ichidai.txt", 89, 30, 2, BackgroundPlace.DESERT, 3, new MapTeleport[]{
            new MapTeleport(new Point(680, 213), new Point(661, 239), Direction.RIGHT),
            new MapTeleport(new Point(680, 1937), new Point(661, 1967), Direction.RIGHT),
            new MapTeleport(new Point(66, 2010), new Point(91, 1967), Direction.DOWN),
    }),;

    private final String mapName;
    private final int mapRow;
    private final int mapCol;
    private final int mapLayerCount;
    private final BackgroundPlace backgroundPlace;
    private final int teleportCount;
    private final MapTeleport[] mapTeleports;

    MapData(String mapName, int mapRow, int mapCol, int mapLayerCount, BackgroundPlace backgroundPlace, int teleportCount, MapTeleport[] mapTeleports) {
        this.mapName = mapName;
        this.mapRow = mapRow;
        this.mapCol = mapCol;
        this.mapLayerCount = mapLayerCount;
        this.backgroundPlace = backgroundPlace;
        this.teleportCount = teleportCount;
        this.mapTeleports = mapTeleports;
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

    public int getTeleportCount() {
        return teleportCount;
    }

    public MapTeleport[] getMapTeleports() {
        return mapTeleports;
    }
}

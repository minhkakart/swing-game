package com.minhkakart.swinggame.enums;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum BackgroundPlace {
    VILLAGE("village", new Color(32, 195, 245), true, CloudImage.WHITE, new int[]{50, 190, 420}, new int[]{30, 110, 70}),
    SNOW("snow", new Color(38, 141, 198), true, CloudImage.WHITE, new int[]{50, 190, 420}, new int[]{30, 110, 70}),
    FOREST("forest", new Color(72, 128, 248), true, CloudImage.WHITE, new int[]{50, 190, 420}, new int[]{30, 110, 70}),
    DARK_FOREST("dark-forest", new Color(23, 24, 8), true, CloudImage.NONE, null, null),
    RED_MOUNTAIN("red-mountain", new Color(19, 156, 170), true, CloudImage.WHITE, new int[]{50, 190, 420}, new int[]{30, 110, 70}),
    ICE_MOUNTAIN("ice-mountain", new Color(19, 156, 170), true, CloudImage.WHITE, new int[]{50, 190, 420}, new int[]{30, 110, 70}),
    SEA("sea", new Color(160, 216, 248), true, CloudImage.NONE, null, null),
    CAVE("cave", new Color(0, 0, 0), false, CloudImage.NONE, null, null),
    DESERT("desert", new Color(41, 196, 246), true, CloudImage.NONE, null, null),
    COUNTRY("country", new Color(86, 171, 238), true, CloudImage.WHITE, new int[]{50, 190, 420}, new int[]{30, 110, 70});

    private final String place;
    private final Color color;
    private final boolean sliding;
    private final CloudImage cloudImage;
    private final int[] cloudX;
    private final int[] cloudY;

    BackgroundPlace(String place, Color color, boolean sliding, CloudImage cloudImage, int[] cloudX, int[] cloudY) {
        this.place = place;
        this.color = color;
        this.sliding = sliding;
        this.cloudImage = cloudImage;
        this.cloudX = cloudX;
        this.cloudY = cloudY;
    }

    public String getPlace() {
        return place;
    }

    public Color getColor() {
        return color;
    }

    public boolean isSliding() {
        return sliding;
    }

    public CloudImage getCloudImage() {
        return cloudImage;
    }

    public List<Point> getClouds() {
        if (cloudX == null || cloudY == null) {
            return new ArrayList<>(0);
        }
        return IntStream.range(0, cloudX.length)
                .mapToObj(i -> new Point(cloudX[i], cloudY[i]))
                .collect(Collectors.toList());
    }
}

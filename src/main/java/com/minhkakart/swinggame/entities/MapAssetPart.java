package com.minhkakart.swinggame.entities;

import com.minhkakart.swinggame.enums.MapAsset;
import com.minhkakart.swinggame.manager.ResourceManager;

import javax.swing.*;
import java.awt.*;

public class MapAssetPart {
    private static final ImageIcon[] PART_SOURCE = {
            new ImageIcon(ResourceManager.getImagePath("map-assets/1.png")),
            new ImageIcon(ResourceManager.getImagePath("map-assets/2.png")),
            new ImageIcon(ResourceManager.getImagePath("map-assets/3.png")),
            new ImageIcon(ResourceManager.getImagePath("map-assets/4.png"))
    };

    private final MapAsset ASSET_NUMBER;
    private final int PART_NUMBER;

    public static final int PART_WIDTH = 24;
    public static final int PART_HEIGHT = 24;

    public MapAssetPart(MapAsset ASSET_NUMBER, int PART_NUMBER) {
        this.ASSET_NUMBER = ASSET_NUMBER;
        this.PART_NUMBER = PART_NUMBER;
    }

    public int getASSET_NUMBER() {
        return ASSET_NUMBER.ordinal() + 1;
    }

    public int getPART_NUMBER() {
        return PART_NUMBER;
    }

    public ImageIcon getPartImage() {
        return PART_SOURCE[getASSET_NUMBER() - 1];
    }

    public Point getStartPoint() {
        return new Point(0, (getPART_NUMBER() - 1) * PART_HEIGHT);
    }

    public Point getEndPoints() {
        int y = getPART_NUMBER() * PART_HEIGHT;
        return new Point(PART_WIDTH, y);
    }

}

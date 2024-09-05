package com.minhkakart.swinggame.model;

import com.minhkakart.swinggame.MainApplication;
import com.minhkakart.swinggame.entities.MapAssetPart;
import com.minhkakart.swinggame.ui.layer.MapLayer;
import com.minhkakart.swinggame.ui.layer.PlayerLayer;

import java.awt.*;

public class GameCamera {

    /**
     * Camera's top-left position
     */
    private final Point position = new Point(0, 0);
    private final MapLayer mapLayer;

    public GameCamera(PlayerLayer playerLayer, MapLayer mapLayer) {
        this.mapLayer = mapLayer;
        setPosition(playerLayer.getPlayer().getPosition());
    }

    public void setPosition(Point point) {
        int x = point.x - MainApplication.WIDTH / 2;
        int y = point.y - MainApplication.HEIGHT * 2 / 3;
        if (x < 0) {
            x = 0;
        } else if (x > mapLayer.getMapName().getMapCol() * MapAssetPart.PART_WIDTH - MainApplication.WIDTH) {
            x = mapLayer.getMapName().getMapCol() * MapAssetPart.PART_WIDTH - MainApplication.WIDTH;
        }
        if (y < 0) {
            y = 0;
        } else if (y > mapLayer.getMapName().getMapRow() * MapAssetPart.PART_HEIGHT - MainApplication.HEIGHT) {
            y = mapLayer.getMapName().getMapRow() * MapAssetPart.PART_HEIGHT - MainApplication.HEIGHT;
        }

        position.setLocation(x, y);
    }

    public MapLayer getMapLayer() {
        return mapLayer;
    }

    public Point getPosition() {
        return position;
    }
}

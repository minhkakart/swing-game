package com.minhkakart.swinggame.manager;

import com.minhkakart.swinggame.entities.MapAssetPart;
import com.minhkakart.swinggame.enums.MapAsset;

import java.util.Optional;

public class ResourceManager {
    private static final String IMAGE_PATH = "src/main/resources/images/";
    private static final String BACKGROUND_PATH = "src/main/resources/images/background/";

    public static String getImagePath(String image) {
        return IMAGE_PATH + image;
    }

    public static String getBackgroundPath(String background) {
        return BACKGROUND_PATH + background;
    }

    public static String getMapAssetDataPath(String map) {
        return "src/main/resources/map-data/asset/" + map;
    }

    public static String getMapColliderDataPath(String map) {
        return "src/main/resources/map-data/collider/" + map;
    }

    public static Optional<MapAssetPart> getMapAssetPart(int id) {
        int partNumber = id % 1000;
        int assetNumber = (id - partNumber) / 1000;
        MapAsset mapAsset;

        switch (assetNumber) {
            case 1:
                mapAsset = MapAsset.ONE;
                break;
            case 2:
                mapAsset = MapAsset.TWO;
                break;
            case 3:
                mapAsset = MapAsset.THREE;
                break;
            case 4:
            default:
                mapAsset = MapAsset.FOUR;
                break;
        }

        if (assetNumber == 1 && partNumber > 120) {
            return Optional.empty();
        }

        if (assetNumber == 2 && partNumber > 141) {
            return Optional.empty();
        }

        if (assetNumber == 3 && partNumber > 143) {
            return Optional.empty();
        }

        if (assetNumber == 4 && partNumber > 103) {
            return Optional.empty();
        }

        return Optional.of(new MapAssetPart(mapAsset, partNumber));

    }
}

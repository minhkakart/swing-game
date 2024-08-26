package com.minhkakart.swinggame.manager;

public class ResourceManager {
    private static final String IMAGE_PATH = "src/main/resources/images/";
    private static final String BACKGROUND_PATH = "src/main/resources/images/background/";

    public static String getImagePath(String image) {
        return IMAGE_PATH + image;
    }

    public static String getBackgroundPath(String background) {
        return BACKGROUND_PATH + background;
    }
}

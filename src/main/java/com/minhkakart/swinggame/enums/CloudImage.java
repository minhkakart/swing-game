package com.minhkakart.swinggame.enums;

import com.minhkakart.swinggame.manager.ResourceManager;

import javax.swing.*;
import java.awt.*;

public enum CloudImage {
    WHITE(new ImageIcon(ResourceManager.getBackgroundPath("white-cloud.png"))),
    BLACK(new ImageIcon(ResourceManager.getBackgroundPath("black-cloud.png"))),
    NONE(null);

    private final ImageIcon image;

    CloudImage(ImageIcon image) {
        this.image = image;
    }

    public Image getDrawImage() {
        return image.getImage();
    }
}

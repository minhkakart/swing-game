package com.minhkakart.swinggame.entities;

import com.minhkakart.swinggame.MainApplication;
import com.minhkakart.swinggame.enums.BackgroundPlace;
import com.minhkakart.swinggame.manager.ResourceManager;

import javax.swing.*;
import java.awt.*;

public class Background {

    // Higher level backgrounds are drawn later
    private DrawImage bgLv1;
    private DrawImage bgLv2;
    private DrawImage bgLv3;
    private DrawImage bgLv4;

    // Clouds
    private final DrawImage whiteCloud;
    private final DrawImage blackCloud;
    private boolean hasCloud;
    private boolean isWhiteCloud;

    // Animation timer
    private final Timer animationTimer;
    private int drawCount = 0;


    private BackgroundPlace backgroundPlace;

    public Background(BackgroundPlace place) {
        this.backgroundPlace = place;
        loadBackgroundImages();
        whiteCloud = new DrawImage(new ImageIcon(ResourceManager.getImagePath("white-cloud.png")), new Point(100, 100));
        blackCloud = new DrawImage(new ImageIcon(ResourceManager.getImagePath("black-cloud.png")), new Point(300, 100));

        animationTimer = new Timer(50, e -> {
            if (drawCount % 20 == 0) {
                if (Math.abs(bgLv1.getPosition().x) == bgLv1.getImageWidth()) {
                    bgLv1.setPosition(new Point(0, bgLv1.getPosition().y));
                }
                bgLv1.setPosition(new Point(bgLv1.getPosition().x - 1, bgLv1.getPosition().y));
            }
            if (drawCount % 10 == 0) {
                if (Math.abs(bgLv2.getPosition().x) == bgLv2.getImageWidth()) {
                    bgLv2.setPosition(new Point(0, bgLv2.getPosition().y));
                }
                bgLv2.setPosition(new Point(bgLv2.getPosition().x - 1, bgLv2.getPosition().y));
            }
            if (drawCount % 5 == 0) {
                if (Math.abs(bgLv3.getPosition().x) == bgLv3.getImageWidth()) {
                    bgLv3.setPosition(new Point(0, bgLv3.getPosition().y));
                }
                bgLv3.setPosition(new Point(bgLv3.getPosition().x - 1, bgLv3.getPosition().y));
            }
            if (drawCount % 2 == 0) {
                if (Math.abs(bgLv4.getPosition().x) == bgLv4.getImageWidth()) {
                    bgLv4.setPosition(new Point(0, bgLv4.getPosition().y));
                }
                bgLv4.setPosition(new Point(bgLv4.getPosition().x - 1, bgLv4.getPosition().y));
            }

            drawCount++;
            if (drawCount == 20) {
                drawCount = 0;
            }
        });
    }

    public void draw(Graphics2D g) {
        reDrawNum(g, bgLv1);
        reDrawNum(g, bgLv2);
        reDrawNum(g, bgLv3);
        reDrawNum(g, bgLv4);

        if(hasCloud) {
            if (isWhiteCloud){
                g.drawImage(whiteCloud.getDrawImage(), 100, 100, null);
            } else {
                g.drawImage(blackCloud.getDrawImage(), 100, 100, null);
            }
        }
    }

    private void reDrawNum(Graphics2D g, DrawImage drawImage) {
        int fillNumber = (int) (Math.ceil((double) MainApplication.WIDTH / drawImage.getImageWidth()) + 1);
        int xPos = (int) drawImage.getPosition().getX();
        int yPos = (int) drawImage.getPosition().getY();
        for (int i = 0; i < fillNumber; i++) {
            g.drawImage(drawImage.getDrawImage(), i * drawImage.getImageWidth() + xPos, yPos, null);
        }
    }

    public void animate() {
        animationTimer.start();
    }

    private void loadBackgroundImages() {
        ImageIcon bgLv1Image = new ImageIcon(ResourceManager.getBackgroundPath(backgroundPlace.getPlace() + "/bgLv1.png"));
        ImageIcon bgLv2Image = new ImageIcon(ResourceManager.getBackgroundPath(backgroundPlace.getPlace() + "/bgLv2.png"));
        ImageIcon bgLv3Image = new ImageIcon(ResourceManager.getBackgroundPath(backgroundPlace.getPlace() + "/bgLv3.png"));
        ImageIcon bgLv4Image = new ImageIcon(ResourceManager.getBackgroundPath(backgroundPlace.getPlace() + "/bgLv4.png"));
        int startY;
        switch (backgroundPlace) {

            case SNOW:
                startY = MainApplication.HEIGHT - (bgLv1Image.getIconHeight() + bgLv3Image.getIconHeight() + bgLv4Image.getIconHeight());

                bgLv1 = new DrawImage(bgLv1Image, new Point(0, startY));
                bgLv2 = new DrawImage(bgLv2Image, new Point(0, bgLv1.getPosition().y + (bgLv1Image.getIconHeight() - bgLv2Image.getIconHeight())));
                bgLv3 = new DrawImage(bgLv3Image, new Point(0, bgLv2.getPosition().y + bgLv2Image.getIconHeight()));
                bgLv4 = new DrawImage(bgLv4Image, new Point(0, bgLv3.getPosition().y + bgLv3Image.getIconHeight()));
                break;
            case VILLAGE:
            default:
                hasCloud = true;
                isWhiteCloud = true;
                startY = MainApplication.HEIGHT - (bgLv1Image.getIconHeight() + bgLv3Image.getIconHeight() + bgLv4Image.getIconHeight()) + 15;

                bgLv1 = new DrawImage(bgLv1Image, new Point(0, startY));
                bgLv2 = new DrawImage(bgLv2Image, new Point(0, bgLv1.getPosition().y + (bgLv1Image.getIconHeight() - bgLv2Image.getIconHeight())));
                bgLv3 = new DrawImage(bgLv3Image, new Point(0, bgLv2.getPosition().y + bgLv2Image.getIconHeight() - 6));
                bgLv4 = new DrawImage(bgLv4Image, new Point(0, bgLv3.getPosition().y + bgLv3Image.getIconHeight() - 9));
        }
    }

    public void changeBackground(BackgroundPlace place) {
        setBackgroundPlace(place);
        loadBackgroundImages();
    }

    public BackgroundPlace getBackgroundPlace(){
        return backgroundPlace;
    }

    public void setBackgroundPlace(BackgroundPlace backgroundPlace) {
        this.backgroundPlace = backgroundPlace;
    }
}

package com.minhkakart.swinggame.entities;

import com.minhkakart.swinggame.MainApplication;
import com.minhkakart.swinggame.enums.BackgroundPlace;
import com.minhkakart.swinggame.enums.CloudImage;
import com.minhkakart.swinggame.interfaces.Drawable;
import com.minhkakart.swinggame.manager.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Background implements Drawable {

    // Higher level backgrounds are drawn later
    private DrawImage bgLv1;
    private DrawImage bgLv2;
    private DrawImage bgLv3;
    private DrawImage bgLv4;

    // Animation timer
    private final Timer animationTimer;
    private int drawCount = 1;

    private BackgroundPlace backgroundPlace;

    public Background(BackgroundPlace place) {
        this.backgroundPlace = place;
        loadBackgroundImages();

        animationTimer = new Timer(50, e -> {
            if (drawCount % 31 == 0) {
                if (bgLv1 != null) {
                    if (Math.abs(bgLv1.getPosition().x) == bgLv1.getImageWidth()) {
                        bgLv1.setPosition(new Point(0, bgLv1.getPosition().y));
                    }
                    bgLv1.setPosition(new Point(bgLv1.getPosition().x - 1, bgLv1.getPosition().y));
                }
            }
            if (drawCount % 13 == 0) {
                if (bgLv2 != null) {
                    if (Math.abs(bgLv2.getPosition().x) == bgLv2.getImageWidth()) {
                        bgLv2.setPosition(new Point(0, bgLv2.getPosition().y));
                    }
                    bgLv2.setPosition(new Point(bgLv2.getPosition().x - 1, bgLv2.getPosition().y));
                }
            }
            if (drawCount % 5 == 0) {
                if (bgLv3 != null) {
                    if (Math.abs(bgLv3.getPosition().x) == bgLv3.getImageWidth()) {
                        bgLv3.setPosition(new Point(0, bgLv3.getPosition().y));
                    }
                    bgLv3.setPosition(new Point(bgLv3.getPosition().x - 1, bgLv3.getPosition().y));
                }
            }
            if (drawCount % 2 == 0) {
                if (bgLv4 != null) {
                    if (Math.abs(bgLv4.getPosition().x) == bgLv4.getImageWidth()) {
                        bgLv4.setPosition(new Point(0, bgLv4.getPosition().y));
                    }
                    bgLv4.setPosition(new Point(bgLv4.getPosition().x - 1, bgLv4.getPosition().y));
                }
            }

            if(backgroundPlace.isSliding()){
                drawCount++;
            }
            if (drawCount == 4030) {
                drawCount = 1;
            }
        });
    }

    @Override
    public void draw(Graphics2D g2d, Point position, boolean isFlipped) {
        reDrawNum(g2d, bgLv1);
        reDrawNum(g2d, bgLv2);
        reDrawNum(g2d, bgLv3);
        reDrawNum(g2d, bgLv4);

        if (backgroundPlace.getCloudImage() != CloudImage.NONE) {
            List<Point> cloudPoints = backgroundPlace.getClouds();
            for (Point point : cloudPoints) {
                g2d.drawImage(backgroundPlace.getCloudImage().getDrawImage(), point.x, point.y, null);
            }
        }
    }

    private void reDrawNum(Graphics2D g, DrawImage drawImage) {
        if (drawImage == null) {
            return;
        }

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

            case FOREST:

                bgLv4 = new DrawImage(bgLv4Image, new Point(0, MainApplication.HEIGHT - bgLv4Image.getIconHeight()));
                bgLv3 = new DrawImage(bgLv3Image, new Point(0, bgLv4.getPosition().y - bgLv3Image.getIconHeight()));
                bgLv2 = new DrawImage(bgLv2Image, new Point(0, bgLv3.getPosition().y - bgLv2Image.getIconHeight()));
                bgLv1 = new DrawImage(bgLv1Image, new Point(0, bgLv2.getPosition().y - bgLv1Image.getIconHeight() + 20));
                break;

            case DARK_FOREST:

                bgLv3 = new DrawImage(bgLv4Image, new Point(0, (MainApplication.HEIGHT - bgLv4Image.getIconHeight()) / 2));
                bgLv1 = new DrawImage(bgLv3Image, new Point(0, (MainApplication.HEIGHT - bgLv3Image.getIconHeight()) / 2));

                bgLv4 = null;
                bgLv2 = null;
                break;

            case SEA:

                bgLv4 = new DrawImage(bgLv4Image, new Point(0, MainApplication.HEIGHT - bgLv4Image.getIconHeight()));
                bgLv3 = new DrawImage(bgLv3Image, new Point(0, bgLv4.getPosition().y - bgLv3Image.getIconHeight() + 12));
                bgLv2 = new DrawImage(bgLv2Image, new Point(0, bgLv3.getPosition().y - bgLv2Image.getIconHeight()));
                bgLv1 = null;
                break;

            case CAVE:

                bgLv4 = null;
                bgLv3 = null;
                bgLv2 = new DrawImage(bgLv2Image, new Point(0, MainApplication.HEIGHT - bgLv2Image.getIconHeight()));
                bgLv1 = new DrawImage(bgLv1Image, new Point(0, bgLv2.getPosition().y - bgLv1Image.getIconHeight()));
                break;

            case ICE_MOUNTAIN:
            case RED_MOUNTAIN:

                bgLv1 = new DrawImage(bgLv1Image, new Point(0, MainApplication.HEIGHT - bgLv1Image.getIconHeight()));
                bgLv2 = new DrawImage(bgLv2Image, new Point(0, MainApplication.HEIGHT - bgLv2Image.getIconHeight()));
                bgLv3 = null;
                bgLv4 = null;
                break;

            case COUNTRY:
                startY = MainApplication.HEIGHT - (bgLv1Image.getIconHeight() + bgLv3Image.getIconHeight() + bgLv4Image.getIconHeight()) + 15;

                bgLv1 = new DrawImage(bgLv1Image, new Point(0, startY));
                bgLv2 = new DrawImage(bgLv2Image, new Point(0, bgLv1.getPosition().y + (bgLv1Image.getIconHeight() - bgLv2Image.getIconHeight())));
                bgLv3 = new DrawImage(bgLv3Image, new Point(0, bgLv2.getPosition().y + bgLv2Image.getIconHeight() - 6));
                bgLv4 = new DrawImage(bgLv4Image, new Point(0, bgLv3.getPosition().y + bgLv3Image.getIconHeight() - 9));
                break;

            case VILLAGE:
            default:
                bgLv4 = new DrawImage(bgLv4Image, new Point(0, MainApplication.HEIGHT - bgLv4Image.getIconHeight()));
                bgLv3 = new DrawImage(bgLv3Image, new Point(0, bgLv4.getPosition().y - bgLv3Image.getIconHeight() + 10));
                bgLv2 = new DrawImage(bgLv2Image, new Point(0, bgLv3.getPosition().y - bgLv2Image.getIconHeight() + 6));
                bgLv1 = new DrawImage(bgLv1Image, new Point(0, bgLv2.getPosition().y - bgLv1Image.getIconHeight() + 30));
        }
    }

    public void changeBackground(BackgroundPlace place) {
        setBackgroundPlace(place);
        loadBackgroundImages();
    }

    public BackgroundPlace getBackgroundPlace() {
        return backgroundPlace;
    }

    public void setBackgroundPlace(BackgroundPlace backgroundPlace) {
        this.backgroundPlace = backgroundPlace;
    }

}

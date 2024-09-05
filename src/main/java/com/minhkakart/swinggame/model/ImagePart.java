package com.minhkakart.swinggame.model;

import com.minhkakart.swinggame.entities.Player;
import com.minhkakart.swinggame.enums.PlayerPart;
import com.minhkakart.swinggame.interfaces.Drawable;
import com.minhkakart.swinggame.manager.ResourceManager;
import sun.awt.image.AbstractMultiResolutionImage;

import javax.swing.*;
import java.awt.*;

public class ImagePart implements Drawable {
    public static final ImageIcon ASSET_0 = new ImageIcon(ResourceManager.getImagePath("Big0.png"));
    public static final ImageIcon ASSET_1 = new ImageIcon(ResourceManager.getImagePath("Big1.png"));
    public static final ImageIcon ASSET_2 = new ImageIcon(ResourceManager.getImagePath("Big2.png"));
    public static final ImageIcon ASSET_3 = new ImageIcon(ResourceManager.getImagePath("Big3.png"));
    public static final ImageIcon ASSET_4 = new ImageIcon(ResourceManager.getImagePath("Big4.png"));

    /**
     * The start point of the image part on the source image
     */
    private Point startPoint;

    /**
     * The size of the image part
     */
    private Dimension size;

    /**
     * The offset to draw the image part from the top-left corner of the entity
     */
    private Point offset;

    private PlayerPart playerPart;

    public ImagePart(PlayerPart playerPart) {
        this.playerPart = playerPart;
        this.startPoint = playerPart.getStartPoint();
        this.size = playerPart.getSize();
    }

    @Override
    public void draw(Graphics2D g2d) {

    }

    @Override
    public void draw(Graphics2D g2d, boolean isFlipped) {

    }

    @Override
    public void draw(Graphics2D g2d, Point position) {


    }

    @Override
    public void draw(Graphics2D g2d, Point position, boolean isFlipped) {
        int source = playerPart.getSource();
        Image image = getSourcedImage(source);
        Point offset = playerPart.getOffset();

        int sx1 = startPoint.x;
        int sy1 = startPoint.y;
        int sx2 = sx1 + size.width;
        int sy2 = sy1 + size.height;

        if (isFlipped) {
            int dx1 = position.x + (Player.PLAYER_DRAW_AREA_WIDTH - offset.x);
            int dy1 = position.y + offset.y;
            int dx2 = dx1 - size.width;
            int dy2 = dy1 + size.height;
            g2d.drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
        } else {
            int dx1 = position.x + offset.x;
            int dy1 = position.y + offset.y;
            int dx2 = dx1 + size.width;
            int dy2 = dy1 + size.height;
            g2d.drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
        }

    }

    public static Image getSourcedImage(int source) {
        switch (source) {
            case 0:
                return ASSET_0.getImage();
            case 1:
                return ASSET_1.getImage();
            case 2:
                return ASSET_2.getImage();
            case 3:
                return ASSET_3.getImage();
            case 4:
                return ASSET_4.getImage();
            default:
                return null;
        }
    }

}

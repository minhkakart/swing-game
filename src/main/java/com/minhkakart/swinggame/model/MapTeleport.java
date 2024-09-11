package com.minhkakart.swinggame.model;

import com.minhkakart.swinggame.enums.Direction;
import com.minhkakart.swinggame.enums.MapData;
import com.minhkakart.swinggame.interfaces.Drawable;

import javax.swing.*;
import java.awt.*;

public class MapTeleport implements Drawable {
    private final Point teleportPosition;
    private final Point playerPosition;
    private final Direction direction;
    private final Rectangle gate;

    private MapData mapOwner;
    private MapData destinationMap;
    private MapTeleport destinationTeleport;


    private static int offset = 1;
    private static final Timer timer = new Timer(200, e -> setOffset(getOffset() * -1));

    static {
        timer.start();
    }

    public MapTeleport(Point teleportPosition, Point playerPosition, Direction direction, Rectangle gate) {
        this.teleportPosition = teleportPosition;
        this.playerPosition = playerPosition;
        this.direction = direction;
        this.gate = gate;
    }

    public MapData getMapOwner() {
        return mapOwner;
    }

    public void setMapOwner(MapData mapOwner) {
        this.mapOwner = mapOwner;
    }

    public MapData getDestinationMap() {
        return destinationMap;
    }

    public void setDestinationMap(MapData destinationMap) {
        this.destinationMap = destinationMap;
    }

    public MapTeleport getDestinationTeleport() {
        return destinationTeleport;
    }

    public void setDestinationTeleport(MapTeleport destinationTeleport) {
        this.destinationTeleport = destinationTeleport;
    }

    private static synchronized void setOffset(int offset) {
        MapTeleport.offset = offset;
    }

    private static synchronized int getOffset() {
        return offset;
    }

    public Point getTeleportPosition() {
        return teleportPosition;
    }

    public Direction getDirection() {
        return direction;
    }

    public Point getPlayerPosition() {
        return playerPosition;
    }

    public Rectangle getGate() {
        return gate;
    }

    @Override
    public void draw(Graphics2D g2d, Point position, boolean isFlipped) {
        switch (direction) {
            case UP:
            case DOWN:
                position.y += getOffset();
                break;
            case LEFT:
            case RIGHT:
                position.x += getOffset();
                break;
        }

        MapArrow.draw(g2d, position, direction);
    }

}

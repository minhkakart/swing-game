package com.minhkakart.swinggame.enums;

import java.awt.*;

public enum PlayerPart {
    HEAD_STAND(new Point(29,82), new Dimension(21,19), new Point(1,1), 0),
    HEAD_RUN_1(new Point(29,101), new Dimension(21,21), new Point(1,0), 0),
    HEAD_RUN_2(new Point(29,101), new Dimension(21,21), new Point(1,0), 0),
    HEAD_RUN_3(new Point(29,101), new Dimension(21,21), new Point(1,1), 0),
    HEAD_RUN_4(new Point(29,101), new Dimension(21,21), new Point(1,1), 0),
    HEAD_RUN_5(new Point(29,101), new Dimension(21,21), new Point(1,2), 0),
//    HEAD_HIT(new Point(29,82), new Dimension(21,19), new Point(1,1), 0),
    BODY_STAND(new Point(79,150), new Dimension(21,11), new Point(0,18), 0),
    BODY_RUN_1(new Point(140, 156), new Dimension(16,10), new Point(3,18), 0),
    BODY_RUN_2(new Point(174,106), new Dimension(19,9), new Point(1,17), 0),
    BODY_RUN_3(new Point(219,126), new Dimension(15,10), new Point(3,18), 0),
    BODY_RUN_4(new Point(225,176), new Dimension(15,9), new Point(2,19), 0),
    BODY_RUN_5(new Point(156,149), new Dimension(21,9), new Point(0,18), 0),

    LEG_STAND(new Point(209,25), new Dimension(15,8), new Point(4,27), 0),
    LEG_RUN_1(new Point(178,214), new Dimension(14,10), new Point(2,25), 0),
    LEG_RUN_2(new Point(135,20), new Dimension(21,10), new Point(0,23), 0),
    LEG_RUN_3(new Point(224,40), new Dimension(16,10), new Point(2,25), 0),
    LEG_RUN_4(new Point(203,218), new Dimension(14,12), new Point(1,23), 0),
    LEG_RUN_5(new Point(176,30), new Dimension(20,10), new Point(0,25), 0);

    private final Point startPoint;
    private final Dimension size;
    private final Point offset;
    private final int source;

    PlayerPart(Point startPoint, Dimension size, Point offset, int source) {
        this.startPoint = startPoint;
        this.size = size;
        this.offset = offset;
        this.source = source;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Dimension getSize() {
        return size;
    }

    public Point getOffset() {
        return offset;
    }

    public int getSource() {
        return source;
    }
}

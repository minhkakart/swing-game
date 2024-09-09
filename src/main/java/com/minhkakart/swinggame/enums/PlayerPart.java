package com.minhkakart.swinggame.enums;

import java.awt.*;

public enum PlayerPart {
    HEAD_STAND(new Point(29, 82), new Dimension(21, 19), new Point(5, 2), 0),
    HEAD_RUN_1(new Point(29, 101), new Dimension(21, 21), new Point(5, 1), 0),
    HEAD_RUN_2(new Point(29, 101), new Dimension(21, 21), new Point(5, 1), 0),
    HEAD_RUN_3(new Point(29, 101), new Dimension(21, 21), new Point(5, 2), 0),
    HEAD_RUN_4(new Point(29, 101), new Dimension(21, 21), new Point(5, 2), 0),
    HEAD_RUN_5(new Point(29, 101), new Dimension(21, 21), new Point(5, 3), 0),

    HEAD_FALL(new Point(29, 82), new Dimension(21, 19), new Point(5, 0), 0),

    HEAD_JUMP(new Point(29, 82), new Dimension(21, 19), new Point(5, 0), 0),

//    HEAD_HIT(new Point(29,82), new Dimension(21,19), new Point(5,2), 0),

    BODY_STAND(new Point(79, 150), new Dimension(21, 11), new Point(4, 19), 0),
    BODY_RUN_1(new Point(140, 156), new Dimension(16, 10), new Point(7, 19), 0),
    BODY_RUN_2(new Point(174, 106), new Dimension(19, 9), new Point(5, 18), 0),
    BODY_RUN_3(new Point(219, 126), new Dimension(15, 10), new Point(7, 19), 0),
    BODY_RUN_4(new Point(225, 176), new Dimension(15, 9), new Point(6, 20), 0),
    BODY_RUN_5(new Point(156, 149), new Dimension(21, 9), new Point(4, 19), 0),

    BODY_FALL(new Point(76, 91), new Dimension(26, 16), new Point(0, 8), 0),

    BODY_JUMP(new Point(156, 104), new Dimension(18, 8), new Point(4, 15), 0),

    LEG_STAND(new Point(209, 25), new Dimension(15, 8), new Point(8, 28), 0),
    LEG_RUN_1(new Point(178, 214), new Dimension(14, 10), new Point(6, 26), 0),
    LEG_RUN_2(new Point(135, 20), new Dimension(21, 10), new Point(4, 24), 0),
    LEG_RUN_3(new Point(224, 40), new Dimension(16, 10), new Point(6, 26), 0),
    LEG_RUN_4(new Point(203, 218), new Dimension(14, 12), new Point(5, 24), 0),
    LEG_RUN_5(new Point(176, 30), new Dimension(20, 10), new Point(4, 26), 0),

    LEG_FALL(new Point(223, 58), new Dimension(13, 13), new Point(10, 22), 0),

    LEG_JUMP(new Point(205, 114), new Dimension(11, 14), new Point(9, 21), 0),

    ROLL_1(new Point(0, 0), new Dimension(28, 28), new Point(0, 0), 0),
    ROLL_2(new Point(0, 29), new Dimension(29, 28), new Point(0, 0), 0),
    ROLL_3(new Point(0, 57), new Dimension(28, 29), new Point(0, 0), 0),
    ROLL_4(new Point(0, 86), new Dimension(29, 28), new Point(0, 0), 0),

    // Next map arrow

    NEXT_MAP_ARROW(new Point(146, 233), new Dimension(13, 16), new Point(0, 0), 4);


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

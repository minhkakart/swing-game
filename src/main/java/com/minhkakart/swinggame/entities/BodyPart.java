package com.minhkakart.swinggame.entities;

import com.minhkakart.swinggame.enums.PlayerLevel;
import com.minhkakart.swinggame.interfaces.Drawable;

import java.awt.*;

public class BodyPart {
    private final int head;
    private final int body;
    private final int legs;

    public BodyPart() {
        this.head = 0;
        this.body = 0;
        this.legs = 0;
    }

    public BodyPart(int head, int body, int legs) {
        this.head = head;
        this.body = body;
        this.legs = legs;
    }

    public void loadBodyPart() {
        // Load body part from file
        // Default body part

    }

    public int getHead() {
        return head;
    }

    public int getBody() {
        return body;
    }

    public int getLegs() {
        return legs;
    }

}

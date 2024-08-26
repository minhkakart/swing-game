package com.minhkakart.swinggame.enums;

public enum BackgroundPlace {
    VILLAGE("village"),
    SNOW("snow");

    private final String place;

    BackgroundPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }
}

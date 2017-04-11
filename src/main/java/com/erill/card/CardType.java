package com.erill.card;

/**
 * Created by Roger Erill on 10/4/17.
 */
public enum CardType {

    PRIMARY_INDUSTRY("[BLUE]"),
    SECONDARY_INDUSTRY("[GREEN]"),
    RESTAURANT("[RED]"),
    MAJOR_ESTABLISHMENT("[PURPLE]"),
    LANDMARK("[YELLOW]");

    private String color;

    CardType(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}

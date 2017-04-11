package com.erill.card;

/**
 * Created by Roger Erill on 10/4/17.
 */
public enum CardType {

    PRIMARY_INDUSTRY("Primary Industry"),
    SECONDARY_INDUSTRY("Secondary Industry"),
    RESTAURANT("Restaurant"),
    MAJOR_ESTABLISHMENT("Major establishment"),
    LANDMARK("Landmark");

    private String name;

    CardType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

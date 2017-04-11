package com.erill.card;

/**
 * Created by Roger Erill on 10/4/17.
 */

public enum CardClass {
    CEREAL("Cereal"),
    FOOD("Food"),
    NATURAL_RESOURCE("Natural resource"),
    STORE("Store"),
    INDUSTRY("Industry"),
    RESTAURANT("Restaurant"),
    MAJOR_ESTABLISHMENT("Major establishment"),
    AQUATIC("Aquatic");

    private String name;

    CardClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

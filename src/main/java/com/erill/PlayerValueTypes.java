package com.erill;

/**
 * Created by Roger Erill on 15/4/17.
 */
public enum PlayerValueTypes {

    VERY_LOW_VALUE(10),
    LOW_VALUE(20),
    MID_VALUE(30),
    HIGH_VALUE(40),
    VERY_HIGH_VALUE(50),
    ENDGAME_VALUE(60);

    int value;

    PlayerValueTypes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

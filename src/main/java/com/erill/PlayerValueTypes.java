package com.erill;

/**
 * Created by Roger Erill on 15/4/17.
 */
public enum PlayerValueTypes {

    VERY_LOW_VALUE(6),
    LOW_VALUE(12),
    MID_VALUE(18),
    HIGH_VALUE(24),
    VERY_HIGH_VALUE(30),
    ENDGAME_VALUE(36);

    int value;

    PlayerValueTypes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

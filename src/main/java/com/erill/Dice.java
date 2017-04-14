package com.erill;

/**
 * Created by Roger Erill on 13/4/17.
 */
public class Dice {

    private int firstDie;
    private int secondDie;

    public Dice() {
    }

    public void rollTwoDices() {
        firstDie = (int) (Math.random() * 6) + 1;
        secondDie = (int) (Math.random() * 6) + 1;
    }

    public void rollOneDie() {
        firstDie = (int) (Math.random() * 6) + 1;
        secondDie = 0;
    }

    public int getFirstDie() {
        return firstDie;
    }

    public int getSecondDie() {
        return secondDie;
    }
}

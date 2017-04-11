package com.erill.printer;

/**
 * Created by Roger Erill on 11/4/17.
 */
public enum PrintColor {

    ANSI_RESET("\u001B[0m"),
    COMMON_PREFIX("\u001B["),
    COMMON_SUFFIX("m"),
    BLACK("30"),
    RED("31"),
    GREEN("32"),
    YELLOW("33"),
    BLUE("34"),
    PURPLE("35"),
    CYAN("36"),
    WHITE("37"),
    BLACK_BG("40"),
    RED_BG("41"),
    GREEN_BG("42"),
    YELLOW_BG("43"),
    BLUE_BG("44"),
    PURPLE_BG("45"),
    CYAN_BG("46"),
    WHITE_BG("47");

    private String ansiColor;

    PrintColor(String ansiColor) {
        this.ansiColor = ansiColor;
    }

    public String getAnsiColor() {
        return ansiColor;
    }
}

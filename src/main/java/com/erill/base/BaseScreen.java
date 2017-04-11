package com.erill.base;

import com.erill.printer.PrintColor;
import com.erill.printer.PrintColorWriter;

import java.io.UnsupportedEncodingException;

/**
 * Created by Roger Erill on 11/4/17.
 */
public class BaseScreen {

    private PrintColorWriter colorOut;

    public BaseScreen() {
        try {
            colorOut = new PrintColorWriter(System.out);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void printColor(PrintColor foreground, PrintColor background, String message) {
        if (colorOut != null) colorOut.println(foreground, background, message);
        else System.out.println(message);
    }
}

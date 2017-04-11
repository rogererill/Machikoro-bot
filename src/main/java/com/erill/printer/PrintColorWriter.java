package com.erill.printer;

import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Roger Erill on 11/4/17.
 */
public class PrintColorWriter extends PrintWriter {

    public static final String COFFEE_SYMBOL = "\u2615";
    public static final String INDUSTRY_SYMBOL = "\u2692";
    public static final String NATURE_SYMBOL = "\u2618";
    public static final String BUSINESS_SYMBOL = "\u0024";
    public static final String LANDMARK_SYMBOL = "\u26FF";
    public static final String LONG_SPACE = "  ";
    public static final String LARGE_SPACE = "   ";
    public static final String ENDLINE = "\n";
    public static final String SHORT_SPACE = " ";

    public PrintColorWriter(PrintStream out) throws UnsupportedEncodingException {
        super(new OutputStreamWriter(out, "UTF-8"), true);
    }

    public void println(PrintColor foreground, PrintColor background, String string) {

        String ansiText = PrintColor.COMMON_PREFIX.getAnsiColor() + foreground.getAnsiColor()
                + ";" + background.getAnsiColor() + PrintColor.COMMON_SUFFIX.getAnsiColor();

        List<String> lines = Arrays.asList(string.split("\n"));
        for (String line : lines) {
            print(ansiText);
            print(line);
            println(PrintColor.ANSI_RESET.getAnsiColor());
            flush();
        }
    }
}

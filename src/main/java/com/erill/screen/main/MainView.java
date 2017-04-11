package com.erill.screen.main;

import com.erill.card.Card;
import com.erill.printer.PrintColor;

/**
 * Created by Roger Erill on 11/4/17.
 */
public interface MainView {

    void printCard(Card card, PrintColor background);
}

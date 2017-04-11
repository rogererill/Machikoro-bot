package com.erill.screen.main;

import com.erill.Board;
import com.erill.base.BaseScreen;
import com.erill.card.Card;
import com.erill.printer.PrintColor;

import static com.erill.printer.PrintColorWriter.ENDLINE;

/**
 * Created by Roger Erill on 11/4/17.
 */

public class MainScreen extends BaseScreen implements MainView {

    private MainPresenter presenter;

    public MainScreen() {
        presenter = new MainPresenter(new Board());
        presenter.bindView(this);

        presenter.printBoard();
    }


    @Override
    public void printCard(Card card, PrintColor background) {
        printColor(PrintColor.WHITE, background, card.getDetailedCardDescription());
        print(ENDLINE);
    }
}

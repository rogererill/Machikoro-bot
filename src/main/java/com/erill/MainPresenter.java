package com.erill;

import com.erill.card.Card;

import java.util.List;

/**
 * Created by Roger Erill on 11/4/17.
 */


public class MainPresenter extends BasePresenter<MainView> {

    private Board board;

    MainPresenter(Board board) {
        this.board = board;
    }

    public void printDeck() {
        List<Card> deck = board.getDeck();
        for (Card card : deck) {
            print(card.getDetailedCardDescription());
        }
    }
}

package com.erill.screen.main;

import com.erill.Board;
import com.erill.base.BasePresenter;
import com.erill.card.Card;
import com.erill.card.CardType;
import com.erill.printer.PrintColor;

import java.util.List;

/**
 * Created by Roger Erill on 11/4/17.
 */


public class MainPresenter extends BasePresenter<MainView> {

    private Board board;

    MainPresenter(Board board) {
        this.board = board;
    }

    public void printBoard() {
        List<Card> boardCards = board.getBoardCards();
        for (Card card : boardCards) {
            PrintColor background = getPrintColor(card);
            getView().printCard(card, background);
        }
    }

    private PrintColor getPrintColor(Card card) {
        CardType type = card.getType();
        switch (type) {
            case PRIMARY_INDUSTRY:
                return PrintColor.BLUE_BG;
            case SECONDARY_INDUSTRY:
                return PrintColor.GREEN_BG;
            case RESTAURANT:
                return PrintColor.RED_BG;
            case MAJOR_ESTABLISHMENT:
                return PrintColor.PURPLE_BG;
            default:
                return PrintColor.BLACK_BG;
        }
    }
}

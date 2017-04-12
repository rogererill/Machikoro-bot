package com.erill.screen.main;

import com.erill.Player;
import com.erill.base.BaseView;
import com.erill.card.Card;
import com.erill.printer.PrintColor;
import javafx.util.Pair;

import java.util.List;

/**
 * Created by Roger Erill on 11/4/17.
 */
public interface MainView extends BaseView {

    void printCard(Card card, PrintColor background);

    void startGame(List<Player> players);

    void endGame();

    void printIncorrectInput(String input);

    void printDiceResult(Pair<Integer, Integer> diceTurn);

    void printPlayers(List<Player> players);

    void endTurn();
}

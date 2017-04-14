package com.erill.screen.main;

import com.erill.Player;
import com.erill.base.BaseView;
import com.erill.card.Card;
import com.erill.printer.PrintColor;

import java.util.List;

/**
 * Created by Roger Erill on 11/4/17.
 */
public interface MainView extends BaseView {

    void printCard(Card card, PrintColor background);

    void startGame(List<Player> players);

    void endGame(Player winner);

    void printIncorrectInput(String input);

    void printDiceResult(int firstDie, int secondDie);

    void printPlayers(List<Player> players, int indexCurrentPlayer);

    void endTurn();
}

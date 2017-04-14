package com.erill.screen.main;

import com.erill.Board;
import com.erill.Player;
import com.erill.base.BaseScreen;
import com.erill.card.Card;
import com.erill.printer.PrintColor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.erill.printer.PrintColorWriter.ENDLINE;

/**
 * Created by Roger Erill on 11/4/17.
 */

public class MainScreen extends BaseScreen implements MainView {

    private MainPresenter presenter;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public MainScreen() {
        presenter = new MainPresenter(new Board());
        presenter.bindView(this);

        presenter.printBoard();
        presenter.createGame(4);
    }

    @Override
    public void printCard(Card card, PrintColor background) {
        printColor(PrintColor.WHITE, background, card.getDetailedCardDescription());
    }

    @Override
    public void startGame(List<Player> players) {
        printPlayers(players, 0);
        printColor(PrintColor.YELLOW, PrintColor.BLACK_BG, "Welcome to this new game. Press enter to start action. Press q to quit");
        readInput();
    }

    @Override
    public void endGame() {

    }

    @Override
    public void printIncorrectInput(String input) {
        print("Unrecognized command " + input);
        readInput();
    }

    @Override
    public void printDiceResult(int firstDie, int secondDie) {
        if (secondDie != 0) print("Dice result is " + firstDie + " and " + secondDie);
        else print("Dice result is " + firstDie);
    }

    @Override
    public void printPlayers(List<Player> players, int indexCurrentPlayer) {
        print(ENDLINE);
        for (int i = 0; i < players.size(); i++) {
            if (indexCurrentPlayer == i) {
                System.out.print("--> ");
            }
            else {
                System.out.print("    ");
            }
            print(players.get(i).toString());
        }
        print(ENDLINE);
    }

    private void readInput() {
        try {
            presenter.readInput(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endTurn() {
        print(ENDLINE);
        print("End of turn. Press enter to proceed");
        readInput();
    }
}

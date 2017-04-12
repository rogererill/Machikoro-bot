package com.erill.screen.main;

import com.erill.Board;
import com.erill.Player;
import com.erill.PlayerBrain;
import com.erill.base.BasePresenter;
import com.erill.card.Card;
import com.erill.card.CardType;
import com.erill.printer.PrintColor;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roger Erill on 11/4/17.
 */


public class MainPresenter extends BasePresenter<MainView> {

    private Board board;
    private List<Player> players;
    private PlayerBrain playerBrain;

    MainPresenter(Board board) {
        this.board = board;
        this.playerBrain = PlayerBrain.getInstance();
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

    public void createGame(int numPlayers) {
        createPlayers(numPlayers);
        playerBrain.setIndexCurrentPlayer(0);
        getView().startGame(players);
    }

    private void createPlayers(int numPlayers) {
        players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            players.add(new Player("Player " + i, false));
        }
        players.get(0).setActivePlayer(true);
    }

    public void readInput(String input) {
        switch (input) {
            case "q":
                getView().endGame();
                break;
            case "":
                updatePlayerBrainInfo();

                Pair<Integer, Integer> diceTurn = playerBrain.throwDice();
                getView().printDiceResult(diceTurn);
                resolveDiceValue(diceTurn);

                updatePlayerBrainInfo();
                Card wantedCard = playerBrain.calculateWantedCard();
                resolveCardPurchase(wantedCard);

                printBoard();
                getView().printPlayers(players);
                getView().endTurn();

                playerBrain.passTurn();

                break;
            default:
                getView().printIncorrectInput(input);
                break;
        }
    }

    //TODO
    private void resolveCardPurchase(Card wantedCard) {

    }

    //TODO
    private void resolveDiceValue(Pair<Integer, Integer> diceTurn) {

    }

    private void updatePlayerBrainInfo() {
        playerBrain.setBoard(board);
        playerBrain.setPlayerList(players);
    }
}

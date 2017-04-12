package com.erill;

import com.erill.card.Card;
import javafx.util.Pair;

import java.util.List;
import java.util.Random;

/**
 * Created by Roger Erill on 12/4/17.
 */
public class PlayerBrain {

    private static PlayerBrain playerBrain = new PlayerBrain();

    private int indexCurrentPlayer;
    private List<Player> playerList;
    private Board board;

    private PlayerBrain() {}

    public static PlayerBrain getInstance() {
        if (playerBrain == null) {
            playerBrain = new PlayerBrain();
        }
        return playerBrain;
    }

    public int getIndexCurrentPlayer() {
        return indexCurrentPlayer;
    }

    public void setIndexCurrentPlayer(int indexCurrentPlayer) {
        this.indexCurrentPlayer = indexCurrentPlayer;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void passTurn() {
        indexCurrentPlayer = (indexCurrentPlayer + 1) % playerList.size();
    }

    //TODO
    public Pair<Integer, Integer> throwDice() {
        int firstDice = new Random(System.currentTimeMillis()).nextInt(6);
        int secondDice = 0;
        return new Pair<>(firstDice, secondDice);
    }

    //TODO
    public Card calculateWantedCard() {
        return null;
    }
}

package com.erill;

import com.erill.card.Card;
import com.erill.card.LandmarkCard;

import java.util.List;

/**
 * Created by Roger Erill on 12/4/17.
 */
public class PlayerBrain {

    private static PlayerBrain playerBrain = new PlayerBrain();

    private int indexCurrentPlayer;
    private List<Player> playerList;
    private Board board;
    private Dice dice;

    private PlayerBrain() {
        dice = new Dice();
    }

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
    public void throwDice() {
        dice.rollOneDie();
    }

    //TODO
    public Card calculateWantedCard() {
        Player player = playerList.get(indexCurrentPlayer);
        List<LandmarkCard> unpurchasedLandmarks = player.getUnpurchasedLandmarks();
        for (LandmarkCard unpurchasedLandmark : unpurchasedLandmarks) {
            if (player.getMoney() >= unpurchasedLandmark.getCost()) {
                unpurchasedLandmark.setActivated(true);
                return unpurchasedLandmark;
            }
        }
        return null;
    }

    public int getFirstDie() {
        return dice.getFirstDie();
    }

    public int getSecondDie() {
        return dice.getSecondDie();
    }

    public int getTotalDice() {
        return getFirstDie() + getSecondDie();
    }
}

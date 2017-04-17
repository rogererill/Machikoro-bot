package com.erill;

import com.erill.card.Card;
import com.erill.card.CardName;
import com.erill.card.LandmarkCard;

import java.util.*;

/**
 * Created by Roger Erill on 12/4/17.
 */
public class PlayerBrain {

    private static PlayerBrain playerBrain = new PlayerBrain();

    private int indexCurrentPlayer;
    private List<Player> playerList;
    private Board board;
    private Dice dice;
    public static HashMap<CardName, List<Integer>> cardValues;

    private PlayerBrain() {
        dice = new Dice();
        cardValues = CardValues.initializeValues();
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
        Player player = playerList.get(indexCurrentPlayer);
        if (!player.hasTrainStation()) {
            dice.rollOneDie();
        }
        else {
            PlayerValueTypes currentType = Utils.getCurrentPlayerValueType(player);
            int percentageTwoDices = 0;
            switch (currentType) {
                case VERY_LOW_VALUE:
                    percentageTwoDices = 0;
                    break;
                case LOW_VALUE:
                    percentageTwoDices = 25;
                    break;
                case MID_VALUE:
                    percentageTwoDices = 50;
                    break;
                case HIGH_VALUE:
                    percentageTwoDices = 80;
                    break;
                case VERY_HIGH_VALUE:
                    percentageTwoDices = 90;
                    break;
                case ENDGAME_VALUE:
                    percentageTwoDices = 95;
                    break;
            }
            int randomChoice = (int) (Math.random() * 100) + 1;
            System.out.println("Two dices percentage: " + percentageTwoDices + " against " + randomChoice);
            if (randomChoice <= percentageTwoDices) {
                dice.rollTwoDices();
            }
            else {
                dice.rollOneDie();
            }
        }
    }

    //TODO
    public Card calculateWantedCard() {
        Player player = playerList.get(indexCurrentPlayer);
        Card landmarkCard = getLandmarkCard(player);
        if (landmarkCard != null) return landmarkCard;
        HashMap<Card, Integer> perceivedCardValues = Utils.fillCardValues(player, board.getBoardCards());
        int randomChoice = (int) (Math.random() * 100) + 1;
        Card selectedCard = Utils.getSelectedCard(player.getMoney(), perceivedCardValues, randomChoice);
        return selectedCard;
    }

    private Card getLandmarkCard(Player player) {
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

    public void throwTwoDice() {
        dice.rollTwoDices();
    }
}

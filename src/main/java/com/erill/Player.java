package com.erill;

import com.erill.card.Card;
import com.erill.card.CardName;
import com.erill.card.LandmarkCard;

import java.util.ArrayList;
import java.util.List;

import static com.erill.printer.PrintColorWriter.LONG_SPACE;

/**
 * Created by Roger Erill on 11/4/17.
 */
public class Player {

    public static final int INITIAL_PLAYER_MONEY = 3;

    private final String name;
    private int money;
    private List<Card> playerCards;
    private boolean isActivePlayer;
    private CardComparator cardComparator;
    private boolean isHumanPlayer;

    private boolean hasShoppingMall = false;
    private boolean hasTrainStation = false;
    private boolean hasHarbor = false;

    public Player(String name, boolean isActivePlayer) {
        this.name = name;
        this.money = INITIAL_PLAYER_MONEY;
        this.playerCards = getInitialCards();
        this.isActivePlayer = isActivePlayer;
        this.isHumanPlayer = false;
        this.cardComparator = new CardComparator();
    }

    private List<Card> getInitialCards() {
        List<Card> initialCards = new ArrayList<>();
        initialCards.add(Card.createCardByName(CardName.WHEAT_FIELD));
        initialCards.add(Card.createCardByName(CardName.BAKERY));
        initialCards.add(Card.createCardByName(CardName.CITY_HALL));
        initialCards.add(new LandmarkCard(CardName.HARBOR, 2));
        initialCards.add(new LandmarkCard(CardName.TRAIN_STATION, 4));
        initialCards.add(new LandmarkCard(CardName.SHOPPING_MALL, 10));
        initialCards.add(new LandmarkCard(CardName.AMUSEMENT_PARK, 16));
        initialCards.add(new LandmarkCard(CardName.RADIO_TOWER, 22));
        initialCards.add(new LandmarkCard(CardName.AIRPORT, 30));
        return initialCards;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(LONG_SPACE).append(money).append("€");
        sb.append(LONG_SPACE);
        int value = Utils.calculateNetValue(this);
        sb.append("[").append(value).append("]").append(LONG_SPACE);
        printCards(sb);
        return sb.toString();
    }

    private void printCards(StringBuilder sb) {
        playerCards.sort(cardComparator);
        for (int i = 0; i < playerCards.size(); i++) {
            Card playerCard = playerCards.get(i);
            if (playerCard instanceof LandmarkCard) {
                if (((LandmarkCard) playerCard).isActivated()) {
                    printActivations(sb, i, playerCard);
                }
            }
            else {
                printActivations(sb, i, playerCard);
            }
        }
    }

    private void printActivations(StringBuilder sb, int i, Card playerCard) {
        sb.append(playerCard.getShortDescription());
        if (i < playerCards.size()-1) {
            sb.append(", ");
        }
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(List<Card> playerCards) {
        this.playerCards = playerCards;
    }

    public boolean isActivePlayer() {
        return isActivePlayer;
    }

    public void setActivePlayer(boolean activePlayer) {
        isActivePlayer = activePlayer;
    }

    public List<LandmarkCard> getUnpurchasedLandmarks() {
        List<LandmarkCard> unpurchasedLandmarks = new ArrayList<>();
        for (Card playerCard : playerCards) {
            if (playerCard instanceof LandmarkCard) {
                LandmarkCard landmarkCard = (LandmarkCard) playerCard;
                if (!landmarkCard.isActivated()) unpurchasedLandmarks.add(landmarkCard);
            }
        }
        unpurchasedLandmarks.sort((o1, o2) -> o2.getCost() - o1.getCost());
        return unpurchasedLandmarks;
    }

    public void buyCard(Card card) {
        playerCards.add(card);
        money -= card.getCost();
    }

    public void addCard(Card card) {
        playerCards.add(card);
    }

    public boolean removeCard(Card card) {
        return playerCards.remove(card);
    }

    public void takeMoney(int amountToPay) {
        this.money -= amountToPay;
    }

    public void giveMoney(int amountToPay) {
        this.money += amountToPay;
    }

    public String printShortDescription() {
        return this.name + "[" + money + "]";
    }

    public void activateLandmarkCard(LandmarkCard landmarkCard) {
        if (landmarkCard.getCardName().equals(CardName.SHOPPING_MALL)) hasShoppingMall = true;
        if (landmarkCard.getCardName().equals(CardName.TRAIN_STATION)) hasTrainStation = true;
        if (landmarkCard.getCardName().equals(CardName.HARBOR)) hasHarbor = true;
        for (Card card : playerCards) {
            if (card instanceof LandmarkCard) {
                if (landmarkCard.getName().equals(card.getName())) {
                    ((LandmarkCard) card).setActivated(true);
                }
            }
        }
    }

    public boolean hasShoppingMall() {
        return hasShoppingMall;
    }

    public boolean hasTrainStation() {
        return hasTrainStation;
    }

    public boolean hasHarbor() {
        return hasHarbor;
    }
}

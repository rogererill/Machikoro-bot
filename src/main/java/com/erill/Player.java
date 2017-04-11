package com.erill;

import com.erill.card.Card;
import com.erill.card.CardName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roger Erill on 11/4/17.
 */
public class Player {

    public static final int INITIAL_PLAYER_MONEY = 3;

    private final String name;
    private int money;
    private List<Card> playerCards;
    private boolean isActivePlayer;

    public Player(String name, boolean isActivePlayer) {
        this.name = name;
        this.money = INITIAL_PLAYER_MONEY;
        this.playerCards = getInitialCards();
        this.isActivePlayer = isActivePlayer;
    }

    public List<Card> getInitialCards() {
        List<Card> initialCards = new ArrayList<>();
        initialCards.add(Card.createCardByName(CardName.WHEAT_FIELD));
        initialCards.add(Card.createCardByName(CardName.BAKERY));
        initialCards.add(Card.createCardByName(CardName.CITY_HALL));
        return initialCards;
    }

    public void buyCard(Card card) {
        playerCards.add(card);
        money -= card.getCost();
    }

    public boolean removeCard(Card card) {
        return playerCards.remove(card);
    }
}

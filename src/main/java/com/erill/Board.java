package com.erill;

import com.erill.card.*;

import java.util.*;


/**
 * Created by Roger Erill on 10/4/17.
 */
public class Board {

    private static final int MAX_DIFFERENT_CARDS = 10;
    private final Stack<Card> deck;
    private List<Card> boardCards;

    public Board() {
        this.deck = createDefaultDeck();
        this.boardCards = getInitialBoard();
    }

    private Stack<Card> createDefaultDeck() {
        Stack<Card> deck = new Stack<>();
        addCard(deck, CardName.WHEAT_FIELD, 6);
        addCard(deck, CardName.RANCH, 6);
        addCard(deck, CardName.FOREST, 6);
        addCard(deck, CardName.MINE, 6);
        addCard(deck, CardName.APPLE_ORCHARD, 6);
        addCard(deck, CardName.BAKERY, 6);
        addCard(deck, CardName.CONVENIENCE_STORE, 6);
        addCard(deck, CardName.CHEESE_FACTORY, 6);
        addCard(deck, CardName.FURNITURE_FACTORY, 6);
        addCard(deck, CardName.FRUIT_MARKET, 6);
        addCard(deck, CardName.CAFE, 6);
        addCard(deck, CardName.RESTAURANT, 6);
        addCard(deck, CardName.STADIUM, 4);
        addCard(deck, CardName.TV_STATION, 4);
        addCard(deck, CardName.BUSINESS_CENTER, 4);
        addCard(deck, CardName.FLOWER_ORCHARD, 6);
        addCard(deck, CardName.MACKEREL_BOAT, 6);
        addCard(deck, CardName.TUNA_BOAT, 6);
        addCard(deck, CardName.FLOWER_SHOP, 6);
        addCard(deck, CardName.FOOD_WAREHOUSE, 6);
        addCard(deck, CardName.SUSHI_BAR, 6);
        addCard(deck, CardName.PIZZA, 6);
        addCard(deck, CardName.HAMBURGER, 6);
        addCard(deck, CardName.PUBLISHER, 5);
        addCard(deck, CardName.TAX_OFFICE, 5);
        Collections.shuffle(deck);
        return deck;
    }

    private void addCard(List<Card> deck, CardName cardName, int number) {
        for (int i = 0; i < number; i++) {
            Card card = Card.createCardByName(cardName);
            card.setId(deck.size());
            deck.add(card);
        }
    }

    public List<Card> getInitialBoard() {
        List<Card> result = new ArrayList<>();
        for (int i = 0; i < MAX_DIFFERENT_CARDS; i++) {
            Card poppedCard = deck.pop();
            result.add(poppedCard);
        }
        int differentCards = (int) result.stream().distinct().count();
        while (differentCards < MAX_DIFFERENT_CARDS) {
            Card poppedCard = deck.pop();
            result.add(poppedCard);
            differentCards = (int) result.stream().distinct().count();
        }
        result.sort((card1, card2) -> {
            List<Integer> activations = card1.getActivations();
            if (activations.isEmpty()) return -1;
            List<Integer> activations2 = card2.getActivations();
            if (activations2.isEmpty()) return 1;

            int compare = activations.get(0).compareTo(activations2.get(0));
            if (compare != 0) return compare;
            return card1.toString().compareTo(card2.toString());
        });
        return result;
    }


    public List<Card> getDeck() {
        return deck;
    }

    public List<Card> getBoardCards() {
        return boardCards;
    }

}

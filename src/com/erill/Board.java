package com.erill;

import com.erill.card.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Roger Erill on 10/4/17.
 */
public class Board {

    private static final int MAX_DIFFERENT_CARDS = 10;
    private final List<Card> deck;
    private List<Card> board;

    public Board() {
        this.deck = createDefaultDeck();
    }

    private List<Card> createDefaultDeck() {
        List<Card> deck = new ArrayList<>();
        addCard(deck, new Card(CardName.WHEAT_FIELD, CardType.PRIMARY_INDUSTRY, CardClass.CEREAL, 1, Arrays.asList(1),1), 6);
        addCard(deck, new Card(CardName.RANCH, CardType.PRIMARY_INDUSTRY, CardClass.FOOD, 1, Arrays.asList(2),1), 6);
        addCard(deck, new Card(CardName.FOREST, CardType.PRIMARY_INDUSTRY, CardClass.NATURAL_RESOURCE, 3, Arrays.asList(5),1), 6);
        addCard(deck, new Card(CardName.MINE, CardType.PRIMARY_INDUSTRY, CardClass.NATURAL_RESOURCE, 5, Arrays.asList(9),5), 6);
        addCard(deck, new Card(CardName.APPLE_ORCHARD, CardType.PRIMARY_INDUSTRY, CardClass.CEREAL, 3, Arrays.asList(10), 3), 6);
        addCard(deck, new Card(CardName.BAKERY, CardType.SECONDARY_INDUSTRY, CardClass.STORE, 1, Arrays.asList(2,3), 1), 6);
        addCard(deck, new Card(CardName.CONVENIENCE_STORE, CardType.SECONDARY_INDUSTRY, CardClass.STORE, 2, Arrays.asList(4), 4), 6);
        addCard(deck, new Card(CardName.CHEESE_FACTORY, CardType.SECONDARY_INDUSTRY, CardClass.INDUSTRY, 5, Arrays.asList(7)), 6);
        addCard(deck, new Card(CardName.FURNITURE_FACTORY, CardType.SECONDARY_INDUSTRY, CardClass.INDUSTRY, 3, Arrays.asList(8)), 6);
        addCard(deck, new Card(CardName.FRUIT_MARKET, CardType.SECONDARY_INDUSTRY, CardClass.INDUSTRY, 2, Arrays.asList(11,12)), 6);
        addCard(deck, new Card(CardName.CAFE, CardType.RESTAURANT, CardClass.RESTAURANT, 2, Arrays.asList(3)), 6);
        addCard(deck, new Card(CardName.RESTAURANT, CardType.RESTAURANT, CardClass.RESTAURANT, 3, Arrays.asList(9,10)), 6);
        addCard(deck, new Card(CardName.STADIUM, CardType.MAJOR_ESTABLISHMENT, CardClass.MAJOR_ESTABLISHMENT, 6, Arrays.asList(6)), 4);
        addCard(deck, new Card(CardName.TV_STATION, CardType.MAJOR_ESTABLISHMENT, CardClass.MAJOR_ESTABLISHMENT, 7, Arrays.asList(6)), 4);
        addCard(deck, new Card(CardName.BUSINESS_CENTER, CardType.MAJOR_ESTABLISHMENT, CardClass.MAJOR_ESTABLISHMENT, 8, Arrays.asList(6)), 4);
        addCard(deck, new Card(CardName.FLOWER_ORCHARD, CardType.PRIMARY_INDUSTRY, CardClass.CEREAL, 2, Arrays.asList(4), 1), 6);
        addCard(deck, new Card(CardName.MACKEREL_BOAT, CardType.PRIMARY_INDUSTRY, CardClass.AQUATIC, 2, Arrays.asList(8), 3), 6);
        addCard(deck, new Card(CardName.TUNA_BOAT, CardType.PRIMARY_INDUSTRY, CardClass.AQUATIC, 5, Arrays.asList(12,14)), 6);
        addCard(deck, new Card(CardName.FLOWER_SHOP, CardType.SECONDARY_INDUSTRY, CardClass.STORE, 1, Arrays.asList(6)), 6);
        addCard(deck, new Card(CardName.FOOD_WAREHOUSE, CardType.SECONDARY_INDUSTRY, CardClass.INDUSTRY, 2, Arrays.asList(12,13)), 6);
        addCard(deck, new Card(CardName.SUSHI_BAR, CardType.RESTAURANT, CardClass.RESTAURANT, 2, Arrays.asList(1)), 6);
        addCard(deck, new Card(CardName.PIZZA, CardType.RESTAURANT, CardClass.RESTAURANT, 1, Arrays.asList(7)), 6);
        addCard(deck, new Card(CardName.HAMBURGER, CardType.RESTAURANT, CardClass.RESTAURANT, 1, Arrays.asList(8)), 6);
        addCard(deck, new Card(CardName.PUBLISHER, CardType.MAJOR_ESTABLISHMENT, CardClass.MAJOR_ESTABLISHMENT, 5, Arrays.asList(7)), 5);
        addCard(deck, new Card(CardName.TAX_OFFICE, CardType.MAJOR_ESTABLISHMENT, CardClass.MAJOR_ESTABLISHMENT, 4, Arrays.asList(8,9)), 5);
        return deck;
    }

    private void addCard(List<Card> deck, Card card, int number) {
        for (int i = 0; i < number; i++) {
            deck.add(card);
        }
    }


}

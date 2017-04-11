package com.erill;

import com.erill.card.*;

import java.util.*;


/**
 * Created by Roger Erill on 10/4/17.
 */
public class Board {

    private static final int MAX_DIFFERENT_CARDS = 10;
    private final Stack<Card> deck;
    private List<Card> board;

    public Board() {
        this.deck = createDefaultDeck();
        this.board = getInitialBoard();
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
            Card card = createCardByName(cardName);
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

    public List<Card> getBoard() {
        return board;
    }

    public Card createCardByName(CardName cardName) {
        switch (cardName) {
            case WHEAT_FIELD:
                return new Card(CardName.WHEAT_FIELD, CardType.PRIMARY_INDUSTRY, CardClass.CEREAL, 1, Arrays.asList(1),1);
            case RANCH:
                return new Card(CardName.RANCH, CardType.PRIMARY_INDUSTRY, CardClass.FOOD, 1, Arrays.asList(2),1);
            case FOREST:
                return new Card(CardName.FOREST, CardType.PRIMARY_INDUSTRY, CardClass.NATURAL_RESOURCE, 3, Arrays.asList(5),1);
            case MINE:
                return new Card(CardName.MINE, CardType.PRIMARY_INDUSTRY, CardClass.NATURAL_RESOURCE, 6, Arrays.asList(9),5);
            case APPLE_ORCHARD:
                return new Card(CardName.APPLE_ORCHARD, CardType.PRIMARY_INDUSTRY, CardClass.CEREAL, 3, Arrays.asList(10),3);
            case BAKERY:
                return new Card(CardName.BAKERY, CardType.SECONDARY_INDUSTRY, CardClass.STORE, 1, Arrays.asList(2,3),1);
            case CONVENIENCE_STORE:
                return new Card(CardName.CONVENIENCE_STORE, CardType.SECONDARY_INDUSTRY, CardClass.STORE, 1, Arrays.asList(4),3);
            case CHEESE_FACTORY:
                return new Card(CardName.CHEESE_FACTORY, CardType.SECONDARY_INDUSTRY, CardClass.INDUSTRY, 5, Arrays.asList(7));
            case FURNITURE_FACTORY:
                return new Card(CardName.FURNITURE_FACTORY, CardType.SECONDARY_INDUSTRY, CardClass.INDUSTRY, 3, Arrays.asList(8));
            case FRUIT_MARKET:
                return new Card(CardName.FRUIT_MARKET, CardType.SECONDARY_INDUSTRY, CardClass.INDUSTRY, 2, Arrays.asList(11,12));
            case CAFE:
                return new Card(CardName.CAFE, CardType.RESTAURANT, CardClass.RESTAURANT, 2, Arrays.asList(3),1);
            case RESTAURANT:
                return new Card(CardName.RESTAURANT, CardType.RESTAURANT, CardClass.RESTAURANT, 3, Arrays.asList(9,10),3);
            case STADIUM:
                return new Card(CardName.STADIUM, CardType.MAJOR_ESTABLISHMENT, CardClass.MAJOR_ESTABLISHMENT, 6, Arrays.asList(6));
            case TV_STATION:
                return new Card(CardName.TV_STATION, CardType.MAJOR_ESTABLISHMENT, CardClass.MAJOR_ESTABLISHMENT, 7, Arrays.asList(6));
            case BUSINESS_CENTER:
                return new Card(CardName.BUSINESS_CENTER, CardType.MAJOR_ESTABLISHMENT, CardClass.MAJOR_ESTABLISHMENT, 8, Arrays.asList(6));
            case TRAIN_STATION:
                return new Card(CardName.TRAIN_STATION, CardType.LANDMARK, CardClass.MAJOR_ESTABLISHMENT, 4, new ArrayList<>());
            case SHOPPING_MALL:
                return new Card(CardName.SHOPPING_MALL, CardType.LANDMARK, CardClass.MAJOR_ESTABLISHMENT, 10, new ArrayList<>());
            case AMUSEMENT_PARK:
                return new Card(CardName.AMUSEMENT_PARK, CardType.LANDMARK, CardClass.MAJOR_ESTABLISHMENT, 16, new ArrayList<>());
            case RADIO_TOWER:
                return new Card(CardName.RADIO_TOWER, CardType.LANDMARK, CardClass.MAJOR_ESTABLISHMENT, 22, new ArrayList<>());
            case FLOWER_ORCHARD:
                return new Card(CardName.FLOWER_ORCHARD, CardType.PRIMARY_INDUSTRY, CardClass.CEREAL, 2, Arrays.asList(4),1);
            case MACKEREL_BOAT:
                return new Card(CardName.MACKEREL_BOAT, CardType.PRIMARY_INDUSTRY, CardClass.AQUATIC, 2, Arrays.asList(8),3);
            case TUNA_BOAT:
                return new Card(CardName.TUNA_BOAT, CardType.PRIMARY_INDUSTRY, CardClass.AQUATIC, 5, Arrays.asList(12,13,14));
            case FLOWER_SHOP:
                return new Card(CardName.FLOWER_SHOP, CardType.SECONDARY_INDUSTRY, CardClass.STORE, 1, Arrays.asList(6),1);
            case FOOD_WAREHOUSE:
                return new Card(CardName.FOOD_WAREHOUSE, CardType.SECONDARY_INDUSTRY, CardClass.INDUSTRY, 2, Arrays.asList(12,13));
            case SUSHI_BAR:
                return new Card(CardName.SUSHI_BAR, CardType.RESTAURANT, CardClass.RESTAURANT, 2, Arrays.asList(1),3);
            case PIZZA:
                return new Card(CardName.PIZZA, CardType.RESTAURANT, CardClass.RESTAURANT, 1, Arrays.asList(7),1);
            case HAMBURGER:
                return new Card(CardName.HAMBURGER, CardType.RESTAURANT, CardClass.RESTAURANT, 1, Arrays.asList(8),1);
            case PUBLISHER:
                return new Card(CardName.PUBLISHER, CardType.MAJOR_ESTABLISHMENT, CardClass.MAJOR_ESTABLISHMENT, 5, Arrays.asList(7));
            case TAX_OFFICE:
                return new Card(CardName.TAX_OFFICE, CardType.MAJOR_ESTABLISHMENT, CardClass.MAJOR_ESTABLISHMENT, 4, Arrays.asList(8,9));
            case CITY_HALL:
                return new Card(CardName.CITY_HALL, CardType.LANDMARK, CardClass.MAJOR_ESTABLISHMENT, 0, new ArrayList<>());
            case HARBOR:
                return new Card(CardName.HARBOR, CardType.LANDMARK, CardClass.MAJOR_ESTABLISHMENT, 2, new ArrayList<>());
            case AIRPORT:
                return new Card(CardName.AIRPORT, CardType.LANDMARK, CardClass.MAJOR_ESTABLISHMENT, 30, new ArrayList<>());
            default:
                return null;
        }
    }
}

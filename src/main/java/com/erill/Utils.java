package com.erill;

import com.erill.card.*;

import java.util.*;

/**
 * Created by Roger Erill on 15/4/17.
 */
public class Utils {

    static Card getSelectedCard(int money, HashMap<Card, Integer> perceivedCardValues, double randomChoice) {
        Map<Card, Double> normalisedValueCards = normaliseValues(getSortedCards(money, perceivedCardValues));
        double accumulated = 0;
        System.out.println("Random number: " + randomChoice);
        for (Map.Entry<Card, Double> entry : normalisedValueCards.entrySet()) {
            accumulated += entry.getValue();
            if (accumulated >= randomChoice) {
                return entry.getKey();
            }
        }
        System.out.println("Decided not to buy anything");
        return null;
    }

    private static Map<Card, Integer> getSortedCards(int money, HashMap<Card, Integer> perceivedCardValues) {
        List<Map.Entry<Card, Integer>> list = new LinkedList<>(perceivedCardValues.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        Map<Card, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Card, Integer> entry : list) {
            if (money >= entry.getKey().getCost()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    private static Map<Card, Double> normaliseValues(Map<Card, Integer> sortedCards) {
        double totalValues = 0;
        for (Map.Entry<Card, Integer> entry : sortedCards.entrySet()) {
            totalValues += entry.getValue();
        }
        double ratio = totalValues > 0 ? Math.min(1,100/totalValues) : 1;
        Map<Card, Double> normalisedValues = new HashMap<>();
        for (Map.Entry<Card, Integer> entry : sortedCards.entrySet()) {
            normalisedValues.put(entry.getKey(), entry.getValue()*ratio);
            System.out.println("Possible card: " + entry.getKey().getName() + " = " + entry.getValue()*ratio);
        }
        return normalisedValues;
    }

    private static int calculateCardValue(CardName cardName, Player player, int playerNetValue, List<Integer> cardValueList) {
        int valuesArrayIndex = calculateValuesArrayIndex(playerNetValue);
        return cardValueList.get(valuesArrayIndex);
    }

    private static int calculateValuesArrayIndex(int playerNetValue) {
        if (playerNetValue <= PlayerValueTypes.VERY_LOW_VALUE.getValue()) return 0;
        if (playerNetValue <= PlayerValueTypes.LOW_VALUE.getValue()) return 1;
        if (playerNetValue <= PlayerValueTypes.MID_VALUE.getValue()) return 2;
        if (playerNetValue <= PlayerValueTypes.HIGH_VALUE.getValue()) return 3;
        if (playerNetValue <= PlayerValueTypes.VERY_HIGH_VALUE.getValue()) return 4;
        return 5;
    }

    public static int calculateNetValue(Player player) {
        int value = 0;
        List<Card> playerCards = player.getPlayerCards();
        for (Card card : playerCards) {
            if (card instanceof LandmarkCard) {
                if (((LandmarkCard) card).isActivated()) value += card.getCost();
            }
            else  {
                value += card.getCost();
            }
        }
        return value;
    }

    static HashMap<Card, Integer> fillCardValues(Player player, List<Card> possibleBoardCards) {
        HashMap<Card, Integer> boardCardValues = new HashMap<>();
        int playerNetValue = calculateNetValue(player);
        for (Card boardCard : possibleBoardCards) {
            CardName cardName = boardCard.getCardName();
            int cardValue = calculateCardValue(cardName, player, playerNetValue, PlayerBrain.cardValues.get(cardName));
            boardCardValues.put(boardCard, cardValue);
        }
        return boardCardValues;
    }

    public static int getRewardForCard(CardName cardName, List<Card> playerCards, int extra) {
        int baseReward = 0;
        int multiplier = 0;
        switch (cardName) {
            case FLOWER_SHOP:
                baseReward = 1 + extra;
                multiplier = getNumCards(CardName.FLOWER_ORCHARD, playerCards);
                break;
            case FURNITURE_FACTORY:
                baseReward = 3;
                multiplier = getNumCards(CardClass.NATURAL_RESOURCE, playerCards);
                break;
            case CHEESE_FACTORY:
                baseReward = 3;
                multiplier = getNumCards(CardClass.FOOD, playerCards);
                break;
            case FRUIT_MARKET:
                baseReward = 2;
                multiplier = getNumCards(CardClass.CEREAL, playerCards);
                break;
            case FOOD_WAREHOUSE:
                baseReward = 2;
                multiplier = getNumCards(CardClass.RESTAURANT, playerCards);
                break;
            default:
                break;
        }
        return baseReward * multiplier;
    }

    private static int getNumCards(CardName targetName, List<Card> playerCards) {
        int result = 0;
        for (Card playerCard : playerCards) {
            if (playerCard.getCardName().equals(targetName)) ++result;
        }
        return result;
    }

    private static int getNumCards(CardClass targetClass, List<Card> playerCards) {
        int result = 0;
        for (Card playerCard : playerCards) {
            if (playerCard.getCardClass().equals(targetClass)) ++result;
        }
        return result;
    }

    public static PlayerValueTypes getCurrentPlayerValueType(Player player) {
        int netValue = calculateNetValue(player);
        PlayerValueTypes[] values = PlayerValueTypes.values();
        for (PlayerValueTypes value : values) {
            if (netValue <= value.getValue()) {
                return value;
            }
        }
        return PlayerValueTypes.ENDGAME_VALUE;
    }

    public static int getNumberRestaurantsAndShops(Player player) {
        int result = 0;
        List<Card> playerCards = player.getPlayerCards();
        for (Card playerCard : playerCards) {
            CardClass cardClass = playerCard.getCardClass();
            if (cardClass.equals(CardClass.STORE) || cardClass.equals(CardClass.RESTAURANT)) ++result;
        }
        return result;
    }
}

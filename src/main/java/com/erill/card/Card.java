package com.erill.card;

import com.erill.printer.PrintColorWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by Roger Erill on 10/4/17.
 */
public class Card {

    private int id;
    private final CardName name;
    private final int cost;
    private final List<Integer> activations;
    private final CardType type;
    private final CardClass cardClass;
    private final int reward;

    public Card(CardName name, CardType type, CardClass cardClass, int cost, List<Integer> activations) {
        this.name = name;
        this.type = type;
        this.cardClass = cardClass;
        this.cost = cost;
        this.activations = activations;
        this.reward = -1;
    }

    public Card(CardName name, CardType type, CardClass cardClass, int cost, List<Integer> activations, int reward) {
        this.name = name;
        this.type = type;
        this.cardClass = cardClass;
        this.cost = cost;
        this.activations = activations;
        this.reward = reward;
    }

    public static Card createCardByName(CardName cardName) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getActivations() {
        return activations;
    }

    public int getCost() {
        return cost;
    }

    public CardType getType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        printActivations(sb);
        printCardName(sb);
        sb.append(PrintColorWriter.LONG_SPACE);
        sb.append("[").append(id).append("]");
        return sb.toString();
    }

    public String getDetailedCardDescription() {
        StringBuilder sb = new StringBuilder();
        printActivations(sb);
        printCardName(sb);
        printTypes(sb);
        printCostAndRewards(sb);
        return sb.toString();
    }

    private void printCardName(StringBuilder sb) {
        sb.append(PrintColorWriter.LONG_SPACE);
        sb.append(name.getName());
        sb.append(PrintColorWriter.LONG_SPACE);
    }

    private void printCostAndRewards(StringBuilder sb) {
        sb.append("Cost: ").append(cost);
        if (reward != -1) {
            sb.append(PrintColorWriter.LARGE_SPACE).append("Reward: ").append(reward);
        }
        sb.append(PrintColorWriter.ENDLINE);
    }

    private void printTypes(StringBuilder sb) {
        switch (type) {
            case PRIMARY_INDUSTRY:
                sb.append(PrintColorWriter.NATURE_SYMBOL);
                break;
            case SECONDARY_INDUSTRY:
                sb.append(PrintColorWriter.INDUSTRY_SYMBOL);
                break;
            case RESTAURANT:
                sb.append(PrintColorWriter.COFFEE_SYMBOL);
                break;
            case MAJOR_ESTABLISHMENT:
                sb.append(PrintColorWriter.BUSINESS_SYMBOL);
                break;
            case LANDMARK:
                sb.append(PrintColorWriter.LANDMARK_SYMBOL);
                break;
        }
        sb.append(PrintColorWriter.SHORT_SPACE).append(cardClass.getName());
        sb.append(PrintColorWriter.ENDLINE);
    }

    private void printActivations(StringBuilder sb) {
        sb.append(activations.get(0));
        for (int i = 1; i < activations.size(); i++) {
            sb.append("-").append(activations.get(i));
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Card)) return false;
        Card other = (Card) obj;
        CardName name = this.name;
        CardName otherName = other.name;
        return name.equals(otherName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }
}

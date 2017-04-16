package com.erill;

import com.erill.card.CardName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Roger Erill on 15/4/17.
 */
public class CardValues {

    public static HashMap<CardName, List<Integer>> initializeValues() {
        HashMap<CardName, List<Integer>> values = new HashMap<>();
        CardName[] cardNameValues = CardName.values();
        for (CardName cardNameValue : cardNameValues) {
            List<Integer> cardPerceivedValues = new ArrayList<>();
            switch (cardNameValue) {
                case WHEAT_FIELD:
                    cardPerceivedValues.addAll(Arrays.asList(100,60,30,15,5,1));
                    break;
                case RANCH:
                    cardPerceivedValues.addAll(Arrays.asList(100,60,30,15,5,1));
                    break;
                case FOREST:
                    cardPerceivedValues.addAll(Arrays.asList(60,70,50,20,10,5));
                    break;
                case MINE:
                    cardPerceivedValues.addAll(Arrays.asList(1,30,60,70,75,65));
                    break;
                case APPLE_ORCHARD:
                    cardPerceivedValues.addAll(Arrays.asList(1,30,50,50,50,40));
                    break;
                case BAKERY:
                    cardPerceivedValues.addAll(Arrays.asList(100,75,40,20,10,5));
                    break;
                case CONVENIENCE_STORE:
                    cardPerceivedValues.addAll(Arrays.asList(80,80,40,20,10,5));
                    break;
                case CHEESE_FACTORY:
                    cardPerceivedValues.addAll(Arrays.asList(1,20,50,60,60,50));
                    break;
                case FURNITURE_FACTORY:
                    cardPerceivedValues.addAll(Arrays.asList(1,20,50,70,75,80));
                    break;
                case FRUIT_MARKET:
                    cardPerceivedValues.addAll(Arrays.asList(1,20,50,60,70,60));
                    break;
                case CAFE:
                    cardPerceivedValues.addAll(Arrays.asList(75,60,30,20,10,5));
                    break;
                case RESTAURANT:
                    cardPerceivedValues.addAll(Arrays.asList(1,30,70,60,60,50));
                    break;
                case STADIUM:
                    cardPerceivedValues.addAll(Arrays.asList(10,30,50,60,70,75));
                    break;
                case TV_STATION:
                    cardPerceivedValues.addAll(Arrays.asList(1,20,30,40,50,60));
                    break;
                case BUSINESS_CENTER:
                    cardPerceivedValues.addAll(Arrays.asList(1,10,30,50,70,80));
                    break;
                case TRAIN_STATION:
                    cardPerceivedValues.addAll(Arrays.asList(100,100,100,100,100,100));
                    break;
                case SHOPPING_MALL:
                    cardPerceivedValues.addAll(Arrays.asList(100,100,100,100,100,100));
                    break;
                case AMUSEMENT_PARK:
                    cardPerceivedValues.addAll(Arrays.asList(100,100,100,100,100,100));
                    break;
                case RADIO_TOWER:
                    cardPerceivedValues.addAll(Arrays.asList(100,100,100,100,100,100));
                    break;
                case FLOWER_ORCHARD:
                    cardPerceivedValues.addAll(Arrays.asList(80,70,40,25,10,5));
                    break;
                case MACKEREL_BOAT:
                    cardPerceivedValues.addAll(Arrays.asList(5,30,70,70,65,50));
                    break;
                case TUNA_BOAT:
                    cardPerceivedValues.addAll(Arrays.asList(10,40,80,100,100,100));
                    break;
                case FLOWER_SHOP:
                    cardPerceivedValues.addAll(Arrays.asList(40,60,70,50,40,30));
                    break;
                case FOOD_WAREHOUSE:
                    cardPerceivedValues.addAll(Arrays.asList(1,40,70,70,60,50));
                    break;
                case SUSHI_BAR:
                    cardPerceivedValues.addAll(Arrays.asList(80,70,40,20,10,1));
                    break;
                case PIZZA:
                    cardPerceivedValues.addAll(Arrays.asList(5,30,70,50,30,20));
                    break;
                case HAMBURGER:
                    cardPerceivedValues.addAll(Arrays.asList(5,30,70,50,30,20));
                    break;
                case PUBLISHER:
                    cardPerceivedValues.addAll(Arrays.asList(1,20,30,50,70,80));
                    break;
                case TAX_OFFICE:
                    cardPerceivedValues.addAll(Arrays.asList(1,10,30,50,70,85));
                    break;
                case CITY_HALL:
                    cardPerceivedValues.addAll(Arrays.asList(100,100,100,100,100,100));
                    break;
                case HARBOR:
                    cardPerceivedValues.addAll(Arrays.asList(100,100,100,100,100,100));
                    break;
                case AIRPORT:
                    cardPerceivedValues.addAll(Arrays.asList(100,100,100,100,100,100));
                    break;
            }
            values.put(cardNameValue, cardPerceivedValues);
        }
        return values;
    }
}

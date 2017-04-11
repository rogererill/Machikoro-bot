package com.erill.card;

/**
 * Created by Roger Erill on 10/4/17.
 */
public enum CardName {

    WHEAT_FIELD("Wheat field"),
    RANCH("Ranch"),
    FOREST("Forest"),
    MINE("Mine"),
    APPLE_ORCHARD("Apple orchard"),
    BAKERY("Bakery"),
    CONVENIENCE_STORE("Convenience Store"),
    CHEESE_FACTORY("Cheese factory"),
    FURNITURE_FACTORY("Furniture factory"),
    FRUIT_MARKET("Fruit and vegetables market"),
    CAFE("Cafe"),
    RESTAURANT("Family restaurant"),
    STADIUM("Stadium"),
    TV_STATION("TV Station"),
    BUSINESS_CENTER("Business center"),
    TRAIN_STATION("Train Station"),
    SHOPPING_MALL("Shopping mall"),
    AMUSEMENT_PARK("Amusement park"),
    RADIO_TOWER("Radio tower"),
    FLOWER_ORCHARD("Flower orchard"),
    MACKEREL_BOAT("Mackerel boat"),
    TUNA_BOAT("Tuna boat"),
    FLOWER_SHOP("Flower shop"),
    FOOD_WAREHOUSE("Food warehouse"),
    SUSHI_BAR("Sushi bar"),
    PIZZA("Pizza joint"),
    HAMBURGER("Hamburger stand"),
    PUBLISHER("Publisher"),
    TAX_OFFICE("Tax office"),
    CITY_HALL("City hall"),
    HARBOR("Harbor"),
    AIRPORT("Airport");


    private String name;

    CardName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package com.erill.card;

/**
 * Created by Roger Erill on 10/4/17.
 */
public enum CardName {

    WHEAT_FIELD("Wheat field",1),
    RANCH("Ranch",2),
    FOREST("Forest",3),
    MINE("Mine",4),
    APPLE_ORCHARD("Apple orchard",5),
    BAKERY("Bakery",6),
    CONVENIENCE_STORE("Convenience Store",7),
    CHEESE_FACTORY("Cheese factory",8),
    FURNITURE_FACTORY("Furniture factory",9),
    FRUIT_MARKET("Fruit and vegetables market",10),
    CAFE("Cafe",11),
    RESTAURANT("Family restaurant",12),
    STADIUM("Stadium",13),
    TV_STATION("TV Station",14),
    BUSINESS_CENTER("Business center",15),
    TRAIN_STATION("Train Station",16),
    SHOPPING_MALL("Shopping mall",17),
    AMUSEMENT_PARK("Amusement park",18),
    RADIO_TOWER("Radio tower",19),
    FLOWER_ORCHARD("Flower orchard",20),
    MACKEREL_BOAT("Mackerel boat",21),
    TUNA_BOAT("Tuna boat",22),
    FLOWER_SHOP("Flower shop",23),
    FOOD_WAREHOUSE("Food warehouse",24),
    SUSHI_BAR("Sushi bar",25),
    PIZZA("Pizza joint",26),
    HAMBURGER("Hamburger stand",27),
    PUBLISHER("Publisher",28),
    TAX_OFFICE("Tax office",29),
    CITY_HALL("City hall",30),
    HARBOR("Harbor",31),
    AIRPORT("Airport",32);


    private String name;
    private int cardId;

    CardName(String name, int cardId) {
        this.name = name;
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public int getCardId() {
        return cardId;
    }
}

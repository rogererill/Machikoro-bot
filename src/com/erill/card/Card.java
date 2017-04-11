package com.erill.card;

import java.util.List;

/**
 * Created by Roger Erill on 10/4/17.
 */
public class Card {

    private final CardName name;
    private final int cost;
    private final List<Integer> activations;
    private final CardType type;
    private final CardClass cardClass;
    private int reward;

    public Card(CardName name, CardType type, CardClass cardClass, int cost, List<Integer> activations) {
        this.name = name;
        this.type = type;
        this.cardClass = cardClass;
        this.cost = cost;
        this.activations = activations;
    }

    public Card(CardName name, CardType type, CardClass cardClass, int cost, List<Integer> activations, int reward) {
        this.name = name;
        this.type = type;
        this.cardClass = cardClass;
        this.cost = cost;
        this.activations = activations;
        this.reward = reward;
    }
}

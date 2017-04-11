package com.erill.card;

import java.util.List;

/**
 * Created by Roger Erill on 10/4/17.
 */
public class CardPrimary extends Card {

    private static final CardType type = CardType.PRIMARY_INDUSTRY;

    private int reward;
    private CardClass cardClass;

    public CardPrimary(CardName name, CardClass cardClass, int cost, List<Integer> activations, int reward) {
        super(name, cost, activations);
        this.cardClass = cardClass;
        this.reward = reward;
    }

    public CardPrimary(CardName name, CardClass cardClas, int cost, List<Integer> activations) {
        super(name, cost, activations);
        this.cardClass = cardClass;
    }
}

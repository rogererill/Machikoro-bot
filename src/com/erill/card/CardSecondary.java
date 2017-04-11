package com.erill.card;

import java.util.List;

/**
 * Created by Roger Erill on 10/4/17.
 */
public class CardSecondary extends Card {

    private static final CardType type = CardType.SECONDARY_INDUSTRY;

    private int reward;
    private CardClass cardClass;

    public CardSecondary(CardName name, CardClass cardClass, int cost, List<Integer> activations, int reward) {
        super(name, cost, activations);
        this.cardClass = cardClass;
        this.reward = reward;
    }

    public CardSecondary(CardName name, CardClass cardClas, int cost, List<Integer> activations) {
        super(name, cost, activations);
        this.cardClass = cardClass;
    }
}

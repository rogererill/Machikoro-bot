package com.erill;

import com.erill.card.Card;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Roger Erill on 15/4/17.
 */
public class CardComparator implements Comparator<Card> {
    @Override
    public int compare(Card card1, Card card2) {
        List<Integer> activations = card1.getActivations();
        if (activations.isEmpty()) return -1;
        List<Integer> activations2 = card2.getActivations();
        if (activations2.isEmpty()) return 1;

        int compare = activations.get(0).compareTo(activations2.get(0));
        if (compare != 0) return compare;
        return card1.toString().compareTo(card2.toString());
    }
}

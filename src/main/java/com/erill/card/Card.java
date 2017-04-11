package com.erill.card;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getActivations() {
        return activations;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(activations.get(0));
        for (int i = 1; i < activations.size(); i++) {
            sb.append("-").append(activations.get(i));
        }
        sb.append("  ");
        sb.append(name.getName());
        sb.append("  ");
        sb.append("[").append(id).append("]");
        return sb.toString();
    }

    public String getDetailedCardDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(activations.get(0));
        for (int i = 1; i < activations.size(); i++) {
            sb.append("-").append(activations.get(i));
        }
        sb.append("  ");
        sb.append(name.getName()).append(" ").append(type.getColor());
        if (type != CardType.MAJOR_ESTABLISHMENT) sb.append("  ").append(cardClass.getName());
        sb.append("\n");
        sb.append("Cost: ").append(cost);
        if (reward != -1) {
            sb.append("   ").append("Reward: ").append(reward);
        }
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Card)) return false;
        Card other = (Card) obj;
        CardName name = this.name;
        CardName otherName = other.name;
        boolean equals = name.equals(otherName);
        return equals;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }
}

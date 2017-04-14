package com.erill.card;

import java.util.ArrayList;

/**
 * Created by Roger Erill on 14/4/17.
 */
public class LandmarkCard extends Card {

    private boolean isActivated;

    public LandmarkCard(CardName name, int cost) {
        super(name, CardType.LANDMARK, CardClass.MAJOR_ESTABLISHMENT, cost, new ArrayList<>());
        isActivated = false;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }
}

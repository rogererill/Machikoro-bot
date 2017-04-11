package com.erill;

/**
 * Created by Roger Erill on 11/4/17.
 */

public class MainScreen implements MainView {

    private MainPresenter presenter;

    public MainScreen() {
        presenter = new MainPresenter(new Board());
        presenter.bindView(this);

        presenter.printDeck();
    }

    @Override
    public void doTest() {

    }
}

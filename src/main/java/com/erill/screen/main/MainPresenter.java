package com.erill.screen.main;

import com.erill.*;
import com.erill.base.BasePresenter;
import com.erill.card.Card;
import com.erill.card.CardClass;
import com.erill.card.CardType;
import com.erill.card.LandmarkCard;
import com.erill.printer.PrintColor;

import java.util.ArrayList;
import java.util.List;

import static com.erill.card.CardName.BUSINESS_CENTER;
import static com.erill.printer.PrintColorWriter.ENDLINE;

/**
 * Created by Roger Erill on 11/4/17.
 */


public class MainPresenter extends BasePresenter<MainView> {

    private Board board;
    private List<Player> players;
    private int numPlayers;
    private PlayerBrain playerBrain;

    MainPresenter(Board board) {
        this.board = board;
        this.playerBrain = PlayerBrain.getInstance();
    }

    public void printBoard() {
        List<Card> boardCards = board.getBoardCards();
        for (Card card : boardCards) {
            PrintColor background = getPrintColor(card);
            getView().printCard(card, background);
        }
    }

    private PrintColor getPrintColor(Card card) {
        CardType type = card.getType();
        switch (type) {
            case PRIMARY_INDUSTRY:
                return PrintColor.BLUE_BG;
            case SECONDARY_INDUSTRY:
                return PrintColor.GREEN_BG;
            case RESTAURANT:
                return PrintColor.RED_BG;
            case MAJOR_ESTABLISHMENT:
                return PrintColor.PURPLE_BG;
            case LANDMARK:
                return PrintColor.YELLOW_BG;
            default:
                return PrintColor.BLACK_BG;
        }
    }

    public void createGame(int numPlayers) {
        this.numPlayers = numPlayers;
        createPlayers(numPlayers);
        playerBrain.setIndexCurrentPlayer(0);
        getView().startGame(players);
    }

    private void createPlayers(int numPlayers) {
        players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            players.add(new Player("Player " + i, false));
        }
        players.get(0).setActivePlayer(true);
    }

    public void readInput(String input) {
        Player currentPlayer = players.get(playerBrain.getIndexCurrentPlayer());
        switch (input) {
            case "q":
                getView().endGame(currentPlayer);
                break;
            case "n":
                updatePlayerBrainInfo();
                printBoard();
                getView().printPlayers(players, playerBrain.getIndexCurrentPlayer());

                playerBrain.throwDice();
                getView().printDiceResult(playerBrain.getFirstDie(), playerBrain.getSecondDie());
                resolveDiceValue(playerBrain.getFirstDie(), playerBrain.getSecondDie());

                updatePlayerBrainInfo();
                Card wantedCard = playerBrain.calculateWantedCard();
                boolean gameFinished = false;
                if (wantedCard != null) {
                    getView().print(ENDLINE);
                    getView().print(currentPlayer.printShortDescription() + " bought");
                    getView().printCard(wantedCard, getPrintColor(wantedCard));
                    getView().print(ENDLINE);
                    gameFinished = resolveCardPurchase(currentPlayer, wantedCard);
                }

                if (gameFinished) {
                    getView().endGame(currentPlayer);
                } else {
                    checkCityHallCoin(currentPlayer, wantedCard);
                    getView().print("Status post dice and card purchasing");
                    getView().printPlayers(players, playerBrain.getIndexCurrentPlayer());
                    playerBrain.passTurn();
                    getView().endTurn();
                }

                break;
            default:
                getView().printIncorrectInput(input);
                break;
        }
    }

    private void checkCityHallCoin(Player currentPlayer, Card wantedCard) {
        if (wantedCard == null && currentPlayer.getMoney() == 0) {
            getView().print("We give " + currentPlayer.printShortDescription()
                    + " 1 coin because of City's hall");
            currentPlayer.setMoney(1);
        }
    }

    //TODO
    private boolean resolveCardPurchase(Player currentPlayer, Card wantedCard) {
        board.removeBoughtCard(wantedCard);
        if (wantedCard instanceof LandmarkCard) {
            currentPlayer.activateLandmarkCard((LandmarkCard) wantedCard);
            currentPlayer.setMoney(currentPlayer.getMoney() - wantedCard.getCost());
            List<LandmarkCard> unpurchasedLandmarks = currentPlayer.getUnpurchasedLandmarks();
            return unpurchasedLandmarks.isEmpty();
        } else {
            currentPlayer.buyCard(wantedCard);
            return false;
        }
    }

    //TODO
    private void resolveDiceValue(int firstDie, int secondDie) {
        int totalValue = firstDie + secondDie;
        int currentPlayerIndex = playerBrain.getIndexCurrentPlayer();
        Player currentPlayer = players.get(currentPlayerIndex);
        resolveRedCards(totalValue, currentPlayerIndex, currentPlayer);
        resolveBlueCards(totalValue, currentPlayerIndex);
        resolveGreenCards(totalValue, currentPlayer);
        resolvePurpleCards(totalValue, currentPlayerIndex, currentPlayer);

    }

    private void resolvePurpleCards(int totalValue, int currentPlayerIndex, Player currentPlayer) {
        List<Card> playerCards = currentPlayer.getPlayerCards();
        Card cardToLose = null;
        Card cardToWin = null;
        Player playerToSteal = null;
        for (Card playerCard : playerCards) {
            List<Integer> activations = playerCard.getActivations();
            if (activations.contains(totalValue) && playerCard.getType().equals(CardType.MAJOR_ESTABLISHMENT)) {
                switch (playerCard.getCardName()) {
                    case STADIUM:
                        resolveStadiumCard(currentPlayerIndex, currentPlayer, playerCard);
                        break;
                    case PUBLISHER:
                        resolvePublisherCard(currentPlayerIndex, currentPlayer, playerCard);
                        break;
                    case TV_STATION:
                        resolveTVStationCard(currentPlayerIndex, currentPlayer, playerCard);
                        break;
                    case BUSINESS_CENTER:
                        playerToSteal = getPlayerToSteal(currentPlayerIndex);
                        playerCards.sort(new CardComparator());
                        cardToLose = playerCards.get(playerCards.size() - 1);
                        List<Card> playerToStealCards = playerToSteal.getPlayerCards();
                        playerToStealCards.sort(new CardComparator());
                        for (Card playerToStealCard : playerToStealCards) {
                            if (!playerToStealCard.getCardClass().equals(CardClass.MAJOR_ESTABLISHMENT)) {
                                cardToWin = playerToStealCard;
                                break;
                            }
                        }
                        break;
                    case TAX_OFFICE:
                        resolveTaxOfficeCard(currentPlayerIndex, currentPlayer, playerCard);
                        break;
                }
            }
        }
        if (playerToSteal != null) {
            currentPlayer.removeCard(cardToLose);
            currentPlayer.addCard(cardToWin);
            playerToSteal.removeCard(cardToWin);
            playerToSteal.addCard(cardToLose);
            getView().print(BUSINESS_CENTER.getName() + " activated. " + currentPlayer.printShortDescription() +
                    " gives " + cardToLose.getName() + " to " + playerToSteal.printShortDescription() +
                    " in exchange for " + cardToWin.getName());
        }
    }

    private Player getPlayerToSteal(int currentPlayerIndex) {
        Player playerToSteal = null;
        for (int i = 0; i < players.size(); i++) {
            if (i != currentPlayerIndex) {
                Player analysedPlayer = players.get(i);
                if (playerToSteal == null || Utils.calculateNetValue(analysedPlayer) > Utils.calculateNetValue(playerToSteal)) {
                    playerToSteal = analysedPlayer;
                }
            }
        }
        return playerToSteal;
    }

    private void resolveTaxOfficeCard(int currentPlayerIndex, Player currentPlayer, Card playerCard) {
        for (int i = 0; i < players.size(); i++) {
            if (i != currentPlayerIndex) {
                Player analyzedPlayer = players.get(i);
                if (analyzedPlayer.getMoney() > 8) {
                    int amountToPay = analyzedPlayer.getMoney()/2;
                    getView().print(playerCard.getName() + " activated. " + analyzedPlayer.printShortDescription() +
                            " pays " + amountToPay + " to " + currentPlayer.printShortDescription());
                }
            }
        }
    }

    private void resolveTVStationCard(int currentPlayerIndex, Player currentPlayer, Card playerCard) {
        Player playerToSteal = null;
        int money = Integer.MIN_VALUE;
        for (int i = 0; i < players.size(); i++) {
            if (i != currentPlayerIndex) {
                Player analysedPlayer = players.get(i);
                if (playerToSteal == null || analysedPlayer.getMoney() > money ||
                    (analysedPlayer.getMoney() == playerToSteal.getMoney())
                        && Utils.calculateNetValue(analysedPlayer) > Utils.calculateNetValue(playerToSteal)) {
                    playerToSteal = analysedPlayer;
                    money = analysedPlayer.getMoney();
                }
            }
        }
        int amountToPay = Math.min(5, playerToSteal.getMoney());
        getView().print(playerCard.getName() + " activated. " + playerToSteal.printShortDescription() +
                " pays " + amountToPay + " to " + currentPlayer.printShortDescription());
        currentPlayer.giveMoney(amountToPay);
        playerToSteal.takeMoney(amountToPay);
    }

    private void resolvePublisherCard(int currentPlayerIndex, Player currentPlayer, Card playerCard) {
        for (int i = 0; i < players.size(); i++) {
            if (i != currentPlayerIndex) {
                Player analysedPlayer = players.get(i);
                int amountToPay = Math.min(Utils.getNumberRestaurantsAndShops(analysedPlayer), analysedPlayer.getMoney());
                getView().print(playerCard.getName() + " activated. " + analysedPlayer.printShortDescription() +
                        " pays " + amountToPay + " to " + currentPlayer.printShortDescription());
                currentPlayer.giveMoney(amountToPay);
                analysedPlayer.takeMoney(amountToPay);
            }
        }
    }

    private void resolveStadiumCard(int currentPlayerIndex, Player currentPlayer, Card playerCard) {
        for (int i = 0; i < players.size(); i++) {
            if (i != currentPlayerIndex) {
                Player analysedPlayer = players.get(i);
                int amountToPay = Math.min(2, analysedPlayer.getMoney());
                getView().print(playerCard.getName() + " activated. " + analysedPlayer.printShortDescription() +
                        " pays " + amountToPay + " to " + currentPlayer.printShortDescription());
                currentPlayer.giveMoney(amountToPay);
                analysedPlayer.takeMoney(amountToPay);
            }
        }
    }

    private void resolveGreenCards(int totalValue, Player currentPlayer) {
        List<Card> playerCards = currentPlayer.getPlayerCards();
        for (Card playerCard : playerCards) {
            List<Integer> activations = playerCard.getActivations();
            if (activations.contains(totalValue) && playerCard.getType().equals(CardType.SECONDARY_INDUSTRY)) {
                int reward = playerCard.getReward();
                boolean getsPlusOneBonus = playerCard.getCardClass().equals(CardClass.STORE) && currentPlayer.hasShoppingMall();
                if (reward == 0) {
                    reward = Utils.getRewardForCard(playerCard.getCardName(), currentPlayer.getPlayerCards(), getsPlusOneBonus ? 1 : 0);
                }
                else {
                    if (getsPlusOneBonus) {
                        reward += 1;
                    }
                }
                getView().print(playerCard.getName() + " activated. " + currentPlayer.printShortDescription() +
                        " receives " + reward + " coins");
                currentPlayer.giveMoney(reward);
            }
        }
    }

    private void resolveBlueCards(int totalValue, int currentPlayerIndex) {
        int iteratorIndex = currentPlayerIndex;
        do {
            Player analysedPlayer = players.get(iteratorIndex);
            List<Card> playerCards = analysedPlayer.getPlayerCards();
            for (Card playerCard : playerCards) {
                List<Integer> activations = playerCard.getActivations();
                if (activations.contains(totalValue) && playerCard.getType().equals(CardType.PRIMARY_INDUSTRY)) {
                    int reward = playerCard.getReward();
                    switch (playerCard.getCardName()) {
                        case MACKEREL_BOAT:
                            if (!analysedPlayer.hasHarbor()) reward = 0;
                            break;
                        case TUNA_BOAT:
                            playerBrain.throwTwoDice();
                            getView().printDiceResult(playerBrain.getFirstDie(), playerBrain.getSecondDie());
                            reward = playerBrain.getTotalDice();
                            break;
                    }
                    getView().print(playerCard.getName() + " activated. " + analysedPlayer.printShortDescription() +
                            " receives " + reward + " coins");
                    analysedPlayer.giveMoney(reward);
                }
            }
            iteratorIndex = (iteratorIndex + 1) % numPlayers;
        } while (iteratorIndex != currentPlayerIndex);
    }

    private void resolveRedCards(int totalValue, int currentPlayerIndex, Player currentPlayer) {
        int iteratorIndex = ((currentPlayerIndex + numPlayers - 1) % numPlayers);
        while (iteratorIndex != currentPlayerIndex) {
            Player analysedPlayer = players.get(iteratorIndex);
            List<Card> playerCards = analysedPlayer.getPlayerCards();
            for (Card playerCard : playerCards) {
                List<Integer> activations = playerCard.getActivations();
                if (activations.contains(totalValue) && playerCard.getType().equals(CardType.RESTAURANT)) {
                    int cardReward = playerCard.getReward();
                    if (analysedPlayer.hasShoppingMall()) {
                        cardReward += 1;
                    }
                    int amountToPay = Math.min(currentPlayer.getMoney(), cardReward);
                    getView().print(playerCard.getName() + " activated. " + currentPlayer.printShortDescription() +
                            " pays " + amountToPay + " to " + analysedPlayer.printShortDescription());
                    currentPlayer.takeMoney(amountToPay);
                    analysedPlayer.giveMoney(amountToPay);
                }
            }
            iteratorIndex = ((iteratorIndex + numPlayers - 1) % numPlayers);
        }
    }

    private void updatePlayerBrainInfo() {
        playerBrain.setBoard(board);
        playerBrain.setPlayerList(players);
    }
}

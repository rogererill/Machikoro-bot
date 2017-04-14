package com.erill.screen.main;

import com.erill.Board;
import com.erill.Player;
import com.erill.PlayerBrain;
import com.erill.base.BasePresenter;
import com.erill.card.Card;
import com.erill.card.CardClass;
import com.erill.card.CardType;
import com.erill.card.LandmarkCard;
import com.erill.printer.PrintColor;

import java.util.ArrayList;
import java.util.List;

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
            case "":
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

                getView().print("Status post dice and card purchasing");
                getView().printPlayers(players, playerBrain.getIndexCurrentPlayer());


                if (gameFinished) {
                    getView().endGame(currentPlayer);
                } else {
                    checkCityHallCoin(currentPlayer, wantedCard);
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

    }

    private void resolveGreenCards(int totalValue, Player currentPlayer) {
        List<Card> playerCards = currentPlayer.getPlayerCards();
        for (Card playerCard : playerCards) {
            List<Integer> activations = playerCard.getActivations();
            if (activations.contains(totalValue) && playerCard.getType().equals(CardType.SECONDARY_INDUSTRY)) {
                int reward = playerCard.getReward();
                if (playerCard.getCardClass().equals(CardClass.STORE) && currentPlayer.hasShoppingMall()) {
                    reward += 1;
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
                    getView().print(playerCard.getName() + " activated. " + analysedPlayer.printShortDescription() +
                            " receives " + playerCard.getReward() + " coins");
                    analysedPlayer.giveMoney(playerCard.getReward());
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
                    int amountToPay = Math.min(currentPlayer.getMoney(), playerCard.getReward());
                    if (analysedPlayer.hasShoppingMall()) {
                        amountToPay += 1;
                    }
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

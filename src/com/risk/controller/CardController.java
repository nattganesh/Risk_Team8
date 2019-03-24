/**
 * Necessary for handling card exchange logic for reinforcement phase
 *
 * @author DKM
 * @author Tianyi
 */
package com.risk.controller;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import com.risk.model.ActionModel;
import com.risk.model.MapModel;
import com.risk.model.PlayerPhaseModel;
import com.risk.model.card.Card;
import com.risk.model.map.Country;
import com.risk.model.player.Player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;


public class CardController extends Observable implements Initializable {

    @FXML
    ListView<Card> yourCard;

    @FXML
    ListView<Card> tradeCard;

    private int reinforcement;

    ActionModel actions;
    Player player;
    
    ObservableList<Card> tradeObservableList = FXCollections.observableArrayList();

    public void renderView()
    {
        yourCard.setCellFactory(param -> new ListCell<Card>() {
            @Override
            protected void updateItem(Card card, boolean empty)
            {
                super.updateItem(card, empty);
                if (empty || card == null || card.getCatagory() == null)
                {
                    setText(null);
                }
                else
                {
                    setText(card.getCatagory());
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        actions = ActionModel.getActionModel();
        yourCard.setItems(PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getCards());
        tradeCard.setItems(tradeObservableList);
        player = PlayerPhaseModel.getPlayerModel().getCurrentPlayer();
        renderView();
    }

    /**
     * This method is used to update the cards the player owned and the cards
     * the player chooses to exchange
     */
    @FXML
    public void yourCardHandler()
    {
        if (yourCard.getSelectionModel().getSelectedItem() != null && tradeCard.getItems().size() < 3)
        {
            Card card = yourCard.getSelectionModel().getSelectedItem();
            tradeCard.getItems().add(card);
            yourCard.getItems().remove(card);

        }
    }

    /**
     * This method is used to update the cards the player owned and the cards
     * the player chooses to exchange.
     */
    @FXML
    public void yourTradeHandler()
    {
        if (tradeCard.getSelectionModel().getSelectedItem() != null)
        {
            Card card = tradeCard.getSelectionModel().getSelectedItem();
            yourCard.getItems().add(card);
            tradeCard.getItems().remove(card);

        }
    }

    /**
     * This method is used to update the total reinforcement based on the cards
     * the player chooses And update the cards the player owned.
     */
    @FXML
    public void tradeCard()
    {
        if (tradeCard.getItems().size() == 3)
        {
            if (cardValidation(tradeCard.getItems()))
            {
                reinforcement = player.calculateReinforcementFromCards();
                for (Card c : tradeCard.getItems())
                {
                    c.removeCard(PlayerPhaseModel.getPlayerModel().getCurrentPlayer());
                }
                System.out.println(PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getCards());
                tradeCard.getItems().clear();
                actions.addAction("you exchanged cards");
                setChanged();
                notifyObservers(reinforcement);
            }
            else
            {
                for (Card card : tradeCard.getItems())
                {
                    yourCard.getItems().add(card);
                }
                tradeCard.getItems().clear();
                actions.addAction("Invalid cards");
            }
        }
        else
        {
            actions.addAction("you have less than 3 cards");
        }
    }

    /**
     * This method handles skipping of card exchange
     */
    @FXML
    public void skipExchangeHandler()
    {
        if (PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getCards().size() > 5)
        {
            actions.addAction("5+ cards == you must exchange");
        }
        else
        {
            setChanged();
            notifyObservers();
        }
    }

    /**
     * This method is used to validate the cards the player chooses to exchange
     *
     * @param selectedCards A list of cards the player chooses to exchange for
     * armies
     * @return The result corresponding to the countries the player occupied
     */
    public boolean cardValidation(ObservableList<Card> selectedCards)
    {
        ObservableList<Card> cards = FXCollections.observableArrayList();
        cards = selectedCards;

        if (((cards.get(0).getCatagory().equals(cards.get(1).getCatagory()))
                && (cards.get(0).getCatagory().equals(cards.get(2).getCatagory())))
                || ((!(cards.get(0).getCatagory().equals(cards.get(1).getCatagory())))
                && (!(cards.get(0).getCatagory().equals(cards.get(2).getCatagory())))
                && (!(cards.get(1).getCatagory().equals(cards.get(2).getCatagory())))))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}

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
import com.risk.model.PlayerModel;
import com.risk.model.card.Card;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

/**
 * @author DKM
 *
 */
public class CardController extends Observable implements Initializable {
	
    @FXML
    ListView<Card> yourCard;

    @FXML
    ListView<Card> tradeCard;
    
    
    private int reinforcement;
    
    ActionModel actions;
    
 
  ObservableList<Card> cardsObservableList = FXCollections.observableArrayList();
  ObservableList<Card> tradeObservableList = FXCollections.observableArrayList();
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		PlayerModel.getPlayerModel().getCurrentPlayer().getCards().add(new Card("category1"));
    	PlayerModel.getPlayerModel().getCurrentPlayer().getCards().add(new Card("category2"));

    	
      cardsObservableList.addAll(PlayerModel.getPlayerModel().getCurrentPlayer().getCards());
      actions = ActionModel.getActionModel();
      yourCard.setItems(cardsObservableList);
      tradeCard.setItems(tradeObservableList);
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
          
            	reinforcement = calculateReinforcementFromCards();   
                tradeCard.getItems().clear();
                PlayerModel.getPlayerModel().getCurrentPlayer().getCards().clear();

                for (Card c : yourCard.getItems())
                {
                    PlayerModel.getPlayerModel().getCurrentPlayer().getCards().add(c);
                }
                actions.addAction("you exchanged cards");
                setChanged();
            	notifyObservers(reinforcement);
            }
            else {
            	 PlayerModel.getPlayerModel().getCurrentPlayer().getCards().clear();
            	 for (Card c : yourCard.getItems())
                 {
                     PlayerModel.getPlayerModel().getCurrentPlayer().getCards().add(c);
                 }
            	 for(Card card: tradeCard.getItems()) {
            		 PlayerModel.getPlayerModel().getCurrentPlayer().getCards().add(card);
            		 yourCard.getItems().add(card);
            	 }
            	 
            	 tradeCard.getItems().clear();
            	 actions.addAction("Invalid cards");
            	 setChanged();
            }
        }
    }
    
    @FXML
    public void skipExchangeHandler()
    {
    	if (PlayerModel.getPlayerModel().getCurrentPlayer().getCards().size() > 5)
    	{
    		actions.addAction("5+ cards == you must exchange");
    	} 	
    	else {
    		setChanged();
    		notifyObservers();
    	}
    }

    /**
     * This method is used to calculate the extra armies earned by exchanging
     * cards
     *
     * @return The result corresponding to the total exchange time.
     */
    public int calculateReinforcementFromCards()
    {
        int currentExchange = MapModel.getMapModel().getExchangeTime();
        int reinforcement = (currentExchange + 1) * 5;
        MapModel.getMapModel().setExchangeTime(currentExchange + 1);
        return reinforcement;
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

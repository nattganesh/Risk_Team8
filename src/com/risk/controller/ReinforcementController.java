/**
 * Necessary for handling business logic for Reinforcement phase.
 *
 * @author DKM
 *
 */
package com.risk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import com.risk.model.player.Player;
import com.risk.model.GamePhaseModel;
import com.risk.model.MapModel;
import com.risk.model.PlayerModel;
import com.risk.model.card.Card;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;


/**
 *
 * @see javafx.fxml.Initializable
 *
 */
public class ReinforcementController implements Initializable {
	public int reinforcement;
	public int TradeInCard = 0;
	public int ContinentControl = 0;
	public boolean controlPoint = false;
	public boolean occupiedTerritory = false;
	public ArrayList<String> cards = new ArrayList<>();

	/**
	 * @see javafx.fxml.XML
	 */
	@FXML
	FlowPane fPane;

	@FXML
	Label armyAvailable;

	@FXML
	TextField inputArmy;

	@FXML
	Label playerId;

	@FXML
	ListView<Country> countryId;
	@FXML
	Button nextPlayer;

	@FXML
	Label inputArmyError;

	@FXML
	Label CardMessage;

	@FXML
	ListView<Card> yourCard;

	@FXML
	ListView<Card> tradeCard;
	
	@FXML
	TextField ArmyCount;
	
	@FXML
	ListView<String> reinforcementMessage;

	ObservableList<Card> cardsObservableList = FXCollections.observableArrayList();
	ObservableList<Card> tradeObservableList = FXCollections.observableArrayList();
	ObservableList<Country> territoryObservableList = FXCollections.observableArrayList();
	ObservableList<String> messageObservableList = FXCollections.observableArrayList();
	
	

	/**
	 * This is the constructor for the reinforcement controller
	 */
	public ReinforcementController() {
	}

	/**
	 * This method is data binding for connection between controller and UI. It also
	 * sets up observable list, in which the view listens for changes and update its
	 * view. 
	 *
	 *
	 * @see javafx.fxml.Initializable.initialize
	 * @see javafx.beans.value.ChangeListener;
	 * @see javafx.beans.value.ObservableValue;
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		playerId.setText(PlayerModel.getPlayerModel().getCurrentPlayer().getName());

		calculateReinforcement();
		armyAvailable.setText("Army: " + Integer.toString(getReinforcement()));
		
//		PlayerModel.getPlayerModel().getCurrentPlayer().getCards().add(new Card("cate1", "blue"));
//		PlayerModel.getPlayerModel().getCurrentPlayer().getCards().add(new Card("cate1", "blueadsf"));
//		PlayerModel.getPlayerModel().getCurrentPlayer().getCards().add(new Card("cate2", "blue"));
//		PlayerModel.getPlayerModel().getCurrentPlayer().getCards().add(new Card("cate2", "blueadsf"));
//		PlayerModel.getPlayerModel().getCurrentPlayer().getCards().add(new Card("cate3", "blueadsf"));
//		PlayerModel.getPlayerModel().getCurrentPlayer().getCards().add(new Card("cate3", "blueadsf"));
//		PlayerModel.getPlayerModel().getCurrentPlayer().getCards().add(new Card("cate4", "blueadsf"));
//		PlayerModel.getPlayerModel().getCurrentPlayer().getCards().add(new Card("cate4", "blueadsf"));
		cardsObservableList.addAll(PlayerModel.getPlayerModel().getCurrentPlayer().getCards());
	
		

		yourCard.setItems(cardsObservableList);
		tradeCard.setItems(tradeObservableList);

		territoryObservableList.addAll(PlayerModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries());

		countryId.setItems(territoryObservableList);
		countryId.setCellFactory(param -> new ListCell<Country>() {
			@Override
			protected void updateItem(Country country, boolean empty) {
				super.updateItem(country, empty);
				if (empty || country == null || country.getName() == null) {
					setText(null);
				} else {
					setText(country.getName());
				}
			}
		});
		
		reinforcementMessage.setItems(messageObservableList);


		inputArmy.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					inputArmy.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}
	@FXML
	public void territoryHandler() {
		if (countryId.getSelectionModel().getSelectedItem() != null) {
			ArmyCount.setText(Integer.toString(countryId.getSelectionModel().getSelectedItem().getArmyCount()));
		}
	}
	
	/**
	 * sets the number of available army to your occupied country
	 */
	@FXML
	public void setArmy() {
		int Armyinput = 0;
		if (!inputArmy.getText().trim().isEmpty() && getReinforcement() != 0
				&& countryId.getSelectionModel().getSelectedItem() != null) {

			Armyinput = Integer.parseInt(inputArmy.getText());
			if (Armyinput <= getReinforcement()) {
				
				for (Country c : territoryObservableList) {
					if (c.getName().equals(countryId.getSelectionModel().getSelectedItem().getName())) {
						c.setArmyCount(Armyinput);
						setReinforcement(Armyinput);				
						ArmyCount.setText(Integer.toString(countryId.getSelectionModel().getSelectedItem().getArmyCount()));
						messageObservableList.add("Added " + Armyinput + " Army to " + c.getName());
						TradeInCard = 0;
						break;
					}
				}	
			}
		} 
		armyAvailable.setText(
				"Army: " + Integer.toString(getReinforcement()));
	}

	@FXML
	/**
	 * Method to set up the Attack.fxml view and set the controller
	 * (AttackController) for the view. Then, changes the scene on the stage to send
	 * user to the attack phase.
	 *
	 * @param event eventlistener for button clicked event
	 * @throws IOException Exception thrown when view is not found
	 */
	public void goToAttackPhase(ActionEvent event) throws IOException {
		if (getReinforcement() > 0 ) { // change this
		   messageObservableList.add("place all your army");
		}
		else if (PlayerModel.getPlayerModel().getCurrentPlayer().getCards().size() >= 5) {
			 messageObservableList.add("you got 5+ cards");
		}
		else {
			reinforcement = 0;
			TradeInCard = 0;
			
			GamePhaseModel.getGamePhaseModel().setPhase("attack");
		}
	}

	@FXML
	public void tradeCard() {
		if (tradeCard.getItems().size() == 3) {
			if (cardValidation()) {
				tradeCard.getItems().clear();
				System.out.println("card size before: " + PlayerModel.getPlayerModel().getCurrentPlayer().getCards().size());
				PlayerModel.getPlayerModel().getCurrentPlayer().getCards().clear();
				
				for (Card c : yourCard.getItems()) {
					
					PlayerModel.getPlayerModel().getCurrentPlayer().getCards().add(c);
				}
				System.out.println("card size after : " + PlayerModel.getPlayerModel().getCurrentPlayer().getCards().size());
				
			}
		}
	}

	@FXML
	public void yourCardHandler() {
		if (yourCard.getSelectionModel().getSelectedItem() != null && tradeCard.getItems().size() < 3) {
			Card card = yourCard.getSelectionModel().getSelectedItem();
			tradeCard.getItems().add(card);
			yourCard.getItems().remove(card);

		}
	}

	@FXML
	public void yourTradeHandler() {
		if (tradeCard.getSelectionModel().getSelectedItem() != null) {
			Card card = tradeCard.getSelectionModel().getSelectedItem();
			yourCard.getItems().add(card);
			tradeCard.getItems().remove(card);

		}
	}

	public boolean cardValidation() {
		ObservableList<Card> cards = FXCollections.observableArrayList();
		cards = tradeCard.getItems();

		if (((cards.get(0).getCatagory().equals(cards.get(1).getCatagory()))
				&& (cards.get(0).getCatagory().equals(cards.get(2).getCatagory())))
				|| ((!(cards.get(0).getCatagory().equals(cards.get(1).getCatagory())))
						&& (!(cards.get(0).getCatagory().equals(cards.get(2).getCatagory())))
						&& (!(cards.get(1).getCatagory().equals(cards.get(2).getCatagory()))))) {

			setReinforcementTradeInCard();
			MapModel.getMapModel().setExchangeTime();
			calculateReinforcement();
			tradeCard.getItems().clear();

			return true;
		} else {
			return false;
		}
	}

	public int getReinforcementOccupiedTerritory() {
		if (!occupiedTerritory) {
			occupiedTerritory = true;
			return (int) Math.floor(PlayerModel.getPlayerModel().getCurrentPlayer().numbOccupied() / 3);
		}
		return 0;
	}

	public int getReinforcementContinentControl() {
		if (!controlPoint) {
			controlPoint = true;
			return ContinentControl;
		}
		return 0;
	}

	public int getReinforcementTradeInCard() {
		
		return TradeInCard;
	}

	public void setReinforcementTradeInCard() {
		TradeInCard = TradeInCard + (MapModel.getMapModel().getExchangeTime() + 1) * 5;
	}

	public void calculateReinforcement() {
		int numbArmies;
		numbArmies = getReinforcementOccupiedTerritory() + getReinforcementContinentControl()
				+ getReinforcementTradeInCard();
		System.out.println(numbArmies);
		if (numbArmies < 3) {
			reinforcement = 3;
		
		} else {
			reinforcement = numbArmies;
		}
		
		armyAvailable.setText("Army: " + Integer.toString(getReinforcement()));
	}

	public int getReinforcement() {
		return reinforcement;
	}
	public void setReinforcement(int i) {
		reinforcement = reinforcement - i;
	}
}

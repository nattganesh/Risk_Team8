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

/**
 *
 * @see javafx.fxml.Initializable
 *
 */
public class ReinforcementController implements Initializable {
	public int reinforcement;
	public int TradeInCard;
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

	ObservableList<Card> cardsObservableList = FXCollections.observableArrayList();
	ObservableList<Card> tradeObservableList = FXCollections.observableArrayList();

	/**
	 * This is the constructor for the reinforcement controller
	 */
	public ReinforcementController() {
	}

	/**
	 * This method is data binding for connection between controller and UI. It also
	 * sets up observable list, in which the view listens for changes and update its
	 * view.
	 *s
	 *
	 *
	 * @see javafx.fxml.Initializable.initialize
	 * @see javafx.beans.value.ChangeListener;
	 * @see javafx.beans.value.ObservableValue;
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		playerId.setText(getName());
		calculateReinforcement();
		armyAvailable.setText("Army: " + Integer.toString(getReinforcement()));
		cardsObservableList.addAll(PlayerModel.getPlayerModel().getCurrentPlayer().getCards());
		cardsObservableList.add(new Card("cate1", "blue"));
		cardsObservableList.add(new Card("cate1", "blueadsf"));
		cardsObservableList.add(new Card("cate2", "blue"));
		cardsObservableList.add(new Card("cate2", "blueadsf"));
		cardsObservableList.add(new Card("cate3", "blueadsf"));
		cardsObservableList.add(new Card("cate3", "blueadsf"));
		cardsObservableList.add(new Card("cate4", "blueadsf"));
		cardsObservableList.add(new Card("cate4", "blueadsf"));
		cardsObservableList.add(new Card("cate4", "blueadsf"));

		yourCard.setItems(cardsObservableList);
		tradeCard.setItems(tradeObservableList);

		initializeTerritory();
		initializeArmyField();
	}

	@FXML
	public void initializeArmyField() {
		inputArmy.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					inputArmy.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}

	@FXML
	public void initializeTerritory() {
		countryId.setItems(PlayerModel.getPlayerModel().getTerritory());
		countryId.setCellFactory(param -> new ListCell<Country>() {
			@Override
			protected void updateItem(Country country, boolean empty) {
				super.updateItem(country, empty);
				if (empty || country == null || country.getName() == null) {
					setText(null);
				} else {
					setText(country.getName() + " (" + country.getArmyCount() + ")");
				}
			}
		});
	}

	/**
	 * sets the number of available army to your occupied country
	 */
	@FXML
	public void setArmy() {
		int Armyinput = 0;
		if (!inputArmy.getText().equals("") && PlayerModel.getPlayerModel().getCurrentPlayer().getReinforcement() != 0
				&& countryId.getSelectionModel().getSelectedItem() != null) {

			Armyinput = Integer.parseInt(inputArmy.getText());
			for (Country country : PlayerModel.getPlayerModel().getTerritory()) {
				if (country.equals(countryId.getSelectionModel().getSelectedItem())) {
					country.setArmyCount(Armyinput);
					PlayerModel.getPlayerModel().updateCurrentTerritory();
					break;
				}
			}
			PlayerModel.getPlayerModel().getCurrentPlayer().setReinforcement(Armyinput);
			armyAvailable.setText(
					"Army: " + Integer.toString(PlayerModel.getPlayerModel().getCurrentPlayer().getReinforcement()));

		}

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
		if (PlayerModel.getPlayerModel().getCurrentPlayer().getReinforcement() > 0) {
		} else {
			GamePhaseModel.getGamePhaseModel().setPhase("attack");
		}
	}

	@FXML
	public void tradeCard() {
		if (cardValidation()) {
			tradeCard.getItems().clear();
			PlayerModel.getPlayerModel().getCurrentPlayer().getCards().clear();
			for (Card c : yourCard.getItems()) {
				PlayerModel.getPlayerModel().getCurrentPlayer().getCards().add(c);
				System.out.println(c.getCatagory());
			}
		}
	}

	@FXML
	public void yourCardHandler() {
		if (yourCard.getSelectionModel().getSelectedItem() != null && tradeCard.getItems().size() < 3) {
			Card card = yourCard.getSelectionModel().getSelectedItem();

			tradeCard.getItems().add(card);
			yourCard.getItems().remove(card);

//        	for (Card c : PlayerModel.getPlayerModel().getCurrentPlayer().getCards()) {
//        		System.out.println(c.getCatagory());
//        	} get nothing means we need to set the Model

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
			
			return true;
		} else {
			return false;
		}
	}

	/**
	 * gets the name of current player in the game
	 *
	 * @return it returns name of current player
	 */
	public String getName() {
		return PlayerModel.getPlayerModel().getCurrentPlayer().getName();
	}

	public int getReinforcementOccupiedTerritory() {
		return (int) Math.floor(PlayerModel.getPlayerModel().getCurrentPlayer().numbOccupied() / 3);
	}

	public int getReinforcementContinentControl() {
		return 0;
	}

	public int getReinforcementTradeInCard() {
		return TradeInCard;
	}

	public void setReinforcementTradeInCard() {
		TradeInCard += (MapModel.getMapModel().getExchangeTime() + 1) * 5;
		System.out.println(TradeInCard);
	}

	public void calculateReinforcement() {
		int numbArmies;
		numbArmies = getReinforcementOccupiedTerritory() + getReinforcementContinentControl()
				+ getReinforcementTradeInCard();
		if (numbArmies < 3) {
			reinforcement = 3;
		}
		reinforcement = numbArmies;
		armyAvailable.setText("Army: " + Integer.toString(getReinforcement()));
	}

	public int getReinforcement() {		
		return reinforcement;
	}

}

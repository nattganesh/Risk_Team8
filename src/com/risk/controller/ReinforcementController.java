/**
 * Necessary for handling business logic for Reinforcement phase. 
 * 
 * @author DKM
 * 
 */
package com.risk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.risk.map.Country;
import com.risk.model.GamePhaseModel;
import com.risk.model.MapModel;
import com.risk.model.PlayerModel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * 
 * @see javafx.fxml.Initializable
 * 	
 */
public class ReinforcementController extends Observable implements Initializable  {

	private PlayerModel players;
	private MapModel maps;
	private GamePhaseModel gamephase;


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
	
	/**
	 * This is the constructor for the reinforcement controller
	 */
	public ReinforcementController(GamePhaseModel game, PlayerModel p, MapModel m) {
		gamephase = game;
		players = p;
		maps = m;
	
	}
	/**
	 * This method is data binding for connection between controller and UI. 
	 * It also sets up observable list, in which the view listens for changes and update its view.
	 * 
	 * 
	 * 
	 * @see javafx.fxml.Initializable.initialize
	 * @see javafx.beans.value.ChangeListener;
	 * @see javafx.beans.value.ObservableValue;
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		playerId.setText(getName());
		armyAvailable.setText("Army available => " + Integer.toString(players.getCurrentPlayer().calculateReinforcement()));
		playerId.setPadding(new Insets(0, 0, 0, 5));
		
		inputArmy.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					inputArmy.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		countryId.setItems(players.getCurrentPlayerCountryObs());
		countryId.setCellFactory(param -> new ListCell<Country>() {
			@Override
			protected void updateItem(Country item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null || item.getName() == null) {
					setText(null);
				} else {
					setText(item.getName() + " => Army: " + item.getArmyCount());

				}
			}	
		});
		
	}
	/**
	 * gets the name of current player in the game
	 * 
	 * @return it returns name of current player
	 */
	public String getName() {
		return "current Player: " + players.getCurrentPlayer().getName();
	}
	
	/**
	 * sets the number of available army to your occupied country
	 */
	public void setArmy() {
		int Armyinput = Integer.parseInt(inputArmy.getText());

		if (players.getCurrentPlayer().getReinforcement() == 0) {
			inputArmyError.setText("0 available army");
		} else if (countryId.getSelectionModel().getSelectedItem() == null
				|| Armyinput > players.getCurrentPlayer().getReinforcement() || Armyinput <= 0) {

			inputArmyError.setText("Invalid Entry");
		} else {
			for (Country c : players.getCurrentPlayer().getOccupiedCountries()) {
				if (c.equals(countryId.getSelectionModel().getSelectedItem())) {
					c.setArmyCount(Armyinput);
					players.setView();
					inputArmyError.setText("sucessful");
					break;
				}
			}
			players.getCurrentPlayer().setReinforcement(Armyinput);
			armyAvailable.setText(
					"Army available => " + Integer.toString(players.getCurrentPlayer().getReinforcement()));
		}
	}
	/**
	 * Method to set up the Attack.fxml view and set the controller (AttackController) for the view. 
	 * Then, changes the scene on the stage to send user to the attack phase.
	 * 
	 * @param event eventlistener for button clicked event
	 * @throws IOException Exception thrown when view is not found
	 */
	
	public void goToAttackPhase(ActionEvent event) throws IOException  {
		if (players.getCurrentPlayer().getReinforcement() > 0) {
		} 
		else {
			gamephase.setPhase("attack");
		}
	}

}

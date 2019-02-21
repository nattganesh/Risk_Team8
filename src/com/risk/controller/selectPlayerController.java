/**
 * This class is the Controller for SelectPlayer.fxml, where users can select number of players.
 * 
 * 
 * @author DKM
 * @version 1.0
 * @see javafx.fxml.Initializable
 */
package com.risk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.risk.army.Player;
import com.risk.army.PlayerController;
import com.risk.map.Country;
import com.risk.model.Model;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class selectPlayerController implements Initializable {

	private ArrayList<Country> countryButton;
	private PlayerController pController;
	private Model model;

	@FXML
	ComboBox<String> playerDropDown;

	@FXML
	Button StartGame;

	public selectPlayerController(Model m) {
		model = m;
	}

	/**
	 * Add 2-6 player in ComboBox component
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		playerDropDown.getItems().addAll("2", "3", "4", "5",
				"6");
	}
	/**
	 * 
	 * @return model gets the model that is passed to this controller
	 */
	public Model getModel() {
		return model;
	}
	/**
	 * sets the number of players
	 * 
	 * @param numberOfPlayer the number of player
	 */
	public void setPlayers(int numberOfPlayer) {
		while (numberOfPlayer > 0) {
			getModel().addPlayer(new Player(Player.PLAYERCOLOR[numberOfPlayer])); 
			numberOfPlayer--;
		}
	}
	/**
	 * Sets the number of player, assign countries to each player and calculate round robin, then load the Reinforcement scene
	 * 
	 * @param eventlistener for button clicked event
	 * @throws IOException the exception thrown for when .fxml not found
	 * @see javafx.event.ActionEvent
	 */
	public void StartGame(ActionEvent event) throws IOException {
		PlayerController pController = new PlayerController(getModel());
		if (playerDropDown.getSelectionModel().getSelectedItem() != null){
			
			pController.setPlayers(Integer.parseInt(playerDropDown.getSelectionModel().getSelectedItem()));
			pController.setStartingPoints();
			pController.assignCountriesToPlayers();
			pController.determinePlayersStartingOrder();
			
			ReinforcementController rController = new ReinforcementController(getModel());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/risk/view/Reinforcement.fxml"));
			loader.setController(rController);
			Parent root = loader.load();
			Scene ReinforcementScene = new Scene(root);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(ReinforcementScene);
			window.show();
			
		}
	}

}

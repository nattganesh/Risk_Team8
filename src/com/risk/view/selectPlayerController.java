/**
 * 
 */
package com.risk.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.risk.army.Player;
import com.risk.army.PlayerController;
import com.risk.map.Country;
import com.risk.run.Model;

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

/**
 * @author DKM
 *
 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		playerDropDown.getItems().addAll("2", "3", "4", "5",
				"6");

	}

	public Model getModel() {
		return model;
	}
	public void setPlayers(int numberOfPlayer) {
		while (numberOfPlayer > 0) {
			getModel().addPlayer(new Player(Player.PLAYERCOLOR[numberOfPlayer])); 
			numberOfPlayer--;
		}
	}
	public void StartGame(ActionEvent event) throws IOException {
		PlayerController pController = new PlayerController(getModel());
		if (playerDropDown.getSelectionModel().getSelectedItem() != null){
			pController.setPlayers(Integer.parseInt(playerDropDown.getSelectionModel().getSelectedItem()));
			pController.setStartingPoints();
			pController.assignCountriesToPlayers();
			pController.determinePlayersStartingOrder();
			
			ReinforcementController rController = new ReinforcementController(getModel());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Reinforcement.fxml"));
			loader.setController(rController);
			Parent root = loader.load();
			Scene ReinforcementScene = new Scene(root);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(ReinforcementScene);
			window.show();
		}
	}

}

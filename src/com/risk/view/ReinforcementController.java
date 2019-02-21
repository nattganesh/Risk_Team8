/**
 * Necessary for handling business logic for Reinforcement phase. 
 * 
 * @author DKM
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
 * 
 * @see javafx.fxml.Initializable
 * 	
 */
public class ReinforcementController implements Initializable {

	private ArrayList<Country> countryButton;
	private PlayerController pController;
	private Model model;

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
	 * 
	 * @param m model that is passed from previous view
	 */
	public ReinforcementController(Model m) {
		model = m;
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
		armyAvailable.setText("Army available => " + Integer.toString(model.getCurrentPlayer().getReinforcement()));
		playerId.setPadding(new Insets(0, 0, 0, 5));
		inputArmy.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					inputArmy.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		countryId.setItems(model.getCurrentPlayerCountryObs());
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
		return "current Player: " + model.getCurrentPlayer().getName();
	}
	
	/**
	 * sets the number of available army to your occupied country
	 */
	public void setArmy() {
		int Armyinput = Integer.parseInt(inputArmy.getText());

		if (model.getCurrentPlayer().getAvailableReinforcement() == 0) {
			inputArmyError.setText("0 available army");
		} else if (countryId.getSelectionModel().getSelectedItem() == null
				|| Armyinput > model.getCurrentPlayer().getAvailableReinforcement() || Armyinput <= 0) {

			inputArmyError.setText("Invalid Entry");
		} else {
			for (Country c : model.getCurrentPlayer().getOccupiedCountries()) {
				if (c.equals(countryId.getSelectionModel().getSelectedItem())) {
					c.setArmyCount(Armyinput);
					model.setView();
					inputArmyError.setText("sucessful");
					break;
				}
			}
			model.getCurrentPlayer().setReinforcement(Armyinput);
			armyAvailable.setText(
					"Army available => " + Integer.toString(model.getCurrentPlayer().getAvailableReinforcement()));
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
		if (model.getCurrentPlayer().getAvailableReinforcement() > 0) {

		} 
		else {
			AttackController attackController = new AttackController(model);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Attack.fxml"));
			loader.setController(attackController);
			Parent root = loader.load();
			Scene FortificationScene = new Scene(root);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(FortificationScene);
			window.show();
		}
	}
}

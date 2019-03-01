/**
 *
 */
package com.risk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.risk.model.GamePhaseModel;
import com.risk.model.MapModel;
import com.risk.model.PlayerModel;
import com.risk.model.card.Card;
import com.risk.model.map.Country;
import com.risk.model.player.Player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * @author DKM
 *
 */
public class FortificationController implements Initializable {

	@FXML
	ListView<Country> Territory;

	@FXML
	ListView<Country> Adjacent;

	@FXML
	TextField moveField;

	ObservableList<Country> territoryObservableList = FXCollections.observableArrayList();
	ObservableList<Country> adjacentObservableList = FXCollections.observableArrayList();
	private boolean fortification = false;
	private Player currentPlayer;

	public FortificationController() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		currentPlayer = PlayerModel.getPlayerModel().getCurrentPlayer();
		territoryObservableList.addAll(PlayerModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries());
		Territory.setItems(territoryObservableList);
		Territory.setCellFactory(param -> new ListCell<Country>() {
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
		Adjacent.setItems(adjacentObservableList);
		Adjacent.setCellFactory(param -> new ListCell<Country>() {
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

	@FXML
	public void territoryHandler() {

		if (Territory.getSelectionModel().getSelectedItem() != null) {
			Adjacent.getItems().clear();
			for (Country country : Territory.getSelectionModel().getSelectedItem().getConnectedCountries()) {
				if (country.getRuler().getName().equals(currentPlayer.getName())) {
					Adjacent.getItems().add(country);
				}
			}
		}
	}

	@FXML
	public void moveHandler() {
		if (Territory.getSelectionModel().getSelectedItem() != null
				&& Adjacent.getSelectionModel().getSelectedItem() != null
				&& Territory.getSelectionModel().getSelectedItem().getArmyCount() > 1
				&& !moveField.getText().trim().isEmpty() && !fortification) {
			int Armyinput = Integer.parseInt(moveField.getText());
			System.out.println(Armyinput);
			if (Armyinput <= Territory.getSelectionModel().getSelectedItem().getArmyCount() - 1) {
				System.out.println("here : " + Armyinput);
				Territory.getSelectionModel().getSelectedItem()
						.setArmyCount(Territory.getSelectionModel().getSelectedItem().getArmyCount() - Armyinput);

				Adjacent.getSelectionModel().getSelectedItem()
						.setArmyCount(Adjacent.getSelectionModel().getSelectedItem().getArmyCount() + Armyinput);

				
				fortification = true;
				System.out.println(fortification);
			}
		}
	}

	public void onNextPlayer(ActionEvent event) throws IOException {
		if (fortification == true) {
			System.out.println(fortification);
			saveToModel();
			PlayerModel.getPlayerModel().IncrementPlayerIndex();
			GamePhaseModel.getGamePhaseModel().setPhase("reinforcement");
		}
	}
	public void saveToModel() {
		PlayerModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries().clear();
		PlayerModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries().addAll(territoryObservableList);
	}

}

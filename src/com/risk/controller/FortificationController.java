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
	ListView<String> messageFortification;
	
	@FXML
	TextField moveField;

	@FXML
	TextField TerritoryArmy;
	
	@FXML
	TextField AdjacentArmy;
	
	
	
	ObservableList<Country> territoryObservableList = FXCollections.observableArrayList();
	ObservableList<Country> adjacentObservableList = FXCollections.observableArrayList();
	ObservableList<String> messageObservableList = FXCollections.observableArrayList();
	
	private boolean fortification = false;


	public FortificationController() {

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		
		messageFortification.setItems(messageObservableList);
		
		territoryObservableList.addAll(PlayerModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries());
		Territory.setItems(territoryObservableList);
		Territory.setCellFactory(param -> new ListCell<Country>() 
		{
			@Override
			protected void updateItem(Country country, boolean empty)
			{
				super.updateItem(country, empty);
				if (empty || country == null || country.getName() == null) 
				{
					setText(null);
				}
				else
				{
					setText(country.getName());
				}
			}
		});
		Adjacent.setItems(adjacentObservableList);
		Adjacent.setCellFactory(param -> new ListCell<Country>()
		{
			@Override
			protected void updateItem(Country country, boolean empty)
			{
				super.updateItem(country, empty);
				if (empty || country == null || country.getName() == null) 
				{
					setText(null);
				} 
				else 
				{
					setText(country.getName());
				}
			}
		});

	}

	@FXML
	public void territoryHandler() 
	{

		if (Territory.getSelectionModel().getSelectedItem() != null) 
		{
			Adjacent.getItems().clear();
			AdjacentArmy.setText("");
			for (Country connectedTerritory : Territory.getSelectionModel().getSelectedItem().getConnectedCountries()) 
			{
				if (connectedTerritory.getRuler().getName().equals(PlayerModel.getPlayerModel().getCurrentPlayer().getName())) 
				{
					Adjacent.getItems().add(connectedTerritory);
				}
			}
			TerritoryArmy.setText(Integer.toString(Territory.getSelectionModel().getSelectedItem().getArmyCount()));
		}
	}
	
	@FXML
	public void adjacentHandler() 
	{
	
		if (Adjacent.getSelectionModel().getSelectedItem() != null) 
		{
			AdjacentArmy.setText(Integer.toString(Adjacent.getSelectionModel().getSelectedItem().getArmyCount()));
		}
	}

	@FXML
	public void moveHandler() 
	{
		if (Territory.getSelectionModel().getSelectedItem() != null
				&& Adjacent.getSelectionModel().getSelectedItem() != null
				&& Territory.getSelectionModel().getSelectedItem().getArmyCount() > 1
				&& !moveField.getText().trim().isEmpty() && !fortification) 
		{
			int Armyinput = Integer.parseInt(moveField.getText());
			if (Armyinput <= Territory.getSelectionModel().getSelectedItem().getArmyCount() - 1) 
			{

				Territory.getSelectionModel().getSelectedItem()
						.reduceArmyCount(Armyinput);

				Adjacent.getSelectionModel().getSelectedItem()
						.setArmyCount(Armyinput);
				
				AdjacentArmy.setText(Integer.toString(Adjacent.getSelectionModel().getSelectedItem().getArmyCount()));
				TerritoryArmy.setText(Integer.toString(Territory.getSelectionModel().getSelectedItem().getArmyCount()));
				fortification = true;
				
			}
			else 
			{
				messageObservableList.add("You don't have that many army");
			}
		} 
		else 
		{
			messageObservableList.add("Invalid Selection");
		}
	}

	public void onNextPlayer(ActionEvent event) throws IOException 
	{
		if (fortification == true) 
		{
			PlayerModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries().clear();
			PlayerModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries().addAll(territoryObservableList);
			PlayerModel.getPlayerModel().IncrementPlayerIndex();
			GamePhaseModel.getGamePhaseModel().setPhase("reinforcement");
		} 
		else 
		{
			messageObservableList.add("you need to fortify");
		}
	}
}

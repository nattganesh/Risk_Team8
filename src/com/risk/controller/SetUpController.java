/**
 * Necessary for handling business logic for Reinforcement phase.
 *
 * @author Tianyi
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
import com.risk.model.PlayerPhaseModel;
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
 * @see javafx.fxml.Initializable
 */
public class SetUpController implements Initializable 
{
	public int TotalArmies;
	public ArrayList<String> cards = new ArrayList<>();
	private boolean setUp = false;
	/**
	 * @see javafx.fxml.XML
	 */
	@FXML
	FlowPane fPane;

	@FXML
	Label armyAvailable;

	@FXML
	Label playerId;

	@FXML
	ListView<Country> countryId;
	@FXML
	Button NextRound;

	@FXML
	Button addArmy;

	@FXML
	TextField ArmyCount;

	@FXML
	ListView<String> setupMessage;

	ObservableList<Country> territoryObservableList = FXCollections.observableArrayList();
	ObservableList<String> messageObservableList = FXCollections.observableArrayList();

	/**
	 * This is the constructor for the setUp controller
	 */
	public SetUpController() 
	{
	}

	/**
	 * This method is data binding for connection between controller and UI. It also
	 * sets up observable list, in which the view listens for changes and update its
	 * view.
	 *
	 *
	 * @see javafx.fxml.Initializable.initialize
	 * @see javafx.beans.value.ObservableValue;
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) 
	{
		playerId.setText(PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getName());
		TotalArmies = PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getStartingP();
		armyAvailable.setText("Army: " + Integer.toString(getArmies()));
		territoryObservableList.addAll(PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries());
		countryId.setItems(territoryObservableList);
		countryId.setCellFactory(param -> new ListCell<Country>() 
		{
			@Override
			protected void updateItem(Country country, boolean empty) 
			{
				super.updateItem(country, empty);
				if (empty || country == null || country.getName() == null) 
				{
					setText(null);
				} else {
					setText(country.getName());
				}
			}
		});

		setupMessage.setItems(messageObservableList);

	}

	@FXML
	public void territoryHandler() 
	{
		if (countryId.getSelectionModel().getSelectedItem() != null) 
		{
			ArmyCount.setText(Integer.toString(countryId.getSelectionModel().getSelectedItem().getArmyCount()));
		}
	}

	/**
	 * This method sets the number of available army to your occupied country
	 */
	@FXML
	public void setArmy() 
	{
		if (getArmies() == 0) 
		{
			messageObservableList.add("You have no armies left");
		} else if (countryId.getSelectionModel().getSelectedItem() == null) 
		{
			messageObservableList.add("Please choose a country first");
		} else if(setUp)
		{
			messageObservableList.add("You already place one army");
		}
		else{
			for (Country c : territoryObservableList) 
			{
				if (c.getName().equals(countryId.getSelectionModel().getSelectedItem().getName())) 
				{
					if ((c.getArmyCount() == 0) || checkIfEachCountryHasOneArmy()) 
					{
						c.setArmyCount(1);
						setStartingPoints();
						ArmyCount.setText(
								Integer.toString(countryId.getSelectionModel().getSelectedItem().getArmyCount()));
						messageObservableList.add("Added 1 Army to " + c.getName());
						setUp = true;
						break;
					}
				}
			}
		}
		armyAvailable.setText("Army: " + Integer.toString(getArmies()));
	}

	/**
	 * This method is used to get the number of total armies
	 * 
	 * @return The the number of total armies
	 */
	public int getArmies() 
	{
		return TotalArmies;
	}

	/**
	 * This method is used to reduce the number of available armies for
	 * reinforcement
	 * 
	 */
	public void setStartingPoints() 
	{
		TotalArmies--;
		PlayerPhaseModel.getPlayerModel().getCurrentPlayer().setStartingPoints(TotalArmies);
	}

	/**
	 * This method is used to check if each country occupied has one army
	 * 
	 * @return true if each country already has one army. Otherwise, return false
	 */
	public boolean checkIfEachCountryHasOneArmy() 
	{
		int i = 1;
		for (Country c : territoryObservableList) 
		{
			if (c.getArmyCount() == 0) 
			{
				i = 0;
			}
		}
		if (i == 1) 
		{
			return true;
		} else 
		{
			return false;
		}
	}

	@FXML
	/**
	 * Method to set up the Reinforcement.fxml or SetUp.fxml view and set the
	 * controller for the view. Then, changes the scene on the stage to send user to
	 * the reinforcement phase or setup phase.
	 *
	 * @param event
	 *            eventlistener for button clicked event
	 * @throws IOException
	 *             Exception thrown when view is not found
	 */
	public void next(ActionEvent event) throws IOException 
	{
		int currentIndex = PlayerPhaseModel.getPlayerModel().getPlayerIndex();
		boolean isAnyPlayerPlacedAllArmies = true;
		for(Player p : PlayerPhaseModel.getPlayerModel().getPlayers())
        {
        	if(p.getStartingP()!=0) 
        		isAnyPlayerPlacedAllArmies = false;
        }
		if(setUp) {
			if (isAnyPlayerPlacedAllArmies) 
			{
				PlayerPhaseModel.getPlayerModel()
						.setPlayerIndex((currentIndex + 1) % PlayerPhaseModel.getPlayerModel().getNumberOfPlayer());
				GamePhaseModel.getGamePhaseModel().setPhase("setup complete");  
				GamePhaseModel.getGamePhaseModel().setPhase("reinforcement");
			} else 
			{
				PlayerPhaseModel.getPlayerModel()
						.setPlayerIndex((currentIndex + 1) % PlayerPhaseModel.getPlayerModel().getNumberOfPlayer());
				GamePhaseModel.getGamePhaseModel().setPhase("setup");
			}
		}
		else
		{
			messageObservableList.add("Please place one army in your country");
		}
	}
}

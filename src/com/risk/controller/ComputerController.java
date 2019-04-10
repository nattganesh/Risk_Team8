/**
 * Necessary for handling computer view during computer turn
 * @author DKM
 * @version 3.0
 * 
 */

package com.risk.controller;
import java.net.URL;
import java.util.ResourceBundle;

import com.risk.model.GamePhaseModel;
import com.risk.model.PlayerPhaseModel;
import com.risk.model.card.Card;
import com.risk.model.map.Country;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class ComputerController implements Initializable 
{

	ObservableList<Country> territoryObservableList = FXCollections.observableArrayList();
    ObservableList<Country> adjacentEnemyObservableList = FXCollections.observableArrayList();
    ObservableList<Country> adjacentOwnedObservableList = FXCollections.observableArrayList();
    ObservableList<Card> cardOwnedObservableList = FXCollections.observableArrayList();
	
    @FXML
    private ListView<Country> adjacentEnemy;

    @FXML
    private ListView<Country> adjacentOwned;

    @FXML
    private ListView<Country> countryId;
    
    @FXML
    private ListView<Card> cardView;
    
    @FXML
    private AnchorPane computerCardViewID;

    /**
     * This method adds computer's occupied territory
     */
    @FXML
    void territoryHandler() 
    {
    	  if (countryId.getSelectionModel().getSelectedItem() != null)
          {
              adjacentEnemyObservableList.clear();
              adjacentOwnedObservableList.clear();
              
              adjacentEnemyObservableList.addAll(countryId.getSelectionModel().getSelectedItem().getConnectedEnemy());
              adjacentOwnedObservableList.addAll(countryId.getSelectionModel().getSelectedItem().getConnectedOwned());
          }
    }

    /**
     * This is method for initializing ComputerController
     * 
     * @see javafx.fxml.Initializable
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		String name = PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getName();
		String behaviour = name.substring(0, name.length()-1);
		System.out.println(behaviour);
		if (GamePhaseModel.getGamePhaseModel().getPhase().equals("reinforcement") && behaviour.equals("AggressivePlayer"))
		{
			computerCardViewID.setVisible(true);
		}
		else 
		{
			computerCardViewID.setVisible(false);
		}
        territoryObservableList.addAll(PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries());
        cardOwnedObservableList.addAll(PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getCards());
        
        countryId.setItems(territoryObservableList);
        adjacentEnemy.setItems(adjacentEnemyObservableList);
        adjacentOwned.setItems(adjacentOwnedObservableList);
        cardView.setItems(cardOwnedObservableList);
        updateView();
        
	}
	
	/**
	 * This method is responsible for going to next phase
	 */
	public void nextComputerPhase()
	{
		if (GamePhaseModel.getGamePhaseModel().getPhase().equals("reinforcement"))
		{
			GamePhaseModel.getGamePhaseModel().setPhase("attack");
		}
		else if (GamePhaseModel.getGamePhaseModel().getPhase().equals("attack"))
		{
			GamePhaseModel.getGamePhaseModel().setPhase("fortification");
		}
		else if (GamePhaseModel.getGamePhaseModel().getPhase().equals("fortification"))
		{
			 PlayerPhaseModel.getPlayerModel().setNextPlayer();
             GamePhaseModel.getGamePhaseModel().setPhase("reinforcement");   
		}
	}

	 /**
     * This method for re-rendering the listView
     */
    public void updateView()
    {
        countryId.setCellFactory(param -> new ListCell<Country>() 
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
                    setText(country.getName() + " (" + country.getArmyCount() + ")");
                }
            }
        });

        adjacentOwned.setCellFactory(param -> new ListCell<Country>() 
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

                    setText(country.getName() + " (" + country.getArmyCount() + ")");
                }
            }
        });

        adjacentEnemy.setCellFactory(param -> new ListCell<Country>() 
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

                    setText(country.getName() + " (" + country.getArmyCount() + ")");
                }
            }
        });
        cardView.setCellFactory(param -> new ListCell<Card>() {
            @Override
            protected void updateItem(Card card, boolean empty)
            {
            	 super.updateItem(card, empty);
                 if (empty || card == null || card.getCatagory() == null)
                 {
                     setText(null);
                 }
                 else
                 {
                     setText(card.getCatagory());
                 }
            }
        });
    }
}

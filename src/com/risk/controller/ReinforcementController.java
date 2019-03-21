/**
 * Necessary for handling business logic for Reinforcement phase.
 *
 * @author DKM
 * @author Tianyi
 *
 */
package com.risk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import com.risk.model.player.Player;
import com.risk.model.ActionModel;
import com.risk.model.GamePhaseModel;
import com.risk.model.MapModel;
import com.risk.model.PlayerModel;
import com.risk.model.card.Card;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 * @see javafx.fxml.Initializable
 */
public class ReinforcementController implements Initializable{

    public int TotalReinforcement;
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
    ListView<Country> adjacentEnemy;
    
    @FXML
    ListView<Country> adjacentOwned;

    @FXML
    Pane CardPane;
    
    @FXML
    AnchorPane child;
   
    
    @FXML
    private CardController cardController; 
    
    
    ObservableList<Country> territoryObservableList = FXCollections.observableArrayList();
    ObservableList<Country> adjacentEnemyObservableList = FXCollections.observableArrayList();
    ObservableList<Country> adjacentOwnedObservableList = FXCollections.observableArrayList();
    
  
  
    PlayerModel player;
    ActionModel actions;

    /**
     * This is the constructor for the reinforcement controller
     */
    public ReinforcementController()
    {
    	
    }

    /**
     * This method is data binding for connection between controller and UI. It
     * also sets up observable list, in which the view listens for changes and
     * update its view.
     *
     *
     * @see javafx.fxml.Initializable.initialize
     * @see javafx.beans.value.ChangeListener;
     * @see javafx.beans.value.ObservableValue;
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
    	
    	
    	actions = ActionModel.getActionModel();
    	cardController.addObserver(new cardSkipObserver());
    	cardController.addObserver(new cardReinforcementObserver());
    	    	
        int reinforcementOccupied = PlayerModel.getPlayerModel().getCurrentPlayer().calculateReinforcementOccupiedTerritory();
        int reinforcementContinent =  PlayerModel.getPlayerModel().getCurrentPlayer().calculateReinforcementContinentControl();
        TotalReinforcement = reinforcementOccupied + reinforcementContinent;
        
        if (reinforcementOccupied > 0)
        {
        	actions.addAction("occupied territory == " + reinforcementOccupied);
        }
        if (reinforcementContinent > 0)
        {
        	actions.addAction("continent control reinforcement == " + reinforcementContinent);
        }
        if(TotalReinforcement < 3) 
        {
			TotalReinforcement = 3;
		}
        armyAvailable.setText("Army: " + Integer.toString(getReinforcement()));
        territoryObservableList.addAll(PlayerModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries());   
        countryId.setItems(territoryObservableList);
        adjacentEnemy.setItems(adjacentEnemyObservableList);
        adjacentOwned.setItems(adjacentOwnedObservableList);
        inputArmy.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    inputArmy.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        updateList();
    }
   
    @FXML
    public void territoryHandler()
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
     * This method sets the number of available army to your occupied country
     */
    @FXML
    public void setArmy()
    {
        int Armyinput = 0;
        if (!inputArmy.getText().trim().isEmpty() && getReinforcement() != 0
                && countryId.getSelectionModel().getSelectedItem() != null)
        {
        	Country selectedCountry = countryId.getSelectionModel().getSelectedItem();
            Armyinput = Integer.parseInt(inputArmy.getText());
            if (Armyinput <= getReinforcement())
            {                
                selectedCountry.setArmyCount(Armyinput);
                setReinforcement(Armyinput);
                updateList();
                actions.addAction("Added " + Armyinput + " Army to " + selectedCountry.getName());         
            }
            else {
            	actions.addAction("you don't have that many reinforcements");
            }
        }
        armyAvailable.setText(
                "Army: " + Integer.toString(getReinforcement()));
    }

    @FXML
    /**
     * Method to set up the Attack.fxml view and set the controller
     * (AttackController) for the view. Then, changes the scene on the stage to
     * send user to the attack phase.
     *
     * @param event eventlistener for button clicked event
     * @throws IOException Exception thrown when view is not found
     */
    public void goToAttackPhase(ActionEvent event) throws IOException
    {
        if (getReinforcement() > 0)
        {
            actions.addAction("place all your army");
        }
        else if (PlayerModel.getPlayerModel().getCurrentPlayer().getCards().size() >= 5)
        {
            actions.addAction("your got 5+ cards");
        }
        else
        {
            GamePhaseModel.getGamePhaseModel().setPhase("attack");
        }
    }

    /**
     * This method is used to get the number of total armies earned for
     * reinforcement
     *
     * @return The the number of total armies
     */
    public int getReinforcement()
    {
        return TotalReinforcement;
    }

    /**
     * This method is used to reduce the number of available armies for
     * reinforcement
     *
     * @param i The number of armies a player added to an occupied country
     */
    public void setReinforcement(int i)
    {
        TotalReinforcement = TotalReinforcement - i;
    }
    
    
    /**
     * This method observes reinforcement calculated by card
     * @author DKM
     *
     */
    private class cardReinforcementObserver implements Observer
    {
		@Override
		public void update(Observable o, Object arg) {
			if (arg != null) {
				int reinforcementFromCards = (int)arg;
				TotalReinforcement += reinforcementFromCards;
				 armyAvailable.setText(
			                "Army: " + Integer.toString(getReinforcement()));
				 
				if (PlayerModel.getPlayerModel().getCurrentPlayer().getCards().size() < 3)
				{
					child.getChildren().clear();
				}
			}
			
		}	
    }
    

    /**
     * This is method observes for skipping card exchange
     * 
     * @author DKM
     *
     */
    private class cardSkipObserver implements Observer 
    {
		@Override
		public void update(Observable o, Object arg) {
			if (arg == null) {
				child.getChildren().clear();	
				
			}
		}
    	
    }
    
    public void updateList()
    {
        countryId.setCellFactory(param -> new ListCell<Country>() {
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
                	setText(country.getName() + " ("+ country.getArmyCount() +")");
                }
            }
        });
        adjacentOwned.setCellFactory(param -> new ListCell<Country>() {
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
                
                	 setText(country.getName() + " ("+ country.getArmyCount() +")");
                }
            }
        });
        adjacentEnemy.setCellFactory(param -> new ListCell<Country>() {
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
                
                	 setText(country.getName() + " ("+ country.getArmyCount() +")");
                }
            }
        });
    }
    

}

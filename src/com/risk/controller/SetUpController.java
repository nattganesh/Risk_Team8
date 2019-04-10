/**
 * Necessary for handling business logic for Setup phase.
 *
 * @author Tianyi
 * @version 3.0
 *
 */
package com.risk.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.risk.model.map.Country;
import com.risk.model.player.Player;
import com.risk.model.ActionModel;
import com.risk.model.GamePhaseModel;
import com.risk.model.PlayerPhaseModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
    ListView<Country> countryId;
    
    @FXML
    Button NextRound;

    @FXML
    Button addArmy;

    ActionModel actions;
    ObservableList<Country> territoryObservableList = FXCollections.observableArrayList();

    /**
     * This is the constructor for the setUp controller
     */
    public SetUpController()
    {
    }

    /**
     * This method is data binding for connection between controller and UI.
     *
     * @see javafx.fxml.Initializable
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        actions = ActionModel.getActionModel();
        TotalArmies = PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getStartingPoints();
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
                }
                else
                {
                    setText(country.getName() + " (" + country.getArmyCount() + ")");
                }
            }
        });
    }

    /**
     * This method sets the number of available army to your occupied country
     */
    @FXML
    public void setArmy()
    {
        if (getArmies() == 0)
        {
            actions.addAction("You have no armies left");
        }
        else if (countryId.getSelectionModel().getSelectedItem() == null)
        {
            actions.addAction("Please choose a country first");
        }
        else if (setUp)
        {
            actions.addAction("You already place one army");
        }
        else
        {
            Country selectedCountry = countryId.getSelectionModel().getSelectedItem();
            if ((selectedCountry.getArmyCount() == 0) || checkIfEachCountryHasOneArmy(territoryObservableList))
            {
                selectedCountry.setArmyCount(1);
                setStartingPoints();
                actions.addAction("Added 1 Army to " + selectedCountry.getName());
                setUp = true;
                next();	
            }
        }
        updateView();

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
     * @param list The observableList which is used to check if each county in
     * it has one army
     * @return true if each country already has one army. Otherwise, return
     * false
     */
    public boolean checkIfEachCountryHasOneArmy(ObservableList<Country> list)
    {
        int i = 1;
        for (Country c : list)
        {
            if (c.getArmyCount() == 0)
            {
                i = 0;
            }
        }
        if (i == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Method to set up the Reinforcement.fxml or SetUp.fxml view and set the
     * controller for the view. Then, changes the scene on the stage to send
     * user to the reinforcement phase or setup phase.
     */
    public void next()
    {
        boolean isAnyPlayerPlacedAllArmies = true;
        for (Player p : PlayerPhaseModel.getPlayerModel().getPlayers())
        {
            if (p.getStartingPoints() != 0)
            {
                isAnyPlayerPlacedAllArmies = false;
            }
        }
        if (setUp)
        {
            if (isAnyPlayerPlacedAllArmies)
            {
            	PlayerPhaseModel.getPlayerModel().setNextPlayer();
                GamePhaseModel.getGamePhaseModel().setPhase("setup complete");
                GamePhaseModel.getGamePhaseModel().setPhase("reinforcement");
            }
            else
            {
            	PlayerPhaseModel.getPlayerModel().setNextPlayer();
                GamePhaseModel.getGamePhaseModel().setPhase("setup");
            }
        }
        else
        {
            actions.addAction("Please place one army in your country");
        }
    }

    /**
     * This method is necessary for updating the view of the list
     */
    public void updateView()
    {
        territoryObservableList.setAll(PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries());
    }
}

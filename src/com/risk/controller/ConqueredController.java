/**
 * This class is necessary for moving army after conquer
 *
 * @author DKM
 * @version 3.0
 * 
 *
 */
package com.risk.controller;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import com.risk.model.ActionModel;
import com.risk.model.PlayerPhaseModel;
import com.risk.model.map.Country;
import com.risk.model.player.Player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.control.TextField;

public class ConqueredController extends Observable implements Initializable 
{

    ObservableList<Country> territoryObservableList = FXCollections.observableArrayList();
    ObservableList<Country> conqueredObservableList = FXCollections.observableArrayList();

    int diceRolled;
    Country conquered;
    int moved = 0;
    Player p = PlayerPhaseModel.getPlayerModel().getCurrentPlayer();

    ActionModel actions;
    @FXML
    ListView<Country> countryOwnedID;

    @FXML
    ListView<Country> conqueredID;

    @FXML
    TextField armyCount;
    boolean move = false;

    /**
     * This method is data binding for connection between controller and UI.
     *
     * @see javafx.fxml.Initializable
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
    
    	
        move = false;
        actions = ActionModel.getActionModel();
        countryOwnedID.setItems(territoryObservableList);
        conqueredID.setItems(conqueredObservableList);
        renderView();
    }

    /**
     * this method is necessary for moving army into conquered country
     */
    @FXML
    public void moveArmyHandler()
    {
        Country reinforcement = countryOwnedID.getSelectionModel().getSelectedItem();
        Country conquered = conqueredID.getSelectionModel().getSelectedItem();
        boolean validateTerritorySelections = p.validateTerritorySelections(reinforcement, conquered);
        boolean validateTerritorysTheSame = p.validateTerritorysTheSame(reinforcement, conquered);
        if (validateTerritorySelections && !armyCount.getText().trim().isEmpty() && validateTerritorysTheSame)
        {
            int army = Integer.parseInt(armyCount.getText());
            if (army < reinforcement.getArmyCount())
            {
                moved = moved + army;
                p.fortify(reinforcement, conquered, army);
                actions.addAction("moving army");
                renderView();

                if (moved >= diceRolled)
                {
                    move = true;
                }
            }
            else
            {
                actions.addAction("invalid move");
            }
        }
        else if (!validateTerritorySelections)
        {
            actions.addAction("Choose conquered country");
        }
        else if (armyCount.getText().trim().isEmpty())
        {
            actions.addAction("please enter the number of army first");
        }
        else
        {
            actions.addAction("countries you choose should not be the same");
        }
    }

    /**
     * This method ends the conquer move When the number of army moved is equal
     * or more than the number of dice the player rolled Then it allows him to
     * end the move
     */
    @FXML
    public void moveComplete()
    {
        if (move)
        {
            moved = 0;
            setChanged();
            notifyObservers(conquered);
        }
        else
        {
            actions.addAction(diceRolled - moved  + " move remaining");
        }

    }

    /**
     * this method sets the conquered country into listview
     *
     * @param c country that is conquered
     */
    public void setConquringArmy(Country c)
    {
        territoryObservableList.clear();
        conqueredObservableList.clear();
        conqueredObservableList.add(c);
        conquered = c;
        territoryObservableList.addAll(PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries());
    }

    /**
     * This method sets the number of army to move in ComboBox
     *
     * @param roll number of dice rolled to conquer
     */
    public void setDiceRoll(int roll)
    {
        this.diceRolled = roll;
      
    }

    /**
     * This method for re-rendering the listView
     */
    public void renderView()
    {
        countryOwnedID.setCellFactory(param -> new ListCell<Country>() 
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
        conqueredID.setCellFactory(param -> new ListCell<Country>() 
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
}

/**
 * This class is necessary for moving army after conquer
 * 
 * @author DKM
 *
 */
package com.risk.controller;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.risk.model.ActionModel;
import com.risk.model.PlayerPhaseModel;
import com.risk.model.map.Country;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class ConqueredController extends Observable implements Initializable {

    ObservableList<Country> territoryObservableList = FXCollections.observableArrayList();
    ObservableList<Country> conqueredObservableList = FXCollections.observableArrayList();

    int diceRolled;
    Country conquered;
    int moved = 0;

    ActionModel actions;
    @FXML
    ListView<Country> countryOwnedID;

    @FXML
    ListView<Country> conqueredID;

    @FXML
    TextField armyCount;
    boolean move = false;

    /**
     * @see javafx.fxml.Initializable#initialize(java.net.URL,s
     * java.util.ResourceBundle)
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
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
        if (reinforcement != null && conquered != null && !armyCount.getText().trim().isEmpty())
        {
            int army = Integer.parseInt(armyCount.getText());
            if (army < reinforcement.getArmyCount())
            {
                moved = moved + army;
                reinforcement.reduceArmyCount(army);
                conquered.setArmyCount(army);
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
    }

    /**
     * This method ends the conquer move
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
            actions.addAction("please move enough army first");
        }

    }

    /**
     * this method sets the conquered country into listview
     *
     * @param c country that is conquered
     */
    public void setConquringArmy(Country c)
    {
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
        actions.addAction(diceRolled + " dice rolled");
    }

    /**
     * This method for re-rendering the listView
     */
    public void renderView()
    {
        countryOwnedID.setCellFactory(param -> new ListCell<Country>() {
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
        conqueredID.setCellFactory(param -> new ListCell<Country>() {
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

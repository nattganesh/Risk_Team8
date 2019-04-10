/**
 * This class is necessary for the controller of fortification phase
 *
 * @author DKM
 * @author Tianyi
 * @version 3.0
 */
package com.risk.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.risk.model.ActionModel;
import com.risk.model.GamePhaseModel;
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

public class FortificationController implements Initializable 
{

    @FXML
    ListView<Country> Territory;

    @FXML
    ListView<Country> Adjacent;

    @FXML
    TextField moveField;

    @FXML
    TextField TerritoryArmy;

    @FXML
    TextField AdjacentArmy;

    ObservableList<Country> territoryObservableList = FXCollections.observableArrayList();
    ObservableList<Country> adjacentObservableList = FXCollections.observableArrayList();

    private boolean fortification = false;
    ArrayList<Country> CountriesArrivedbyPath;
    ActionModel actions;
    Player p = PlayerPhaseModel.getPlayerModel().getCurrentPlayer();

    /**
     * This is a constructor of the FortificationController class
     */
    public FortificationController()
    {

    }

    /**
     * This method is data binding for connection between controller and UI.
     *
     * @see javafx.fxml.Initializable
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {

        actions = ActionModel.getActionModel();
        territoryObservableList.addAll(PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries());
        Territory.setItems(territoryObservableList);
        Adjacent.setItems(adjacentObservableList);
        updateView();

    }

    /**
     * This method is necessary for updating the view of the list
     */
    public void updateView()
    {
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

    /**
     * This method handles loading adjacent connected countries of the country
     * that current player chooses for fortification Only countries owned by the
     * player will be displayed
     */
    @FXML
    public void territoryHandler()
    {
        if (Territory.getSelectionModel().getSelectedItem() != null)
        {
            Adjacent.getItems().clear();
            AdjacentArmy.setText("");
            CountriesArrivedbyPath = new ArrayList<>();
            Adjacent.getItems().addAll(PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getCountriesArrivedbyPath(Territory.getSelectionModel().getSelectedItem(), Territory.getSelectionModel().getSelectedItem(), CountriesArrivedbyPath));
            TerritoryArmy.setText(Integer.toString(Territory.getSelectionModel().getSelectedItem().getArmyCount()));
        }
    }

    /**
     * This method handles showing the army count of the adjacent country the
     * player chooses
     */
    @FXML
    public void adjacentHandler()
    {
        if (Adjacent.getSelectionModel().getSelectedItem() != null)
        {
            AdjacentArmy.setText(Integer.toString(Adjacent.getSelectionModel().getSelectedItem().getArmyCount()));
        }
    }

    /**
     * This method handles the movement of armies selected country to adjacent
     * country
     */
    @FXML
    public void moveHandler()
    {
        Country firstCountry = Territory.getSelectionModel().getSelectedItem();
        Country secondCountry = Adjacent.getSelectionModel().getSelectedItem();
        boolean validateTerritorySelections = p.validateTerritorySelections(firstCountry, secondCountry);
        boolean validateAttackerHasEnoughArmy = p.validateAttackerHasEnoughArmy(firstCountry);
        if (validateTerritorySelections
                && validateAttackerHasEnoughArmy
                && !moveField.getText().trim().isEmpty() && !fortification)
        {
            int Armyinput = Integer.parseInt(moveField.getText());
            if (Armyinput <= 0)
            {
                actions.addAction("The number does not meet the requirement");
                moveField.clear();
            }
            else if (Armyinput <= Territory.getSelectionModel().getSelectedItem().getArmyCount() - 1)
            {
                Player p = Territory.getSelectionModel().getSelectedItem().getRuler();
                p.fortify(firstCountry, secondCountry, Armyinput);

                AdjacentArmy.setText(Integer.toString(Adjacent.getSelectionModel().getSelectedItem().getArmyCount()));
                TerritoryArmy.setText(Integer.toString(Territory.getSelectionModel().getSelectedItem().getArmyCount()));
                actions.addAction("Move Successfully");

                PlayerPhaseModel.getPlayerModel().setNextPlayer();
                GamePhaseModel.getGamePhaseModel().setPhase("reinforcement");
            }
            else
            {
                actions.addAction("You don't have that many army");
            }
        }
        else
        {
            actions.addAction("Invalid Selection");
        }
    }

}

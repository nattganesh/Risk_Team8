/**
 * This class is necessary for the controller of fortification phase
 *
 * @author DKM
 * @author Tianyi
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class FortificationController implements Initializable {

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

    /**
     * This is a constructor of the FortificationController class
     */
    public FortificationController()
    {

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

        actions = ActionModel.getActionModel();
        territoryObservableList.addAll(PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries());
        Territory.setItems(territoryObservableList);
        Adjacent.setItems(adjacentObservableList);
        updateView();

        if (!isAnyCountriesConnected(PlayerPhaseModel.getPlayerModel().getCurrentPlayer()))
        {
            int currentIndex = PlayerPhaseModel.getPlayerModel().getPlayerIndex();
            PlayerPhaseModel.getPlayerModel().setPlayerIndex((currentIndex + 1) % PlayerPhaseModel.getPlayerModel().getNumberOfPlayer());
            GamePhaseModel.getGamePhaseModel().setPhase("reinforcement");
        }

    }

    public void updateView()
    {
        Territory.setCellFactory(param -> new ListCell<Country>() {
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

        Adjacent.setCellFactory(param -> new ListCell<Country>() {
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
     * This method handles loading adjacent connected countries that current
     * player owns
     */
    @FXML
    public void territoryHandler()
    {
        if (Territory.getSelectionModel().getSelectedItem() != null)
        {
            Adjacent.getItems().clear();
            AdjacentArmy.setText("");
            CountriesArrivedbyPath = new ArrayList<>();
            Adjacent.getItems().addAll(getCountriesArrivedbyPath(Territory.getSelectionModel().getSelectedItem(), Territory.getSelectionModel().getSelectedItem(), CountriesArrivedbyPath));
            TerritoryArmy.setText(Integer.toString(Territory.getSelectionModel().getSelectedItem().getArmyCount()));
        }
    }

    /**
     * This method handles showing the army count of the adjacent country
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
        if (Territory.getSelectionModel().getSelectedItem() != null
                && Adjacent.getSelectionModel().getSelectedItem() != null
                && Territory.getSelectionModel().getSelectedItem().getArmyCount() > 1
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
                Territory.getSelectionModel().getSelectedItem()
                        .reduceArmyCount(Armyinput);
                Adjacent.getSelectionModel().getSelectedItem()
                        .setArmyCount(Armyinput);
                AdjacentArmy.setText(Integer.toString(Adjacent.getSelectionModel().getSelectedItem().getArmyCount()));
                TerritoryArmy.setText(Integer.toString(Territory.getSelectionModel().getSelectedItem().getArmyCount()));
                actions.addAction("Move Successfully");

                int currentIndex = PlayerPhaseModel.getPlayerModel().getPlayerIndex();
                PlayerPhaseModel.getPlayerModel().setPlayerIndex((currentIndex + 1) % PlayerPhaseModel.getPlayerModel().getNumberOfPlayer());
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

    /**
     * This method is used to get all accessible countries of the country player
     * chooses to move armies from
     *
     * @param country The country which is being checked to find all accessible
     * countries to it
     * @param firstCountry The country the player chooses to move armies from
     * @param countries The list that saves all countries accessible to the
     * country the player chooses
     * @return The list of accessible countries corresponding to the country
     *
     */
    public static ArrayList<Country> getCountriesArrivedbyPath(Country country, Country firstCountry, ArrayList<Country> countries)
    {
        Player p = country.getRuler();
        for (Country c : country.getConnectedCountries())
        {
            Player player = c.getRuler();
            if (player.getName().equals(p.getName()))
            {
                if (isCountryDuplicated(c, firstCountry, countries))
                {
                    countries.add(c);
                    countries = getCountriesArrivedbyPath(c, firstCountry, countries);
                }
            }
        }
        return countries;
    }

    /**
     * This method is used to check if the country accessible is already in the
     * result list And avoid adding the origin country the player choose to the
     * result list
     *
     * @param country The country which is being checked to find all accessible
     * countries to it
     * @param firstCountry The country the player chooses to move armies from
     * @param countries The list that saves all countries accessible to the
     * country the player chooses
     * @return true if the country accessible is not in the result list;
     * otherwise return false
     */
    public static boolean isCountryDuplicated(Country country, Country firstCountry, ArrayList<Country> countries)
    {
        int i = 0;
        if (country.getName().equalsIgnoreCase(firstCountry.getName()))
        {
            i = 1;
        }
        else
        {
            for (Country c : countries)
            {
                if (c.getName().equalsIgnoreCase(country.getName()))
                {
                    i = 1;
                }
            }
        }
        if (i == 0)
        {
            return true;
        }
        return false;
    }

    /**
     * This method checks if there is any country has accessible countries and
     * the country which has accessible countries has enough armies for move
     *
     * @return true if there is a country which has accessible countries and it
     * has enough armies for move. Otherwise, return false.
     */
    public static boolean isAnyCountriesConnected(Player p)
    {
        int i = 0;

        for (Country c : p.getOccupiedCountries())
        {
            ArrayList<Country> result = new ArrayList<>();
            if ((getCountriesArrivedbyPath(c, c, result).size() != 0) && (c.getArmyCount() > 1))
            {
                i = 1;
                break;
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

}

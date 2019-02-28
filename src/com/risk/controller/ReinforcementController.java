/**
 * Necessary for handling business logic for Reinforcement phase.
 *
 * @author DKM
 *
 */
package com.risk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.risk.model.map.Country;
import com.risk.model.GamePhaseModel;
import com.risk.model.MapModel;
import com.risk.model.PlayerModel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
 *
 * @see javafx.fxml.Initializable
 *
 */
public class ReinforcementController implements Initializable {


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
    Label playerId;

    @FXML
    ListView<Country> countryId;
    @FXML
    Button nextPlayer;

    @FXML
    Label inputArmyError;

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
     *
     * @see javafx.fxml.Initializable.initialize
     * @see javafx.beans.value.ChangeListener;
     * @see javafx.beans.value.ObservableValue;
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        playerId.setText(getName());
        armyAvailable.setText("Army: " + Integer.toString(getReinforcement()));
        initializeTerritory();
        initializeArmyField();
    }

    public void initializeArmyField()
    {
        inputArmy.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    inputArmy.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void initializeTerritory()
    {
        countryId.setItems(PlayerModel.getPlayerModel().getTerritory());
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
                    setText(country.getName() + " (" + country.getArmyCount() + ")");
                }
            }
        });
    }

    public int getReinforcement()
    {
        return PlayerModel.getPlayerModel().getCurrentPlayer().calculateReinforcement();
    }

    /**
     * gets the name of current player in the game
     *
     * @return it returns name of current player
     */
    public String getName()
    {
        return PlayerModel.getPlayerModel().getCurrentPlayer().getName();
    }

    /**
     * sets the number of available army to your occupied country
     */
    @FXML
    public void setArmy()
    {
        int Armyinput = 0;
        if (!inputArmy.getText().equals("") && PlayerModel.getPlayerModel().getCurrentPlayer().getReinforcement() != 0 && countryId.getSelectionModel().getSelectedItem() != null)
        {

            Armyinput = Integer.parseInt(inputArmy.getText());
            for (Country country : PlayerModel.getPlayerModel().getTerritory())
            {
                if (country.equals(countryId.getSelectionModel().getSelectedItem()))
                {
                    country.setArmyCount(Armyinput);
                    PlayerModel.getPlayerModel().updateCurrentTerritory();
                    break;
                }
            }
            PlayerModel.getPlayerModel().getCurrentPlayer().setReinforcement(Armyinput);
            armyAvailable.setText(
                    "Army: " + Integer.toString(PlayerModel.getPlayerModel().getCurrentPlayer().getReinforcement()));

        }

    }

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
        if (PlayerModel.getPlayerModel().getCurrentPlayer().getReinforcement() > 0)
        {
        }
        else
        {
            GamePhaseModel.getGamePhaseModel().setPhase("attack");
        }
    }

}

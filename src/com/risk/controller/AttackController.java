/**
 * This class is necessary for Controller of the Attack Phase
 *
 * @author DKM
 * @author Tianyi
 *
 */
package com.risk.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.risk.model.ActionModel;
import com.risk.model.DeckModel;
import com.risk.model.GamePhaseModel;
import com.risk.model.PlayerPhaseModel;
import com.risk.model.card.Card;
import com.risk.model.dice.Dice;
import com.risk.model.map.Country;
import com.risk.model.player.Player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AttackController implements Initializable, Observer {

    @FXML
    ListView<Country> countryId;

    @FXML
    Label inputArmyError;
    
    @FXML
    ListView<Country> adjacentEnemy;

    @FXML
    ListView<Country> adjacentOwned;

    @FXML
    ComboBox<Integer> AttackerDice;

    @FXML
    ComboBox<Integer> DefenderDice;

    @FXML
    Button rollDiceHandler;

    @FXML
    Button AllOut;

    @FXML
    private ConqueredController conqueringController;

    @FXML
    AnchorPane child;

    ObservableList<Country> territoryOwnedObservableList = FXCollections.observableArrayList();
    ObservableList<Country> adjacentEnemyObservableList = FXCollections.observableArrayList();
    ObservableList<Country> adjacentOwnedObservableList = FXCollections.observableArrayList();

    int[] rollLimit;
    ActionModel actions;
    boolean occupy = false;
    Player p = PlayerPhaseModel.getPlayerModel().getCurrentPlayer();

    /**
     * This is the constructor for AttackController classs
     */
    public AttackController()
    {
    }

    /**
     * @see javafx.fxml.Initializable#initialize(java.net.URL,s
     * java.util.ResourceBundle)
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        conqueringController.addObserver(this);
        actions = ActionModel.getActionModel();
        territoryOwnedObservableList.setAll(p.getOccupiedCountries());
        countryId.setItems(territoryOwnedObservableList);
        adjacentEnemy.setItems(adjacentEnemyObservableList);
        adjacentOwned.setItems(adjacentOwnedObservableList);
        
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
                    setText(country.getName() + " (" + country.getArmyCount() + ")");
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
                    setText(country.getName() + " (" + country.getArmyCount() + ")");
                }
            }
        });
    }

    /**
     * This method updates the view
     * @param attacker current attacker
     */
    public void updateView(Country attacker)
    {
    	 adjacentEnemyObservableList.setAll(attacker.getConnectedEnemy());
    	 adjacentOwnedObservableList.setAll(attacker.getConnectedOwned());
    	 territoryOwnedObservableList.setAll(p.getOccupiedCountries());	
    }

    /**
     * this method is responsible for populating adjacent territories
     */
    @FXML
    public void territoryHandler()
    {
    	Country attacker = countryId.getSelectionModel().getSelectedItem();   
        if (attacker != null)
        {
            clearDiceRolls();    
            adjacentEnemyObservableList.setAll(attacker.getConnectedEnemy());
            adjacentOwnedObservableList.setAll(attacker.getConnectedOwned());
        }
    }

    /**
     * this method is necessary for clearing combobox for dice roll
     */
    public void clearDiceRolls()
    {
        AttackerDice.getItems().clear();
        DefenderDice.getItems().clear();
        AttackerDice.getSelectionModel().clearSelection();
        DefenderDice.getSelectionModel().clearSelection();
    }

    /**
     * This is a method to fill in surrounding enemy in the ListView
     */
    @FXML
    public void adjacentEnemyHandler()
    {
	    Country defender = adjacentEnemy.getSelectionModel().getSelectedItem();
        Country attacker = countryId.getSelectionModel().getSelectedItem();
        if (defender != null && attacker != null && attacker.getArmyCount() > 1)
        {
            actions.addAction("attacker == " + attacker.getName() + " (" + attacker.getArmyCount() + ")");
            actions.addAction("defender == " + defender.getName() + " (" + defender.getArmyCount() + ")");
            initializeDice();

        }
        else
        {
            AttackerDice.getItems().clear();
            DefenderDice.getItems().clear();
        }
    }

    /**
     * this method initializes the number of dice roll available
     */
    public void initializeDice()
    {
        if (validateTerritorySelections())
        {
            rollLimit = setRollLimit(countryId.getSelectionModel().getSelectedItem(), adjacentEnemy.getSelectionModel().getSelectedItem());
            AttackerDice.getItems().clear();
            for (int i = 0; i < rollLimit[0]; i++)
            {
                AttackerDice.getItems().add(i + 1);
            }
            DefenderDice.getItems().clear();
            for (int i = 0; i < rollLimit[1]; i++)
            {
                DefenderDice.getItems().add(i + 1);
            }
            if (rollLimit[0] >= 1)
            {
                AttackerDice.setValue(rollLimit[0]);
                DefenderDice.setValue(rollLimit[1]);
            }
        }
    }

    /**
     *
     * @return returns true if attacker and defender has been selected
     */
    public boolean validateTerritorySelections()
    {
        boolean valid = false;
        if (countryId.getSelectionModel().getSelectedItem() != null && adjacentEnemy.getSelectionModel().getSelectedItem() != null)
        {
            valid = true;
        }
        return valid;
    }

    /**
     * @return returns true if defender and attacker both selected dice roll
     */
    public boolean validateDiceSelections()
    {
        boolean valid = false;
        if (AttackerDice.getSelectionModel().getSelectedItem() != null && DefenderDice.getSelectionModel().getSelectedItem() != null)
        {
            valid = true;
        }
        return valid;
    }

    /**
     * This is rolls the dice
     */
    @FXML
    public void rollDiceHandler()
    {
        if (validateTerritorySelections() && validateDiceSelections() && countryId.getSelectionModel().getSelectedItem().getArmyCount()> 1)
        {
            int diceAttack = AttackerDice.getSelectionModel().getSelectedItem();
            int diceDefender = DefenderDice.getSelectionModel().getSelectedItem();
            actions.addAction("attacker rolled " + diceAttack + " dice");
            actions.addAction("defender rolled " + diceDefender + " dice");
            rollDice(diceAttack, diceDefender, countryId.getSelectionModel().getSelectedItem(), adjacentEnemy.getSelectionModel().getSelectedItem());
            initializeDice();
        }
        else if (!validateTerritorySelections())
        {
            actions.addAction("select attacker and defender");
        }
        else if (!validateDiceSelections())
        {
            actions.addAction("select number of rolls");
            AttackerDice.getItems().clear(); 
            DefenderDice.getItems().clear();
        }
        else
        {
            actions.addAction("you do not have enough army to attack");
            clearDiceRolls();
        }
    }

    /**
     * This method rolls dices of attacker and defender at attack phase. The
     * number of armies of both countries decreases according to the rolling
     * results
     *
     * @param diceattack The number of dice attacker wants to roll
     * @param dicedefend The number of dice defender wants to roll
     * @param attackingCountry The country which invokes the attack
     * @param defendingCountry The country which is attacked
     */
    public void rollDice(int diceattack, int dicedefend, Country attackingCountry, Country defendingCountry)
    {
        int[] dattack = rollResult(diceattack);
        int[] ddefend = rollResult(dicedefend);
        int rolltime = setRollTime(diceattack, dicedefend);
        for (int i = 0; i < rolltime; i++)
        {
            if (dattack[i] > ddefend[i])
            {
                attackingCountry.getRuler().attack(attackingCountry, defendingCountry, 1);
                actions.addAction("defender has lost 1 army");
                if (defendingCountry.getArmyCount() == 0)
                {
                    actions.addAction("You have already occupied this country!");
                    actions.addAction("Please move armies to your new country!");
                    attackingCountry.getRuler().attack(attackingCountry, defendingCountry, 2);
                    occupy = true;
                    child.setVisible(true);
                    conqueringController.setConquringArmy(defendingCountry);
                    conqueringController.setDiceRoll(diceattack);
                    break;
                }               
            }
            else
            {

                attackingCountry.getRuler().attack(attackingCountry, defendingCountry, 3);
                updateView(attackingCountry);
                actions.addAction("attacker has lost 1 army");
                
            }
        }
        updateView(attackingCountry);
    }

    /**
     * 
     */
    @FXML
    public void AllOut()
    {
        Country attack = countryId.getSelectionModel().getSelectedItem();
        Country defend = adjacentEnemy.getSelectionModel().getSelectedItem();
        if (attack != null && defend != null && attack.getArmyCount() > 1)
        {
            boolean roll = true;
            while (roll)
            {
                int result[] = setRollLimit(attack, defend);
                rollDice(result[0], result[1], attack, defend);
                if (attack.getArmyCount() == 1 || defend.getArmyCount() == 0)
                {
                    roll = false;
                    clearDiceRolls();
                }
            }
        }
        else
        {
            actions.addAction("invalid move");
        }
    }

    /**
     * this sets the limit of roll depending the country chosen
     * 
     * @param attack country that is attacking
     * @param defend country that is defending 
     * @return returns array of roll limit for attacker and defender
     */
    public int[] setRollLimit(Country attack, Country defend)
    {
        int dicerange_attack;
        int dicerange_defend;
        int[] result = new int[2];
        if ((attack.getArmyCount() - 1) > 3)
        {
            dicerange_attack = 3;
        }
        else
        {
            dicerange_attack = attack.getArmyCount() - 1;
        }
        if (defend.getArmyCount() >= 2)
        {
            dicerange_defend = 2;
        }
        else
        {
            dicerange_defend = 1;
        }
        result[0] = dicerange_attack;
        result[1] = dicerange_defend;
        return result;
    }

    /**
     * 
     * 
     * @param diceattack
     * @param dicedefend
     * @return
     */
    public int setRollTime(int diceattack, int dicedefend)
    {
        int rolltime;
        if (diceattack >= dicedefend)
        {
            rolltime = dicedefend;
        }
        else
        {
            rolltime = diceattack;
        }
        return rolltime;
    }

    public int[] rollResult(int diceNumber)
    {
        int[] result = new int[diceNumber];
        for (int i = 0; i < diceNumber; i++)
        {
            result[i] = Dice.roll();
        }
        int tmp;
        for (int i = 0; i < diceNumber; i++)
        {
            for (int j = i + 1; j < diceNumber; j++)
            {
                if (result[i] < result[j])
                {
                    tmp = result[i];
                    result[i] = result[j];
                    result[j] = tmp;
                }
            }
        }
        return result;
    }

    /**
     * This method sets the GamePhaseModel to fortification, which notifies the
     * subscribed GameController to set new scene
     *
     * @param event listens for click event
     */
    public void goToFortificationPhase(ActionEvent event)
    {
        if (occupy)
        {
            DeckModel.getCardModel().sendCard(PlayerPhaseModel.getPlayerModel().getCurrentPlayer());
        }
        GamePhaseModel.getGamePhaseModel().setPhase("fortification");
    }

    /**
     * This method is necessary for getting update from card exchange
     */
    @Override
    public void update(Observable o, Object arg)
    {
        child.setVisible(false);
        adjacentOwned.getItems().clear();
        territoryOwnedObservableList.setAll(p.getOccupiedCountries());	
    }
}

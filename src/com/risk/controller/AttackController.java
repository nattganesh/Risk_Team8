/**
 * This class is necessary for Controller of the Attack Phase
 *
 * @author DKM
 * @author Tianyi
 *
 */
package com.risk.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.Observable;
import java.util.ResourceBundle;

import com.risk.model.ActionModel;
import com.risk.model.GamePhaseModel;
import com.risk.model.PlayerModel;
import com.risk.model.card.Card;
import com.risk.model.dice.Dice;
import com.risk.model.map.Country;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.FlowPane;

public class AttackController extends Observable implements Initializable {

	    @FXML
	    ListView<Country> countryId;
	    
	    @FXML
	    Label inputArmyError;

	    @FXML
	    TextField ArmyCount;

	    @FXML
	    ListView<Country> adjacentEnemy;
	    
	    @FXML
	    ListView<Country> adjacentOwned;
	    
	    @FXML
	    TextField inputArmy;
	    
	    @FXML
	    ComboBox<Integer> AttackerDice;
	    
	    @FXML
	    ComboBox<Integer> DefenderDice;
	    
	    @FXML
	    Button rollDiceHandler;
	    
	    @FXML
	    Button AllOut;

	    
	    ObservableList<Country> territoryObservableList = FXCollections.observableArrayList();
	    ObservableList<Country> adjacentEnemyObservableList = FXCollections.observableArrayList();
	    ObservableList<Country> adjacentOwnedObservableList = FXCollections.observableArrayList();
	    
	    int [] rollLimit;
	    ActionModel actions;
	    

	
    /**
     * This is the constructor for AttackController class
     */
    public AttackController()
    {

       
    }

    /**
     * (non-Javadoc)
     *
     * @see javafx.fxml.Initializable#initialize(java.net.URL,s
     * java.util.ResourceBundle)
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
    	 actions = ActionModel.getActionModel();
    	 territoryObservableList.addAll(PlayerModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries());
         countryId.setItems(territoryObservableList);
         
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
                     setText(country.getName());
                 }
             }
         });
         
         adjacentEnemy.setItems(adjacentEnemyObservableList);
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
         
         adjacentOwned.setItems(adjacentOwnedObservableList);
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
    }
    
  
    @FXML
    public void territoryHandler()
    {
    	
        if (countryId.getSelectionModel().getSelectedItem() != null)
        {
        	// i dont' wannt them to be in sync
        	AttackerDice.getItems().clear();
            adjacentEnemyObservableList.clear();
            adjacentOwnedObservableList.clear();
                       
            adjacentEnemyObservableList.addAll(countryId.getSelectionModel().getSelectedItem().getConnectedEnemy());
               
            adjacentOwnedObservableList.addAll(countryId.getSelectionModel().getSelectedItem().getConnectedOwned());
            ArmyCount.setText(Integer.toString(countryId.getSelectionModel().getSelectedItem().getArmyCount()));
            
            
            if (countryId.getSelectionModel().getSelectedItem().getArmyCount() > 1)
            {
            	actions.addAction("("+ countryId.getSelectionModel().getSelectedItem().getName() + ") == ENOUGH ARMY" );
            }
            if (countryId.getSelectionModel().getSelectedItem().getArmyCount() == 1)
            {
            	actions.addAction("("+ countryId.getSelectionModel().getSelectedItem().getName() + ") == NOT ENOUGH ARMY" );
            }
          
        }
        
    }
    
    /**
     * This is a method to fill in surrounding enemy in the ListView
     */
    @FXML
    public void adjacentEnemyHandler()
    {
    	if (adjacentEnemy.getSelectionModel().getSelectedItem() != null && countryId.getSelectionModel().getSelectedItem().getArmyCount() > 1)
    	{
    		actions.addAction("defender == " + adjacentEnemy.getSelectionModel().getSelectedItem().getName());
    		rollLimit = setRollLimit(countryId.getSelectionModel().getSelectedItem(), adjacentEnemy.getSelectionModel().getSelectedItem());
    		initializeDice();
    		actions.addAction("attacker can roll up to == " + rollLimit[0]);
    		actions.addAction("defender is rolling max == " + rollLimit[1]);
    	} 
    	else {
    		AttackerDice.getItems().clear();
    		DefenderDice.getItems().clear();
    	}
    }
    @FXML
    public void goToFortificationPhase()
    {
    	
    }
    
    /**
     * this method initializes the number of dice roll available
     */
    public void initializeDice()
    {
    	AttackerDice.getItems().clear();
    	for (int i = 0; i < rollLimit[0]; i++)
		{
    		AttackerDice.getItems().add(i+1);
		}
    	DefenderDice.getItems().clear();
    	for (int i = 0; i < rollLimit[1]; i++)
		{
    		DefenderDice.getItems().add(i+1);
		}
    }
   
    /**
     * This is rolls the dice
     */
    @FXML
    public void rollDiceHandler() 
    {
    
    	 if (AttackerDice.getSelectionModel().getSelectedItem() != null)
    	 {
    		 actions.addAction("rolling dice");
    		 int diceAttack = AttackerDice.getSelectionModel().getSelectedItem();
    		 int diceDefender = DefenderDice.getSelectionModel().getSelectedItem();
    		 rollDice(diceAttack, diceDefender, countryId.getSelectionModel().getSelectedItem(), adjacentEnemy.getSelectionModel().getSelectedItem());
    		 
    	 }
    
    }
    
    /**
	 * This method rolls dices of attacker and defender at attack phase. 
	 * The number of armies of both countries decreases according to the rolling results
	 * 
	 * @param diceattack The number of dice attacker wants to roll
	 * @param dicedefend The number of dice defender wants to roll
	 * @param attack The country which invokes the attack
	 * @param defend The country which is attacked
	 */
    public void rollDice(int diceattack, int dicedefend, Country attack, Country defend) 
    {
		int[] dattack = new int[diceattack];
		int[] ddefend = new int[dicedefend];
		int rolltime;
		for (int i = 0; i < diceattack; i++) 
		{
			dattack[i] = Dice.roll();
		}
		for (int i = 0; i < dicedefend; i++)
		{
			ddefend[i] = Dice.roll();
		}
		Arrays.sort(dattack);
		Arrays.sort(ddefend);
		if (diceattack >= dicedefend)
		{
			rolltime = dicedefend;
		} else 
		{
			rolltime = diceattack;
		}
		for (int i = 0; i < rolltime; i++)
		{
			if (defend.getArmyCount() != 0) 
			{
				if (dattack[diceattack - 1 - i] > ddefend[dicedefend - 1 - i]) 
				{
					defend.reduceArmyCount(1);
					
				} else 
				{
					attack.reduceArmyCount(1);
					ArmyCount.setText(Integer.toString(countryId.getSelectionModel().getSelectedItem().getArmyCount()));
				}
			} else 
			{
				defend.setRuler(PlayerModel.getPlayerModel().getCurrentPlayer());
				actions.addAction("You have already occupied this country!");
				break;
			}
		}
	}
    @FXML
    public void AllOut() {
    	Country attack=countryId.getSelectionModel().getSelectedItem();
    	Country defend=adjacentEnemy.getSelectionModel().getSelectedItem();
    	boolean roll = true;
    	while(roll) {
    		int result[] = setRollLimit(attack, defend);
    		rollDice(result[0], result[1],attack, defend);
    		if(attack.getArmyCount()==1||defend.getArmyCount()==0) {
    			actions.addAction("You have already occupied this country!");
    			defend.setRuler(PlayerModel.getPlayerModel().getCurrentPlayer());
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
    	                     setText(country.getName());
    	                 }
    	             }
    	         });
    			roll=false;
    		}
    	}
    }
	

    
    public int[] setRollLimit(Country attack, Country defend)
	{
		int dicerange_attack;
		int dicerange_defend;
		int[] result = new int[2];
		if ((attack.getArmyCount() - 1) > 3) 
		{
			dicerange_attack = 3;
		} else 
		{
			dicerange_attack = attack.getArmyCount() - 1;
		}
		if (defend.getArmyCount() >= 2) 
		{
			dicerange_defend = 2;
		} else 
		{
			dicerange_defend = 1;
		}
		result[0] = dicerange_attack;
		result[1] = dicerange_defend;
		return result;
	}
    
}

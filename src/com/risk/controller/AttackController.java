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
import java.util.Observer;
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
import javafx.collections.ListChangeListener;
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
import javafx.scene.layout.FlowPane;

public class AttackController implements Initializable, Observer {

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

	    @FXML
	    private ConqueredController conqueringController;
	    
	    @FXML 
	    AnchorPane child;
	    
	    ObservableList<Country> territoryObservableList = FXCollections.observableArrayList();
	    ObservableList<Country> adjacentEnemyObservableList = FXCollections.observableArrayList();
	    ObservableList<Country> adjacentOwnedObservableList = FXCollections.observableArrayList();
	    
	    int [] rollLimit;
	    ActionModel actions;
	    boolean occupy=false;
	    

	
    /**
     * This is the constructor for AttackController classs
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
    	conqueringController.addObserver(this);
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
        	clearDiceRolls();
    
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
    	if (adjacentEnemy.getSelectionModel().getSelectedItem() != null && countryId.getSelectionModel().getSelectedItem().getArmyCount() > 1)
    	{
    		actions.addAction("defender == " + adjacentEnemy.getSelectionModel().getSelectedItem().getName());
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
    	if (validateTerritorySelections())
    	{
    		rollLimit = setRollLimit(countryId.getSelectionModel().getSelectedItem(), adjacentEnemy.getSelectionModel().getSelectedItem());
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
        	if (rollLimit[0] >= 1)
        	{
        		AttackerDice.setValue(rollLimit[0]);
            	DefenderDice.setValue(rollLimit[1]);
        	}	
    	}
    }
    
    public boolean validateTerritorySelections() 
    {
    	boolean valid = false;
    	if(countryId.getSelectionModel().getSelectedItem() != null && adjacentEnemy.getSelectionModel().getSelectedItem() != null)
    	{
    		valid = true;
    	}
    	return valid;
    }
    
    public boolean validateDiceSelections()
    {
    	boolean valid = false;
    	if(AttackerDice.getSelectionModel().getSelectedItem() != null && DefenderDice.getSelectionModel().getSelectedItem() != null)
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
    	 int attackerArmy = countryId.getSelectionModel().getSelectedItem().getArmyCount();
    	 
    	 if (attackerArmy <  2) 
    	 {
    		 actions.addAction("you do not have enough army to attack");
    		 clearDiceRolls();
    	 }
    	 else if (validateTerritorySelections() && validateDiceSelections() && attackerArmy > 1)
    	 {
    		 actions.addAction("rolling dice");
    		 int diceAttack = AttackerDice.getSelectionModel().getSelectedItem();
    		 int diceDefender = DefenderDice.getSelectionModel().getSelectedItem();
    		 rollDice(diceAttack, diceDefender, countryId.getSelectionModel().getSelectedItem(), adjacentEnemy.getSelectionModel().getSelectedItem()); 
    		 initializeDice();
    	 } 
    	 
    	 else 
    	 {
    		 actions.addAction("select number of rolls");
    		 AttackerDice.getItems().clear();
    		 DefenderDice.getItems().clear();
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
					actions.addAction("defender has lost 1 army");
					if (defend.getArmyCount() == 0) {

						defend.setRuler(PlayerModel.getPlayerModel().getCurrentPlayer());
						PlayerModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries().add(defend);
						
						adjacentEnemyObservableList.remove(defend);
						territoryObservableList.add(defend);
						adjacentOwnedObservableList.add(defend);
						child.setVisible(true);
						conqueringController.setConquringArmy(defend);
						conqueringController.setDiceRoll(diceattack);
					}
					else 
					{
						adjacentEnemyObservableList.set(adjacentEnemyObservableList.indexOf(defend),defend);
					}
				
					
				} 
				else 
				{
					attack.reduceArmyCount(1);
					actions.addAction("attacker has lost 1 army");					
					ArmyCount.setText(Integer.toString(countryId.getSelectionModel().getSelectedItem().getArmyCount()));	
				}
			} 
			else 
			{
				defend.setRuler(PlayerModel.getPlayerModel().getCurrentPlayer());
				PlayerModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries().add(defend);
				
				adjacentEnemyObservableList.remove(defend);
				territoryObservableList.add(defend);
				adjacentOwnedObservableList.add(defend);
				
				actions.addAction("You have already occupied this country!");
				occupy = true;
				actions.addAction("Please move armies to your new country!");
				//update the move army view in here
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
    			occupy = true;
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
    	else
    	{
    		actions.addAction("invalid move");
    	}
    	
    }
	
    /**
     * This method sets the number of available army to your occupied country
     */
    //need to be modified
    @FXML
    public void setArmy()
    {
    	Country country1=countryId.getSelectionModel().getSelectedItem();
    	//this country 2 need to be modified appropriately
    	Country country2=adjacentEnemy.getSelectionModel().getSelectedItem();
        int Armyinput = 0;
        if (!inputArmy.getText().trim().isEmpty() && country1.getArmyCount() > 1
                && country1 != null)
        {

            Armyinput = Integer.parseInt(inputArmy.getText());
            if (Armyinput < country1.getArmyCount())
            {
            	country2.setArmyCount(Armyinput);
            	country1.reduceArmyCount(Armyinput);
            	actions.addAction("Added " + Armyinput + " Army from "+ country1.getName()+ " to " + country2.getName());
            }
            else {
            	actions.addAction("Invaid army input");
            }
        }
        else {
        	actions.addAction("Invaid country");
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
    /**
     * This method sets the GamePhaseModel to fortification, which notifies the
     * subscribed GameController to set new scene
     *
     * @param event listens for click event
     */
    public void goToFortificationPhase(ActionEvent event)
    {
    	if(occupy) {
    		//there should be added the part of sending cards to player
    	}
        GamePhaseModel.getGamePhaseModel().setPhase("fortification");
    }

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		Country conquered = (Country)arg;
		child.setVisible(false);
		adjacentOwnedObservableList.set(adjacentOwnedObservableList.indexOf(conquered),conquered);
	
	}
}

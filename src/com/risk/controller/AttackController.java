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
import com.risk.model.MapModel;
import com.risk.model.PlayerModel;
import com.risk.model.card.Card;
import com.risk.model.dice.Dice;
import com.risk.model.map.Country;
import com.risk.model.player.Player;

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
	    Player p = PlayerModel.getPlayerModel().getCurrentPlayer();

	
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
    
   /**
    * this method is responsible for populating adjacent territories 
    */
    @FXML
    public void territoryHandler()
    {
    	
        if (countryId.getSelectionModel().getSelectedItem() != null)
        {
        	// i dont' wannt them to be in sync
        	clearDiceRolls();
        	
        	Country attacker = countryId.getSelectionModel().getSelectedItem();
    
            adjacentEnemyObservableList.clear();
            adjacentOwnedObservableList.clear();
                       
            adjacentEnemyObservableList.addAll(attacker.getConnectedEnemy());
               
            adjacentOwnedObservableList.addAll(attacker.getConnectedOwned());
            ArmyCount.setText(Integer.toString(attacker.getArmyCount()));
          
        
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
    	if (adjacentEnemy.getSelectionModel().getSelectedItem() != null && countryId.getSelectionModel().getSelectedItem().getArmyCount() > 1)
    	{
    		Country defender = adjacentEnemy.getSelectionModel().getSelectedItem();
    		Country attacker = countryId.getSelectionModel().getSelectedItem();
    		
    		actions.addAction("attacker == "+ attacker.getName() + " (" + attacker.getArmyCount() + ")");
    		actions.addAction("defender == " + defender.getName() + " (" + defender.getArmyCount() + ")");
    		initializeDice();

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
    
    /**
     * 
     * @return returns true if attacker and defender has been selected
     */
    public boolean validateTerritorySelections() 
    {
    	boolean valid = false;
    	if(countryId.getSelectionModel().getSelectedItem() != null && adjacentEnemy.getSelectionModel().getSelectedItem() != null)
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
    	 
    	 if (validateTerritorySelections() && validateDiceSelections() && attackerArmy > 1)
    	 {
    		 
    		 actions.addAction("rolling dice");
    		 int diceAttack = AttackerDice.getSelectionModel().getSelectedItem();
    		 int diceDefender = DefenderDice.getSelectionModel().getSelectedItem();
    		 
    		 actions.addAction("attacker rolled " + diceAttack + " dice");
    		 actions.addAction("defender rolled " + diceDefender + " dice");
    		 
    		 rollDice(diceAttack, diceDefender, countryId.getSelectionModel().getSelectedItem(), adjacentEnemy.getSelectionModel().getSelectedItem()); 
    		 initializeDice();
    	 } else if(!validateTerritorySelections()) {
    		 actions.addAction("select attacker and defender");
    	 } else if(!validateDiceSelections()) {
    		 actions.addAction("select number of rolls");
    		 AttackerDice.getItems().clear();
    		 DefenderDice.getItems().clear();
    	 } else {
    		 actions.addAction("you do not have enough army to attack");
    		 clearDiceRolls();
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
		int tmp;
		for(int i =0;i<diceattack;i++) {
			for(int j =i+1;j<diceattack;j++) {
				if(dattack[i]<dattack[j]) {
					tmp = dattack[i];
					dattack[i]=dattack[j];
					dattack[j]=tmp;
				}
			}
		}
		for(int i =0;i<dicedefend;i++) {
			for(int j =i+1;j<dicedefend;j++) {
				if(ddefend[i]<ddefend[j]) {
					tmp = ddefend[i];
					ddefend[i]=ddefend[j];
					ddefend[j]=tmp;
				}
			}
		}
		if (diceattack >= dicedefend)
		{
			rolltime = dicedefend;
		} else 
		{
			rolltime = diceattack;
		}
		for (int i = 0; i < rolltime; i++) 
		{

			if (dattack[i] > ddefend[i]) 
			{
				
				defend.reduceArmyCount(1);
				adjacentEnemyObservableList.set(adjacentEnemyObservableList.indexOf(defend), defend);
				actions.addAction("defender has lost 1 army");
				

				if (defend.getArmyCount() == 0) 
				{
					actions.addAction("You have already occupied this country!");
					actions.addAction("Please move armies to your new country!");
					defend.getRuler().removeCountry(defend);
					defend.setRuler(attack.getRuler());
					attack.getRuler().addCountry(defend);

					adjacentEnemyObservableList.remove(defend);
					territoryObservableList.add(defend);
					adjacentOwnedObservableList.add(defend);
					for(Country c: PlayerModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries()) {
						System.out.println(c.getName()+c.getArmyCount());
					}
					child.setVisible(true);
					conqueringController.setConquringArmy(defend);
					conqueringController.setDiceRoll(diceattack);
					break;
				} else 
				{
					adjacentEnemyObservableList.set(adjacentEnemyObservableList.indexOf(defend), defend);
				}
			} else {

				for (Country c : PlayerModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries()) 
				{

					if (c.getName().equals(countryId.getSelectionModel().getSelectedItem().getName())) 
					{
						c.reduceArmyCount(1);
						territoryObservableList.set(territoryObservableList.indexOf(c), c);
						ArmyCount.setText(
								Integer.toString(countryId.getSelectionModel().getSelectedItem().getArmyCount()));
						actions.addAction("attacker has lost 1 army");
						break;
					}
				}
			}
		}

	}
    
 
    @FXML
    public void AllOut() {
    	Country attack=countryId.getSelectionModel().getSelectedItem();
    	Country defend=adjacentEnemy.getSelectionModel().getSelectedItem();
    	if (attack != null && defend != null && attack.getArmyCount() > 1) 
    	{
	    	boolean roll = true;
	    	while(roll) {
	    		int result[] = setRollLimit(attack, defend);
	    		rollDice(result[0], result[1],attack, defend);
	    		if(attack.getArmyCount()==1||defend.getArmyCount()==0) {
	    			roll=false;
	    			clearDiceRolls();
	    		}
	    	}
    	}
    	else
    	{
    		actions.addAction("invalid move");
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

	/* (non-Javadoc)s
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		Country conquered = (Country)arg;
		
		child.setVisible(false);
		adjacentOwned.getItems().clear();
		ArmyCount.setText(Integer.toString(countryId.getSelectionModel().getSelectedItem().getArmyCount()));
		System.out.println(conquered.getName() + conquered.getArmyCount());
		adjacentOwned.getItems().addAll(countryId.getSelectionModel().getSelectedItem().getConnectedCountries());
//		adjacentOwnedObservableList.set(adjacentOwnedObservableList.indexOf(conquered),conquered);
		
	}
}

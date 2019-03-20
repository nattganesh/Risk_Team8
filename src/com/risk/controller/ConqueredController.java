/**
 * 
 */
package com.risk.controller;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.risk.model.ActionModel;
import com.risk.model.PlayerModel;
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

/**
 * @author DKM
 *
 */
public class ConqueredController extends Observable implements Initializable{
	
	  ObservableList<Country> territoryObservableList = FXCollections.observableArrayList();
	  ObservableList<Country> conqueredObservableList = FXCollections.observableArrayList();
	  
	  int diceRolled;
	  int moved = 0;
		  
	  ActionModel actions;
	  @FXML
	  ListView<Country> countryOwnedID;
	  
	  @FXML
	  ListView<Country> conqueredID;	  
	  
	  @FXML
	  TextField armyCount;
	  boolean move=false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	     actions = ActionModel.getActionModel();
		 countryOwnedID.setItems(territoryObservableList);         
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
                	 setText(country.getName() + " ("+ country.getArmyCount() +")");
                 }
             }
         });
		 
		 conqueredID.setItems(conqueredObservableList);
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
                	 setText(country.getName() + " ("+ country.getArmyCount() +")");
                 }
             }
         });
		 
		 
        
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
			// 7 army and 3 dice rolled - you need to move at least 3 but less than 6 for that country			
			if (army < reinforcement.getArmyCount())
			{
				moved = moved + army;
				reinforcement.reduceArmyCount(army);
				conquered.setArmyCount(army);		
				actions.addAction("moving army");				
				conqueredObservableList.set(conqueredObservableList.indexOf(conquered), conquered);
				territoryObservableList.set(territoryObservableList.indexOf(reinforcement), reinforcement);
				
				if (moved >= diceRolled)
				{
					move=true;
					
				}
			}
			else 
			{
				actions.addAction("invalid move");
			}
			
			
			
		}
	}
	
	@FXML
	public void moveComplete()
	{
		if(move) 
		{
			Country conquered = conqueredID.getSelectionModel().getSelectedItem();
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
	 * this  method sets the conquered country into listview
	 * 
	 * @param c country that is conquered
	 */
	public void setConquringArmy(Country c)
	{
		conqueredObservableList.clear();
		conqueredObservableList.add(c);
		territoryObservableList.addAll(PlayerModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries());
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
}

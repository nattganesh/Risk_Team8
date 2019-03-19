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
	  

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		 territoryObservableList.addAll(PlayerModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries());
		 actions = ActionModel.getActionModel();
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
        
	}

	/**
	 * this method is necessary for moving army into conquered country
	 */
	@FXML
	public void moveArmyHandler()
	{
		
		if (countryOwnedID.getSelectionModel().getSelectedItem() != null && conqueredID.getSelectionModel().getSelectedItem() != null && !armyCount.getText().trim().isEmpty())
		{
			
			Country reinforcement = countryOwnedID.getSelectionModel().getSelectedItem();
			Country conquered = conqueredID.getSelectionModel().getSelectedItem();
			int army = Integer.parseInt(armyCount.getText());
			if (army+1 < reinforcement.getArmyCount())
			{
				moved = moved + army;
				System.out.println("army to move : " + army);
				if (moved > diceRolled)
				{
					setChanged();
					notifyObservers(conquered);
				}
				reinforcement.reduceArmyCount(army);
				conquered.setArmyCount(army);
			}
			else 
			{
				actions.addAction("invalid move");
			}
			
			
			
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
}

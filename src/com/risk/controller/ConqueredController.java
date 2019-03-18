/**
 * 
 */
package com.risk.controller;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

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
	  
	  @FXML
	  ListView<Country> countryID;
	  
	  @FXML
	  ListView<Country> conqueredID;
	  
	  @FXML
	  ComboBox <Integer> armyCount;

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		 territoryObservableList.addAll(PlayerModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries());
		 
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
		 
         countryID.setItems(territoryObservableList);         
         countryID.setCellFactory(param -> new ListCell<Country>() {
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
		
		if (countryID.getSelectionModel().getSelectedItem() != null && conqueredID.getSelectionModel().getSelectedItem() != null && armyCount.getSelectionModel().getSelectedItem() != null)
		{
			Country reinforcement = countryID.getSelectionModel().getSelectedItem();
			Country conquered = conqueredID.getSelectionModel().getSelectedItem();
			int army = armyCount.getSelectionModel().getSelectedItem();
			
			reinforcement.reduceArmyCount(army);
			conquered.setArmyCount(army);
			setChanged();
			notifyObservers(conquered);
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
		armyCount.getItems().clear();
		for (int i = 0; i < roll; i++)
		{
			armyCount.getItems().add(i+1);
		}
	}
}

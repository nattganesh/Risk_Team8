/**
 * 
 */
package com.risk.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.risk.map.Continent;
import com.risk.map.Country;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * @author DKM
 *
 */
public class MapEditorController implements Initializable {
	@FXML
	ListView<Continent> Continent;
	
	
	@FXML
	ListView<Country> Territory;
	
	@FXML
	TextField TerritoryInput;
	
	@FXML
	ListView<Country> Neighbour;
	
	
	@FXML
	TextField NeighbourInput;
	

	

	ObservableList<Continent>ContinentList = FXCollections.observableArrayList();
	ObservableList<Country>TerritoryList = FXCollections.observableArrayList();
	ObservableList<Country>AdjacentList = FXCollections.observableArrayList();
	
	
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Continent> continent = new ArrayList<>();
	
		
		continent.add(new Continent("North America", 10));
		continent.add(new Continent("South America", 10));
		continent.add(new Continent("Europe", 10));
		continent.add(new Continent("Asia", 10));
		continent.add(new Continent("Australia", 10));
		continent.add(new Continent("Africa", 10));
		ContinentList.addAll(continent);

		
		
		Continent.setItems(ContinentList);
		Continent.setCellFactory(param -> new ListCell<Continent>() {
            @Override
            protected void updateItem(Continent continent, boolean empty)
            {
                super.updateItem(continent, empty);
                if (empty || continent == null || continent.getName() == null)
                {
                    setText(null);
                }
                else
                {
                    setText(continent.getName());
                }
            }
		});
		
		
		Territory.setItems(TerritoryList);
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
		

		Neighbour.setItems(AdjacentList);
		Neighbour.setCellFactory(param -> new ListCell<Country>() {
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
   
	@FXML
	public void loadTerritory(MouseEvent arg0) {	
		TerritoryList.clear();
		AdjacentList.clear();
		TerritoryList.addAll(Continent.getSelectionModel().getSelectedItem().getCountries());

	}
	
	
	@FXML 
	public void TerritoryAdd() {
		if(!TerritoryInput.getText().equals("")) {
			Country country = new Country(TerritoryInput.getText(), Continent.getSelectionModel().getSelectedItem().getName());
			Continent.getSelectionModel().getSelectedItem().setCountry(country);
			TerritoryList.clear();
			TerritoryList.addAll(Continent.getSelectionModel().getSelectedItem().getCountries());
		}
	}
	
	@FXML
	public void loadNeighbours(MouseEvent arg0) {	
		AdjacentList.clear();
		AdjacentList.addAll(Territory.getSelectionModel().getSelectedItem().getConnectedCountries());
	}
	
	@FXML 
	public void AdjacentAdd() {
		if(!NeighbourInput.getText().equals("")) {
			Country country = new Country(NeighbourInput.getText(), Territory.getSelectionModel().getSelectedItem().getName());
			Territory.getSelectionModel().getSelectedItem().getConnectedCountries().add(country);
			AdjacentList.clear();
			AdjacentList.addAll(Territory.getSelectionModel().getSelectedItem().getConnectedCountries());
		}
	}
	
	
	
	
	
	
}

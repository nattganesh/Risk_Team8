/**
 * Necessary for handling business logic for Reinforcement phase.
 *
 * @author DKM
 *
 */
package com.risk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import com.risk.model.player.Player;
import com.risk.model.GamePhaseModel;
import com.risk.model.MapModel;
import com.risk.model.PlayerModel;
import com.risk.model.card.Card;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	public int TotalReinforcement;
	public ArrayList<String> cards = new ArrayList<>();

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

	@FXML
	Label CardMessage;

	@FXML
	ListView<Card> yourCard;

	@FXML
	ListView<Card> tradeCard;
	
	@FXML
	TextField ArmyCount;
	
	@FXML
	ListView<String> reinforcementMessage;

	ObservableList<Card> cardsObservableList = FXCollections.observableArrayList();
	ObservableList<Card> tradeObservableList = FXCollections.observableArrayList();
	ObservableList<Country> territoryObservableList = FXCollections.observableArrayList();
	ObservableList<String> messageObservableList = FXCollections.observableArrayList();
	
	

	/**
	 * This is the constructor for the reinforcement controller
	 */
	public ReinforcementController() {
	}

	/**
	 * This method is data binding for connection between controller and UI. It also
	 * sets up observable list, in which the view listens for changes and update its
	 * view. 
	 *
	 *
	 * @see javafx.fxml.Initializable.initialize
	 * @see javafx.beans.value.ChangeListener;
	 * @see javafx.beans.value.ObservableValue;
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		playerId.setText(PlayerModel.getPlayerModel().getCurrentPlayer().getName());
		TotalReinforcement = calculateReinforcementOccupiedTerritory(PlayerModel.getPlayerModel().getCurrentPlayer()) 
				+ calculateReinforcementContinentControl(PlayerModel.getPlayerModel().getCurrentPlayer());
		armyAvailable.setText("Army: " + Integer.toString(getReinforcement()));
		
		cardsObservableList.addAll(PlayerModel.getPlayerModel().getCurrentPlayer().getCards());
	
		

		yourCard.setItems(cardsObservableList);
		tradeCard.setItems(tradeObservableList);

		territoryObservableList.addAll(PlayerModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries());

		countryId.setItems(territoryObservableList);
		countryId.setCellFactory(param -> new ListCell<Country>()
		{
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
		
		reinforcementMessage.setItems(messageObservableList);


		inputArmy.textProperty().addListener(new ChangeListener<String>() 
		{
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				if (!newValue.matches("\\d*")) 
				{
					inputArmy.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}
	
	@FXML
	public void territoryHandler() 
	{
		if (countryId.getSelectionModel().getSelectedItem() != null)
		{
			ArmyCount.setText(Integer.toString(countryId.getSelectionModel().getSelectedItem().getArmyCount()));
		}
	}
	
	/**
	 * sets the number of available army to your occupied country
	 */
	@FXML
	public void setArmy()
	{
		int Armyinput = 0;
		if (!inputArmy.getText().trim().isEmpty() && getReinforcement() != 0
				&& countryId.getSelectionModel().getSelectedItem() != null)
		{

			Armyinput = Integer.parseInt(inputArmy.getText());
			if (Armyinput <= getReinforcement()) 
			{
				
				for (Country c : territoryObservableList)
				{
					if (c.getName().equals(countryId.getSelectionModel().getSelectedItem().getName())) 
					{
						c.setArmyCount(Armyinput);
						setReinforcement(Armyinput);				
						ArmyCount.setText(Integer.toString(countryId.getSelectionModel().getSelectedItem().getArmyCount()));
						messageObservableList.add("Added " + Armyinput + " Army to " + c.getName());
						break;
					}
				}	
			}
		} 
		armyAvailable.setText(
				"Army: " + Integer.toString(getReinforcement()));
	}

	@FXML
	/**
	 * Method to set up the Attack.fxml view and set the controller
	 * (AttackController) for the view. Then, changes the scene on the stage to send
	 * user to the attack phase.
	 *
	 * @param event eventlistener for button clicked event
	 * @throws IOException Exception thrown when view is not found
	 */
	public void goToAttackPhase(ActionEvent event) throws IOException 
	{
		if (getReinforcement() > 0 ) 
		{ 
		   messageObservableList.add("place all your army");
		}
		else if (PlayerModel.getPlayerModel().getCurrentPlayer().getCards().size() >= 5)
		{
			 messageObservableList.add("you got 5+ cards");
		}
		else 
		{
			GamePhaseModel.getGamePhaseModel().setPhase("attack");
		}
	}
	
	/**
	 * This method is used to update the cards the player owned and the cards the player chooses to exchange
	 * 
	 */
	@FXML
	public void yourCardHandler() 
	{
		if (yourCard.getSelectionModel().getSelectedItem() != null && tradeCard.getItems().size() < 3) 
		{
			Card card = yourCard.getSelectionModel().getSelectedItem();
			tradeCard.getItems().add(card);
			yourCard.getItems().remove(card);
		}
	}
	
	/**
	 * This method is used to update the cards the player owned and the cards the player chooses to exchange
	 * 
	 */
	@FXML
	public void yourTradeHandler() 
	{
		if (tradeCard.getSelectionModel().getSelectedItem() != null)
		{
			Card card = tradeCard.getSelectionModel().getSelectedItem();
			yourCard.getItems().add(card);
			tradeCard.getItems().remove(card);

		}
	}
	
	/**
	 * This method is used to update the total reinforcement based on the cards the player chooses
	 * And update the cards the player owned.
	 * 
	 */
	@FXML
	public void tradeCard()
	{
		if (tradeCard.getItems().size() == 3)
		{
			if (cardValidation(tradeCard.getItems())) 
			{
				TotalReinforcement += calculateReinforcementFromCards();
				armyAvailable.setText("Army: " + Integer.toString(getReinforcement()));
				tradeCard.getItems().clear();
				PlayerModel.getPlayerModel().getCurrentPlayer().getCards().clear();
				
				for (Card c : yourCard.getItems()) 
				{
					PlayerModel.getPlayerModel().getCurrentPlayer().getCards().add(c);
				}	
			}
		}
	}

	/**
	 * This method is used to validate the cards the player chooses to exchange
	 * 
	 * @param selectedCards A list of cards the player chooses to exchange for armies
	 * @return The result corresponding to the countries the player occupied
	 */
	public boolean cardValidation(ObservableList<Card> selectedCards) 
	{
		ObservableList<Card> cards = FXCollections.observableArrayList();
		cards = selectedCards;

		if (((cards.get(0).getCatagory().equals(cards.get(1).getCatagory()))
				&& (cards.get(0).getCatagory().equals(cards.get(2).getCatagory())))
				|| ((!(cards.get(0).getCatagory().equals(cards.get(1).getCatagory())))
						&& (!(cards.get(0).getCatagory().equals(cards.get(2).getCatagory())))
						&& (!(cards.get(1).getCatagory().equals(cards.get(2).getCatagory()))))) 
		{			
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	/**
	 * This method is used to calculate the extra armies based on the number of countries the player already occupied
	 * 
	 * @param currentPlayer The player who is in his reinforcement round
	 * @return The result corresponding to the countries the player occupied
	 */
	public int calculateReinforcementOccupiedTerritory(Player currentPlayer) 
	{
		int reinforcement = (int) Math.floor(currentPlayer.numbOccupied() / 3);
		return reinforcement;
	}
	
	
	/**
	 * This method is used to calculate the extra armies earned if the player has occupied continents
	 * 
	 * @param currentPlayer The player who is in his reinforcement round
	 * @return The result corresponding to the countries the player occupied
	 */
	public int calculateReinforcementContinentControl(Player currentPlayer)
	{
		String currentRuler = currentPlayer.getName();
		int reinforcement = 0;
		for (Continent continent : MapModel.getMapModel().getContinents())
		{
			boolean control = true;
			for (Country country : continent.getCountries()) 
			{
				if (country.getRuler().getName() != currentRuler) 
				{
					control = false;
					break;
				}
			}
			if (control) 
			{
				reinforcement = reinforcement + continent.getPointsWhenFullyOccupied();
			}
		}
		return reinforcement;
	}
	
	/**
	 * This method is used to calculate the extra armies earned by exchanging cards
	 * 
	 * @return The result corresponding to the total exchange time.
	 */
	public int calculateReinforcementFromCards() 
	{
		int currentExchange = MapModel.getMapModel().getExchangeTime();
		int reinforcement = (currentExchange +1) * 5;
		MapModel.getMapModel().setExchangeTime(currentExchange + 1);
		return reinforcement;
	}
	/**
	 * This method is used to get the number of total armies earned for reinforcement 
	 * 
	 * @return The the number of total armies
	 */
	public int getReinforcement()
	{
		return TotalReinforcement;
	}
	
	/**
	 * This method is used to reduce the number of available armies for reinforcement 
	 * 
	 * @param i The number of armies a player added to an occupied country
	 */
	public void setReinforcement(int i) 
	{
		TotalReinforcement = TotalReinforcement - i;
	}
}

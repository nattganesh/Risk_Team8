/**
 * This class is the Controller for SelectPlayer.fxml, where users can select number of players.
 * 
 * 
 * @author DKM
 * @version 1.0
 * @see javafx.fxml.Initializable
 */
package com.risk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.risk.army.Player;
import com.risk.dice.Dice;
import com.risk.map.Country;
import com.risk.model.Model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class SetUpController implements Initializable {

	private Model model;

	@FXML
	ComboBox<String> playerDropDown;

	@FXML
	Button StartGame;

	public SetUpController(Model m) {
		model = m;
	}

	/**
	 * Add 2-6 player in ComboBox component
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		playerDropDown.getItems().addAll("2", "3", "4", "5",
				"6");
	}
	/**
	 * sets the number of players
	 * 
	 * @param numberOfPlayer the number of player
	 */
	public void setPlayers(int numberOfPlayer) {
		while (numberOfPlayer > 0) {
			model.addPlayer(new Player(Player.PLAYERCOLOR[numberOfPlayer])); 
			numberOfPlayer--;
		}
	}
	/**
	 * Sets the number of player, assign countries to each player and calculate round robin, then load the Reinforcement scene
	 * 
	 * @param eventlistener for button clicked event
	 * @throws IOException the exception thrown for when .fxml not found
	 * @see javafx.event.ActionEvent
	 */
	public void StartGame(ActionEvent event) throws IOException {
		if (playerDropDown.getSelectionModel().getSelectedItem() != null){
			
			setPlayers(Integer.parseInt(playerDropDown.getSelectionModel().getSelectedItem()));
			calcStartingArmies();
			assignCountriesToPlayers();
			determinePlayersStartingOrder();
			
			ReinforcementController rController = new ReinforcementController(model);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/risk/view/Reinforcement.fxml"));
			loader.setController(rController);
			Parent root = loader.load();
			Scene ReinforcementScene = new Scene(root);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(ReinforcementScene);
			window.show();
			
		}
	}
	
	/**
	 *  This method calculates the round robin.
	 *  It first roll the dice to determine who goes first. 
	 *  The player who rolls the highest number starts the game. 
	 *  Then the play order goes clockwise from the starting player. 
	 *  The game starts after the order of play has been determined.
	 */
    public void determinePlayersStartingOrder()
    {
        int[] diceRolls = new int[model.getNumberOfPlayer()];

        for (int i = 0; i < diceRolls.length; i++)
        {
            diceRolls[i] = Dice.roll();
        }
        int maxRollIndex = 0;
        for (int i = 1; i < diceRolls.length; i++)
        {
            if (diceRolls[maxRollIndex] < diceRolls[i])
            {
                maxRollIndex = i;
            }
        }
        boolean tieBreakingNeeded = false;
        for (int i = 1; i < diceRolls.length; i++)
        {
            if (diceRolls[maxRollIndex] == diceRolls[i] && maxRollIndex != i)
            {
                tieBreakingNeeded = true;
            }
        }
        if (tieBreakingNeeded)
        {
            determinePlayersStartingOrder();
        }
        else
        {
            System.out.println("The Dice Roll Results are as follows");
            for (int i = 0; i < diceRolls.length; i++)
            {
                System.out.println(model.getPlayers().get(i).getName() + " --> " + diceRolls[i]);
            }
            System.out.println();
            System.out.println("The player: " + model.getPlayers().get(maxRollIndex).getName()
                    + " has won the dice roll with a roll of " + diceRolls[maxRollIndex]
                    + ".\nHe/She will start first, and the play order goes clockwise from the starting player.");

            System.out.println("\n---------------------------------------------");
            for (int i = 0; i < maxRollIndex; i++)
            {
                Player temp = model.getPlayers().remove(0);
                model.addPlayer(temp);
            }
            initializeCurrentPlayer();
            System.out.println("The Play Order is as follows:");
            for (int i = 0; i < diceRolls.length; i++)
            {
                System.out.println(model.getPlayers().get(i).getName());
            }
            System.out.println("\n---------------------------------------------");
        }
    }

	/**
	 * This method assigns countries to players.
	 */
    public void assignCountriesToPlayers()
    {
        boolean[] countryOccupied = new boolean[Country.MAX_NUMBER_OF_COUNTRIES];
        int i = 0;
        while (i < Country.MAX_NUMBER_OF_COUNTRIES)
        {
            for (Player p : model.getPlayers())
            {
                int random = (int) (Math.random() * Country.MAX_NUMBER_OF_COUNTRIES);
                while (countryOccupied[random])
                {
                    random = (int) (Math.random() * Country.MAX_NUMBER_OF_COUNTRIES);
                }
                if (!countryOccupied[random])
                {
                    model.getCountries().get(random).setRuler(p);
                    model.getCountries().get(random).setIsOccupied(true);
                    model.getCountries().get(random).setArmyCount(1);

                    countryOccupied[random] = true;
                    p.getOccupiedCountries().add(model.getCountries().get(random));
                    i++;
                }
                if (i >= Country.MAX_NUMBER_OF_COUNTRIES)
                {
                    break;
                }
            }
        }
        boolean[] armiesRemaining = new boolean[model.getNumberOfPlayer()];
        boolean done = false;
        System.out.println("dfdf");
        while (!done)
        {
            for (int ii = 0; ii < model.getNumberOfPlayer(); ii++)
            {
                if (model.getPlayers().get(ii).armiesLeft() > 0)
                {
                    int random = (int) (Math.random() * model.getPlayers().get(ii).numbOccupied());
                    model.getPlayers().get(ii).getOccupiedCountries().get(random).setArmyCount(1);
                }
                else
                {
                    armiesRemaining[ii] = true;
                }
            }
            int countP = 0;
            for (boolean d : armiesRemaining)
            {
                if (d)
                {
                    countP++;
                }
            }
            if (countP == model.getNumberOfPlayer())
            {
                done = true;
            }
        }
    }
    
	/**
	 * This method sets the observable list for the country occupied by current player.	 * 
	 */
    public void initializeCurrentPlayer()
    {
        model.setCurrentPlayerCountryObs();
    }

	/**
	 * This method sets the starting armies during initialization based on number of players.
	 */
    public void calcStartingArmies()
    {
        for (Player player : model.getPlayers())
        {
            player.setStartingPoints(calcStartingArmiesHelper(model.getNumberOfPlayer()));
        }
    }


	/**
	 * This is a helper method for setStartingPoints. Based on the number of players it returns 
	 * inital army count.
	 * 
	 * @param getPlayerSize number of players
	 * @return startingP initial army count
	 */
    public int calcStartingArmiesHelper(int getPlayerSize)
    {
        int startingP = 0;
        switch (getPlayerSize)
        {
            case 2:
                startingP = 40;
                break;
            case 3:
                startingP = 35;
                break;
            case 4:
                startingP = 30;
                break;
            case 5:
                startingP = 25;
                break;
            case 6:
                startingP = 20;
                break;
        }
        return startingP;

    }

}

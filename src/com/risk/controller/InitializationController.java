///**
// * This class is the Controller for SelectPlayer.fxml, where users can select number of players.
// *
// *
// * @author DKM
// * @version 1.0
// * @see javafx.fxml.Initializable
// */
//package com.risk.controller;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Observable;
//import java.util.Observer;
//import java.util.ResourceBundle;
//
//import com.risk.model.player.Player;
//import com.risk.model.dice.Dice;
//import com.risk.model.map.Country;
//import com.risk.model.GamePhaseModel;
//import com.risk.model.MapModel;
//import com.risk.model.PlayerModel;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.stage.Stage;
//
//public class InitializationController extends Observable implements Initializable {
//
//    private GamePhaseModel gamephase;
//    private PlayerModel players;
//    private MapModel maps;
//
//    @FXML
//    ComboBox<String> playerDropDown;
//
//    @FXML
//    Button StartGame;
//
//    public InitializationController(GamePhaseModel game, PlayerModel p, MapModel m)
//    {
//        gamephase = game;
//        players = p;
//        maps = m;
//
//    }
////	public void initializeReinforcement(ActionEvent e) throws IOException {
////		loader.setController(rController);
////		Parent root = loader.load();
////		Scene ReinforcementScene = new Scene(root);
////		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
////		window.setScene(ReinforcementScene);
////		window.show();
////	}
////	
//
//    /**
//     * Add 2-6 player in ComboBox component
//     *
//     * @see javafx.fxml.Initializable#initialize(java.net.URL,
//     * java.util.ResourceBundle)
//     */
//    @Override
//    public void initialize(URL location, ResourceBundle resources)
//    {
//        playerDropDown.getItems().addAll("2", "3", "4", "5",
//                "6");
//    }
//
//    /**
//     * sets the number of players
//     *
//     * @param numberOfPlayer the number of player
//     */
//    public void setPlayers(int numberOfPlayer)
//    {
//        while (numberOfPlayer > 0)
//        {
//            players.addPlayer(new Player(PlayerModel.PLAYERCOLOR[numberOfPlayer]));
//            numberOfPlayer--;
//        }
//    }
//
//    /**
//     * Sets the number of player, assign countries to each player and calculate
//     * round robin, then load the Reinforcement scene
//     *
//     * @param eventlistener for button clicked event
//     * @throws IOException the exception thrown for when .fxml not found
//     * @see javafx.event.ActionEvent
//     */
//    public void StartGame(ActionEvent event) throws IOException
//    {
//        if (playerDropDown.getSelectionModel().getSelectedItem() != null)
//        {
//            setPlayers(Integer.parseInt(playerDropDown.getSelectionModel().getSelectedItem()));
//            calcStartingArmies();
//            assignCountriesToPlayers();
//            determinePlayersStartingOrder();
//            gamephase.setPhase("reinforcement");
//        }
//    }
//
//    /**
//     * This method calculates the round robin. It first roll the dice to
//     * determine who goes first. The player who rolls the highest number starts
//     * the game. Then the play order goes clockwise from the starting player.
//     * The game starts after the order of play has been determined.
//     */
//    public void determinePlayersStartingOrder()
//    {
//        int[] diceRolls = new int[players.getNumberOfPlayer()];
//
//        for (int i = 0; i < diceRolls.length; i++)
//        {
//            diceRolls[i] = Dice.roll();
//        }
//        int maxRollIndex = 0;
//        for (int i = 1; i < diceRolls.length; i++)
//        {
//            if (diceRolls[maxRollIndex] < diceRolls[i])
//            {
//                maxRollIndex = i;
//            }
//        }
//        boolean tieBreakingNeeded = false;
//        for (int i = 1; i < diceRolls.length; i++)
//        {
//            if (diceRolls[maxRollIndex] == diceRolls[i] && maxRollIndex != i)
//            {
//                tieBreakingNeeded = true;
//            }
//        }
//        if (tieBreakingNeeded)
//        {
//            determinePlayersStartingOrder();
//        }
//        else
//        {
//            System.out.println("The Dice Roll Results are as follows");
//            for (int i = 0; i < diceRolls.length; i++)
//            {
//                System.out.println(players.getPlayers().get(i).getName() + " --> " + diceRolls[i]);
//            }
//            System.out.println();
//            System.out.println("The player: " + players.getPlayers().get(maxRollIndex).getName()
//                    + " has won the dice roll with a roll of " + diceRolls[maxRollIndex]
//                    + ".\nHe/She will start first, and the play order goes clockwise from the starting player.");
//
//            System.out.println("\n---------------------------------------------");
//            for (int i = 0; i < maxRollIndex; i++)
//            {
//                Player temp = players.getPlayers().remove(0);
//                players.addPlayer(temp);
//            }
//            initializeCurrentPlayer();
//            System.out.println("The Play Order is as follows:");
//            for (int i = 0; i < diceRolls.length; i++)
//            {
//                System.out.println(players.getPlayers().get(i).getName());
//            }
//            System.out.println("\n---------------------------------------------");
//        }
//    }
//
//    /**
//     * This method assigns countries to players.
//     */
//    public void assignCountriesToPlayers()
//    {
//        boolean[] countryOccupied = new boolean[Country.MAX_NUMBER_OF_COUNTRIES];
//        int i = 0;
//        while (i < Country.MAX_NUMBER_OF_COUNTRIES)
//        {
//            for (Player p : players.getPlayers())
//            {
//                int random = (int) (Math.random() * Country.MAX_NUMBER_OF_COUNTRIES);
//                while (countryOccupied[random])
//                {
//                    random = (int) (Math.random() * Country.MAX_NUMBER_OF_COUNTRIES);
//                }
//                if (!countryOccupied[random])
//                {
//                    maps.getCountries().get(random).setRuler(p);
//                    maps.getCountries().get(random).setIsOccupied(true);
//                    maps.getCountries().get(random).setArmyCount(1);
//
//                    countryOccupied[random] = true;
//                    p.getOccupiedCountries().add(maps.getCountries().get(random));
//                    i++;
//                }
//                if (i >= Country.MAX_NUMBER_OF_COUNTRIES)
//                {
//                    break;
//                }
//            }
//        }
//        boolean[] armiesRemaining = new boolean[players.getNumberOfPlayer()];
//        boolean done = false;
//
//        while (!done)
//        {
//            for (int ii = 0; ii < players.getNumberOfPlayer(); ii++)
//            {
//                if (players.getPlayers().get(ii).armiesLeft() > 0)
//                {
//                    int random = (int) (Math.random() * players.getPlayers().get(ii).numbOccupied());
//                    players.getPlayers().get(ii).getOccupiedCountries().get(random).setArmyCount(1);
//                }
//                else
//                {
//                    armiesRemaining[ii] = true;
//                }
//            }
//            int countP = 0;
//            for (boolean d : armiesRemaining)
//            {
//                if (d)
//                {
//                    countP++;
//                }
//            }
//            if (countP == players.getNumberOfPlayer())
//            {
//                done = true;
//            }
//        }
//    }
//
//    /**
//     * This method sets the observable list for the country occupied by current
//     * player.	*
//     */
//    public void initializeCurrentPlayer()
//    {
//        players.setCurrentPlayerCountryObs();
//    }
//
//    /**
//     * This method sets the starting armies during initialization based on
//     * number of players.
//     */
//    public void calcStartingArmies()
//    {
//        for (Player player : players.getPlayers())
//        {
//            player.setStartingPoints(calcStartingArmiesHelper(players.getNumberOfPlayer()));
//        }
//    }
//
//    /**
//     * This is a helper method for setStartingPoints. Based on the number of
//     * players it returns inital army count.
//     *
//     * @param getPlayerSize number of players
//     * @return startingP initial army count
//     */
//    public int calcStartingArmiesHelper(int getPlayerSize)
//    {
//        int startingP = 0;
//        switch (getPlayerSize)
//        {
//            case 2:
//                startingP = 40;
//                break;
//            case 3:
//                startingP = 35;
//                break;
//            case 4:
//                startingP = 30;
//                break;
//            case 5:
//                startingP = 25;
//                break;
//            case 6:
//                startingP = 20;
//                break;
//        }
//        return startingP;
//
//    }
//
//}

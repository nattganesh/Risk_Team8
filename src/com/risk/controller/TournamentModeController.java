/**
 * This class is a controller for the tournament mode view
 */

package com.risk.controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.risk.model.MapModel;
import com.risk.model.PlayerPhaseModel;
import com.risk.model.exceptions.CannotFindException;
import com.risk.model.exceptions.DuplicatesException;
import com.risk.model.player.Player;
import com.risk.model.strategy.Aggressive;
import com.risk.model.strategy.Benevolent;
import com.risk.model.strategy.Cheater;
import com.risk.model.strategy.Random;
import com.risk.model.utilities.FileParser;
import com.risk.model.utilities.Validate;
import com.risk.model.utilities.loadGame.LoadGame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class TournamentModeController implements Initializable {

    @FXML
    private ComboBox<String> typePlayerID;

    @FXML
    private Button assignMap;

    @FXML
    private ComboBox<Integer> selectMapID;

    @FXML
    private ComboBox<Integer> selectPlayerID;
    
    @FXML
    private Button startTournament;
    
    @FXML
    private ComboBox<String> typeMapID;

    @FXML
    private ListView<String> hiscoreView;
    
    @FXML
    private ListView<String> mapView;
    
    @FXML
    private ListView<Player> playerView;


    @FXML
    private ComboBox<Integer> numberMapsID;

    @FXML
    private ComboBox<Integer> numberPlayersID;
    
    @FXML
    private ComboBox<Integer> numberGamesID;

    @FXML
    private TextField numberTurnsID;
    
    // at the end this should be displayed
    ObservableList<String> hiscoreObservableList = FXCollections.observableArrayList();
    
    // these are the data you need arya
    ObservableList<Player> playerObservableList = FXCollections.observableArrayList();
    ObservableList<String> mapObservableList = FXCollections.observableArrayList();
    ArrayList<String> files = new ArrayList<String>(); 
   
    int turns;
    int games;

	/**
	 * This method is necessary for initialization of the necessary objects for the tournamentmode controller
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		numberMapsID.getItems().addAll(1,2,3,4,5);
		numberPlayersID.getItems().addAll(2,3,4);
		numberGamesID.getItems().addAll(1,2,3,4,5);
		typeMapID.getItems().addAll("map1", "map2", "map3", "map4", "map5");
		typePlayerID.getItems().addAll("Aggressive", "Benevolent", "Cheater", "Random");
		
		hiscoreView.setItems(hiscoreObservableList);
		mapView.setItems(mapObservableList);
		playerView.setItems(playerObservableList);
        playerView.setCellFactory(param -> new ListCell<Player>() {
            @Override
            protected void updateItem(Player player, boolean empty)
            {
                super.updateItem(player, empty);
                if (empty || player == null || player.getName() == null)
                {

                    setText(null);

                }
                else
                {
                    setText(player.getName());
                }
            }
        });
	}
	
	/**
	 * this method is necessary to start the game
	 * @throws FileNotFoundException 
	 * @throws DuplicatesException 
	 * @throws CannotFindException 
	 */
	@FXML
	public void startTournament() throws FileNotFoundException, CannotFindException, DuplicatesException
	{
		if (selectMapID.getSelectionModel().getSelectedItem() != null && selectPlayerID.getSelectionModel().getSelectedItem() != null)
		{
			if (mapObservableList.size() == numberMapsID.getSelectionModel().getSelectedItem() 
					&& playerObservableList.size() == numberPlayersID.getSelectionModel().getSelectedItem()
					&& numberGamesID.getSelectionModel().getSelectedItem() != null
					&& !numberTurnsID.getText().trim().isEmpty()
					&& Integer.parseInt(numberTurnsID.getText()) >= 10
					&& Integer.parseInt(numberTurnsID.getText()) <= 50)
			{
				MapModel.getMapModel().getContinents().clear();
	            MapModel.getMapModel().getCountries().clear();
	            PlayerPhaseModel.getPlayerModel().getPlayers().clear();
	            files.clear();
	           
				
				turns = Integer.parseInt(numberTurnsID.getText());
				games = numberGamesID.getSelectionModel().getSelectedItem();
				
				
				for (int i = 0 ; i < mapObservableList.size(); i++)
				{
					files.add(i, "src/com/risk/main/mapTextFiles/" + mapObservableList.get(i) + ".txt");
				}
				

	            
				MapEditorController mapEditor = new MapEditorController();
				
				Scanner scan = new Scanner (new File (files.get(0)));
				FileParser fileParser = new FileParser();

				fileParser.init(scan);
				
				mapEditor.setPlayers(playerObservableList);
				mapEditor.setDeck();
				mapEditor.autoAssignCountriesToPlayers();
				mapEditor.determinePlayersStartingOrder();
				
				System.out.println("in here " + MapModel.getMapModel().getCountries().size());
							    
				
				// this is where you can start the game
			}
		}
	}
	
	/**
	 * this method is necessary to select number of starting players
	 */
	@FXML
	public void selectNumbPlayers()
	{
		selectPlayerID.getItems().clear();
		playerObservableList.clear();
		int numbPlayers = numberPlayersID.getSelectionModel().getSelectedItem();
		for (int i = 1; i <= numbPlayers; i++)
		{
			selectPlayerID.getItems().add(i);
		}
	}
	
	/**
	 * this method is necessary to select number of maps
	 */
	@FXML
	public void selectNumbMaps()
	{
		selectMapID.getItems().clear();
		mapObservableList.clear();
		int numbMaps = numberMapsID.getSelectionModel().getSelectedItem();
		for (int i = 1; i <= numbMaps; i++)
		{
			selectMapID.getItems().add(i);
		}
	}

	
	/**
     * This sets the strategy of the player
     * 
     * @param player this is the player without strategy
     */
    public Player setStrategy(Player player)
    {
    	String behaviour = player.getName().substring(0, player.getName().length());
   
    	if (behaviour.equals("Aggressive"))
    	{
    		player.setStrategy(new Aggressive());
    	}
    	else if (behaviour.equals("Benevolent"))
    	{
    		player.setStrategy(new Benevolent());
    	}
    	else if (behaviour.equals("Cheater"))
    	{
    		player.setStrategy(new Cheater());
    	}
    	else if (behaviour.equals("Random"))
    	{
    		player.setStrategy(new Random());
    	}
    	return player;
    }
	
    /**
     * this method checks whether the behaviour has already been assigned
     * @param computerName name of the behaviour
     * @return returns true if already assigned, else false
     */
	public boolean checkBehaviourAssigned(String computerName)
	{
		for (Player player : playerObservableList)
		{
			if (player.getName().equals(computerName))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * this method checks whether the map has already been assigned
	 * @param mapName type of the map
	 * @return returns true if map assigned else false
	 */
	public boolean checkMapAssigned(String mapName)
	{
		
		for (String map : mapObservableList)
		{
			
			if (map.equals(mapName))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * this method is necessary to assign player with a behaviour
	 */
	@FXML
	public void assignPlayer()
	{
		if (selectPlayerID.getSelectionModel().getSelectedItem() != null && typePlayerID.getSelectionModel().getSelectedItem() != null)
		{
			String behaviour = typePlayerID.getSelectionModel().getSelectedItem();
			int playerIndex = selectPlayerID.getSelectionModel().getSelectedItem();
			if (!checkBehaviourAssigned(behaviour))
			{
				if (playerObservableList.size() < numberPlayersID.getSelectionModel().getSelectedItem())
				{
					Player player =  new Player(behaviour);
					player = setStrategy(player);
					playerObservableList.add(player);
				}
				else 
				{
					Player player =  new Player(behaviour);
					player = setStrategy(player);
					playerObservableList.set(playerIndex-1, player);
				}	
			}
		}
	}
	
	/**
	 * this method is necessary to select type of map
	 */
	@FXML
	public void assignMap()
	{
		if (selectMapID.getSelectionModel().getSelectedItem() != null && typeMapID.getSelectionModel().getSelectedItem() != null)
		{
			String mapName = typeMapID.getSelectionModel().getSelectedItem();
			int mapIndex = selectMapID.getSelectionModel().getSelectedItem();
			if (!checkMapAssigned(mapName))
			{
				if (mapObservableList.size() < numberMapsID.getSelectionModel().getSelectedItem())
				{
				
					mapObservableList.add(mapName);
				}
				else 
				{
					mapObservableList.set(mapIndex-1, mapName);
				}
			}
		}
	}
}

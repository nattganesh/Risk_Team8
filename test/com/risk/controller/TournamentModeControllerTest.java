package com.risk.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.risk.model.DeckModel;
import com.risk.model.MapModel;
import com.risk.model.PlayerPhaseModel;
import com.risk.model.exceptions.CannotFindException;
import com.risk.model.exceptions.DuplicatesException;
import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import com.risk.model.player.Player;
import com.risk.model.strategy.Aggressive;
import com.risk.model.strategy.Benevolent;
import com.risk.model.strategy.Random;
import com.risk.model.utilities.FileParser;
import com.risk.controller.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static org.junit.Assert.*;
/**
 * Test cases of Tournament Mode
 * 
 * @author Tianyi
 */
public class TournamentModeControllerTest 
{
	private Player p1;
	private Player p2;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	@Before
    public void setUp()
    {
    	p1 = new Player("AggressivePlayer1");
    	p2 = new Player("BenevolentPlayer2");
    	playerList.add(p1);
    	playerList.add(p2);
    }
	
	/**
	 * This is used to test tournament round where the player does the reinforcement, attack and fortification automatically
	 * 
	 * @throws CannotFindException file format is invalid
     * @throws DuplicatesException country is duplicated
     * @throws FileNotFoundException file is not found
	 */
	@Test
	public void testTournamentRound() throws FileNotFoundException, CannotFindException, DuplicatesException
	{
		String path = "src/com/risk/main/mapTextFiles/mapL1.txt";
		MapModel.getMapModel().getContinents().clear();
		MapModel.getMapModel().getCountries().clear();
		PlayerPhaseModel.getPlayerModel().getPlayers().clear();
		MapEditorController mapEditor = new MapEditorController();
		Scanner scan = new Scanner(new File(path));
		FileParser fileParser = new FileParser();
		fileParser.init(scan);
		ObservableList<Player> playerObservableList = FXCollections.observableArrayList(p1,p2);
		mapEditor.setPlayers(playerObservableList);
		mapEditor.setDeck();
		mapEditor.calcStartingArmies();
		mapEditor.autoAssignCountriesToPlayers();
		mapEditor.determinePlayersStartingOrder();
		TournamentModeController tournament = new TournamentModeController();
		String expectResult = "AggressivePlayer1";
		ArrayList<Player> playerList1 = new ArrayList<Player>();
		playerList1  = PlayerPhaseModel.getPlayerModel().getPlayers();
		String result =tournament.tournamentRound(playerList1,30);
		assertTrue(expectResult.equals(result));
	}
}

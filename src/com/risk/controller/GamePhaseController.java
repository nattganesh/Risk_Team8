/**
 * 
 */
package com.risk.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import com.risk.exceptions.CannotFindException;
import com.risk.exceptions.CountLimitException;
import com.risk.exceptions.DuplicatesException;
import com.risk.model.GamePhaseModel;
import com.risk.model.MapModel;
import com.risk.model.PlayerModel;
import com.risk.utilities.RiskMap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * @author DKM
 *
 */
public class GamePhaseController implements Observer{
	GamePhaseModel gamephase;
	MapModel maps;
	PlayerModel players;
	ReinforcementController rController;
	AttackController aController;
	FortificationController fController;
	
	Scene scene;
	Parent root;
	
	String inputFile = "src/com/risk/run/inputtext/input.txt";
	RiskMap riskMapParser;
	private Stage stage;
	
	
	public GamePhaseController(GamePhaseModel game, MapModel m, PlayerModel p) {
		gamephase = game;
		maps = m;
		players = p;
		
		game.addObserver(this);
		
		rController = new ReinforcementController(game, p,m);
		aController = new AttackController(game, p,m);
		fController = new FortificationController(game, p,m);
		
	}

	
	@Override
	public void update(Observable o, Object phase) {
		String view = (String)phase;
		if (view.equals("reinforcement")) {
			FXMLLoader reinforcementLoader = new FXMLLoader(getClass().getResource("/com/risk/view/Reinforcement.fxml"));
			reinforcementLoader.setController(rController);
			try {
				stage.getScene().setRoot(reinforcementLoader.load());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stage.show();
		}
		else if (view.equals("attack")) {
			FXMLLoader attackLoader = new FXMLLoader(getClass().getResource("/com/risk/view/Attack.fxml"));
			attackLoader.setController(aController);
			try {
				stage.getScene().setRoot(attackLoader.load());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stage.show();
		}
		else if(view.equals("fortification")) {
			FXMLLoader fortificationLoader = new FXMLLoader(getClass().getResource("/com/risk/view/Fortification.fxml"));
			fortificationLoader.setController(fController);
			try {
				stage.getScene().setRoot(fortificationLoader.load());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stage.show();
		}
		
	}


	/**
	 * @param primaryStage 
	 * @throws DuplicatesException 
	 * @throws CannotFindException 
	 * @throws CountLimitException 
	 * @throws IOException 
	 * 
	 */
	public void setUp(Stage primaryStage) throws CountLimitException, CannotFindException, DuplicatesException, IOException {
		stage = primaryStage;
		riskMapParser = new RiskMap(maps, inputFile);
		riskMapParser.setUp();
		InitializationController selectController = new InitializationController(gamephase, players, maps);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/risk/view/Initialization.fxml"));
		loader.setController(selectController);
		Parent root = loader.load();
	    Screen screen = Screen.getPrimary();
	    Rectangle2D bounds = screen.getVisualBounds();
	    primaryStage.setX((bounds.getWidth() - primaryStage.getWidth()) / 2); 
	    primaryStage.setY((bounds.getHeight() - primaryStage.getHeight()) / 2);  
	    primaryStage.setWidth(bounds.getWidth() / 2);
	    primaryStage.setHeight(bounds.getHeight() / 2);
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();	
        
	}




}

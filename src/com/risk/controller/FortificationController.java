/**
 * 
 */
package com.risk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import com.risk.model.GamePhaseModel;
import com.risk.model.MapModel;
import com.risk.model.PlayerModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author DKM
 *
 */
public class FortificationController implements Initializable {
	
	private PlayerModel players;
	private MapModel maps;
	private GamePhaseModel gamephase;
	
	public FortificationController(GamePhaseModel game, PlayerModel p, MapModel m) {
		gamephase = game;
		players = p;
		maps = m;
		
		
	}
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(players.getNumberOfPlayer());
	}
	public void onNextPlayer(ActionEvent event) throws IOException {
		players.IncrementPlayerIndex();
		gamephase.setPhase("reinforcement");
	}

}

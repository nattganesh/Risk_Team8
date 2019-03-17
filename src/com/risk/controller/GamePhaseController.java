/**
 * This file is necessary for changing the phases of the RISK game
 *
 * @author DKM
 *
 */
package com.risk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.risk.model.ActionModel;
import com.risk.model.GamePhaseModel;
import com.risk.model.PlayerModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GamePhaseController implements Observer, Initializable{

    ReinforcementController rController;
    AttackController aController;
    FortificationController fController;
    SetUpController sController;
    
    Scene scene;
    Parent root;
    private Stage stage;
    
    @FXML
    Pane mainPane;
    
    @FXML
    Label playerID;
    
    @FXML
    Label phaseID;
    
    @FXML
    ListView<String> actionMessage;
    
    /**
     * This is a constructor for GamePhaseController
     *
     * @param s Stage of Javafx application to set different phase in the game
     */
    public GamePhaseController(Stage s)
    {
        GamePhaseModel.getGamePhaseModel().addObserver(this);
        this.stage = s;
    }
    
    /**
     * This method receives notification from the changes in the state from the
     * GamePhaseModel, then changes the scene to the next phase.
     */
    @Override
    public void update(Observable o, Object phase)
    {
    	ActionModel.getActionModel().clearAction();
        String view = (String) phase;
        phaseID.setText(view);
        if (view.equals("setup"))
        {
        	try {
        		mainPane.getChildren().clear();
				mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/com/risk/view/SetUp.fxml")));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        else if (view.equals("reinforcement"))
        {
        	try {
        		playerID.setText(PlayerModel.getPlayerModel().getCurrentPlayer().getName());
        		mainPane.getChildren().clear();
				mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/com/risk/view/Reinforcement.fxml")));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        else if (view.equals("attack"))
        {
        	try {
        		playerID.setText(PlayerModel.getPlayerModel().getCurrentPlayer().getName());
        		mainPane.getChildren().clear();
				mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/com/risk/view/Attack.fxml")));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        else if (view.equals("fortification"))
        {
        	try {
        		playerID.setText(PlayerModel.getPlayerModel().getCurrentPlayer().getName());
        		mainPane.getChildren().clear();
				mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/com/risk/view/Fortification.fxml")));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        else if (view.equals("Conquering"))
        {
        	try {
        		playerID.setText(PlayerModel.getPlayerModel().getCurrentPlayer().getName());
        		mainPane.getChildren().clear();
				mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/com/risk/view/ConqueringTerritoryView.fxml")));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }

    }

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		actionMessage.setItems(ActionModel.getActionModel().getActions());
		try {
			mainPane.getChildren().clear();
			mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/com/risk/view/MapSelector.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

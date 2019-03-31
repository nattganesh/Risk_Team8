/**
 * 
 */
package com.risk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.risk.model.GamePhaseModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class StartUpController implements Initializable{

    @FXML
    private ComboBox<String> modeID;
    
    private Stage stage;
    
    public StartUpController(Stage primaryStage)
    {
    	stage = primaryStage;
    }

    /**
     * this method initializes combobox for different mode
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		modeID.getItems().addAll("single", "tournament");
	}
    
	@FXML
	public void selectGameHandler()
	{
		if (modeID.getSelectionModel().getSelectedItem() != null)
		{
			String mode = modeID.getSelectionModel().getSelectedItem();
			if (mode.equals("single"))
			{
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/risk/view/LayoutView.fxml"));
		        GamePhaseController gamephase = new GamePhaseController();
		        loader.setController(gamephase);
		        
				try {
					stage.getScene().setRoot(loader.load());
				} catch (IOException e) {
					e.printStackTrace();
				}
				stage.show();
			}
			else if (mode.equals("tournament"))
			{
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/risk/view/TournamentMode.fxml"));
		        TournamentModeController tournamentController = new TournamentModeController();
		        loader.setController(tournamentController);
		  
				try {
					stage.getScene().setRoot(loader.load());
				} catch (IOException e) {
					e.printStackTrace();
				}
				stage.show();
			}
		}
	}
}


/*
 * if (view.equals("reinforcement")) {
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
 */
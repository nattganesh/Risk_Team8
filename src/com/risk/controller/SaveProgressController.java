/**
 * 
 * Necessary for handling save progress view during game play
 * 
 * @version 3.0
 * @author DKM
 * 
 */
package com.risk.controller;


import com.risk.model.GamePhaseModel;
import com.risk.model.MapModel;
import com.risk.model.utilities.saveGame.SaveGame;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SaveProgressController
{

	@FXML
	TextField mapID;
	
	@FXML
	TextField fileID;
	
	@FXML
	AnchorPane saveProgressID;
	
	@FXML
	public void closeSaveProgress()
	{
		saveProgressID.setVisible(false);
	}
	
	/**
	 * This method save the game progress
	 */
	@FXML
	public void saveProgress()
	{
		mapID.setText(MapModel.getMapModel().getMapType());
		if (!fileID.getText().trim().isEmpty())
		{
			System.out.println(SaveGame.generate(fileID.getText(), MapModel.getMapModel().getMapType()));
			GamePhaseModel.getGamePhaseModel().setPhase("startup");
		}
	}
	
	/**
	 * This method send game back to startup
	 */
	@FXML
	public void quitGame()
	{
		GamePhaseModel.getGamePhaseModel().setPhase("startup");
	}
	
	/**
	 * This method controls showing save progress UI
	 */
	public void showSaveProgress()
	{
		mapID.setText(MapModel.getMapModel().getMapType());
		saveProgressID.setVisible(true);
	}
	
}

/**
 * 
 */
package com.risk.controller;

import java.util.Observable;

import com.risk.model.GamePhaseModel;
import com.risk.model.utilities.saveGame.SaveGame;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * @author DKM
 *
 */
public class SaveProgressController{

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
	
	@FXML
	public void saveProgress()
	{
		if (!fileID.getText().trim().isEmpty() && !mapID.getText().trim().isEmpty())
		{
			System.out.println(SaveGame.generate(fileID.getText(), mapID.getText())); 
			GamePhaseModel.getGamePhaseModel().setPhase("startup");
		}
	}
	
	@FXML
	public void quitGame()
	{
		GamePhaseModel.getGamePhaseModel().setPhase("startup");
	}
	
	public void showSaveProgress()
	{
		saveProgressID.setVisible(true);
	}
	
}

package com.risk.controller;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import com.risk.model.GamePhaseModel;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

/**
 * This class is necessary for Controller of the Attack Phase
 * @author DKM
 *
 */
public class AttackController extends Observable implements Initializable
{

	/**
	 * This is the constructor for AttackController class
	 */
    public AttackController()
    {
    }

   /** (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
    }

    /**
     * This method sets the GamePhaseModel to fortification, which notifies the subscribed GameController 
     * to set new scene
     * 
     * @param event listens for click event
     */
    public void goToFortificationPhase(ActionEvent event) 
    {
        GamePhaseModel.getGamePhaseModel().setPhase("fortification");
    }
}

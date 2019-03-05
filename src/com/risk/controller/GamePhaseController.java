/**
 * This file is necessary for changing the phases of the RISK game
 *
 * @author DKM
 *
 */
package com.risk.controller;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import com.risk.model.GamePhaseModel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GamePhaseController implements Observer {

    ReinforcementController rController;
    AttackController aController;
    FortificationController fController;
    SetUpController sController;
    
    Scene scene;
    Parent root;
    private Stage stage;

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
        String view = (String) phase;
        if (view.equals("setup"))
        {
        	sController = new SetUpController();
               
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/com/risk/view/SetUp.fxml"));
            setupLoader.setController(sController);
            System.out.println("we in setup");
            try
            {
                stage.getScene().setRoot(setupLoader.load());
                

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            stage.show();
        }
        else if (view.equals("reinforcement"))
        {
            rController = new ReinforcementController();

            FXMLLoader reinforcementLoader = new FXMLLoader(getClass().getResource("/com/risk/view/Reinforcement.fxml"));
            reinforcementLoader.setController(rController);
            System.out.println("we in reinforcement");
            try
            {
                stage.getScene().setRoot(reinforcementLoader.load());

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            stage.show();
        }
        else if (view.equals("attack"))
        {
            aController = new AttackController();

            FXMLLoader attackLoader = new FXMLLoader(getClass().getResource("/com/risk/view/Attack.fxml"));
            attackLoader.setController(aController);
            try
            {
                stage.getScene().setRoot(attackLoader.load());

                System.out.println("we in attack");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            stage.show();
        }
        else if (view.equals("fortification"))
        {
            fController = new FortificationController();
            FXMLLoader fortificationLoader = new FXMLLoader(getClass().getResource("/com/risk/view/Fortification.fxml"));
            fortificationLoader.setController(fController);
            try
            {
                stage.getScene().setRoot(fortificationLoader.load());
                System.out.println("we in fortification");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            stage.show();
        }

    }
}

/**
 *
 */
package com.risk.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import com.risk.model.exceptions.CannotFindException;
import com.risk.model.exceptions.CountLimitException;
import com.risk.model.exceptions.DuplicatesException;
import com.risk.model.GamePhaseModel;
import com.risk.model.MapModel;
import com.risk.model.PlayerModel;
//import com.risk.model.utilities.RiskMap;

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
public class GamePhaseController implements Observer {


    ReinforcementController rController;
    AttackController aController;
    FortificationController fController;

    Scene scene;
    Parent root;
    private Stage stage;

    public GamePhaseController(Stage s)
    {
        GamePhaseModel.getGamePhaseModel().addObserver(this);

        this.stage = s;
     

    }

    @Override
    public void update(Observable o, Object phase)
    {
        String view = (String) phase;
        if (view.equals("reinforcement"))
        {
        	   rController = new ReinforcementController();
               
            FXMLLoader reinforcementLoader = new FXMLLoader(getClass().getResource("/com/risk/view/Reinforcement.fxml"));
            reinforcementLoader.setController(rController);
            System.out.println("here~");
            try
            {
            	
                stage.getScene().setRoot(reinforcementLoader.load());
                
                System.out.println("we in reinforcement");
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            stage.show();
        }

    }
}

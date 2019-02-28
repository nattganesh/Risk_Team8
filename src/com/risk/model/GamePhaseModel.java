/**
 *
 */
package com.risk.model;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author DKM
 *
 */
public class GamePhaseModel extends Observable {

    String phase = "setup";
    private static GamePhaseModel gamePhaseModel;

    private GamePhaseModel()
    {
    }

    public void setPhase(String p)
    {
        phase = p;
        setChanged();
        
        notifyObservers(phase);
    }

    public static GamePhaseModel getGamePhaseModel()
    {
        if (gamePhaseModel == null)
        {
            gamePhaseModel = new GamePhaseModel();
        }
        return gamePhaseModel;
    }
}

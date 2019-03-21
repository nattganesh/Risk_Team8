/**
 * This file is an entry point for JavaFX application. Necessary to parse the file,
 * instantiate the only Model, a selectPlayerController (controller) and SelectPlayer.fxml (view) for the selecting number of players.
 *
 * The Model will be passed around different controller and view during this application.
 *
 * @author Natheepan
 * @author Tianyi
 * @author Dong Jae Kim
 * @version 1.0
 * @since 1.0
 *
 */
package com.risk.main;

import com.risk.controller.GamePhaseController;
import com.risk.controller.MapEditorController;
import com.risk.controller.ReinforcementController;
import com.risk.model.exceptions.CannotFindException;
import com.risk.model.exceptions.CountLimitException;
import com.risk.model.exceptions.DuplicatesException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Run extends Application {
	
	@FXML
	Pane content;
	
	@FXML
	Pane mainPane;

    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * Start is called when JavaFX is ready to be initialized
     *
     * @throws IOException
     * @throws DuplicatesException
     * @throws CannotFindException
     * @throws CountLimitException
     *
     * @see javafx.application.Application
     */
    @Override
    public void start(Stage primaryStage) throws CountLimitException, CannotFindException, DuplicatesException, IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/risk/view/LayoutView.fxml"));

        GamePhaseController gamephase = new GamePhaseController(primaryStage);
        loader.setController(gamephase);

        Parent root = loader.load();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX((bounds.getWidth() - primaryStage.getWidth()));
        primaryStage.setY((bounds.getHeight() - primaryStage.getHeight()));
        primaryStage.setWidth(bounds.getWidth() / 1.25);
        primaryStage.setHeight(bounds.getHeight() / 1.5);
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

}

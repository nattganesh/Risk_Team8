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
import com.risk.model.MapModel;
import com.risk.model.PlayerModel;
import com.risk.model.player.Player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GamePhaseController implements Observer, Initializable{

    ReinforcementController rController;
    AttackController aController;
    FortificationController fController;
    SetUpController sController;
    
    XYChart.Series set1 = new XYChart.Series<>();
    ObservableList<Player> playerBar = FXCollections.observableArrayList();
    
    
    Scene scene;
    Parent root;
    private Stage stage;
    
    @FXML
    Pane mainPane;
    
    @FXML
    TextField playerID;
    
    @FXML
    TextField phaseID;
    

    @FXML
    private BarChart<?, ?> worldDomination;

    @FXML
    private CategoryAxis X;

    @FXML
    private NumberAxis y;
    
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
    }
    
    
    private class playerObserver implements Observer
    {
		@Override
		public void update(Observable o, Object arg) {
			Player player = (Player)arg;
			
//			ObservableList<XYChart.Series<String, Integer>> answer = FXCollections.observableArrayList();
//			System.out.println(player.getName() + " occupied territory:  " + player.getOccupiedCountries().size());
			
		
			int index = playerBar.indexOf(player);
			if (index == -1)
			{
				playerBar.add(player);
			}
			else
			{
				playerBar.set(index, player);
				
			}
			
			
			initializeBar();
//			
		  	
//		  	for (Data<String, Integer> data : set1.getData())
//		  	{
//		  		if (data.getXValue().equals(player.getName())) {
//		  			data.setYValue(data.getYValue().intValue()+1);
//		  		}
//		  		else 
//		  		{
//		  			set1.getData().add(new XYChart.Data(player.getName(), 1));
//		  			System.out.println("hello");
//		  		}
//		  		System.out.println("WTF? " + data.getXValue());
//		  	}
//		  	System.out.println(set1.getData());
//		  	set1.getData()set.
		  	
		  	
//		  	System.out.println(playerBar.size());
		  	
		  	
		
		  	
		  	
//		    else 
//		    {
//		    	(Player)set1.getData().get(index);
//		    }
//			set1.getData().add(new XYChart.Data(player.getName(), 1));
//			worldDomination.getData().addAll(set1);
			
		}
		
		public void initializeBar()
		{
			set1.getData().clear();
			worldDomination.getData().clear();
			for (Player p : playerBar)
			{
				System.out.println(p.getName() + " : " + p.getOccupiedCountries().size());
				set1.getData().add(new XYChart.Data(p.getName(), (	p.getOccupiedCountries().size() * 100.0/42)));
				
			}

			worldDomination.getData().addAll(set1);
			System.out.println("=====");
		}
		
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		PlayerModel.getPlayerModel().addObserver(new playerObserver());
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

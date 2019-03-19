/**
 * This file is necessary for changing the phases of the RISK game
 *
 * @author DKM
 *
 */
package com.risk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.risk.model.ActionModel;
import com.risk.model.GamePhaseModel;
import com.risk.model.MapModel;
import com.risk.model.PlayerModel;
import com.risk.model.map.Country;
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
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
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
    

    XYChart.Series<String, Integer> army = new XYChart.Series<>();
    ObservableList<PieChart.Data> playerPieChart = FXCollections.observableArrayList();
    ArrayList<Player> piePlayersList = new ArrayList<>();
//    ObservableList<Player> playerBarGraph = FXCollections.observableArrayList();
    ArrayList<Player> barPlayersList = new ArrayList<>();
    
    
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
    private PieChart worldDomination1;
    
    @FXML
    private BarChart<String, Integer> worldDomination2;
    
   

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
    
    /**
     * This class observes players
     * @author DKM
     *
     */
    private class playerObserver implements Observer
    {
		@Override
		public void update(Observable o, Object arg) {
			Player player = (Player)arg;
			System.out.println("here");
			int index = piePlayersList.indexOf(player);
			if (index == -1)
			{
				piePlayersList.add(player);
				
				playerPieChart.add(new PieChart.Data(player.getName(), player.getOccupiedCountries().size() * 100.0/42));
			}
			else
			{
				piePlayersList.set(index, player);
				playerPieChart.get(index).setName(player.getName());
				playerPieChart.get(index).setPieValue(player.getOccupiedCountries().size() * 100.0/42);
				
			}
			System.out.println("initializing bar");		
		}	
    }
    
    
    private class mapObserver implements Observer
    {

		@Override
		public void update(Observable o, Object arg) {
			Country country = (Country)arg;
			System.out.println("army count changed for " + country.getName() + " (" + country.getArmyCount() + ")");
			Player player = country.getRuler();
			int index = barPlayersList.indexOf(player);
			if (index == -1)
			{
				barPlayersList.add(player);
				army.getData().add(new Data<String, Integer>(player.getName(), player.getTotalArmy()));	
			}
			else
			{
				barPlayersList.set(index, player);
				army.getData().get(index).setXValue(player.getName());
				army.getData().get(index).setYValue(player.getTotalArmy());
			}
			worldDomination2.getData().clear();
			worldDomination2.getData().add(army);	
			
		}   
    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		PlayerModel.getPlayerModel().addObserver(new playerObserver());
		MapModel.getMapModel().addObserver(new mapObserver());
		actionMessage.setItems(ActionModel.getActionModel().getActions());
		
		worldDomination1.setData(playerPieChart);
		
		try {
			mainPane.getChildren().clear();
			mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/com/risk/view/MapSelector.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

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
import javafx.collections.ListChangeListener;
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
    

    ObservableList<XYChart.Series<String, Integer>> barData =  FXCollections.observableArrayList();
    XYChart.Series<String, Integer> serie = new XYChart.Series<String, Integer>();
    
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    
    
    ArrayList<Player> piePlayersList = new ArrayList<>();
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
				
				pieChartData.add(new PieChart.Data(player.getName()+" ("+Math.round(player.getOccupiedCountries().size() * 100.0/42) + "%)", 
						player.getOccupiedCountries().size() * 100.0/42));
			}
			else
			{
				piePlayersList.set(index, player);
				pieChartData.get(index).setName(player.getName()+" ("+Math.round(player.getOccupiedCountries().size() * 100.0/42) + "%)");
				pieChartData.get(index).setPieValue(player.getOccupiedCountries().size() * 100.0/42);
				
				
				
			}
			
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
				Data<String, Integer> data = new Data<String, Integer>(player.getName()+" ("+player.getTotalArmy()+")", player.getTotalArmy());
				serie.getData().add(data);	
				System.out.println("exception");
			}
			else
			{
				barPlayersList.set(index, player);
				serie.getData().get(index).setXValue(player.getName()+" ("+player.getTotalArmy()+")");
				serie.getData().get(index).setYValue(player.getTotalArmy());
			}
		}   
    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		PlayerModel.getPlayerModel().addObserver(new playerObserver());
		MapModel.getMapModel().addObserver(new mapObserver());
		actionMessage.setItems(ActionModel.getActionModel().getActions());
		worldDomination1.setData(pieChartData);
		barData.add(serie);
		worldDomination2.setData(barData);	
		
		
		
		try {
			mainPane.getChildren().clear();
			mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/com/risk/view/MapSelector.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

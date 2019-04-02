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
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.risk.model.ActionModel;
import com.risk.model.GamePhaseModel;
import com.risk.model.MapModel;
import com.risk.model.PlayerPhaseModel;
import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import com.risk.model.player.Player;
import com.risk.model.utilities.saveGame.SaveGame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GamePhaseController implements Observer, Initializable {

    ReinforcementController rController;
    AttackController aController;
    FortificationController fController;
    SetUpController sController;
    
    @FXML
    SaveProgressController saveController;

    ObservableList<XYChart.Series<String, Integer>> barData = FXCollections.observableArrayList();
    ObservableList<Data<String, Integer>> data = FXCollections.observableArrayList();
    XYChart.Series<String, Integer> serie = new XYChart.Series<String, Integer>();
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    ArrayList<Player> piePlayersList = new ArrayList<>();
    ArrayList<Player> barPlayersList = new ArrayList<>();

    Scene scene;
    Parent root;
    String view = "setup";
    Stage primaryStage;

    @FXML
    Pane mainPane;

    @FXML
    AnchorPane savePane;
    
    @FXML
    TextField playerID;

    @FXML
    TextField phaseID;

    @FXML
    private PieChart worldDomination1;

    @FXML
    private BarChart<String, Integer> worldDomination2;

    @FXML
    private ListView<Continent> worldDomination3;

    @FXML
    private CategoryAxis X;

    @FXML
    private NumberAxis y;

    @FXML
    ListView<String> actionMessage;
    
    @FXML
    AnchorPane phaseDominationViewID;

    @FXML
    AnchorPane winnerPane;

    @FXML
    Text winnerID;
    
    @FXML
    Button saveID;
    
    @FXML
    Button newID;

    /**
     * This is a constructor for GamePhaseController
     *
     */
    public GamePhaseController(Stage stage)
    {
        GamePhaseModel.getGamePhaseModel().addObserver(this);
        primaryStage = stage;
    }

    /**
     * this method saves the progress of the current game
     */
    @FXML
    public void saveProgress()
    {
    	saveController.showSaveProgress();
    }
    
    @FXML
    public void newGame()
    {
    	GamePhaseModel.getGamePhaseModel().setPhase("startup");
    	newID.setVisible(false);
    }


    /**
     * This method receives notification from the changes in the state from the
     * GamePhaseModel, then changes the scene to the next phase.
     */
    @Override
    public void update(Observable o, Object phase)
    {
        ActionModel.getActionModel().clearAction();
        view = (String) phase;
        System.out.println("phase notified " + view);
        

        
        if (!view.equals("setup") && !view.equals("startup"))
        {	
        	saveID.setVisible(true);
        	phaseDominationViewID.setVisible(true);
        }	
        if (view.equals("setup"))
        {
            try
            {
                phaseID.setText(view);
                playerID.setText(PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getName());
                mainPane.getChildren().clear();
                mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/com/risk/view/SetUp.fxml")));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else if (view.equals("startup"))
        {
        	saveController.closeSaveProgress();
        	saveID.setVisible(true);
        	phaseDominationViewID.setVisible(false);
        	restartGame();
        	
            
          	for (Player p : PlayerPhaseModel.getPlayerModel().getPlayers())
          	{
          		System.out.println(p.getCards().size());
          	}
            
        }
        else if (view.equals("winner"))
        {
            phaseID.setText(view);
            winnerID.setText(PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getName() + " is the winner");
            mainPane.getChildren().clear();
            saveID.setVisible(false);
            winnerPane.setVisible(true);
        }
        else if (view.equals("setup complete"))
        {
            phaseID.setText(view);
            HashSet<String> s = new HashSet<String>();
            for (Continent continent : MapModel.getMapModel().getContinents())
            {
                for (Country country : continent.getCountries())
                {
                    s.add(country.getRuler().getName());
                }
                if (s.size() == 1)
                {
                    continent.setRuler(continent.getCountries().get(0).getRuler());
                }
                else
                {
                    continent.setRuler(null);
                }
                s.clear();
            }
            worldDomination2.getData().setAll(serie);
            worldDomination3.setItems(MapModel.getMapModel().getContinents());
            updateContinentDominationView();
        }
        else if (view.equals("reinforcement"))
        {
            try
            {
                if (PlayerPhaseModel.getPlayerModel().getCurrentPlayer().isComputerPlayer())
                {
                	// checks if it's computer player?
                	 phaseID.setText("automatic");
                     playerID.setText(PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getName());
                     mainPane.getChildren().clear();
                }  
                else 
                {
                	 // else it's human
                	 phaseID.setText(view);
                     playerID.setText(PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getName());
                     mainPane.getChildren().clear();
                     mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/com/risk/view/Reinforcement.fxml")));
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else if (view.equals("attack"))
        {
            try
            {
                phaseID.setText(view);
                playerID.setText(PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getName());

                mainPane.getChildren().clear();
                mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/com/risk/view/Attack.fxml")));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        else if (view.equals("fortification"))
        {
            try
            {
                phaseID.setText(view);
                playerID.setText(PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getName());
                mainPane.getChildren().clear();
                mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/com/risk/view/Fortification.fxml")));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method observes the PlayerPhaseModel when a country has been
     * occupied
     */
    private class playerObserver implements Observer {

        @Override
        public void update(Observable o, Object arg)
        {
            Country c = (Country) arg;
            Player player = c.getRuler();
            int index = piePlayersList.indexOf(player);

            System.out.println((player.getOccupiedCountries().size() + " / " + MapModel.getMapModel().getCountries().size()));
            if (checkWinner(player))
            {
                GamePhaseModel.getGamePhaseModel().setPhase("winner");
            }

            if (index == -1)
            {
                piePlayersList.add(player);
                pieChartData.add(new PieChart.Data(player.getName() + " (" + Math.round(player.getOccupiedCountries().size() * 100.0 / MapModel.getMapModel().getCountries().size()) + "%)",
                        player.getOccupiedCountries().size() * 100.0 / 42));
            }
            else
            {
                piePlayersList.set(index, player);
                pieChartData.get(index).setName(player.getName() + " (" + Math.round(player.getOccupiedCountries().size() * 100.0 / MapModel.getMapModel().getCountries().size()) + "%)");
                pieChartData.get(index).setPieValue(player.getOccupiedCountries().size() * 100.0 / 42);
            }
        }

        public boolean checkWinner(Player p)
        {

            return p.getOccupiedCountries().size() == MapModel.getMapModel().getCountries().size();

        }

    }

    /**
     *
     * This method observes the PlayerPhaseModel when a country has been
     * occupied
     *
     */
    private class continentObserver implements Observer {

        @Override
        public void update(Observable arg0, Object arg)
        {
            Country country = (Country) arg;

            if (view.equals("attack"))
            {
                boolean occupy = true;
                for (Country c : country.getContinent().getCountries())
                {
                    if (!c.getRuler().getName().equals(country.getRuler().getName()))
                    {
                        occupy = false;
                        break;
                    }
                }

                if (occupy)
                {
                    country.getContinent().setRuler(country.getRuler());
                    updateContinentDominationView();
                }
            }
        }
    }

    /**
     * This method observes the PlayerPhaseModel when a country has been
     * occupied
     *
     * @author DKM
     *
     */
    private class mapObserver implements Observer {

        @Override
        public void update(Observable o, Object arg)
        {
            Country country = (Country) arg;

            Player player = country.getRuler();
            int index = barPlayersList.indexOf(player);

            if (index == -1)
            {
                barPlayersList.add(player);
                serie.getData().add(new XYChart.Data<String, Integer>(player.getName() + " (" + player.getTotalArmy() + ")", (int) player.getTotalArmy()));
            }

            else
            {
                barPlayersList.set(index, player);
                serie.getData().get(index).setXValue(player.getName() + " (" + player.getTotalArmy() + ")");
                serie.getData().get(index).setYValue((int) player.getTotalArmy());
            }
            if (!view.equals("setup"))
            {
                worldDomination2.getData().setAll(serie);
            }

        }
    }

    /**
     * This method is used to update the domination of continent when the owner
     * of continents changes
     *
     */
    public void updateContinentDominationView()
    {
        worldDomination3.setCellFactory(param -> new ListCell<Continent>() {
            @Override
            protected void updateItem(Continent continent, boolean empty)
            {
                super.updateItem(continent, empty);
                if (empty || continent == null || continent.getRuler() == null)
                {

                    setText(null);

                }
                else
                {
                    setText(continent.getName() + "(" + continent.getRuler().getName() + ")");
                }
            }
        });
    }

    /**
     * This method is data binding for connection between controller and UI.
     *
     * @see javafx.fxml.Initializable
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        PlayerPhaseModel.getPlayerModel().addObserver(new playerObserver());
        PlayerPhaseModel.getPlayerModel().addObserver(new continentObserver());
        MapModel.getMapModel().addObserver(new mapObserver());
        actionMessage.setItems(ActionModel.getActionModel().getActions());
        worldDomination1.setData(pieChartData);

        try
        {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/com/risk/view/MapSelector.fxml")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    
    public void restartGame()
    {

        barData.clear();
        data.clear();
        serie.getData().clear();
        pieChartData.clear();
        piePlayersList.clear();
        barPlayersList.clear();
        
    	
    	 MapModel.getMapModel().clear();
    	 PlayerPhaseModel.getPlayerModel().clear();
    	 
    	
	   	 FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/risk/view/StartUp.fxml"));
	     StartUpController sController = new StartUpController(primaryStage);
	     loader.setController(sController);
	     Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
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

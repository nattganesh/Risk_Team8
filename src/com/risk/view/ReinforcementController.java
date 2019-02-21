/**
 *
 */
package com.risk.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.risk.army.Player;
import com.risk.army.PlayerController;
import com.risk.map.Country;
import com.risk.run.Model;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

/**
 * @author DKM
 *
 */
public class ReinforcementController implements Initializable {

    private ArrayList<Country> countryButton;
    private PlayerController pController;
    private final Model model;

    @FXML
    FlowPane fPane;

    @FXML
    Label armyAvailable;

    @FXML
    TextField inputArmy;

    @FXML
    Label playerId;

    @FXML
    ListView<Country> countryId;
    @FXML
    Button nextPlayer;

    @FXML
    Label inputArmyError;

    public ReinforcementController(Model m)
    {
        model = m;
    }

    /**
     * This is data binding
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        playerId.setText(getName());
        armyAvailable.setText("Army available => " + Integer.toString(model.getCurrentPlayer().getReinforcement()));
        playerId.setPadding(new Insets(0, 0, 0, 5));
        inputArmy.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    inputArmy.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        countryId.setItems(model.getCurrentPlayerCountryObs());
        countryId.setCellFactory(param -> new ListCell<Country>() {
            @Override
            protected void updateItem(Country item, boolean empty)
            {
                super.updateItem(item, empty);
                if (empty || item == null || item.getName() == null)
                {
                    setText(null);
                }
                else
                {
                    setText(item.getName() + " => Army: " + item.getArmyCount());

                }
            }
        });
    }

    public String getName()
    {
        return "current Player: " + model.getCurrentPlayer().getName();
    }

    public void setArmy()
    {
        int Armyinput = Integer.parseInt(inputArmy.getText());

        if (model.getCurrentPlayer().getAvailableReinforcement() == 0)
        {
            inputArmyError.setText("0 available army");
        }
        else if (countryId.getSelectionModel().getSelectedItem() == null
                || Armyinput > model.getCurrentPlayer().getAvailableReinforcement() || Armyinput <= 0)
        {

            inputArmyError.setText("Invalid Entry");
        }
        else
        {
            for (Country c : model.getCurrentPlayer().getOccupiedCountries())
            {
                if (c.equals(countryId.getSelectionModel().getSelectedItem()))
                {
                    c.setArmyCount(Armyinput);
                    model.setView();
                    inputArmyError.setText("sucessful");
                    break;

                }
            }
            model.getCurrentPlayer().setReinforcement(Armyinput);
            armyAvailable.setText(
                    "Army available => " + Integer.toString(model.getCurrentPlayer().getAvailableReinforcement()));
        }
    }

    public void onNextPlayer()
    {
        if (model.getCurrentPlayer().getAvailableReinforcement() > 0)
        {

        }
        else
        {
            model.IncrementPlayerIndex();
            armyAvailable.setText("Army available => " + Integer.toString(model.getCurrentPlayer().getReinforcement()));
            inputArmyError.setText("");
            playerId.setText(getName());
        }
    }

}

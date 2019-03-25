package com.risk.controller;



import java.net.URL;
import java.util.ResourceBundle;

import com.risk.model.PlayerPhaseModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class WinnerController implements Initializable {

    @FXML
    private Text winnerID;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		winnerID.setText(PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getName() + " is the WINNER");
	}
}

/**
 * 
 */
package com.risk.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.risk.run.Model;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author DKM
 *
 */
public class AttackController implements Initializable {
	
	private Model model;
	
	public AttackController(Model m) {
		model = m;
	}
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(model.getPlayerSize());
	}
	public void goToFortificationPhase(ActionEvent event) throws IOException  {
		
			FortificationController fortificationController = new FortificationController(model);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Fortification.fxml"));
			loader.setController(fortificationController);
			Parent root = loader.load();
			Scene ReinforcementScene = new Scene(root);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(ReinforcementScene);
			window.show();
		
	}
}

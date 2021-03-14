/**
 * 
 */
package fr.isen.java2.view;

import fr.isen.java2.App;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class HomeScreenController {
	
	@FXML
	public void handleLaunchButton() throws IOException {
		// This is quite sparse : just load the next scene on click, and voilà!		
		App.setRoot("/isen/quiz/fr.isen.java2.view/QuizzOverview");
	}

	@FXML
	private void handleCloseButton(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	public void handleHomeButton() throws IOException {
		// This is quite sparse : just load the next scene on click, and voilà!
		App.setRoot("/fr/isen/java2/view/HomePageScreen");
	}

	@FXML
	public void handleUserListButton() throws IOException {
		// This is quite sparse : just load the next scene on click, and voilà!
		App.setRoot("/fr/isen/java2/view/AddUserScreen");
	}
}

package fr.isen.java2;

import com.jfoenix.controls.JFXDatePicker;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.time.LocalDate;

public class App extends Application {

	private static Scene scene;

//	@Override
//	public void start(Stage stage) throws IOException {
//		// Put a fancy name on our window
//		stage.setTitle("my beautiful title");
//		stage.initStyle(StageStyle.UNDECORATED);
//		// Load our first scene using the convenient method provided by JavaFX. Mind
//		// your package hierarchy !
//		scene = new Scene(loadFXML("/fr/isen/java2/view/AddUserScreen"), 640, 480);
//
//		// get the primary stage and put the first scene on the stage
//		stage.setScene(scene);
//		// Curtains up !
//		stage.show();
//	}

	//init xy offsets
	private double xOffset = 0;
	private double yOffset = 0;
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = loadFXML("/fr/isen/java2/view/HomePageScreen");
		stage.initStyle(StageStyle.UNDECORATED);

		//set mouse pressed
		root.setOnMousePressed(event -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();
		});
		//set mouse drag
		root.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() - xOffset);
			stage.setY(event.getScreenY() - yOffset);
		});

		scene = new Scene(root);

		stage.setScene(scene);
		stage.show();
	}

	public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

}

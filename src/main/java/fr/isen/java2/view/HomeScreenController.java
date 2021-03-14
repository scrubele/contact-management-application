package fr.isen.java2.view;

import fr.isen.java2.App;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class HomeScreenController {

    @FXML
    public void handleHomeButton() throws IOException {
        App.setRoot(App.homePageScreenFXML);
    }

    @FXML
    public void handleUserListButton() throws IOException {
        App.setRoot(App.addUserScreenFXML);
    }

    @FXML
    public void handleListView() throws IOException {
        App.launchUserListController();
    }

    @FXML
    private void handleCloseButton(MouseEvent event) {
        System.exit(0);
    }
}

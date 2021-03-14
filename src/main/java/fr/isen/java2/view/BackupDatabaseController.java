package fr.isen.java2.view;

import fr.isen.java2.App;
import fr.isen.java2.db.entities.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BackupDatabaseController implements Initializable {

    @FXML
    private void handleCloseButton(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    public void handleHomeButton() throws IOException {
        App.setRoot("/fr/isen/java2/view/HomePageScreen");
    }

    @FXML
    public void handleUserListButton() throws IOException {
        App.launchUserListController("/fr/isen/java2/view/UserListScreen");
    }

    @FXML
    public void handleAddUserButton() throws IOException {
        App.launchUserListController("/fr/isen/java2/view/AddUserScreen");
    }

    @FXML
    public void handleDatabaseBackup() {
        List<Person> personList = App.personDao.listPersons();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}

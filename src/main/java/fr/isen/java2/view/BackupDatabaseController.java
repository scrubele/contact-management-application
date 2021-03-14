package fr.isen.java2.view;

import fr.isen.java2.App;
import fr.isen.java2.db.database.DatabaseBackupWriter;
import fr.isen.java2.db.entities.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class BackupDatabaseController implements Initializable {

    @FXML
    TextArea CSVTextArea; //i think you already know about this

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
        App.setRoot("/fr/isen/java2/view/AddUserScreen");
    }

    @FXML
    public void handleDatabaseBackup() throws FileNotFoundException {
        List<Person> personList = App.personDao.listPersons();
        List<String[]> personValuesList = new ArrayList<>();
        for (Person person : personList) {
            personValuesList.add(person.getFields());
        }
        DatabaseBackupWriter.writeToCSV(personValuesList, "data.csv");
        showAlert("Information Dialog", "Database backup saved in data.csv!");
        showBackup();
    }

    public void showAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void showBackup() throws FileNotFoundException {
        Scanner s = new Scanner(new File("data.csv")).useDelimiter("\n");
        CSVTextArea.clear();
        while (s.hasNext()) {
            if (s.hasNextInt()) { // check if next token is an int
                CSVTextArea.appendText(s.nextInt() + " "); // display the found integer
            } else {
                CSVTextArea.appendText(s.next() + " "); // else read the next token
            }
            CSVTextArea.appendText("\n");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}

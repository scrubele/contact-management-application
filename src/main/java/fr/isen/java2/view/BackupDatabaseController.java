package fr.isen.java2.view;

import fr.isen.java2.App;
import fr.isen.java2.db.database.DatabaseBackupWriter;
import fr.isen.java2.db.entities.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class BackupDatabaseController implements Initializable {

    final String CSV_OUTPUT_FILE = "data.csv";
    @FXML
    TextArea CSVTextArea;

    @FXML
    private void handleCloseButton(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    public void handleHomeButton() throws IOException {
        App.setRoot(App.homePageScreenFXML);
    }

    @FXML
    public void handleUserListButton() throws IOException {
        App.launchUserListController();
    }

    @FXML
    public void handleAddUserButton() throws IOException {
        App.setRoot(App.addUserScreenFXML);
    }

    @FXML
    public void handleDatabaseBackup() throws FileNotFoundException {
        List<Person> personList = App.personDao.listPersons();
        List<String[]> personValuesList = new ArrayList<>();
        for (Person person : personList) {
            personValuesList.add(person.getFields());
        }
        DatabaseBackupWriter.writeToCSV(personValuesList, CSV_OUTPUT_FILE);
        App.showAlert("Information Dialog", "Database backup saved in data.csv!");
        showBackup();
    }

    public void showBackup() throws FileNotFoundException {
        Scanner s = new Scanner(new File(CSV_OUTPUT_FILE)).useDelimiter("\n");
        CSVTextArea.clear();
        while (s.hasNext()) {
            if (s.hasNextInt()) {
                CSVTextArea.appendText(s.nextInt() + " ");
            } else {
                CSVTextArea.appendText(s.next() + " ");
            }
            CSVTextArea.appendText("\n");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}

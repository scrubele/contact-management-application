package fr.isen.java2.view;

import com.jfoenix.controls.JFXDatePicker;
import fr.isen.java2.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {

    @FXML
    private DatePicker datePicker;

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

    @FXML
    private void handleButtonAction(MouseEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final JFXDatePicker datePicker = new JFXDatePicker(LocalDate.now());
        datePicker.setOnAction(event -> {
            LocalDate date = datePicker.getValue();
            System.out.println("Selected date: " + date);
        });
    }
}

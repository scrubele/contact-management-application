package fr.isen.java2.view;

import com.jfoenix.controls.JFXDatePicker;
import fr.isen.java2.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    final String delimiter = "; ";

    @FXML
    protected TextField first_name;
    @FXML
    protected TextField last_name;
    @FXML
    protected TextField nickname;
    @FXML
    protected TextField email;
    @FXML
    protected TextField phone_number;
    @FXML
    protected DatePicker birth_date;
    @FXML
    protected TextField country;
    @FXML
    protected TextField state;
    @FXML
    protected TextField city;

    @FXML
    private void handleCloseButton(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    protected void handleHomeButton() throws IOException {
        App.setRoot(App.homePageScreenFXML);
    }

    @FXML
    protected void handleBackupButton() throws IOException {
        App.setRoot(App.backupScreenFXML);
    }

    @FXML
    protected void handleUserListButton() throws IOException {
        App.launchUserListController();
    }

    @FXML
    protected void handleAddUserButton() throws IOException {
        App.setRoot(App.addUserScreenFXML);
    }

    @FXML
    protected void resetAll() throws IOException {
        last_name.clear();
        first_name.clear();
        nickname.clear();
        phone_number.clear();
        country.clear();
        state.clear();
        city.clear();
        email.clear();
    }

    protected String reformatDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date d = formatter.parse(birth_date.getValue().toString());
            formatter.applyPattern("yyyy-MM-dd hh:mm:ss.SSSSSS");
            return formatter.format(d);
        }
        catch (NullPointerException e){
            App.showAlert("Error", "Date couldn't be null!");
        }
        return null;
    }

    protected String reformatAddress() {
        return country.getText() + delimiter + state.getText() + delimiter + city.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final JFXDatePicker birth_date = new JFXDatePicker(LocalDate.now());
        birth_date.setOnAction(event -> {
            LocalDate date = birth_date.getValue();
            System.out.println("Selected date: " + date);
        });
    }
}

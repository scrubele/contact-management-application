package fr.isen.java2.view;

import com.jfoenix.controls.JFXDatePicker;
import fr.isen.java2.App;
import fr.isen.java2.db.entities.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UpdateUserController implements Initializable {
    @FXML
    private TextField idperson;
    @FXML
    private TextField first_name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField nickname;
    @FXML
    private TextField email;
    @FXML
    private TextField phone_number;
    @FXML
    private DatePicker birth_date;
    @FXML
    private TextField country;
    @FXML
    private TextField state;
    @FXML
    private TextField city;


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
    public void handleBackupButton() throws IOException {
        App.setRoot("/fr/isen/java2/view/BackupDatabaseScreen");
    }

    @FXML
    public void handleAddUserButton() throws IOException {
        App.setRoot("/fr/isen/java2/view/AddUserScreen");

    }

    @FXML
    public void resetAll() throws IOException {
        last_name.clear();
        first_name.clear();
        nickname.clear();
        phone_number.clear();
        country.clear();
        state.clear();
        city.clear();
        email.clear();
    }

    @FXML
    public void updateUser() throws IOException, ParseException {
        String address = country.getText() + ", " + state.getText() + ", " + city.getText();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = formatter.parse(birth_date.getValue().toString());
        formatter.applyPattern("yyyy-MM-dd hh:mm:ss.SSSSSS");
        String formattedBirthDate = formatter.format(d);
        App.personDao.updatePerson(Integer.valueOf(idperson.getText()), last_name.getText(), first_name.getText(),
                nickname.getText(),
                phone_number.getText()
                , address, email.getText(), formattedBirthDate);
        System.out.println("Updated");
    }

    @FXML
    public void removeUser() {
        App.personDao.deletePerson(Integer.valueOf(idperson.getText()));
        System.out.println("Deleted");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final JFXDatePicker birth_date = new JFXDatePicker(LocalDate.now());
        birth_date.setOnAction(event -> {
            LocalDate date = birth_date.getValue();
            System.out.println("Selected date: " + date);
        });
    }

    public void init(Person person) {
        idperson.setText(person.getIdperson().toString());
        first_name.setText(person.getFirstName());
        last_name.setText(person.getLastName());
        nickname.setText(person.getNickname());
        email.setText(person.getEmailAddress());
        phone_number.setText(person.getPhoneNumber());
        String[] addressParts = person.getAddress().split(", ");
        try {
            country.setText(addressParts[0]);
            state.setText(addressParts[1]);
            city.setText(addressParts[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        birth_date.setValue(person.getBirthDate());


    }
}

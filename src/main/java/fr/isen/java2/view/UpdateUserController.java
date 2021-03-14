package fr.isen.java2.view;

import fr.isen.java2.App;
import fr.isen.java2.db.entities.Person;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.text.ParseException;

public class UpdateUserController extends UserController {

    @FXML
    private TextField idperson;

    @FXML
    public void updateUser() throws ParseException {
        String address = reformatAddress();
        String formattedBirthDate = reformatDate();
        App.personDao.updatePerson(Integer.valueOf(idperson.getText()), last_name.getText(), first_name.getText(),
                nickname.getText(), phone_number.getText(), address, email.getText(), formattedBirthDate);
        System.out.println("Updated");
        App.showAlert("Information Dialog", "User updated!");
    }

    @FXML
    public void removeUser() {
        App.personDao.deletePerson(Integer.valueOf(idperson.getText()));
        App.showAlert("Information Dialog", "User deleted!");
    }

    public void init(Person person) {
        idperson.setText(person.getIdperson().toString());
        first_name.setText(person.getFirstName());
        last_name.setText(person.getLastName());
        nickname.setText(person.getNickname());
        email.setText(person.getEmailAddress());
        phone_number.setText(person.getPhoneNumber());
        String[] addressParts = person.getAddress().split(delimiter);
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

package fr.isen.java2.view;

import fr.isen.java2.App;
import javafx.fxml.FXML;

import java.text.ParseException;

public class AddUserController extends UserController {

    @FXML
    public void addUser() throws ParseException {
        String address = reformatAddress();
        String formattedBirthDate = reformatDate();
        App.personDao.addPerson(last_name.getText(), first_name.getText(), nickname.getText(), phone_number.getText()
                , address, email.getText(), formattedBirthDate);
        System.out.println("Added");
    }
}

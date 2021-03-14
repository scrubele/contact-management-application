package fr.isen.java2.view;

import fr.isen.java2.db.entities.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class UserRowController implements Initializable {

    @FXML
    protected Label idperson;
    @FXML
    protected Label lastName;
    @FXML
    protected Label firstName;
    @FXML
    protected Label nickname;

    @FXML
    protected ImageView imageView;


    public void init(Person entry) {

        this.idperson.setText(entry.getIdperson().toString());
        this.lastName.setText(entry.getLastName());
        this.firstName.setText(entry.getFirstName());
        this.nickname.setText(entry.getNickname());
        if (!entry.getImagePath().isEmpty()) {
            Image image = new Image(entry.getImagePath());
            this.imageView.setImage(image);
        }
    }

    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public String toString() {
        return "UserRowController{" +
                "lastName=" + lastName +
                ", firstName=" + firstName +
                ", nickname=" + nickname +
                '}';
    }
}
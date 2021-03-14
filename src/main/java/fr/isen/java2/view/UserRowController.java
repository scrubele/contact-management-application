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
    protected Label lastName;
    @FXML
//    protected Label firstName;
//    @FXML
//    protected Label nickName;
//
//    @FXML
//    protected ImageView imageView;

    /**
     * initialize window, will be called after loading fxml file
     */
    public void init(Person entry) {
        // load the image
//        Image image = new Image(entry.getImagePath());

        //set title and image (icon)
        this.lastName.setText(entry.getLastName());
//        this.firstName.setText(entry.getFirstName());
//        this.nickName.setText(entry.getNickname());
//        this.imageView.setImage(image);
    }

    public void initialize(URL location, ResourceBundle resources) {

    }

}
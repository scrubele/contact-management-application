package fr.isen.java2.view;


import fr.isen.java2.App;
import fr.isen.java2.db.entities.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class UserListController implements Initializable {

    protected static final String ROW1_FXML = "/fr/isen/java2/view/UserRow";

    protected List<Person> list = null;

    public UserListController(List<Person> list) {
        this.list = list;
    }

    public UserListController() {
        this.list = list;
    }

    @FXML
    protected ListView<Pane> listView;

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
    public void resetAll() throws IOException {
    }


    /**
     * initialize window, will be called after loading fxml file
     */
    public void init() {
        int counter = 0;

        //create rows
        for (Person entry : this.list) {
            addRow(entry, ROW1_FXML);

            //increment counter
            counter++;
        }
    }

    protected void addRow (Person entry, String fxmlPath) {
        // load fxml
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlPath + ".fxml"));

            //set controller
            UserRowController UserRowController = new UserRowController();
            loader.setController(UserRowController);

            AnchorPane rootPane = loader.load();//FXMLLoader.load(new File(fxmlPath).toURI().toURL());

            //initialize tab controller
            UserRowController.init(entry);

            this.listView.getItems().add(rootPane);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * initialize window, this method isnt used in this example (only Interface Initializable is important for Code Injection --> @FXML annotation)
     */
    public void initialize(URL location, ResourceBundle resources) {
        //
    }

}

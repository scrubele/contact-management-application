package fr.isen.java2.view;


import fr.isen.java2.App;
import fr.isen.java2.db.entities.Person;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
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
    private Button btn_add_user;

    @FXML
    private Button btn_home;

    @FXML
    private Button btn_x;

    @FXML
    private Button btn_exit;


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


    public void init() {
        int counter = 0;
        for (Person entry : this.list) {
            addRow(entry, ROW1_FXML);

            counter++;
        }
        initListeners();
    }

    public void initListeners() {
        btn_add_user.setOnMouseClicked(
                (Event event) -> {
                    btn_add_user.requestFocus();
                    try {
                        App.setRoot("/fr/isen/java2/view/AddUserScreen");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    event.consume();
                });
        btn_home.setOnMouseClicked(
                (Event event) -> {
                    btn_home.requestFocus();
                    try {
                        handleHomeButton();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    event.consume();
                });
        btn_x.setOnMouseClicked(
                (Event event) -> {
                    btn_x.requestFocus();
                    System.exit(0);
                    event.consume();
                });
        btn_exit.setOnMouseClicked(
                (Event event) -> {
                    btn_x.requestFocus();
                    System.exit(0);
                    event.consume();
                });
        final Pane[] selectedItem = {new Pane()};
        listView.setOnMouseClicked(event -> {
            selectedItem[0] = listView.getSelectionModel().getSelectedItem();
            Label textField = (Label) selectedItem[0].getChildren().get(0);
            Integer id = Integer.valueOf(textField.getText());
            Person person = App.personDao.getPerson(id);
            System.out.println("clicked on " + selectedItem[0].toString()+ textField + " "+ id);
            try {
                App.setRoot("/fr/isen/java2/view/UpdateUserScreen");
            } catch (IOException e) {
                e.printStackTrace();
            }
            UpdateUserController updateUserController = new UpdateUserController();
            updateUserController.init(person);
        });
    }

    protected void addRow(Person entry, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlPath + ".fxml"));
            UserRowController userRowController = new UserRowController();
            loader.setController(userRowController);
            AnchorPane rootPane = loader.load();
            userRowController.init(entry);
            this.listView.getItems().add(rootPane);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
    }

}

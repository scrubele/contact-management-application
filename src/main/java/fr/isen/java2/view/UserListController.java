package fr.isen.java2.view;


import fr.isen.java2.App;
import fr.isen.java2.db.entities.Person;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserListController implements Initializable {

    protected static final String ROW1_FXML = "/fr/isen/java2/view/UserRow";

    protected List<Person> list = null;
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
    private Button btn_tooltip;
    @FXML
    private Button btn_backup;

    public UserListController(List<Person> list) {
        this.list = list;
    }

    public UserListController() {
        this.list = list;
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
                        App.setRoot(App.addUserScreenFXML);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    event.consume();
                });
        btn_home.setOnMouseClicked(
                (Event event) -> {
                    btn_home.requestFocus();
                    try {
                        App.setRoot(App.homePageScreenFXML);
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
        btn_exit.setOnMouseClicked(
                (Event event) -> {
                    btn_x.requestFocus();
                    System.exit(0);
                    event.consume();
                });
        btn_backup.setOnMouseClicked(event -> {
            btn_backup.requestFocus();
            try {
                App.setRoot(App.backupScreenFXML);
            } catch (IOException e) {
                e.printStackTrace();
            }
            event.consume();
        });
        btn_tooltip.setTooltip(new Tooltip("To view user details, click on the user row"));
        listView.setOnMouseClicked(event -> launchUserDetail());
    }

    protected void launchUserDetail() {
        final Pane[] selectedItem = {new Pane()};
        selectedItem[0] = listView.getSelectionModel().getSelectedItem();
        Label textField = (Label) selectedItem[0].getChildren().get(0);
        Integer id = Integer.valueOf(textField.getText());
        Person person = App.personDao.getPerson(id);
        FXMLLoader loader = new FXMLLoader(App.class.getResource(App.updateUserScreenFXML+ ".fxml"));
        try {
            App.setRoot(loader);
            UpdateUserController updateUserController = loader.getController();
            updateUserController.init(person);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

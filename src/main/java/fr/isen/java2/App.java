package fr.isen.java2;

import fr.isen.java2.db.daos.PersonDao;
import fr.isen.java2.db.entities.Person;
import fr.isen.java2.view.UserListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;

public class App extends Application {

    public final static String homePageScreenFXML = "/fr/isen/java2/view/HomePageScreen";
    public final static String addUserScreenFXML = "/fr/isen/java2/view/AddUserScreen";
    public final static String userListScreenFXML = "/fr/isen/java2/view/UserListScreen";
    public final static String backupScreenFXML = "/fr/isen/java2/view/BackupScreen";
    public final static String updateUserScreenFXML = "/fr/isen/java2/view/UpdateUserScreen.fxml";
    public static PersonDao personDao = new PersonDao();
    private static Scene scene;
    private double xOffset = 0;
    private double yOffset = 0;

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void setRoot(FXMLLoader fxmlLoader) throws IOException {
        scene.setRoot(fxmlLoader.load());
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void launchUserListController(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        List<Person> list = personDao.listPersons();
        UserListController controller = new UserListController(list);
        loader.setController(controller);
        scene.setRoot(loader.load());
        controller.init();
    }

    public static void showAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = loadFXML(App.homePageScreenFXML);
        stage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

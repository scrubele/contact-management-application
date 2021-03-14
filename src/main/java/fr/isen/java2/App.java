package fr.isen.java2;

import fr.isen.java2.db.daos.PersonDao;
import fr.isen.java2.db.entities.Person;
import fr.isen.java2.view.UserListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;

public class App extends Application {

    public static PersonDao personDao = new PersonDao();
    private static Scene scene;
    //init xy offsets
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

        //create and set controller
        List<Person> list = personDao.listPersons();
//		list.add(new Person(4, "Granger", "Hermione", "Hermione",
//				"+380967498094", "USA", "Hermione.Granger@gmail.com", LocalDate.now()));
        UserListController controller = new UserListController(list);
        loader.setController(controller);

        scene.setRoot(loader.load());
        controller.init();

    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = loadFXML("/fr/isen/java2/view/HomePageScreen");
        stage.initStyle(StageStyle.UNDECORATED);

        //set mouse pressed
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        //set mouse drag
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file (Login.fxml)
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        // Set the title of the window
        primaryStage.setTitle("EXERCISE 5");

        // Set the scene and show the primary stage
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

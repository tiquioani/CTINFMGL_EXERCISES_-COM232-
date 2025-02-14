package Controller;

import com.example.sputify.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    Label usernameLabel;

    @FXML
    Label passwordLabel;

    @FXML
    TextField usernameTF;

    @FXML
    TextField passwordTF;

    @FXML
    Button loginButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void loginButtonHandler(ActionEvent event)throws IOException{
        String uname = usernameTF.getText();
        String pword = passwordTF.getText();

        System.out.print("username:" + uname);
        System.out.print("password:"+ pword);

        if(DatabaseHandler.validateLogin(uname, pword)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sputify/Homepage.fxml"));
            // System.out.println("Login successful");

            root = loader.load();

            HomeController homeController = loader.getController();

            // Pass username from textfield to displayName() method
            //homeController.displayName(uname);

            // Load stage and scene
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else{
            System.out.println("LOGIN unsuccessful");
        }

    }
}
package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.User;
import com.example.sputify.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController implements Initializable {

    ObservableList<User> userlist = FXCollections.observableArrayList();

    @FXML
    private TableColumn<User, String> accountcreatedcol;

    @FXML
    private TableView<User> mytable;

    @FXML
    private TableColumn<User, String> passwordcol;

    @FXML
    private TableColumn<User, String> statuscol;

    @FXML
    private TableColumn<User, String> usernamecol;

    @FXML
    private Label homeLabel;

    @FXML
    private Button createButton;

    @FXML
    private Button deleteButton;
    @FXML
    private TextField nameTF;

    @FXML
    private TextField passwordTF;
    @FXML
    private TextField statusTF;

    @FXML
    private Button updateButton;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        homeLabel.setText("USER");
        initializeCol();
        displayUsers();
    }

    private void initializeCol(){
        usernamecol.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordcol.setCellValueFactory(new PropertyValueFactory<>("password"));
        accountcreatedcol.setCellValueFactory(new PropertyValueFactory<>("accountcreated"));
        statuscol.setCellValueFactory(new PropertyValueFactory<>("accountstatus"));
    }



    @FXML
    private void createuser(ActionEvent event) {

        String username = nameTF.getText();
        String password = passwordTF.getText();
        String status = statusTF.getText();

        username = username.trim();
        password = password.trim();
        status = status.trim();

        if(username.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Empty username");
            alert.showAndWait();
            return;

        }

        if(password.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("no password provided");
            alert.showAndWait();
            return;

        }

        if(status.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("empty status");
            alert.showAndWait();
            return;
        }

        User user = new User(username, password, "", status);

        if(DatabaseHandler.addUser(user))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("user created");
            alert.showAndWait();
            displayUsers(); // Refresh the table

        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("unsuccessful");

        }
    }

    @FXML
    public void deleteuser(ActionEvent event) {

        User user = mytable.getSelectionModel().getSelectedItem();

        String username = (user.getUsername());

        System.out.println(username);

        if(DatabaseHandler.deleteUser(user))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("user deleted");
            alert.showAndWait();
            displayUsers();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("cannot delete user");
            alert.showAndWait();
        }
        displayUsers();
    }

    @FXML
    public void updateuser(ActionEvent event) {

        String username = nameTF.getText();
        String password = passwordTF.getText();
        String status = statusTF.getText();

        username = username.trim();
        password = password.trim();
        status = status.trim();

        if(username.length() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Empty username");

        }

        if(password.length() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("no password provided");
            alert.showAndWait();

        }

        if(status.length() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("empty status");
            alert.showAndWait();
        }

        User user = new User(username, password,"", status);

        if(DatabaseHandler.updateUser(user)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("user updated");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("cannot update user");
            alert.showAndWait();
        }
        displayUsers();

    }

    private void displayUsers(){
        userlist.clear();
        // Returns a set of Users
        ResultSet result = DatabaseHandler.getUsers();

        try{
            //while loop through set of users from  database.
            while(result.next()){
                String username = result.getString("username");
                String password = result.getString("password");
                String accountcreated = result.getString("accountcreated");
                String accountstatus = result.getString("accountstatus");

                //create new user instance
                User newuser = new User(username, password, accountcreated, accountstatus);
                //Add to array list
                userlist.add(newuser);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        mytable.setItems(userlist);
    }


}
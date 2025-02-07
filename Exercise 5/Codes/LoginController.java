import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    @FXML
    private TextField usernametextfield;

    @FXML
    private TextField passwordtextfield;

    // This method is bound to the "Login" button's onAction event
    @FXML
    private void loginbuttonHandler(ActionEvent event) {
        String username = usernametextfield.getText();
        String password = passwordtextfield.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(AlertType.ERROR, "Login Failed", "Please enter both username and password.");
            return;
        }

        // Check credentials against the database
        if (checkCredentials(username, password)) {
            showAlert(AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
            openMainPage(); // Handle the transition to the main application page
        } else {
            showAlert(AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }

    // This method checks the database for matching credentials
    private boolean checkCredentials(String username, String password) {
        boolean isValid = false;

        try (Connection connection = DatabaseConnection.connect()) {
            if (connection != null) {
                String query = "SELECT * FROM accounts WHERE username = ? AND password = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);

                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        isValid = true; // Matching record found
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Database Error", "Error connecting to the database.");
        }

        return isValid;
    }

    // Helper method to show alerts
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Placeholder for the logic to transition to the main page after login
    private void openMainPage() {
        // Here, implement your logic to open the main application page.
        System.out.println("Opening main application page...");
        // Example: Load a new FXML file for the main page
        // FXMLLoader.load(getClass().getResource("MainPage.fxml"));
    }
}

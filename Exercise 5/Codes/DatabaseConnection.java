import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/user_login";
    private static final String USER = "root";  // 
    private static final String PASSWORD = "WJ28@krhps"; 

    public static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection successful!");
            return connection;
        } catch (SQLException e) {
            System.err.println("Connection failed. Error message: " + e.getMessage());
            return null;
        }
    }
}



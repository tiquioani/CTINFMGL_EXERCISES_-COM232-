module com.example.sputify {
    requires javafx.controls;
    requires javafx.fxml;


    requires java.sql;

    opens com.example.sputify to javafx.fxml;
    exports com.example.sputify;
    exports Controller;
    opens Controller to javafx.fxml;
    opens Model to javafx.base;
}
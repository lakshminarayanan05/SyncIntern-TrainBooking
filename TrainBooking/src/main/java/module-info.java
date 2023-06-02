module com.example.trainbooking {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.example.trainbooking to javafx.fxml;
    exports com.example.trainbooking;
}
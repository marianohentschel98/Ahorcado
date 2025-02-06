module com.example.ahorcado {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires junit;
    requires org.junit.jupiter.api;


    opens com.example.ahorcado to javafx.fxml;
    opens com.example.ahorcado.controller to javafx.fxml;
    exports com.example.ahorcado;
    exports com.example.ahorcado.controller;
}
module com.example.bachelorthesis {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.bachelorthesis to javafx.fxml;
    exports com.example.bachelorthesis.gui;
    opens com.example.bachelorthesis.gui to javafx.fxml;
    exports com.example.bachelorthesis.controller;
    opens com.example.bachelorthesis.controller to javafx.fxml;
}
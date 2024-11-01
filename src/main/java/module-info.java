module com.example.week7 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;

    opens com.example.week7 to javafx.fxml;
    exports com.example.week7;
    exports com.example.week7.models;
    opens com.example.week7.models to javafx.fxml;
    exports com.example.week7.exceptions;
    opens com.example.week7.exceptions to javafx.fxml;
    exports com.example.week7.controllers;
    opens com.example.week7.controllers to javafx.fxml;
}
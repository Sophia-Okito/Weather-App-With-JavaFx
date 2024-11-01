package com.example.week7;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        showHomeScreen();
    }

    //Displays the home screen.
    public static void showHomeScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
            Parent root = fxmlLoader.load();

            root.setId("rootPane");
            Scene scene = new Scene(root, 1200, 600);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Weather App");
            primaryStage.getScene().getStylesheets().add(HelloApplication.class.getResource("/com/example/week7/styles/style.css").toExternalForm());
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Quits the application.
    public static void quitApplication() {
        Platform.exit();
    }

    //Main method to launch the application.
    public static void main(String[] args) {
        launch();
    }
}

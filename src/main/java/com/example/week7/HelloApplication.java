package com.example.week7;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage primaryStage;

    //Initializes the primary stage and displays the home screen.
    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        showHomeScreen();
    }

    //Displays the home screen.
    public static void showHomeScreen() {
        try {
            FXMLLoader fxmlLoader = new
                    FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Home");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Displays the course management screen.
    public static void showCourseManagement() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication
                    .class.getResource("CourseManagement.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Course Management");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Displays the student management screen.
    public static void showStudentManagement() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication
                    .class.getResource("StudentManagement.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Student Management");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Displays the enroll course screen.
    public static void showEnrollCourse() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication
                    .class.getResource("EnrollCourse.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Enroll Course");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Displays the assign grade screen.
    public static void showAssignGrade() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication
                    .class.getResource("AssignGrade.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Assign Grade");
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

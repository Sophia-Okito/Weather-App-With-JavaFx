package com.example.week7.controllers;

import com.example.week7.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {

    //Button to navigate to the course management screen.
    @FXML
    private Button manageCoursesButton;

    //Button to navigate to the student management screen.
    @FXML
    private Button manageStudentsButton;

    //Button to navigate to the enroll course screen.
    @FXML
    private Button enrollCourseButton;

    //Button to navigate to the assign grade screen.
    @FXML
    private Button assignGradeButton;

    //Button to quit the application.
    @FXML
    private Button quitButton;

    //Initializes the home screen and sets up the event handlers for the buttons.
    @FXML
    private void initialize() {
        manageCoursesButton.setOnAction(event -> HelloApplication.showCourseManagement());
        manageStudentsButton.setOnAction(event -> HelloApplication.showStudentManagement());
        enrollCourseButton.setOnAction(event -> HelloApplication.showEnrollCourse());
        assignGradeButton.setOnAction(event -> HelloApplication.showAssignGrade());
        quitButton.setOnAction(event -> HelloApplication.quitApplication());
    }

    //The Home.fxml file is used to define the layout of the home screen. It contains the buttons for managing courses, students, enrolling in courses, assigning grades, and quitting the application. The HomeController class is responsible for handling the events triggered by these buttons and navigating to the appropriate screens.
}

package com.example.week7.controllers;

import com.example.week7.HelloApplication;
import com.example.week7.Management;
import com.example.week7.models.Course;
import com.example.week7.models.Student;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;

import java.util.List;

public class EnrollCourseController {

    //ComboBox to display the list of students.
    @FXML
    private ComboBox<Student> studentComboBox;

    //ComboBox to display the list of courses.
    @FXML
    private ComboBox<Course> courseComboBox;

    //Initializes the assign grade screen and loads the student and course data.
    @FXML
    private void initialize() {
        loadStudentData();
        loadCourseData();
    }

    //Loads the student data from the Management class and populates the studentComboBox.
    private void loadStudentData() {
        List<Student> students = Management.getAllStudents();
        studentComboBox.setItems(FXCollections.observableArrayList(students));
    }

    //Loads the course data from the Management class and populates the courseComboBox.
    private void loadCourseData() {
        List<Course> courses = Management.getAllCourses();
        courseComboBox.setItems(FXCollections.observableArrayList(courses));
    }

    //Handles the enroll button click event. It enrolls the selected student in the selected course.
    @FXML
    private void handleEnroll() {
        Student selectedStudent = studentComboBox.getValue();
        Course selectedCourse = courseComboBox.getValue();

        if (selectedStudent != null && selectedCourse != null) {
            try {
                Management.enrollStudent(selectedStudent.getId(), selectedCourse.getCode());
                showConfirmationAlert("Enrollment Successful", selectedStudent.getName() + " has been enrolled in " + selectedCourse.getName() + ".");
            } catch (Exception e) {
                showErrorAlert("Enrollment Failed", e.getMessage());
            }
        } else {
            showErrorAlert("Selection Error", "Please select both a student and a course.");
        }
    }

    //Handles the back button click event.
    @FXML
    private void handleBackButton() {
        HelloApplication.showHomeScreen();
    }

    //Displays an error alert with the given title and message.
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //Displays a confirmation alert with the given title and message.
    private void showConfirmationAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

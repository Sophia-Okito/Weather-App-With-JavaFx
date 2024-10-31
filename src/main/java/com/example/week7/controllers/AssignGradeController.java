package com.example.week7.controllers;

import com.example.week7.HelloApplication;
import com.example.week7.Management;
import com.example.week7.models.Course;
import com.example.week7.models.Student;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.List;

public class AssignGradeController {

    //ComboBox to display the list of students.
    @FXML
    private ComboBox<Student> studentComboBox;

    //ComboBox to display the list of courses.
    @FXML
    private ComboBox<Course> courseComboBox;

    //TextField to enter the grade.
    @FXML
    private TextField gradeTextField;

    //Initializes the assign grade screen and loads the student and course data.
    @FXML
    private void initialize() {
        loadStudentData();
        loadCourseData();
    }

    //Handles the back button click event.
    @FXML
    private void handleBackButton() {
        HelloApplication.showHomeScreen();
    }

    //Handles the assign grade button click event. It assigns the grade to the selected student in the selected course.
    @FXML
    private void handleAssignGrade() {
        Student selectedStudent = studentComboBox.getValue();
        Course selectedCourse = courseComboBox.getValue();
        String gradeText = gradeTextField.getText().trim();

        if (selectedStudent == null || selectedCourse == null || gradeText.isEmpty()) {
            showErrorAlert("Selection Error", "Please select a student, a course, and enter a grade.");
            return;
        }

        try {
            int grade = Integer.parseInt(gradeText);
            Management.assignGrade(selectedStudent.getId(), selectedCourse.getCode(), grade);
            showConfirmationAlert("Grade Assigned", selectedStudent.getName() + " has been assigned a grade of " + grade + " in " + selectedCourse.getName() + ".");
        } catch (NumberFormatException e) {
            showErrorAlert("Invalid Grade", "Please enter a valid number for the grade.");
        } catch (Exception e) {
            showErrorAlert("Assignment Failed", e.getMessage());
        }
    }

    //Loads the student data into the studentComboBox.
    private void loadStudentData() {
        List<Student> students = Management.getAllStudents();
        studentComboBox.setItems(FXCollections.observableArrayList(students));
    }

    //Loads the course data into the courseComboBox.
    private void loadCourseData() {
        List<Course> courses = Management.getAllCourses();
        courseComboBox.setItems(FXCollections.observableArrayList(courses));
    }

    //Displays an error alert with the given title and message.
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //Displays a confirmation alert with the given title and message.
    private void showConfirmationAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    //
}

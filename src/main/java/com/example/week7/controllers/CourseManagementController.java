package com.example.week7.controllers;

import com.example.week7.Management;
import com.example.week7.HelloApplication;
import com.example.week7.models.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Optional;

public class CourseManagementController {

    //Table to display the list of courses.
    @FXML
    private TableView<Course> courseTable;

    //Column to display the name of the course.
    @FXML
    private TableColumn<Course, String> name;

    //Column to display the code of the course.
    @FXML
    private TableColumn<Course, String> code;

    //Column to display the capacity of the course.
    @FXML
    private TableColumn<Course, Integer> capacity;


    //Initializes the course management screen and loads the course data.
    @FXML
    private void initialize() {
        name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        code.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
        capacity.setCellValueFactory(cellData -> cellData.getValue().capacityProperty().asObject());

        loadCourseData();
    }

    //Handles the back button click event.
    @FXML
    private void handleBackButton() {
        HelloApplication.showHomeScreen();
    }

    //Handles the add course button click event. It uses a dialog to get the course details
    // from the user and adds the course to the list of courses.
    @FXML
    private void handleAddCourse() {
        try {
            Dialog<Course> dialog = new Dialog<>();
            dialog.setTitle("Add New Course");
            dialog.setHeaderText("Enter Course Details");

            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            TextField nameField = new TextField();
            nameField.setPromptText("Course Name");
            TextField codeField = new TextField();
            codeField.setPromptText("Course Code");
            TextField capacityField = new TextField();
            capacityField.setPromptText("Course Capacity");

            grid.add(new Label("Name:"), 0, 0);
            grid.add(nameField, 1, 0);
            grid.add(new Label("Code:"), 0, 1);
            grid.add(codeField, 1, 1);
            grid.add(new Label("Capacity:"), 0, 2);
            grid.add(capacityField, 1, 2);

            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    try {
                        String name = nameField.getText().trim();
                        String code = codeField.getText().trim();
                        int capacity = Integer.parseInt(capacityField.getText().trim());
                        return new Course(name, code, capacity);
                    } catch (NumberFormatException e) {
                        showErrorAlert("Invalid Input", "Capacity must be a number.");
                    }
                }
                return null;
            });

            Optional<Course> result = dialog.showAndWait();
            result.ifPresent(course -> {
                Management.addCourse(course);
                courseTable.getItems().add(course);
            });
        } catch (Exception e) {
            showErrorAlert("Error Adding Course", e.getMessage());
        }

    }

    //Loads the course data from the management system and displays it in the table.
    private void loadCourseData() {
        try {
            List<Course> courses = Management.getAllCourses();
            ObservableList<Course> observableCourses = FXCollections.observableArrayList(courses);
            courseTable.setItems(observableCourses);
        } catch (Exception e) {
            showErrorAlert("Error loading courses", "An error occurred while loading course data.");
        }
    }

    //Displays an error alert with the given title and message.
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //CourseManagement.fxml file is used to design the course management screen.
}

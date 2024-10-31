package com.example.week7.controllers;

import com.example.week7.Management;
import com.example.week7.HelloApplication;
import com.example.week7.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Optional;

public class StudentManagementController {

    //Table to display the list of students.
    @FXML
    public TableColumn<Student, String> courses;

    //Table to display the list of students.
    @FXML
    private TableView<Student> studentTable;

    //Column to display the name of the student.
    @FXML
    private TableColumn<Student, String> name;

    //Column to display the ID of the student.
    @FXML
    private TableColumn<Student, String> id;


    //Initializes the student management screen and loads the student data.
    @FXML
    private void initialize() {
        name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        courses.setCellValueFactory(cellData -> cellData.getValue().getCourses());
        loadStudentData();
    }

    //Handles the back button click event.
    @FXML
    private void handleBackButton() {
        HelloApplication.showHomeScreen();
    }

    //Handles the add student button click event. It uses a dialog to get the student details
    @FXML
    private void handleAddStudent() {
        try {
            Dialog<Student> dialog = new Dialog<>();
            dialog.setTitle("Add New Student");
            dialog.setHeaderText("Enter Student Details");

            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            TextField nameField = new TextField();
            nameField.setPromptText("Student Name");

            TextField iDField = new TextField();
            iDField.setPromptText("Student ID");

            grid.add(new Label("Name:"), 0, 0);
            grid.add(nameField, 1, 0);
            grid.add(new Label("ID:"), 0, 1);
            grid.add(iDField, 1, 1);


            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    String name = nameField.getText().trim();
                    String id = iDField.getText().trim();
                    return new Student(id, name);
                }
                return null;
            });

            Optional<Student> result = dialog.showAndWait();
            result.ifPresent(student -> {
                Management.addStudent(student);
                studentTable.getItems().add(student);
            });
        } catch (Exception e) {
            showErrorAlert("Error Adding Student", e.getMessage());
        }

    }

    //Handles the delete student button click event. It deletes the selected student from the list of students.
    @FXML
    private void handleUpdateStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            showErrorAlert("No Selection", "Please select a student to update.");
            return;
        }

        try {
            Dialog<Student> dialog = new Dialog<>();
            dialog.setTitle("Update Student");
            dialog.setHeaderText("Update Student Details");

            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            TextField nameField = new TextField();
            nameField.setText(selectedStudent.getName());

            grid.add(new Label("Name:"), 0, 0);
            grid.add(nameField, 1, 0);

            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    selectedStudent.nameProperty().set(nameField.getText().trim());
                    return selectedStudent;
                }
                return null;
            });

            Optional<Student> result = dialog.showAndWait();
            result.ifPresent(student -> {
                Management.updateStudent(student);
                studentTable.refresh();
            });
        } catch (Exception e) {
            showErrorAlert("Error Updating Student", e.getMessage());
        }
    }

    //Handles the delete student button click event. It deletes the selected student from the list of students.
    private void loadStudentData() {
        try {
            List<Student> students = Management.getAllStudents();
            ObservableList<Student> observableCourses = FXCollections.observableArrayList(students);
            studentTable.setItems(observableCourses);
        } catch (Exception e) {
            showErrorAlert("Error loading courses", "An error occurred while loading course data.");
        }
    }

    //Shows a confirmation alert with the given title and message.
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //StudentManagement.fxml is a JavaFX file that contains the layout for the student management screen.
}

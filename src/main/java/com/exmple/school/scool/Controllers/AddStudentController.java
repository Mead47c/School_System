package com.exmple.school.scool.Controllers;

import com.exmple.school.scool.models.Student;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddStudentController {

    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private ComboBox<String> genderComboBox;

    private StudentsController studentsController;

    // Setter to link the main StudentsController
    public void setStudentsController(StudentsController controller) {
        this.studentsController = controller;
    }

    @FXML
    private void initialize() {
        // Initialize gender options
        genderComboBox.getItems().addAll("Male", "Female");
    }

    @FXML
    private void handleAddStudent() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String gender = genderComboBox.getValue();

        // Validate required fields
        if (id.isEmpty() || name.isEmpty() || gender == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill in ID, Name, and select Gender.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Student newStudent = new Student(id, name, email, phone, gender);

        if (studentsController != null) {
            studentsController.addStudentToTable(newStudent);
        }

        // Close the add student window
        closeWindow();
    }

    @FXML
    private void handleCancel() {
        closeWindow();
    }

    // Helper method to close window
    private void closeWindow() {
        Stage stage = (Stage) idField.getScene().getWindow();
        stage.close();
    }
}

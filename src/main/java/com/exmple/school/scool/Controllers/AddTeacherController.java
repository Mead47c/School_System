package com.exmple.school.scool.Controllers;

import com.exmple.school.scool.models.Teacher;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddTeacherController {

    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private Button addButton;
    @FXML private Button cancelButton;

    private TeachersController teachersController;

    // Setter to receive reference to TeachersController
    public void setTeachersController(TeachersController controller) {
        this.teachersController = controller;
    }

    @FXML
    public void initialize() {
        // Initialize gender combo box options
        genderComboBox.getItems().addAll("Male", "Female");
    }

    // Handle Add button click event
    @FXML
    private void handleAddTeacher() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String gender = genderComboBox.getValue();

            // Basic validation
            if (name.isEmpty() || gender == null) {
                showAlert("Validation Error", "Please fill in all required fields.");
                return;
            }

            Teacher newTeacher = new Teacher(id, name, email, phone, gender);

            // Add the new teacher to the main table via controller
            if (teachersController != null) {
                teachersController.addTeacherToTable(newTeacher);
            }

            // Close the add teacher window
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            showAlert("Input Error", "ID must be a number.");
        }
    }

    // Handle Cancel button click event
    @FXML
    private void handleCancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    // Utility method to show alert dialogs
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

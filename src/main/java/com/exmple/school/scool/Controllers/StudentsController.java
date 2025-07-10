package com.exmple.school.scool.Controllers;

import com.exmple.school.scool.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class StudentsController {

    @FXML private TableView<Student> studentsTable;
    @FXML private TableColumn<Student, String> idColumn;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, String> emailColumn;
    @FXML private TableColumn<Student, String> phoneColumn;
    @FXML private TableColumn<Student, String> genderColumn;

    @FXML private TextField searchField;

    // Database connection parameters
    private final String URL = "jdbc:mysql://localhost:4200/useres";
    private final String USER = "root";
    private final String PASS = "11223344Mm";

    private ObservableList<Student> studentList = FXCollections.observableArrayList();
    private FilteredList<Student> filteredList;

    @FXML
    public void initialize() {
        // Setup table columns with Student properties
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        loadStudents();

        // Initialize filtered list and bind to TableView
        filteredList = new FilteredList<>(studentList, b -> true);
        studentsTable.setItems(filteredList);

        // Add listener to filter table data based on search input (ID or Name)
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(student -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Show all if search is empty
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return student.getId().toLowerCase().contains(lowerCaseFilter) ||
                        student.getName().toLowerCase().contains(lowerCaseFilter);
            });
        });
    }

    // Load students data from database into observable list
    public void loadStudents() {
        studentList.clear();

        String query = "SELECT * FROM students";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Student s = new Student(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("gender")
                );
                studentList.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a new student to database and update table view if successful
    public void addStudentToTable(Student student) {
        String insertQuery = "INSERT INTO students (id, name, email, phone, gender) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(insertQuery)) {

            ps.setString(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getPhone());
            ps.setString(5, student.getGender());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Show Add Student dialog
    @FXML
    private void handleAddStudent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/exmple/school/scool/views/add-student.fxml"));
            Parent root = loader.load();

            AddStudentController controller = loader.getController();
            controller.setStudentsController(this);

            Stage stage = new Stage();
            stage.setTitle("Add Student");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Delete selected student from database and update table view
    @FXML
    private void handleDeleteStudent() {
        Student selectedStudent = studentsTable.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            deleteStudentFromDatabase(selectedStudent.getId());
            studentList.remove(selectedStudent);
            System.out.println("Student deleted: " + selectedStudent.getName());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a student to delete.");
            alert.showAndWait();
        }
    }

    // Delete student record from database by ID
    private void deleteStudentFromDatabase(String id) {
        String deleteQuery = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(deleteQuery)) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

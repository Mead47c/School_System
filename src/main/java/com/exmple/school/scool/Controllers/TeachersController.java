package com.exmple.school.scool.Controllers;

import com.exmple.school.scool.database.DatabaseConnection;
import com.exmple.school.scool.models.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class TeachersController {

    @FXML private TableView<Teacher> teachersTable;
    @FXML private TableColumn<Teacher, Integer> idColumn;
    @FXML private TableColumn<Teacher, String> nameColumn;
    @FXML private TableColumn<Teacher, String> emailColumn;
    @FXML private TableColumn<Teacher, String> phoneColumn;
    @FXML private TableColumn<Teacher, String> genderColumn;

    @FXML private TextField searchField;
    @FXML private Button addButton;
    @FXML private Button deleteButton;

    private ObservableList<Teacher> teacherList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        loadTeachers();

        FilteredList<Teacher> filteredData = new FilteredList<>(teacherList, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(teacher -> {
                if (newValue == null || newValue.isEmpty()) return true;
                String lowerCaseFilter = newValue.toLowerCase();
                return String.valueOf(teacher.getId()).toLowerCase().contains(lowerCaseFilter) ||
                        teacher.getName().toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<Teacher> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(teachersTable.comparatorProperty());
        teachersTable.setItems(sortedData);
    }

    private void loadTeachers() {
        teacherList.clear();
        String query = "SELECT * FROM teachers";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                teacherList.add(new Teacher(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("gender")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTeacherToTable(Teacher teacher) {
        teacherList.add(teacher);
        String insertQuery = "INSERT INTO teachers (id, name, email, phone, gender) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(insertQuery)) {

            ps.setInt(1, teacher.getId());
            ps.setString(2, teacher.getName());
            ps.setString(3, teacher.getEmail());
            ps.setString(4, teacher.getPhone());
            ps.setString(5, teacher.getGender());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddTeacher() {
        try {
            URL fxmlUrl = getClass().getResource("/com/exmple/school/scool/views/add-teachers.fxml");
            if (fxmlUrl == null) {
                System.err.println("Error: FXML file not found!");
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent root = loader.load();

            AddTeacherController controller = loader.getController();
            controller.setTeachersController(this);

            Stage stage = new Stage();
            stage.setTitle("Add Teacher");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteTeacher() {
        Teacher selectedTeacher = teachersTable.getSelectionModel().getSelectedItem();

        if (selectedTeacher != null) {
            deleteTeacherFromDatabase(selectedTeacher.getId());
            teacherList.remove(selectedTeacher);
            System.out.println("Teacher deleted: " + selectedTeacher.getName());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a teacher to delete.");
            alert.showAndWait();
        }
    }

    private void deleteTeacherFromDatabase(int id) {
        String deleteQuery = "DELETE FROM teachers WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(deleteQuery)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

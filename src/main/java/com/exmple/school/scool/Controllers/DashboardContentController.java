package com.exmple.school.scool.Controllers;

import com.exmple.school.scool.models.GradeOverview;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class DashboardContentController {

    @FXML private TableView<GradeOverview> gradesTable;
    @FXML private TableColumn<GradeOverview, String> codeColumn;
    @FXML private TableColumn<GradeOverview, String> nameColumn;
    @FXML private TableColumn<GradeOverview, String> teacherColumn;
    @FXML private TableColumn<GradeOverview, Integer> gradeColumn;

    // Database connection details
    private final String URL = "jdbc:mysql://localhost:4200/useres";
    private final String USER = "root";
    private final String PASS = "11223344Mm";

    @FXML
    public void initialize() {
        // Initialize table columns with GradeOverview properties
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        loadGradesOverview();
    }

    // Load grades overview data from database
    private void loadGradesOverview() {
        ObservableList<GradeOverview> grades = FXCollections.observableArrayList();

        String query = "SELECT * FROM Grads_Overview";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                grades.add(new GradeOverview(
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getString("teacher"),
                        rs.getInt("grade")
                ));
            }

            gradesTable.setItems(grades);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.exmple.school.scool.Controllers;

import com.exmple.school.scool.database.DatabaseConnection; // ← استيراد الكلاس الصحيح
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

    @FXML
    public void initialize() {
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        loadGradesOverview();
    }

    private void loadGradesOverview() {
        ObservableList<GradeOverview> grades = FXCollections.observableArrayList();

        String query = "SELECT * FROM Grads_Overview";

        try (Connection conn = DatabaseConnection.connect();
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

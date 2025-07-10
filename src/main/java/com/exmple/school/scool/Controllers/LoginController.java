package com.exmple.school.scool.Controllers;

import com.exmple.school.scool.database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        Connection conn = DatabaseConnection.connect();

        if (conn != null) {
            try {
                String sql = "SELECT * FROM useres WHERE username = ? AND password = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    // Login successful: Load dashboard view
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/exmple/school/scool/views/dashboard-view.fxml"));
                    Parent dashboardRoot = loader.load();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(dashboardRoot));
                    stage.show();
                } else {
                    System.out.println("Invalid username or password");
                }

                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Database connection failed");
        }
    }

    @FXML
    private void handleCloseApp(ActionEvent event) {
        System.out.println("Closing app...");
        System.exit(0);
    }

    @FXML
    private void handleSignupClick(javafx.scene.input.MouseEvent event) {
        try {
            Parent signupRoot = FXMLLoader.load(getClass().getResource("/com/exmple/school/scool/views/signup-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(signupRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

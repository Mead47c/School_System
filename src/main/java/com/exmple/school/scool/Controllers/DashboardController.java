package com.exmple.school.scool.Controllers;

import com.exmple.school.scool.models.AboutUs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class DashboardController {

    @FXML
    private StackPane contentArea;

    @FXML
    public void initialize() {
        loadDashboardContent();  // Load dashboard content on initialization
    }

    @FXML
    private void handleDashboardButton() {
        loadDashboardContent();
    }

    @FXML
    private void handleStudentsButton() {
        loadStudentsContent();
    }

    @FXML
    private void handleTeachersButton() {
        loadTeachersContent();
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        // Create and show custom confirmation popup for logout
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Logout Confirmation");

        Label label = new Label("Are you sure you want to log out?");
        label.setStyle("-fx-font-size: 16px; -fx-text-fill: #1a2b49;");

        Button btnLogout = new Button("Log Out");
        btnLogout.setStyle("-fx-background-color: #e0aa3e; -fx-text-fill: #1a2b49; -fx-font-weight: bold;");
        btnLogout.setOnAction(e -> {
            try {
                Parent loginRoot = FXMLLoader.load(getClass().getResource("/com/exmple/school/scool/views/login-view.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(loginRoot));
                stage.setTitle("Login");
                stage.show();
                dialog.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Button btnCancel = new Button("Cancel");
        btnCancel.setStyle("-fx-background-color: #cccccc; -fx-text-fill: #1a2b49;");
        btnCancel.setOnAction(e -> dialog.close());

        HBox buttons = new HBox(15, btnLogout, btnCancel);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(20, label, buttons);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle(
                "-fx-padding: 20; -fx-background-color: white; -fx-border-radius: 10; " +
                        "-fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8,0,0,2);"
        );

        Scene scene = new Scene(layout);
        dialog.setScene(scene);
        dialog.setResizable(false);
        dialog.showAndWait();
    }

    @FXML
    private void handleAboutUsButton() {
        AboutUs.showAboutUsStage();
    }

    // Helper methods to load content into the main content area
    private void loadDashboardContent() {
        loadFXMLToContentArea("/com/exmple/school/scool/views/dashboard-content.fxml");
    }

    private void loadStudentsContent() {
        loadFXMLToContentArea("/com/exmple/school/scool/views/students.fxml");
    }

    private void loadTeachersContent() {
        loadFXMLToContentArea("/com/exmple/school/scool/views/Teachers.fxml");
    }

    private void loadFXMLToContentArea(String fxmlPath) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentArea.getChildren().setAll(fxml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/exmple/school/scool/views/dashboard-view.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Dashboard");
        stage.show();
    }
}

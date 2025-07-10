package com.exmple.school.scool.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SignupController {

    @FXML
    private void handleCloseApp(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleLoginClick(MouseEvent event) {
        try {
            Parent loginRoot = FXMLLoader.load(getClass().getResource("/com/exmple/school/scool/views/login-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loginRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSignup(ActionEvent event) {
        // TODO: Add signup logic here
        System.out.println("Signup button clicked");
    }
}

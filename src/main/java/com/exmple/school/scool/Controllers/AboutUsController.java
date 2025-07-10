package com.exmple.school.scool.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AboutUsController {

    @FXML
    private Button closeButton;

    @FXML
    private void handleClose() {
        // Close the current window
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}

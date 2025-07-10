package com.exmple.school.scool.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AboutUs {

    /**
     * Displays the "About Us" modal window.
     */
    public static void showAboutUsStage() {
        try {
            FXMLLoader loader = new FXMLLoader(AboutUs.class.getResource("/com/exmple/school/scool/views/aboutus-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 420, 260);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("About School System");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.setAlwaysOnTop(true);

            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.exmple.school.scool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the login view from FXML file
        Parent root = FXMLLoader.load(getClass().getResource("/com/exmple/school/scool/views/login-view.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        // Remove window decorations (title bar, close buttons, etc.)
        primaryStage.initStyle(StageStyle.UNDECORATED);

        // Set application icon
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.png")));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

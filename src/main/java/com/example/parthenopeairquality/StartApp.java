package com.example.gradleairquality;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class StartApp extends Application {

    public static void main(String[] args) {


        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            String css = this.getClass().getResource("style.css").toExternalForm();
            scene.getStylesheets().add(css);
            Image icon = new Image("com/example/gradleairquality/icon_round.png");
            stage.getIcons().add(icon);
            stage.setTitle("Parthenope Air Quality");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
package com.example.gradleairquality;

import com.example.gradleairquality.Model.MapManagement.GeographicMap;
import com.example.gradleairquality.Model.MapManagement.Interface_MapManagement;
import com.example.gradleairquality.Model.MapManagement.Planimetry;
import com.example.gradleairquality.Model.ThresholdManagement.Threshold.ThresholdsMap;
import com.example.gradleairquality.Model.UserManagement.Manager;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Text wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private BorderPane loginPane;
    @FXML
    private AnchorPane dashboardPane;

    @FXML
    public void onEnter(ActionEvent ae) throws SQLException, ClassNotFoundException, IOException {
        validateLogin(ae);
    }




    public void validateLogin(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        Manager manager = new Manager(username.getText(), password.getText());
        manager.logIn();
        if (!manager.logIn()) {
            wrongLogIn.setText("Credenziali errate");
            wrongLogIn.setVisible(true);
        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root = loader.load();

            DashboardController dashboardController = loader.getController();

            ModelViewController modelViewController = ModelViewController.getInstance();
            modelViewController.setManager(manager);
            Interface_MapManagement map;
            if (manager.getType().equals("GA")) {
                map = new GeographicMap(0.0, 0.0);
            } else {
                map = new Planimetry(manager.getGovernanceArea().getNome(), 0.0, 0.0, 0.0);
            }
            modelViewController.setMap(map.viewmap(manager));
            modelViewController.setThresholds(ThresholdsMap.getThresholdsInstance(manager));
            dashboardController.sendModelViewController(modelViewController);
            dashboardController.mapImage.setImage(modelViewController.getMap().get(0));


            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }
    }


    public void switchToSignup(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void switchToCompare(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("compare.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEdit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("edit.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToProfile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
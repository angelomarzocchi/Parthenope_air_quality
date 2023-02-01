package com.example.gradleairquality;

import com.example.gradleairquality.Model.CompareManagement.AreaEstimator;
import com.example.gradleairquality.Model.ThresholdManagement.Sensor.measureType;
import com.example.gradleairquality.Model.UserManagement.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private ModelViewController modelViewController;

    @FXML
    private Text nameText;

    @FXML
    private Text usernameText;

    @FXML
    private Text dateText;

    @FXML
    private Text areaText;

    @FXML
    private ImageView profileImageView;

    @FXML
    private Text tempText;

    @FXML
    private Text humidityText;

    @FXML
    private Text windText;

    @FXML
    private Text carbonText;

    @FXML
    private Text pm2Text;

    @FXML
    private Text pm10Text;

    @FXML
    private ListView notificationListView;

    @FXML
    private Button notificationButton;

    public void sendModelViewController(ModelViewController modelViewController) {
        this.modelViewController = modelViewController;

        //riempire i textField
        Manager manager = modelViewController.getManager();
        nameText.setText(manager.getName());
        usernameText.setText(manager.getUsername());
        dateText.setText(manager.getBirthDate().toString());
        areaText.setText(manager.getGovernanceArea().getNome());

        AreaEstimator areaEstimator = new AreaEstimator(modelViewController.getSensors());
        Map<measureType, Double> map = areaEstimator.getAverages();

        tempText.setText(map.get(measureType.TEMPERATURE).toString());
        humidityText.setText(map.get(measureType.HUMIDITY).toString());
        windText.setText(map.get(measureType.WIND).toString());
        carbonText.setText(map.get(measureType.CARBON).toString());
        pm2Text.setText(map.get(measureType.PM2).toString());
        pm10Text.setText(map.get(measureType.PM10).toString());

        notificationListView.getItems().addAll(modelViewController.getThresholdExceedingChecker().getEvents());


    }


    public void switchToLogin(ActionEvent event) throws IOException {

        modelViewController.free();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        System.gc();
    }

    public void switchToDashboard(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        Parent root = loader.load();
        DashboardController dashboardController = loader.getController();
        dashboardController.sendModelViewController(modelViewController);
        dashboardController.mapImage.setImage(modelViewController.getMap().get(0));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCompare(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("compare.fxml"));
        Parent root = loader.load();
        CompareController compareController = loader.getController();
        compareController.sendModelViewController(modelViewController);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEdit(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("edit.fxml"));
        Parent root = loader.load();
        EditController editController = loader.getController();
        editController.sendModelViewController(modelViewController);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showNotifications() {
        notificationListView.setVisible(!notificationListView.isVisible());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notificationListView.setVisible(false);
    }
}

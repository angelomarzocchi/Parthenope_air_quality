package com.example.gradleairquality;

import com.example.gradleairquality.Model.ThresholdManagement.Sensor.measureType;
import com.example.gradleairquality.Model.ThresholdManagement.Threshold.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class EditController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;


    private TemperatureThresholdEditor temperatureThresholdEditor;
    private HumidityThresholdEditor humidityThresholdEditor;
    private WindThresholdEditor windThresholdEditor;
    private CarbonThresholdEditor carbonThresholdEditor;
    private PM2ThresholdEditor pm2ThresholdEditor;
    private PM10ThresholdEditor pm10ThresholdEditor;
    private HashMap<measureType, String> newvalues;

    @FXML
    private TextField TemperatureTextField;

    @FXML
    private TextField HumidityTextField;

    @FXML
    private TextField WindTextField;

    @FXML
    private TextField CarbonTextField;

    @FXML
    private TextField PM2TextField;

    @FXML
    private TextField PM10TextField;

    @FXML
    private Text AlertText;

    @FXML
    private Button confirmButton;

    @FXML
    private ListView notificationListView;

    @FXML
    private Button notificationButton;

    @FXML
    private Text temperatureValueText;

    @FXML
    private Text humidityValueText;

    @FXML
    private Text windValueText;

    @FXML
    private Text carbonValueText;

    @FXML
    private Text pm2ValueText;

    @FXML
    private Text pm10ValueText;

    @FXML
    private Button refreshButtonTemperature;

    @FXML
    private Button refreshButtonHumidity;

    @FXML
    private Button refreshButtonWind;

    @FXML
    private Button refreshButtonCarbon;

    @FXML
    private Button refreshButtonPM2;

    @FXML
    private Button refreshButtonPM10;



    ModelViewController modelViewController;

    public void sendModelViewController(ModelViewController modelViewController) throws SQLException, ClassNotFoundException {
        this.modelViewController = modelViewController;
        modelViewController.setThresholds(ThresholdsMap.getThresholdsInstance(modelViewController.getManager()));
        notificationListView.getItems().addAll(modelViewController.getThresholdExceedingChecker().getEvents());

        temperatureValueText.setText(String.valueOf(  modelViewController.getThresholds().getThresholds().get(measureType.TEMPERATURE).getThreshold()));
        humidityValueText.setText(String.valueOf(  modelViewController.getThresholds().getThresholds().get(measureType.HUMIDITY).getThreshold()));
        pm10ValueText.setText(String.valueOf(  modelViewController.getThresholds().getThresholds().get(measureType.PM10).getThreshold()));
        pm2ValueText.setText(String.valueOf(  modelViewController.getThresholds().getThresholds().get(measureType.PM2).getThreshold()));
        carbonValueText.setText(String.valueOf(  modelViewController.getThresholds().getThresholds().get(measureType.CARBON).getThreshold()));
        windValueText.setText(String.valueOf(  modelViewController.getThresholds().getThresholds().get(measureType.WIND).getThreshold()));
    }


    public void onConfirmButtonSelected() throws SQLException, ClassNotFoundException {

        newvalues.put(measureType.TEMPERATURE, TemperatureTextField.getText());
        newvalues.put(measureType.HUMIDITY, HumidityTextField.getText());
        newvalues.put(measureType.WIND, WindTextField.getText());
        newvalues.put(measureType.CARBON, CarbonTextField.getText());
        newvalues.put(measureType.PM2, PM2TextField.getText());
        newvalues.put(measureType.PM10, PM10TextField.getText());
        boolean allGood = true;
        for (measureType m : measureType.values()) {
            int value = modelViewController.getThresholds().getThresholds().get(m).getThreshold();
            try {
                value = Integer.parseInt(newvalues.get(m));
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }


            EditRequest editRequest = new EditRequest(value, m);
            if (!temperatureThresholdEditor.edit(editRequest, modelViewController.getManager())) {
                allGood = false;
                break;

            }


        }

        AlertText.setVisible(true);
        if (allGood) {
            AlertText.setText("Soglie modificate con successo");
            AlertText.setFill(Color.GREEN);
        } else {

            AlertText.setText("Alcune soglie non sono corrette");
            AlertText.setFill(Color.RED);
        }


        modelViewController.getThresholds().saveChanges(modelViewController.getManager());
        temperatureValueText.setText(String.valueOf(  modelViewController.getThresholds().getThresholds().get(measureType.TEMPERATURE).getThreshold()));
        humidityValueText.setText(String.valueOf(  modelViewController.getThresholds().getThresholds().get(measureType.HUMIDITY).getThreshold()));
        pm10ValueText.setText(String.valueOf(  modelViewController.getThresholds().getThresholds().get(measureType.PM10).getThreshold()));
        pm2ValueText.setText(String.valueOf(  modelViewController.getThresholds().getThresholds().get(measureType.PM2).getThreshold()));
        carbonValueText.setText(String.valueOf(  modelViewController.getThresholds().getThresholds().get(measureType.CARBON).getThreshold()));
        windValueText.setText(String.valueOf(  modelViewController.getThresholds().getThresholds().get(measureType.WIND).getThreshold()));


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

    public void switchToProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
        Parent root = loader.load();
        ProfileController profileController = loader.getController();
        profileController.sendModelViewController(modelViewController);

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
        newvalues = new HashMap<>();
        pm2ThresholdEditor = new PM2ThresholdEditor(null);
        pm10ThresholdEditor = new PM10ThresholdEditor(pm2ThresholdEditor);
        carbonThresholdEditor = new CarbonThresholdEditor(pm10ThresholdEditor);
        windThresholdEditor = new WindThresholdEditor(carbonThresholdEditor);
        humidityThresholdEditor = new HumidityThresholdEditor(windThresholdEditor);
        temperatureThresholdEditor = new TemperatureThresholdEditor(humidityThresholdEditor);
    }


    public void onTempRefresh() throws SQLException, ClassNotFoundException {
        modelViewController.getThresholds().resetToDefaultValues(measureType.TEMPERATURE);
        modelViewController.getThresholds().saveChanges(modelViewController.getManager());
        temperatureValueText.setText(String.valueOf(  modelViewController.getThresholds().getThresholds().get(measureType.TEMPERATURE).getThreshold()));
    }
    public void onHumidityRefresh() throws SQLException, ClassNotFoundException {
        modelViewController.getThresholds().resetToDefaultValues(measureType.HUMIDITY);
        modelViewController.getThresholds().saveChanges(modelViewController.getManager());
        humidityValueText.setText(String.valueOf(  modelViewController.getThresholds().getThresholds().get(measureType.HUMIDITY).getThreshold()));
    }

    public void onWindRefresh() throws SQLException, ClassNotFoundException {
        modelViewController.getThresholds().resetToDefaultValues(measureType.WIND);
        modelViewController.getThresholds().saveChanges(modelViewController.getManager());
        windValueText.setText(String.valueOf(  modelViewController.getThresholds().getThresholds().get(measureType.WIND).getThreshold()));
    }

    public void onCarbonRefresh() throws SQLException, ClassNotFoundException {
        modelViewController.getThresholds().resetToDefaultValues(measureType.CARBON);
        modelViewController.getThresholds().saveChanges(modelViewController.getManager());
        carbonValueText.setText(String.valueOf(  modelViewController.getThresholds().getThresholds().get(measureType.CARBON).getThreshold()));
    }

    public void onPM2Refresh() throws SQLException, ClassNotFoundException {
        modelViewController.getThresholds().resetToDefaultValues(measureType.PM2);
        modelViewController.getThresholds().saveChanges(modelViewController.getManager());
        pm2ValueText.setText(String.valueOf(  modelViewController.getThresholds().getThresholds().get(measureType.PM2).getThreshold()));
    }

    public void onPM10Refresh() throws SQLException, ClassNotFoundException {
        modelViewController.getThresholds().resetToDefaultValues(measureType.PM10);
        modelViewController.getThresholds().saveChanges(modelViewController.getManager());
        pm10ValueText.setText(String.valueOf(  modelViewController.getThresholds().getThresholds().get(measureType.PM10).getThreshold()));
    }

}

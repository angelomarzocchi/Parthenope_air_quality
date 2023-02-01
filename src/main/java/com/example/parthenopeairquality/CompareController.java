package com.example.gradleairquality;

import com.example.gradleairquality.Model.CompareManagement.Comparison;
import com.example.gradleairquality.Model.CompareManagement.ComparisonResult;
import com.example.gradleairquality.Model.CompareManagement.SensorComparator;
import com.example.gradleairquality.Model.ThresholdManagement.Sensor.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class CompareController implements Initializable {
    private Stage stage;
    private Scene scene;





    @FXML
    private ListView sensorList;

    @FXML
    private Text sensor1Name;

    @FXML
    private Text sensor2Name;

    @FXML
    private Text sensor1Text1;

    @FXML
    private Text sensor1Text2;

    @FXML
    private Text sensor1Text3;

    @FXML
    private Text sensor1Text4;

    @FXML
    private Text sensor1Text5;

    @FXML
    private Text sensor1Text6;

    @FXML
    private Text sensor2Text1;

    @FXML
    private Text sensor2Text2;

    @FXML
    private Text sensor2Text3;

    @FXML
    private Text sensor2Text4;

    @FXML
    private Text sensor2Text5;

    @FXML
    private Text sensor2Text6;

    @FXML
    private ListView notificationListView;



    private Text[] textsS1;

    private Text[] textsS2;


    private ModelViewController modelViewController;

    public void sendModelViewController(ModelViewController modelViewController) {
        this.modelViewController = modelViewController;

        LinkedList<String> stringList = new LinkedList<>();
        for (Sensor s : modelViewController.getSensors()) {
            String string;
            if (s.isOnline()) {
                string = s.getCode() + " " + s.getLocation() + " " + "Status: online";
            } else string = s.getCode() + " " + s.getLocation() + " " + "Status: offline";

            stringList.add(string);

        }
        sensorList.getItems().addAll(stringList);

        notificationListView.getItems().addAll(modelViewController.getThresholdExceedingChecker().getEvents());

    }

    public void showSensorDetail(MouseEvent event) {
        if (notificationListView.isVisible()) {
            notificationListView.setVisible(false);
        }
        sensorList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        ObservableList selected = sensorList.getSelectionModel().getSelectedIndices();


        Sensor sensor1 = modelViewController.getSensors().get((Integer) selected.get(0));
        Sensor sensor2 = modelViewController.getSensors().get((Integer) selected.get(1));

        sensor1Name.setText(sensor1.getCode());
        sensor2Name.setText(sensor2.getCode());

        SensorDecorator[] decorators1 = new SensorDecorator[6];
        SensorDecorator[] decorators2 = new SensorDecorator[6];

        decorators1[0] = new TemperatureDecorator(sensor1);
        decorators2[0] = new TemperatureDecorator(sensor2);

        System.out.println(decorators1[0].getMeasures().get(0).getType());

        decorators1[1] = new HumidityDecorator(sensor1);
        decorators2[1] = new HumidityDecorator(sensor2);

        decorators1[2] = new WindDecorator(sensor1);
        decorators2[2] = new WindDecorator(sensor2);

        decorators1[3] = new CarbonDecorator(sensor1);
        decorators2[3] = new CarbonDecorator(sensor2);

        decorators1[4] = new PM2Decorator(sensor1);
        decorators2[4] = new PM2Decorator(sensor2);

        decorators1[5] = new PM10Decorator(sensor1);
        decorators2[5] = new PM10Decorator(sensor2);
        int index = 0;
        for (int i = 0; i < decorators1.length; i++) {
            if (decorators1[i].getMeasures().size() > 0 && decorators2[i].getMeasures().size() > 0) {

                SensorComparator comparator = null;
                switch (decorators1[i].getMeasures().get(0).getType()) {
                    case PM2 ->
                            comparator = new SensorComparator((PM2Decorator) decorators1[i], (PM2Decorator) decorators2[i]);
                    case PM10 ->
                            comparator = new SensorComparator((PM10Decorator) decorators1[i], (PM10Decorator) decorators2[i]);
                    case WIND ->
                            comparator = new SensorComparator((WindDecorator) decorators1[i], (WindDecorator) decorators2[i]);
                    case CARBON ->
                            comparator = new SensorComparator((CarbonDecorator) decorators1[i], (CarbonDecorator) decorators2[i]);
                    case HUMIDITY ->
                            comparator = new SensorComparator((HumidityDecorator) decorators1[i], (HumidityDecorator) decorators2[i]);
                    case TEMPERATURE ->
                            comparator = new SensorComparator((TemperatureDecorator) decorators1[i], (TemperatureDecorator) decorators2[i]);

                }
                Comparison result = comparator.compare().get(0);
                Integer integer1 = decorators1[i].getMeasures().get(decorators1[i].getMeasures().size() - 1).getValue();

                textsS1[index].setText(decorators1[i].getMeasures().get(0).getType().toString + " " +
                        integer1 + " " + Yardstick.valueOf(decorators1[i].getMeasures().get(0).getType().toString()).getSymbol()

                );
                textsS1[index].setVisible(true);
                Integer integer2 = decorators2[i].getMeasures().get(decorators2[i].getMeasures().size() - 1).getValue();
                textsS2[index].setText(decorators2[i].getMeasures().get(0).getType().toString + " " +
                        integer2 + " " + Yardstick.valueOf(decorators2[i].getMeasures().get(0).getType().toString()).getSymbol()

                );

                textsS2[index].setVisible(true);
                if (result.getResult().equals(ComparisonResult.FIRST_IS_GREATER)) {
                    textsS1[index].setFill(Color.RED);
                    textsS2[index].setFill(Color.GREEN);
                } else if (result.getResult().equals(ComparisonResult.SECOND_IS_GREATER)) {
                    textsS2[index].setFill(Color.RED);
                    textsS1[index].setFill(Color.GREEN);

                }

                index++;
            }

        }


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
        textsS1 = new Text[6];
        textsS2 = new Text[6];

        textsS1[0] = sensor1Text1;
        textsS1[1] = sensor1Text2;
        textsS1[2] = sensor1Text3;
        textsS1[3] = sensor1Text4;
        textsS1[4] = sensor1Text5;
        textsS1[5] = sensor1Text6;

        textsS2[0] = sensor2Text1;
        textsS2[1] = sensor2Text2;
        textsS2[2] = sensor2Text3;
        textsS2[3] = sensor2Text4;
        textsS2[4] = sensor2Text5;
        textsS2[5] = sensor2Text6;


        for (int i = 0; i < 6; i++) {
            textsS1[i].setVisible(false);
            textsS2[i].setVisible(false);
        }
    }
}

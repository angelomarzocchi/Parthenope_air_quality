package com.example.gradleairquality;

import com.example.gradleairquality.Model.ThresholdManagement.Sensor.*;
import com.example.gradleairquality.Model.UserManagement.DBService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    private Stage stage;
    private Scene scene;
    private Parent root;
    private ModelViewController modelViewController;
    @FXML
    private ListView<String> listView;

    private Text[] texts;
    private Text[] types;


    @FXML
    public ImageView mapImage;

    @FXML
    private Text location;

    @FXML
    private Text longitude;

    @FXML
    private Text latitude;
    @FXML
    private Text location_text;
    @FXML
    private Text latitude_text;
    @FXML
    private Text longitude_text;
    @FXML
    private Text type1;
    @FXML
    private Text type1_text;
    @FXML
    private Text type2;
    @FXML
    private Text type2_text;
    @FXML
    private Text type3;
    @FXML
    private Text type3_text;
    @FXML
    private Text type4;
    @FXML
    private Text type4_text;
    @FXML
    private Text type5;
    @FXML
    private Text type5_text;
    @FXML
    private Text type6;
    @FXML
    private Text type6_text;

    @FXML
    private ListView notificationListView;

    @FXML
    private Button notificationButton;


    LinkedList<Sensor> sensors;


    public void showSensorDetail(MouseEvent event) {

        if (notificationListView.isVisible()) {
            notificationListView.setVisible(false);
        }


        int selected = listView.getSelectionModel().getSelectedIndex();
        Sensor sensor = modelViewController.getSensors().get(selected);
        Image image = modelViewController.getMap().get(0);


        //mapImage.setImage(new Image("com/example/gradleairquality/icon_round.png"));

        //mapImage.setFitHeight(image.getHeight());
        //mapImage.setFitWidth(image.getWidth());

        latitude.setVisible(true);
        longitude.setVisible(true);
        latitude_text.setVisible(true);
        longitude_text.setVisible(true);
        location_text.setVisible(true);
        this.location.setVisible(true);


        SensorDecorator[] decorators = new SensorDecorator[6];
        decorators[0] = new TemperatureDecorator(sensor);
        decorators[1] = new HumidityDecorator(sensor);
        decorators[2] = new WindDecorator(sensor);
        decorators[3] = new CarbonDecorator(sensor);
        decorators[4] = new PM2Decorator(sensor);
        decorators[5] = new PM10Decorator(sensor);
        location_text.setText(sensor.getLocation());
        longitude_text.setText(sensor.getLongitude().toString());
        latitude_text.setText(sensor.getLatitude().toString());
        int index = 0;
        for (SensorDecorator s : decorators) {
            if (s.getMeasures().size() > 0) {
                types[index].setText(s.getMeasures().get(0).getType().toString);
                Integer integer = s.getMeasures().get(s.getMeasures().size() - 1).getValue();
                texts[index].setText(integer + " " + Yardstick.valueOf(s.getMeasures().get(0).getType().toString()).getSymbol());
                index++;
            }
        }

        for (int i = 0; i < index; i++) {
            types[i].setVisible(true);
            texts[i].setVisible(true);
        }

        for (int i = index; i < 6; i++) {
            types[i].setVisible(false);
            texts[i].setVisible(false);
        }


    }

    public void sendModelViewController(ModelViewController modelViewController) throws SQLException, ClassNotFoundException {
        this.modelViewController = modelViewController;
        Random random = new Random();
        modelViewController.setSensors(DBService.getSensors(modelViewController.getManager()));
        modelViewController.getSensors().get(0).addMeasure(new Measure(measureType.TEMPERATURE, LocalDate.now(), random.nextInt(0, 45)));
        modelViewController.getSensors().get(0).addMeasure(new Measure(measureType.HUMIDITY, LocalDate.now(), random.nextInt(10, 100)));
        modelViewController.getSensors().get(1).addMeasure(new Measure(measureType.PM2, LocalDate.now(), random.nextInt(5, 100)));
        modelViewController.getSensors().get(1).addMeasure(new Measure(measureType.PM10, LocalDate.now(), random.nextInt(5, 100)));
        modelViewController.getSensors().get(2).addMeasure(new Measure(measureType.WIND, LocalDate.now(), random.nextInt(5, 50)));
        modelViewController.getSensors().get(2).addMeasure(new Measure(measureType.CARBON, LocalDate.now(), random.nextInt(300, 8000)));
        modelViewController.getSensors().get(3).addMeasure(new Measure(measureType.TEMPERATURE, LocalDate.now(), random.nextInt(5, 45)));
        modelViewController.getSensors().get(3).addMeasure(new Measure(measureType.HUMIDITY, LocalDate.now(), random.nextInt(5, 100)));
        modelViewController.getSensors().get(4).setOnlineStatus(false);


        LinkedList<String> stringList = new LinkedList<>();
        for (Sensor s : modelViewController.getSensors()) {
            String string;
            if (s.isOnline()) {
                string = s.getCode() + " " + s.getLocation() + " " + "Status: online";
            } else string = s.getCode() + " " + s.getLocation() + " " + "Status: offline";

            stringList.add(string);

        }
        listView.getItems().addAll(stringList);
        notificationListView.getItems().clear();
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

    public void switchToCompare(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("compare.fxml"));

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
        types = new Text[6];
        texts = new Text[6];

        types[0] = type1;
        types[1] = type2;
        types[2] = type3;
        types[3] = type4;
        types[4] = type5;
        types[5] = type6;

        texts[0] = type1_text;
        texts[1] = type2_text;
        texts[2] = type3_text;
        texts[3] = type4_text;
        texts[4] = type5_text;
        texts[5] = type6_text;

        for (int i = 0; i < 6; i++) {
            types[i].setVisible(false);
            texts[i].setVisible(false);
        }
        latitude.setVisible(false);
        longitude.setVisible(false);
        latitude_text.setVisible(false);
        longitude_text.setVisible(false);
        location_text.setVisible(false);
        this.location.setVisible(false);


    }


}







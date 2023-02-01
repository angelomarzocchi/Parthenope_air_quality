package com.example.gradleairquality;

import com.example.gradleairquality.Model.ThresholdManagement.Threshold.Comuni;
import com.example.gradleairquality.Model.UserManagement.DBService;
import com.example.gradleairquality.Model.UserManagement.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    private Stage stage;
    private Scene scene;


    @FXML
    private Button backButton;

    @FXML
    private TextField taxCodeTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField repeatPasswordTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private TextField emailTextField;

    @FXML
    private ChoiceBox<Comuni> areaChoiceBox;

    @FXML
    private Button signupButton;

    @FXML
    private Text wrongSignup;

    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();


    }

    public void onClickSignUp(ActionEvent event) throws SQLException, ClassNotFoundException, InterruptedException, IOException {

        if (taxCodeTextField.getText().length() != 16) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("Codice fiscale non valido");

        } else if (passwordTextField.getText().length() < 8) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("Password troppo breve");
        } else if (!passwordTextField.getText().equals(repeatPasswordTextField.getText())) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("Passwords non corrispondenti");
        } else if (nameTextField.getText().length() < 1) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("Inserisci un nome valido");
        } else if (surnameTextField.getText().length() < 1) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("Inserisci un cognome valido");
        } else if (birthDatePicker.getValue() == null) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("Inserisci una data di nascita");
        } else if (birthDatePicker.getValue().isAfter(LocalDate.now())) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("Provieni dal futuro?");
        } else if (!emailTextField.getText().contains("@")) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("Inserisci una mail valida");
        } else if (areaChoiceBox.getValue() == null) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("Seleziona un'area");
        } else if (!DBService.checkAreaAvailability(areaChoiceBox.getValue())) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("L'area selezionata ha giá un manager");
        } else {
            Manager manager = new Manager(taxCodeTextField.getText().toUpperCase(), passwordTextField.getText());
            manager.setBirthDate(Date.valueOf(birthDatePicker.getValue()));
            manager.setEmail(emailTextField.getText());
            manager.setSurname(surnameTextField.getText());
            manager.setGovernanceArea(areaChoiceBox.getValue());
            manager.setName(nameTextField.getText());


            DBService.insertNewManager(manager);
            switchToLogin(event);


        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wrongSignup.setVisible(false);

        areaChoiceBox.getItems().addAll(Comuni.values());


    }
}

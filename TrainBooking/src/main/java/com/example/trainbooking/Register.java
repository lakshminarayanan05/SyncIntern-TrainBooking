package com.example.trainbooking;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Register implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private long mobile;
    private String name;
    private String gender;
    private String password;
    private String confirmpassword;

    @FXML
    private CheckBox checkBox;
    @FXML
    private TextField mobilefield;
    @FXML
    private TextField namefield;
    @FXML
    private TextField passwordfield;
    @FXML
    private TextField confirmpasswordfield;

    @FXML
    private ChoiceBox<String> genderbox;

    private final String[] genders={"Male","Female","Others"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderbox.getItems().addAll(genders);
        genderbox.setOnAction(this::getGender);
    }

    private void getGender(ActionEvent event) {
        gender = genderbox.getValue();
    }
    @FXML
    private void register(ActionEvent event) throws Exception {
        Passengers passenger = new Passengers();
        mobile = Long.parseLong(mobilefield.getText());
        name = namefield.getText();
        password = passwordfield.getText();
        confirmpassword = confirmpasswordfield.getText();
        passenger.setMobile(mobile);
        passenger.setName(name);
        passenger.setGender(gender);
        if (password.equals(confirmpassword)) {
            passenger.setPassword(password);
            if (checkBox.isSelected()) {
                RegisterDAO.addUser(passenger);
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("Fill the all boxes");
                alert.show();
            }
        }
        else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Password Mismatched");
                alert.show();
            }
        }
}


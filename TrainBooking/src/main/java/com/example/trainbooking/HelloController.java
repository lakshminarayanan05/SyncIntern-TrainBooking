package com.example.trainbooking;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable{
    static int login = 0;
    private Stage stage;
    private Scene scene;
    private Parent root;
    String fromstation;
    String tostation;
    LocalDate myDate;
    int adults;
    int kids;

    @FXML
    private Label loginlabel;
    @FXML
    private TextField adulttext;
    @FXML
    private TextField kidtext;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ChoiceBox<String> trStation1;

    private final String[] station={"Chennai","Coimbatore","Kaniyakumari"};

    @FXML
    private ChoiceBox<String> trStation2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(login!=0) {
            loginlabel.setText("logged in");
        }
        trStation1.getItems().addAll(station);
        trStation2.getItems().addAll(station);
        trStation1.setOnAction(this::getStation1);
        trStation2.setOnAction(this::getStation2);
    }
    private void getStation1(ActionEvent event) {
        fromstation = trStation1.getValue();
    }
    private void getStation2(ActionEvent event) {
        tostation = trStation2.getValue();
    }
    @FXML
    private void getDate(ActionEvent event){
        myDate = datePicker.getValue();
    }

    @FXML
    private void bookTrain(ActionEvent event) throws Exception {
        BookedTickets bookedTickets = new BookedTickets();
        adults = Integer.parseInt(adulttext.getText());
        kids = Integer.parseInt(kidtext.getText());
        bookedTickets.setFromstation(fromstation);
        bookedTickets.setTostation(tostation);
        bookedTickets.setDate(myDate);
        bookedTickets.setAdults(adults);
        bookedTickets.setKids(kids);
        BookedTickets.addbooking(bookedTickets);
        Booking.getbooking(bookedTickets);
        if(login != 0 ) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("booking.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Kindly Login first!");
            alert.show();
            login++;
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    private void loginac(ActionEvent event) throws IOException {
            login++;
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    @FXML
    private void register(ActionEvent event)  {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.example.trainbooking;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Booking implements Initializable {
    private Parent root;
    private Stage stage;
    private Scene scene;
    double balance,totalPrice;
    private static BookedTickets bookedTickets1;
    @FXML
    private Label balancelabel;
    @FXML
    private Label trainlabel;
    @FXML
    private Label pricelabel;
    @FXML
    private Label cgstlabel;
    @FXML
    private Label sgstlabel;
    @FXML
    private Label totallabel;
    @FXML
    private TextField moneyfield;
    @FXML
    private Button PayandBook;

    public static void getbooking(BookedTickets bookedTickets) {
        bookedTickets1 = bookedTickets;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLabel(url, resourceBundle);
    }



    public void setLabel(URL url, ResourceBundle resourceBundle) {

        try {
            balance = 5000;
            balancelabel.setText(String.valueOf(balance));
            trainlabel.setText(BookedTickets.getTrain(bookedTickets1)+" "+BookedTickets.getTime(bookedTickets1));
            int adultprice = 0;
            adultprice = BookedTickets.getadultPrice(bookedTickets1);
            int kidprice = BookedTickets.getkidPrice(bookedTickets1);
            int Price = (bookedTickets1.getAdults() * adultprice) + (bookedTickets1.getKids() * kidprice);
            pricelabel.setText(String.valueOf(Price));
            double cgst = 0.15 * Price;
            double sgst = 0.07 * Price;
            totalPrice = Price + cgst + sgst;
            cgstlabel.setText(String.valueOf(cgst));
            sgstlabel.setText(String.valueOf(sgst));
            totallabel.setText(String.valueOf(totalPrice));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void PayandBook(ActionEvent event) throws Exception {
        if(balance>totalPrice){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Booked");
            alert.setContentText("Your Booking is Confirmed");
            alert.show();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setContentText("Insufficient Balance");
            alert.show();
        }
    }

}

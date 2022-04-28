/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.hotel.hotelmanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class HomePageController implements Initializable {

    @FXML
    private ImageView logout;
    @FXML
    private Label adminName;
    @FXML
    private Button hebergement;
    @FXML
    private Button club;
    @FXML
    private Button evenement;
    @FXML
    private Button recompenses;
    @FXML
    private Button dash;
    @FXML
    private Button reclamation;
    @FXML
    private Button users;
    @FXML
    private Button settings;
    @FXML
    private AnchorPane holdPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleLogout(MouseEvent event) {
    }

    @FXML
    private void createRoom(ActionEvent event) {
    }

    @FXML
    private void createCheckIn(ActionEvent event) {
    }

    @FXML
    private void createCheckOut(ActionEvent event) {
    }

    @FXML
    private void createCustomerBill(ActionEvent event) {
    }

    @FXML
    private void createDash(ActionEvent event) {
    }
    
}

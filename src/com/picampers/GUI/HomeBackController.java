/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.picampers.GUI;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Arij
 */
public class HomeBackController implements Initializable {

    @FXML
    private Label usernameLabel11;
    @FXML
    private Label usernameLabel1;
    @FXML
    private JFXButton club;
    @FXML
    private JFXButton hebergement;
    @FXML
    private JFXButton evenement;
    @FXML
    private JFXButton recomponse;
    @FXML
    private JFXButton ResReclamation;

    @FXML
    private JFXButton utilisateur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void goTO_club(ActionEvent event) {
                            try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/picampers/GUI/ListeClubs.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeHebergementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void goTO_hebergement(ActionEvent event) {      
      
                    try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/picampers/GUI/ListeHebergements.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeHebergementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
      @FXML
    private void goTO_evenement(ActionEvent event) {
                        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/com/picampers/GUI/FXMLEvent.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            //Logger.getLogger(FXMLEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void goTO_recomponse(ActionEvent event) {
                   try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/com/picampers/GUI/Recompense/GestionPromotion.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            //Logger.getLogger(ListeHebergementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
   private void goTO_resReclamation(ActionEvent event) {
                   try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/picampers/GUI/FXMLReclamation.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
           // Logger.getLogger(ListeHebergementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
   private void goTO_utilisateur(ActionEvent event) {
            try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/picampers/GUI/gestionUser.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
           // Logger.getLogger(ListeHebergementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  /*  @FXML
    void test(ActionEvent event) {
        System.out.println("testing");
    }*/

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers;

import com.picampers.Controller.GestionPromotionController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author arij
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/picampers/GUI/Login.fxml"));
            // Parent root1 = FXMLLoader.load(getClass().getResource("FXML.fxml"));

            //khedmet ramez 
            //Parent root = FXMLLoader.load(getClass().getResource("/com/picampers/GUI/Recompense/GestionPromotion.fxml"));
            
            //khedmet bouhmid
            //Parent root = FXMLLoader.load(getClass().getResource("/com/picampers/GUI/FXMLReclamation.fxml"));
            
            //khedmet tilouche
            //Parent root = FXMLLoader.load(getClass().getResource("/com/picampers/GUI/FXMLEvent.fxml"));
            
            Scene scene = new Scene(root);
            //  Scene scene1 = new Scene(root1);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
       // GestionPromotionController gc = new GestionPromotionController();
       //gc.searchPromo();
    }
    
}

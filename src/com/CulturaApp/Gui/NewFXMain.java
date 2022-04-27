/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.CulturaApp.Gui;

import com.CulturaApp.Controller.GestionPromotionController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ramez
 */
public class NewFXMain extends Application {

    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        try {
            //Parent root = FXMLLoader.load(getClass().getResource("Promotion/FXMLPromotion.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("Promotion/GestionPromotion.fxml"));
            Scene scene = new Scene(root);  

            this.primaryStage = primaryStage;
            this.primaryStage.setScene(scene);
            this.primaryStage.setTitle("Gestion Promotion");
            this.primaryStage.show();    
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        GestionPromotionController gc = new GestionPromotionController();
       gc.searchPromo();
    }
    
}

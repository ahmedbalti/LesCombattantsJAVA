package com.picampers.GUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class PopOverTest extends Application {

    
    
    
    
    @Override
    public void start(Stage stage) {
        
        
     javafx.scene.image.Image img = new javafx.scene.image.Image("checkmark.png");
         Notifications notificationsBuilder = Notifications.create()
                .title("Modifier Reservation")
                .text("Modification effectué")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                   System.out.println("Cliquer sur la notification");
               }
           });
        notificationsBuilder.darkStyle();
        notificationsBuilder.showConfirm();
        System.out.println("valide");
        
        
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
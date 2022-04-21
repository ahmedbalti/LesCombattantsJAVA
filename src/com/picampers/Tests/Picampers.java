/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.Tests;

import com.picampers.utils.DataSource;
import javafx.application.Application;


import java.sql.DriverManager;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.picampers.IServices.IHebergementService;



/**
 *
 * @author Lenovo
 */
public class Picampers extends Application {
    
@Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/picampers/GUI/Connexion.fxml"));
       // Parent root1 = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        
        Scene scene = new Scene(root);
      //  Scene scene1 = new Scene(root1);      
        stage.setScene(scene);
        stage.show();
        
  
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        
//java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

//Sujet s1=new Sujet(5,"voyage","foret","mer",0,"voyage","description","une image");

//Commentaire c1=new Commentaire(3,49,"voyage",0);

//ISujetService iSujetService=new SujetService();
//ICommentaireService iCommentaireService=new CommentaireService();


//iSujetService.add(s1);
//iSujetService.delete(50);
//iSujetService.update(52);  
//iSujetService.afficher(52);
//iSujetService.affichertous();
       
//iCommentaireService.add(c1);
//iCommentaireService.delete(6);
//iCommentaireService.update(7);  
//iCommentaireService.afficher(7);
//iCommentaireService.afficherparsujet(49);
 
       
        
        launch(args);
   
     }
    
    }
        
        
    
        
        
        
        
  
    


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.picampers.Controller;

import com.picampers.entities.Promotion;
import com.picampers.entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ramez
 */
public class DetailsController implements Initializable {


    @FXML
    private Label name;

    @FXML
    private Label Lprenom;

    @FXML
    private Label Lemail;

    @FXML
    private Label Ladresse;

    @FXML
    private Label Ltel;

    @FXML
    private Label Lscore;

    @FXML
    private Label Ltype;

    @FXML
    private Label Ltitle;

    @FXML
    private Label Ldesc;

    @FXML
    private Label LscoreMin;

    @FXML
    private Label LtypeP;


    Promotion pr ;
    User us;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          
          
          //setUser(us);
          //setPromotion(pr);
    }
    
    public void setUser( User u){

        System.out.println(u);

        try {
            name.setText(u.getNom());
        Lprenom.setText(u.getPrenom());
        Lemail.setText(u.getEmail());
        Ladresse.setText(u.getAdresse());
       Ltel.setText( String.valueOf(u.getTel()));
      Lscore.setText( String.valueOf(u.getScore()));
      Ltype.setText(u.getType());

      
            
        } catch (Exception e) {
               e.printStackTrace();
        }
      
    }    
      
     public void setPromotion(Promotion p){

           System.out.println(p);

           try {
             Ltitle.setText(p.getTitle());
             Ldesc.setText(p.getDescription());
             LscoreMin.setText( String.valueOf(p.getScoreMin()));
                LtypeP.setText(p.getType());  
         } catch (Exception e) {
              e.printStackTrace();
         }
              
      
     }
}

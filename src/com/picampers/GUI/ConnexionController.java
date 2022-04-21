/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.GUI;

import com.picampers.utils.DataSource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import com.picampers.IServices.IHebergementService;
import com.picampers.Services.HebergementService;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ConnexionController implements Initializable {

private static int workload = 13;
private Connection conn; 
@FXML private Button con;
@FXML private TextField idfield;
@FXML private TextField pass;         
private static String usr;
String passwd;

    public static String getUsr() {
        return usr;
    }

    public static void setUsr(String usr) {
        ConnexionController.usr = usr;
    }

 

  @FXML
    private boolean VerifyUsr(String usr){
        try {
            Statement stmt = null;
            conn = DataSource.getMyInstance().getMyConnexion();
            
            stmt = conn.createStatement();
            String sql = "SELECT * FROM utilisateur where username='"+usr+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next() ) {
                return false;
            }  } catch (SQLException ex) {
            Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
        }
     return true;
    }    
    

  @FXML
       private boolean VerifyPass(String usr){
  try {
            Statement stmt = null;
            conn = DataSource.getMyInstance().getMyConnexion();
            stmt = conn.createStatement();
  String sql = "SELECT password FROM utilisateur where username='"+usr+"'";
   try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
             while (resultSet.next()) {
            passwd=(resultSet.getString("password"));
             }
  } catch (SQLException e) {
       System.out.println("erreur");
        } 
   } catch (SQLException ex) {
            Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
//String test_hash = passwd;

//String newName = test_hash.substring(0,2)+'a'+test_hash.substring(3);
 
if(pass.getText().equals(passwd))
         
return true;
else
return false;
}    
        
@FXML
private void handleConAction(ActionEvent event) throws IOException {

usr=idfield.getText();          
if(VerifyUsr(usr) && VerifyPass(usr)){
Image img=new Image("/com/picampers/images/checkmark.png");
Notifications notificationsBuilder = Notifications.create()
                .graphic(new ImageView(img))
                .title("Picampers")
                .text("Authentification effectué")
                .hideAfter(Duration.seconds(3))
                .position(Pos.BASELINE_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                @Override
               public void handle(ActionEvent event) {
                   System.out.println("Cliquer sur la notification");
               }
           });
        notificationsBuilder.darkStyle();
//        notificationsBuilder.show();
        System.out.println("valide");
                
   /*  Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error Dialog");
alert.setHeaderText("Look, an Error Dialog");
alert.setContentText("Ooops, there was an error!");

alert.showAndWait();
        */  
       IHebergementService iHebergementService=new HebergementService();  
       FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeHebergements.fxml"));
       ListeHebergementsController controller = loader.getController();
       controller.setUsr(usr);
       Stage stage; 
       Parent root;
       stage=(Stage) con.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("/com/picampers/GUI/ListeHebergements.fxml")); 
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();   
    } 
    else
       idfield.setStyle("-fx-background-color: #FF3333;");
       pass.setStyle("-fx-background-color: #FF3333;");            
         }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    //idfield.setText("ala");
    //pass.setText("ala");
            
    }    
}

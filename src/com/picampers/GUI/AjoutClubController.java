/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.GUI;

import com.picampers.Services.ClubService;
import com.picampers.entities.Club;
import com.picampers.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.apache.commons.io.IOUtils;
import org.controlsfx.control.Notifications;
import com.picampers.IServices.IClubService;


/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AjoutClubController implements Initializable {

    
@FXML private ImageView imageView;
@FXML private Label fileSelected;
@FXML private Button aaa;    
private String imageFile;
private Connection conn;   
@FXML private TextField anom;
@FXML private TextField alieu;
@FXML private TextField adesc;
@FXML private TextField adispo;
@FXML private TextField atype;
@FXML private Button ajout;
@FXML private Button retour;  
@FXML private Button ajousondage;  
@FXML private TitledPane sondagefield;   
@FXML private AnchorPane sondagefield1;   
@FXML private ChoiceBox nbsondage;

@FXML private Label lblreponse1;
@FXML private Label lblreponse2;
@FXML private Label lblreponse3;
@FXML private Label lblreponse4;
@FXML private Label lblreponse5;

    
public void pickImage (ActionEvent actionEvent) throws MalformedURLException, IOException {

    try {
        fileSelected.setVisible(false);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Veuillez choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif")); // limit fileChooser options to image files
        File selectedFile = fileChooser.showOpenDialog(fileSelected.getScene().getWindow());
       // File f1 = new File("D:\\" + selectedFile.getName());

       // System.out.println(selectedFile.getAbsolutePath());
        
        InputStream input = new FileInputStream(selectedFile.getAbsolutePath());
        OutputStream output = new FileOutputStream("C:\\wamp\\www\\images\\"+selectedFile.getName());
        
        IOUtils.copy(input, output);

        
        if (selectedFile != null) {
          //  imageFile = selectedFile.toURI().toURL().toString();
           imageFile="file:/C:/wamp/www/images/"+selectedFile.getName();
          
           
            fileSelected.setText(imageFile);
            Image image = new Image(imageFile);
            imageView.setImage(image);
           
            
            
        } else {
            
  Image imgch=new Image("file:/C:/wamp/www/images/noIMG.PNG");    
  imageView.setImage(imgch); 
  
  
}   } catch (FileNotFoundException ex) {
        Logger.getLogger(AjoutClubController.class.getName()).log(Level.SEVERE, null, ex);
   
   
        }
    }
      
      

@FXML
private void handlesondageaction(ActionEvent event) throws IOException {
       sondagefield.setExpanded(true);
       sondagefield1.setVisible(true);
    
   
       
     
}
 

@FXML
private void handleAjoutAction(ActionEvent event) throws IOException {
if (anom.getText() == null || anom.getText().trim().isEmpty()) {
    anom.setStyle("-fx-background-color: #FF3333;");
    adesc.setStyle("");
    alieu.setStyle("");
    
}
else if (adesc.getText() == null || adesc.getText().trim().isEmpty()) {
    adesc.setStyle("-fx-background-color: #FF3333;");
    anom.setStyle("");
    alieu.setStyle("");
}
else if (alieu.getText() == null || alieu.getText().trim().isEmpty()) {
    alieu.setStyle("-fx-background-color: #FF3333;");
    adesc.setStyle("");
    anom.setStyle("");
}
else {        
        
      IClubService iClubService=new ClubService();  
      Club c1=new Club();   
      c1.setNom(anom.getText());
      c1.setDescription(adesc.getText());
      c1.setType(atype.getText());
      c1.setLieu(alieu.getText());
      c1.setDisponibilite(1);
      c1.setImage(fileSelected.getText());
      iClubService.add(c1);
        
      
      int idsuj=c1.getId();                  
      
      Image img=new Image("/com/picampers/images/checkmark.png");
       
      Stage stage; 
      Parent root;
      stage=(Stage) ajout.getScene().getWindow();
      root = FXMLLoader.load(getClass().getResource("ListeClubs.fxml"));
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();      
        }
    }    
    
    
    @FXML
    private void handleretourAction(ActionEvent event) throws IOException {
        
       Stage stage; 
       Parent root;
       stage=(Stage) retour.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("ListeClubs.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    } 
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
}

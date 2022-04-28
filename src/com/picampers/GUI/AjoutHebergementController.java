/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.picampers.GUI;

import com.picampers.Services.HebergementService;
import com.picampers.entities.Hebergement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import org.apache.commons.io.IOUtils;
import com.picampers.IServices.IHebergementService;


/**
 *
 * @author Arij
 */
public class AjoutHebergementController implements Initializable {

    
@FXML private ImageView imageView;
@FXML private Label fileSelected;
@FXML private Button aaa;    
private String imageFile;
private Connection conn;   
@FXML private TextField namee;
@FXML private TextField nbrstars;
@FXML private TextField nbrrooms;
@FXML private TextField addresse;
@FXML private TextField mdescription;
@FXML private TextField mdisponibilite;
@FXML private TextField typee;
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
        Logger.getLogger(AjoutHebergementController.class.getName()).log(Level.SEVERE, null, ex);
   
   
        }
    }
      
      

@FXML
private void handleAjoutAction(ActionEvent event) throws IOException {
if (namee.getText() == null || namee.getText().trim().isEmpty()) {
    namee.setStyle("-fx-background-color: #FF3333;");
    mdescription.setStyle("");
    addresse.setStyle("");
    nbrrooms.setStyle("");
    nbrstars.setStyle("");
}
else if (mdescription.getText() == null || mdescription.getText().trim().isEmpty()) {
    mdescription.setStyle("-fx-background-color: #FF3333;");
    namee.setStyle("");
    addresse.setStyle("");
    nbrrooms.setStyle("");
    nbrstars.setStyle("");
}
else if (addresse.getText() == null || addresse.getText().trim().isEmpty()) {
    addresse.setStyle("-fx-background-color: #FF3333;");
    mdescription.setStyle("");
    namee.setStyle("");
    nbrrooms.setStyle("");
    nbrstars.setStyle("");
}
else {        
        
      IHebergementService iHebergementService=new HebergementService();  
      Hebergement c1=new Hebergement();   
      c1.setName(namee.getText());
      c1.setNbStars(Integer.parseInt(nbrstars.getText()));  
      c1.setNbRooms(Integer.parseInt(nbrrooms.getText())); 
      c1.setAddress(addresse.getText());
      c1.setDescription(mdescription.getText());
      c1.setType(typee.getText());
      c1.setDisponibilite(1);
      c1.setImage(fileSelected.getText());
      iHebergementService.add(c1);
      int idsuj=c1.getId();                    
      
      Image img=new Image("/com/picampers/images/checkmark.png");

      
      Stage stage; 
      Parent root;
      stage=(Stage) ajout.getScene().getWindow();
      root = FXMLLoader.load(getClass().getResource("ListeHebergements.fxml"));
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
       root = FXMLLoader.load(getClass().getResource("ListeHebergements.fxml"));
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

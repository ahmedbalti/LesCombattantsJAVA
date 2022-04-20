/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.io.IOUtils;
import org.controlsfx.control.Notifications;
import com.picampers.IServices.IClubService;
import com.picampers.Services.ClubService;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ModifierClubController implements Initializable {

@FXML private Label fileSelected;
@FXML private TextField mtitre;
@FXML private TextField mdesc;
@FXML private TextField mtag1;
@FXML private TextField mtag2;
@FXML private TextField mdescription;  
@FXML private Button retour;
@FXML private Button modifier;
@FXML private ImageView imageView;
private String imageFile;    
private static int val;

   public static void setVal(int val) {
      ModifierClubController.val = val;
    }

    public static int getVal() {
        return val;
    }
    
private static String usr;

    public static String getUsr() {
        return usr;
    }

    public static void setUsr(String usr) {
        ModifierClubController.usr = usr;
    }
    
    
    
    
    @FXML
    private void handleretourAction(ActionEvent event) throws IOException {
       IClubService iclubService=new ClubService();  
       FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheClub.fxml"));
       ClubController controller = loader.getController();
       controller.setVal(val);
       controller.setUsr(usr);
       Stage stage; 
       Parent root;
       stage=(Stage) retour.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("AfficheClub.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }    
    
    @FXML
    private void handlemodifierAction(ActionEvent event) throws IOException {
    IClubService iSujetService=new ClubService();
     
int id= val;
String nom=mtitre.getText();
String lieu=mdesc.getText();
String type=mtag1.getText();
//String disponibilite=mtag2.getText();
String description=mdescription.getText();
//String img=fileSelected.getText();
int cover_id = iSujetService.afficher(val).get(0).getCover_id();
iSujetService.update(id,type,nom,lieu,description,cover_id,fileSelected.getText());

Image img1=new Image("/com/picampers/images/checkmark.png");

       Stage stage; 
       Parent root;
       stage=(Stage) modifier.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("AfficheClub.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }    
        
     public void pickImage (ActionEvent actionEvent) throws MalformedURLException, FileNotFoundException, IOException {

        fileSelected.setVisible(false);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Veuillez choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif")); // limit fileChooser options to image files
        File selectedFile = fileChooser.showOpenDialog(fileSelected.getScene().getWindow());

        InputStream input = new FileInputStream(selectedFile.getAbsolutePath());
        OutputStream output = new FileOutputStream("C:\\wamp\\www\\images\\"+selectedFile.getName());
        
        IOUtils.copy(input, output);       
        
        
        if (selectedFile != null) {

imageFile="file:/C:/wamp/www/picampers/web/images/"+selectedFile.getName();
fileSelected.setText(imageFile);
Image image = new Image(imageFile);
            imageView.setImage(image);
        } else {
            fileSelected.setText("erreur");
        }}   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                fileSelected.setVisible(false);
IClubService iClubService=new ClubService(); 
        System.out.println("image"+iClubService.afficher(val).get(0).getImage());
           Image image = new Image(iClubService.afficher(val).get(0).getImage());
            imageView.setImage(image);
  
mtitre.setText(iClubService.afficher(val).get(0).getNom()); 
mdesc.setText(iClubService.afficher(val).get(0).getLieu()); 
mtag1.setText(iClubService.afficher(val).get(0).getType()); 
//mtag2.setText(iClubService.afficher(val).get(0).getDisponibilite());
mdescription.setText(iClubService.afficher(val).get(0).getDescription());

}
}

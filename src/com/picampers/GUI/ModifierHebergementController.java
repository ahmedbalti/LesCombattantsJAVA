/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import com.picampers.IServices.IHebergementService;
import com.picampers.Services.HebergementService;

/**
 *
 * @author Arij
 */
public class ModifierHebergementController implements Initializable {

@FXML private Label fileSelected;
@FXML private TextField namee;
@FXML private TextField nbrstars;
@FXML private TextField nbrrooms;
@FXML private TextField addresse;
@FXML private TextField typee;
@FXML private TextField mdiponibilite;
@FXML private TextField mdescription;  
@FXML private Button retour;
@FXML private Button modifier;
@FXML private ImageView imageView;
private String imageFile;    
private static int val;

   public static void setVal(int val) {
      ModifierHebergementController.val = val;
    }

    public static int getVal() {
        return val;
    }
    
private static String usr;

    public static String getUsr() {
        return usr;
    }

    public static void setUsr(String usr) {
        ModifierHebergementController.usr = usr;
    }
    
    
    
    
    @FXML
    private void handleretourAction(ActionEvent event) throws IOException {
       IHebergementService iHebergementService=new HebergementService();  
       FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheHebergement.fxml"));
       HebergementController controller = loader.getController();
       controller.setVal(val);
       controller.setUsr(usr);
       Stage stage; 
       Parent root;
       stage=(Stage) retour.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("AfficheHebergement.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }    
    
    @FXML
    private void handlemodifierAction(ActionEvent event) throws IOException {
    IHebergementService iSujetService=new HebergementService();
     
int id= val;
String type=typee.getText();
String name=namee.getText();
 int nb_stars=(Integer.parseInt(nbrstars.getText()));
 int nb_rooms=(Integer.parseInt(nbrrooms.getText()));
//int nb_stars= val;
//int nb_rooms=val;
String address=addresse.getText();
String description=mdescription.getText();
//String img=fileSelected.getText();
int cover_id = iSujetService.afficher(val).get(0).getCover_id();
iSujetService.update(id,type,name,nb_stars,nb_rooms,address,description,cover_id,fileSelected.getText());

Image img1=new Image("/com/picampers/images/checkmark.png");

       Stage stage; 
       Parent root;
       stage=(Stage) modifier.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("AfficheHebergement.fxml"));
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
    IHebergementService iHebergementService=new HebergementService(); 
        System.out.println("image"+iHebergementService.afficher(val).get(0).getImage());
           Image image = new Image(iHebergementService.afficher(val).get(0).getImage());
            imageView.setImage(image);
            mdescription.setText(iHebergementService.afficher(val).get(0).getDescription());  
            namee.setText(iHebergementService.afficher(val).get(0).getName()); 
            nbrstars.setText(String.valueOf(iHebergementService.afficher(val).get(0).getNbStars()));
            //nbrrooms.setText(String.valueOf(iHebergementService.afficher(val).get(0).getNbRooms()));
            addresse.setText(iHebergementService.afficher(val).get(0).getAddress()); 
            typee.setText(iHebergementService.afficher(val).get(0).getType()); 

}
}

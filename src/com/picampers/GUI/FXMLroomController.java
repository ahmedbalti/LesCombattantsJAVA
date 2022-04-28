/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.GUI;

import com.picampers.Services.HebergementService;
import com.picampers.Services.RoomService;
import com.picampers.entities.Hebergement;
import com.picampers.entities.Room;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.io.IOUtils;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.List;
/**
 * FXML Controller class
 *
 * @author Arij
 */
public class FXMLroomController implements Initializable {

    @FXML
    private TableView<Room> table;
    @FXML
    private TableColumn<Room, Integer> cid;
    @FXML
    private TableColumn<Room, Integer> ccoverid;
    @FXML
    private TableColumn<Room, Integer> chebr;
    @FXML
    private TableColumn<Room, Integer> cnumber;
    @FXML
    private TableColumn<Room, Integer> cetage;
    @FXML
    private TableColumn<Room, Integer> cnblit;
    @FXML
    private TableColumn<Room, String> cprix;
    @FXML
    private TableColumn<Room, String> cdesc;
    @FXML
    private TableColumn<Room, Integer> cdispo;
    @FXML
    private TableColumn<Room, String> cempla;
    @FXML
    private TableColumn<Room, String> ctype;
    @FXML
    private TextField tfnumber;
    @FXML
    private TextField tfetage;
    @FXML
    private TextField tfnblit;
    @FXML
    private TextField tfprice;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfemplacement;
    @FXML
    private TextField tftype;
    @FXML
    private ComboBox<String> cbhebergement;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField tfrecherche;
    RoomService sr=new RoomService();
    HebergementService hs=new HebergementService();
    ObservableList<Room> data = FXCollections.observableArrayList(); 
    ObservableList<String> options = FXCollections.observableArrayList(hs.getAllHebergementName()); 
    private String imageFile;
    @FXML
    private Label fileSelected;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbhebergement.setItems(options);
        recherche_avance(sr.affichertous());
    }    
    public String controleDeSaisie(){
        
        String erreur="";
        if(tfdescription.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Description\n";
        }
        if(tfemplacement.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Emplacement\n";
        }
        
        if(!tfetage.getText().trim().matches("[0-9]+") ||tfetage.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Etage\n";
        }
        if(!tfnblit.getText().trim().matches("[0-9]+") ||tfnblit.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Nb lit\n";
        }
        if(tfnumber.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Number\n";
        }
        if(!tfprice.getText().trim().matches("[0-9]+") ||tfprice.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ price\n";
        }
        if(tftype.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ type\n";
        }
        if(cbhebergement.getValue()==null){
            erreur+="-Veuillez remplir le champ hebergement\n";
        }
        if(!tfnumber.getText().trim().matches("[0-9]+") ){
            erreur+="-Veuillez remplir le champ Number\n";
        }
        return erreur;
        
    }
    @FXML
    private void pickImage(ActionEvent event) throws MalformedURLException, IOException{  
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
    private void ajouter(ActionEvent event) {
        
        if(controleDeSaisie().length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout chambre");
            alert.setContentText(controleDeSaisie());
            alert.showAndWait();
        }
        else{
            Room r=new Room(fileSelected.getText(),
                hs.getHebergementByName(cbhebergement.getValue()).getId(), 
                Integer.parseInt(tfnumber.getText()), Integer.parseInt(tfetage.getText()),
                Integer.parseInt(tfnblit.getText()), tfprice.getText(), tfemplacement.getText(),
                tfdescription.getText(), tftype.getText(), true);
            sr.add(r);
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Ajout chambre");
            tray.setMessage("vous avez bien ajouter une chambre");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
            recherche_avance(sr.affichertous());
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        
        if(table.getSelectionModel().getSelectedItem()!=null){
            if(controleDeSaisie().length()>0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur modifier chambre");
                alert.setContentText(controleDeSaisie());
                alert.showAndWait();
            }
            else{
                Room r=new Room(fileSelected.getText(),
                hs.getHebergementByName(cbhebergement.getValue()).getId(), 
                Integer.parseInt(tfnumber.getText()), Integer.parseInt(tfetage.getText()),
                Integer.parseInt(tfnblit.getText()), tfprice.getText(), tfemplacement.getText(),
                tfdescription.getText(), tftype.getText(), true);
                sr.update(table.getSelectionModel().getSelectedItem().getId(), r);
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle("modifier chambre");
                tray.setMessage("vous avez bien modifier une chambre");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(1000));
            }
        }
        recherche_avance(sr.affichertous());
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        if(table.getSelectionModel().getSelectedItem()!=null){
            sr.delete(table.getSelectionModel().getSelectedItem().getId());
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("supprimer chambre");
            tray.setMessage("vous avez bien supprimer une chambre");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
        }
        
        recherche_avance(sr.affichertous());
    }
    
    public void refresh(List<Room> rooms){
        data.clear();
        data=FXCollections.observableArrayList(rooms); 
        cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        ccoverid.setCellValueFactory(new PropertyValueFactory<>("id_cover"));
        cdesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        cdispo.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
        cempla.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
        cetage.setCellValueFactory(new PropertyValueFactory<>("etage"));
        chebr.setCellValueFactory(new PropertyValueFactory<>("hebergement"));
        cnblit.setCellValueFactory(new PropertyValueFactory<>("nb_lit"));
        cnumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        ctype.setCellValueFactory(new PropertyValueFactory<>("type"));
        cprix.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.setItems(data);
    }
    public void recherche_avance(List<Room> rooms){
        refresh(rooms);
        FilteredList<Room> filtereddata=new FilteredList<>(data,b->true);
        tfrecherche.textProperty().addListener((observable,oldvalue,newValue) -> {
            filtereddata.setPredicate(b->{
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                String lowercasefilter=newValue.toLowerCase();
                if(String.valueOf(b.getId()).toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(String.valueOf(b.getEtage()).toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(String.valueOf(b.getNb_lit()).toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(b.getDescription().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                
                else if(b.getEmplacement().toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(b.getPrice().toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(b.getType().toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                
                else if(String.valueOf(b.getNumber()).toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else{
                    return false;
                }
                
            });
        });
        
        table.setItems(filtereddata);
    }

    @FXML
    private void filldata(MouseEvent event) {
        Room t=table.getSelectionModel().getSelectedItem();
        if(t!=null){
            tfnblit.setText(String.valueOf(t.getNb_lit()));
            tfnumber.setText(String.valueOf(t.getNumber()));
            tfdescription.setText(t.getDescription());
            tfetage.setText(String.valueOf(t.getEtage()));
            tfprice.setText(String.valueOf(t.getPrice()));
            tfemplacement.setText(t.getEmplacement());
            tftype.setText(t.getType());

        
        }
    }

    @FXML
    private void stat(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/picampers/GUI/FXMLstat.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage=new Stage();
            primaryStage.setTitle("Stat");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLroomController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void tri(ActionEvent event) {
          recherche_avance(sr.sortByEtage());
    }
    @FXML
    private void gotohebergement(ActionEvent event) {
Stage closestage=(Stage)((Node)event.getSource()).getScene().getWindow();
                    closestage.close();
try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/picampers/GUI/ListeHebergements.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage=new Stage();
            primaryStage.setTitle("Stat");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLroomController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
@FXML
    private void gotoclub(ActionEvent event) {
}
}

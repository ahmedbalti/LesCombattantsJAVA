/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.GUI;

import com.picampers.Services.ClubService;
import com.picampers.Services.TableService;
import com.picampers.entities.Table;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
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
/**
 * FXML Controller class
 *
 * @author hajer
 */
public class FXMLtableController implements Initializable {

    @FXML
    private TableView<Table> table;
    @FXML
    private TableColumn<Table, Integer> cid;
    @FXML
    private TableColumn<Table, Integer> ccoverid;
    @FXML
    private TableColumn<Table, Integer> cclub;
    @FXML
    private TableColumn<Table, Integer> cnumero;
    @FXML
    private TableColumn<Table, String> cposition;
    @FXML
    private TableColumn<Table, String> cforme;
    @FXML
    private TableColumn<Table, String> ctype;
    @FXML
    private TableColumn<Table, Boolean> cdisponibilite;
    @FXML
    private TextField tfnumero;
    @FXML
    private TextField tfposition;
    @FXML
    private TextField tfforme;
    @FXML
    private TextField tftype;
    @FXML
    private ImageView imageView;
    @FXML
    private Label fileSelected;
    @FXML
    private TextField tfrecherche;
    @FXML
    private ComboBox<String> cbclub;
    TableService sr=new TableService();
    ClubService hs=new ClubService();
    ObservableList<Table> data = FXCollections.observableArrayList(); 
    ObservableList<String> options = FXCollections.observableArrayList(hs.getAllClubName()); 
    private String imageFile;

    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbclub.setItems(options);
        recherche_avance(sr.affichertous());
    }    
    public String controleDeSaisie(){
        
        String erreur="";
        if(tfforme.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Forme\n";
        }
        if(tfnumero.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Numero\n";
        }
        
        if(tfposition.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Position\n";
        }
        
        if(tftype.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ type\n";
        }
        if(cbclub.getValue()==null){
            erreur+="-Veuillez remplir le champ club\n";
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
        Logger.getLogger(FXMLtableController.class.getName()).log(Level.SEVERE, null, ex);
   
   
        }
        /*
        club numero position forme type dispo image
        /*
        club numero position forme type dispo image
        */
    }

    @FXML
    private void ajouter(ActionEvent event) {
        
        if(controleDeSaisie().length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout table");
            alert.setContentText(controleDeSaisie());
            alert.showAndWait();
        }
        else{
            Table r=new Table(
                hs.getClubByName(cbclub.getValue()).getId(), 
                Integer.parseInt(tfnumero.getText()),
                tfposition.getText(), tfforme.getText(),
                tftype.getText(), true,fileSelected.getText());
            sr.add(r);
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Ajout table");
            tray.setMessage("vous avez bien ajouter une table");
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
                alert.setTitle("Erreur modifier table");
                alert.setContentText(controleDeSaisie());
                alert.showAndWait();
            }
            else{
                Table r=new Table(
                hs.getClubByName(cbclub.getValue()).getId(), 
                Integer.parseInt(tfnumero.getText()),
                tfposition.getText(), tfforme.getText(),
                tftype.getText(), true,fileSelected.getText());
                sr.update(table.getSelectionModel().getSelectedItem().getId(), r);
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle("modifier table");
                tray.setMessage("vous avez bien modifier une table");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(1000));
                recherche_avance(sr.affichertous());
            }
        }
        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        if(table.getSelectionModel().getSelectedItem()!=null){
            sr.delete(table.getSelectionModel().getSelectedItem().getId());
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("supprimer table");
            tray.setMessage("vous avez bien supprimer une table");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
        }
        
        recherche_avance(sr.affichertous());
    }
    
    public void refresh(List<Table> tables){
        data.clear();
        data=FXCollections.observableArrayList(tables); 
        cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        ccoverid.setCellValueFactory(new PropertyValueFactory<>("cover_id"));
        cclub.setCellValueFactory(new PropertyValueFactory<>("club"));
        cdisponibilite.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
        cforme.setCellValueFactory(new PropertyValueFactory<>("forme"));
        cnumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        cposition.setCellValueFactory(new PropertyValueFactory<>("position"));
        ctype.setCellValueFactory(new PropertyValueFactory<>("type"));
       
        table.setItems(data);
    }
    public void recherche_avance(List<Table> tables){
        refresh(tables);
        FilteredList<Table> filtereddata=new FilteredList<>(data,b->true);
        tfrecherche.textProperty().addListener((observable,oldvalue,newValue) -> {
            filtereddata.setPredicate(b->{
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                String lowercasefilter=newValue.toLowerCase();
                if(String.valueOf(b.getId()).toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(String.valueOf(b.getClub()).toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(String.valueOf(b.getNumero()).toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(b.getForme().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                
                else if(b.getPosition().toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                
                else if(b.getType().toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                
                
                else{
                    return false;
                }
                
            });
        });
        
        table.setItems(filtereddata);
    }

    //@FXML
    private void filldata(MouseEvent event) {
        Table t=table.getSelectionModel().getSelectedItem();
        if(t!=null){
            tfnumero.setText(String.valueOf(t.getNumero()));
            tfposition.setText(t.getPosition());
            tfforme.setText(String.valueOf(t.getForme()));
            
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
            Logger.getLogger(FXMLtableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
 @FXML
    private void tri(ActionEvent event){
        recherche_avance(sr.sortByEtage());
}
@FXML
    private void gotoclub(ActionEvent event) {
Stage closestage=(Stage)((Node)event.getSource()).getScene().getWindow();
                    closestage.close();
try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/picampers/GUI/ListeClubs.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage=new Stage();
            primaryStage.setTitle("Stat");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLtableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

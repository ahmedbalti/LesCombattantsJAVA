/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.GUI;

import com.picampers.IServices.IHebergementService;
import com.picampers.Services.HebergementService;
import com.picampers.entities.Hebergement;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Arij
 */
public class FXMLchoisireHebergementaController implements Initializable {

    @FXML
    private TableColumn<Hebergement, String> namee;
    @FXML
    private TableColumn<Hebergement, Integer> nbrstars;
    @FXML
    private TableColumn<Hebergement, Integer> nbrrooms;
    @FXML
    private TableColumn<Hebergement, String> addresse;
    @FXML 
    private TableColumn<Hebergement, String> mdescription;
    @FXML 
    private TableColumn<Hebergement, String> typee;
    @FXML 
    private TableColumn<Hebergement, String> mdisponibilite;
    @FXML 
    private TableColumn<Hebergement, String> tabid;
    @FXML 
    private TableView<Hebergement> table;
    public static int id_hebergement_globale;
       private ObservableList<ObservableList> data;
   private ObservableList<Hebergement> listehebergements;
    @FXML
    private TextField tfrecherche;
    @FXML
    private Label adminName;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        recherche_avance();
    }    

    @FXML
    private void suivant(ActionEvent event) {
        if(table.getSelectionModel().getSelectedItem()!=null){
            id_hebergement_globale=table.getSelectionModel().getSelectedItem().getId();
            Stage closestage=(Stage)((Node)event.getSource()).getScene().getWindow();
                    closestage.close();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/picampers/GUI/FXMLreservationhebergement.fxml"));
                        Scene scene = new Scene(root);
                        Stage primaryStage=new Stage();
                        primaryStage.setTitle("Signup");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLchoisireHebergementaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Selectionne hebergement");
            alert.showAndWait();
        }
        
    }
     private void loadHebergements(){
      IHebergementService iHebergementService =new HebergementService();
      List<Hebergement> liste= new ArrayList();
      listehebergements = FXCollections.observableArrayList(iHebergementService.listehebergements());  
      tabid.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("id"));      
      namee.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("name"));
      nbrstars.setCellValueFactory(new PropertyValueFactory<Hebergement,Integer>("nb_stars"));
      nbrrooms.setCellValueFactory(new PropertyValueFactory<Hebergement,Integer>("nb_rooms"));
      addresse.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("address"));
      mdescription.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("description"));
      typee.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("type"));
      mdisponibilite.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("disponibilite"));
 
      table.setItems(listehebergements);  
    }
     public void recherche_avance(){
        loadHebergements();
        FilteredList<Hebergement> filtereddata=new FilteredList<>(listehebergements,b->true);
        tfrecherche.textProperty().addListener((observable,oldvalue,newValue) -> {
            filtereddata.setPredicate(b->{
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                String lowercasefilter=newValue.toLowerCase();
                if(String.valueOf(b.getId()).toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(String.valueOf(b.getNbStars()).toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(String.valueOf(b.getNbRooms()).toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(b.getDescription().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                
                else if(b.getType().toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                
                else if(String.valueOf(b.getNbRooms()).toLowerCase().indexOf(lowercasefilter)!=-1){
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
    private void Retour(ActionEvent event) {
                                                    try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/picampers/GUI/HomeFront.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  }


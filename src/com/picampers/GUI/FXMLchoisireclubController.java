/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.GUI;

import com.picampers.IServices.IClubService;
import com.picampers.Services.ClubService;
import com.picampers.entities.Club;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
 * @author hajer
 */
public class FXMLchoisireclubController implements Initializable {

    @FXML
    private TableView<Club> table;
    @FXML
    private TableColumn<Club, Integer> tabid;
    @FXML
    private TableColumn<Club, String> tabtitre;
    @FXML
    private TableColumn<Club, String> tabuser;
    @FXML
    private TableColumn<Club, String> tabtag1;
    @FXML
    private TableColumn<Club, String> tabtag2;
    @FXML
    private TableColumn<Club, Boolean> tabtag3;
    @FXML
    private TextField tfrecherche;
    private ObservableList<ObservableList> data;
    private ObservableList<Club> listeclubs;
    private Connection conn;
    public static int id_club_globale;
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
            id_club_globale=table.getSelectionModel().getSelectedItem().getId();
            Stage closestage=(Stage)((Node)event.getSource()).getScene().getWindow();
                    closestage.close();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/picampers/GUI/FXMLreservationclub.fxml"));
                        Scene scene = new Scene(root);
                        Stage primaryStage=new Stage();
                        primaryStage.setTitle("Signup");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLchoisireclubController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Selectionne club");
            alert.showAndWait();
        }
    }
    void loadClubs() {
        IClubService iClubService = new ClubService();
        listeclubs = FXCollections.observableArrayList(iClubService.listeclubs());
        tabid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabtitre.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tabuser.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        tabtag1.setCellValueFactory(new PropertyValueFactory<>("description"));
        tabtag2.setCellValueFactory(new PropertyValueFactory<>("type"));
        tabtag3.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));

        table.setItems(listeclubs);
    }
    public void recherche_avance(){
        loadClubs();
        FilteredList<Club> filtereddata=new FilteredList<>(listeclubs,b->true);
        tfrecherche.textProperty().addListener((observable,oldvalue,newValue) -> {
            filtereddata.setPredicate(b->{
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                String lowercasefilter=newValue.toLowerCase();
                if(String.valueOf(b.getId()).toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(String.valueOf(b.getCover_id()).toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(String.valueOf(b.getImage()).toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(b.getDescription().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                
                else if(b.getType().toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                
                else if(String.valueOf(b.getLieu()).toLowerCase().indexOf(lowercasefilter)!=-1){
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

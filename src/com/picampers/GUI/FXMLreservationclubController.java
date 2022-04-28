/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.GUI;

import com.picampers.Services.ClubService;
import com.picampers.Services.ReservationTableService;
import com.picampers.Services.TableService;
import com.picampers.entities.Club;
import com.picampers.entities.ReservationTable;
import com.picampers.entities.Table;
import com.picampers.utils.Mailapi;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hajer
 */
public class FXMLreservationclubController implements Initializable {

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
    private DatePicker date;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfnbpersonne;
    TableService sr=new TableService();
    ClubService hs=new ClubService();
    ReservationTableService rs=new ReservationTableService();
    ObservableList<Table> data = FXCollections.observableArrayList(); 
    @FXML
    private HBox chosenhotelCard;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refresh();
    }    
    public String controleDeSaisie(){
        
        String erreur="";
        if(tfname.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Name\n";
        }
        if(tfnbpersonne.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Nb personne\n";
        }
        
       
        if(date.getValue()==null){
            erreur+="-Veuillez remplir le champ date\n";
        }
        
        return erreur;
        
    }
    @FXML
    private void reserver(ActionEvent event) {
        Time t=new Time(0, 0, 0);
        
        if(controleDeSaisie().length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur reservation");
            alert.setContentText(controleDeSaisie());
            alert.showAndWait();
        }
        else{
            ReservationTable r=new ReservationTable(
                table.getSelectionModel().getSelectedItem().getId(),
                Date.valueOf(date.getValue()),
                t,
                tfnbpersonne.getText(),tfname.getText());
            rs.add(r);
            Table tab=table.getSelectionModel().getSelectedItem();
            tab.setDisponibilite(false);
            sr.update(tab.getId(), tab);
            Mailapi.send("testapimail63@gmail.com", "TESTapimail2022","hajer.moumni@esprit.tn", "Reservation table", "Vous avez reserver une table pour : "+r.getNbr_personnes()+" personnes");

            refresh();
        }
    }
    public void refresh(){
        data.clear();
        data=FXCollections.observableArrayList(sr.affichertous(FXMLchoisireclubController.id_club_globale)); 
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

    @FXML
    private void precedent(ActionEvent event) {
         Stage closestage=(Stage)((Node)event.getSource()).getScene().getWindow();
                    closestage.close();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/picampers/GUI/FXMLchoisireclub.fxml"));
                        Scene scene = new Scene(root);
                        Stage primaryStage=new Stage();
                        primaryStage.setTitle("Signup");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLreservationclubController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

    @FXML
    private void gotoreservation(ActionEvent event) {
        Stage closestage=(Stage)((Node)event.getSource()).getScene().getWindow();
                    closestage.close();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/picampers/GUI/FXMLviewreservationtable.fxml"));
                        Scene scene = new Scene(root);
                        Stage primaryStage=new Stage();
                        primaryStage.setTitle("Signup");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLreservationclubController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

    @FXML
    private void Acceuil(ActionEvent event) {
    }
}

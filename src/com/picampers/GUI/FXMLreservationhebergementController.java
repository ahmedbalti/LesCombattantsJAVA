/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.GUI;

import com.picampers.Services.HebergementService;
import com.picampers.Services.ReservationHebergementService;
import com.picampers.Services.RoomService;
import com.picampers.entities.Hebergement;
import com.picampers.entities.ReservationHebergement;
import com.picampers.entities.Room;
import com.picampers.utils.Mailapi;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Arij
 */
public class FXMLreservationhebergementController implements Initializable {

    @FXML
    private TextField tftitre;
    @FXML
    private TextField tfnom;
    @FXML
    private RadioButton rall;
    @FXML
    private ToggleGroup g1;
    @FXML
    private RadioButton rdemi;
    @FXML
    private DatePicker dpdebut;
    @FXML
    private DatePicker dpfin;
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
    RoomService sr=new RoomService();
    HebergementService hs=new HebergementService();
    ReservationHebergementService rs=new ReservationHebergementService();
    ObservableList<Room> data = FXCollections.observableArrayList(); 
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
        if(tfnom.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Name\n";
        }
        if(tftitre.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Nb personne\n";
        }
        
       
        if(dpdebut.getValue()==null){
            erreur+="-Veuillez remplir le champ date debut\n";
        }
        if(dpfin.getValue()==null){
            erreur+="-Veuillez remplir le champ date fin\n";
        }
        
        return erreur;
        
    }
    @FXML
    private void reserver(ActionEvent event) {
        boolean all_day;
        if(rall.isSelected()){
            all_day=true;
        }
        else{
            all_day=false;
        }
        
        if(controleDeSaisie().length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur reservation");
            alert.setContentText(controleDeSaisie());
            alert.showAndWait();
        }
        else{
            ReservationHebergement r=new ReservationHebergement(
                table.getSelectionModel().getSelectedItem().getId(),
                tftitre.getText(), 
                dpdebut.getValue().atStartOfDay(), 
                dpfin.getValue().atStartOfDay(),
                tfnom.getText(),
                all_day);
            rs.add(r);
            Room room=table.getSelectionModel().getSelectedItem();
            room.setDisponibilite(false);
            sr.update(room.getId(), room);
            Mailapi.send("testapimail63@gmail.com", "TESTapimail2022","arij.ghazouani@esprit.tn", "Reservation chambre", "Vous avez reserver une chambre de : "+r.getStart()+" jusqua: "+r.getNd());

            refresh();
        }
    }
    public void refresh(){
        data.clear();
        data=FXCollections.observableArrayList(sr.afficherparhebergement(FXMLchoisireHebergementaController.id_hebergement_globale)); 
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

    @FXML
    private void precedent(ActionEvent event) {
        Stage closestage=(Stage)((Node)event.getSource()).getScene().getWindow();
                    closestage.close();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/picampers/GUI/FXMLchoisireHebergementa.fxml"));
                        Scene scene = new Scene(root);
                        Stage primaryStage=new Stage();
                        primaryStage.setTitle("Signup");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLchoisireHebergementaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

    @FXML
    private void gotoreservation(ActionEvent event) {
        Stage closestage=(Stage)((Node)event.getSource()).getScene().getWindow();
                    closestage.close();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/picampers/GUI/FXMLviewreservation.fxml"));
                        Scene scene = new Scene(root);
                        Stage primaryStage=new Stage();
                        primaryStage.setTitle("Signup");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLchoisireHebergementaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

    @FXML
    private void Acceuil(ActionEvent event) {
    }
}

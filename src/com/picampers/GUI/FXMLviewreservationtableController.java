/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.GUI;

import com.picampers.Services.ReservationTableService;
import com.picampers.Services.TableService;
import com.picampers.entities.ReservationTable;
import com.picampers.entities.Table;
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
public class FXMLviewreservationtableController implements Initializable {

    @FXML
    private TableView<ReservationTable> table;
    @FXML
    private TableColumn<ReservationTable, Integer> cid;
    @FXML
    private TableColumn<ReservationTable, Integer> ctabl;
    @FXML
    private TableColumn<ReservationTable, Date> cdate;
    @FXML
    private TableColumn<ReservationTable, Time> cheure;
    @FXML
    private TableColumn<ReservationTable, String> cnbpersonne;
    @FXML
    private TableColumn<ReservationTable, String> cname;
    @FXML
    private DatePicker date;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfnbpersonne;
    ReservationTableService rs=new ReservationTableService();
    TableService ts=new TableService();
    ObservableList<ReservationTable> data = FXCollections.observableArrayList(); 
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

    @FXML
    private void modifier(ActionEvent event) {
        Time t=new Time(0, 0, 0);
        ReservationTable r=new ReservationTable(
                table.getSelectionModel().getSelectedItem().getId(),
                Date.valueOf(date.getValue()),
                t,
                tfnbpersonne.getText(),tfname.getText());
        if(table.getSelectionModel().getSelectedItem()!=null){
            rs.update(table.getSelectionModel().getSelectedItem().getId(), r);
            refresh();
        }
        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        if(table.getSelectionModel().getSelectedItem()!=null){
            rs.delete(table.getSelectionModel().getSelectedItem().getId());
            Table t=ts.getTableById(table.getSelectionModel().getSelectedItem().getTabl());
            t.setDisponibilite(true);
            ts.update(t.getId(), t);
        }
        refresh();
    }

    @FXML
    private void precedent(ActionEvent event) {
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
                        Logger.getLogger(FXMLviewreservationtableController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
    public void refresh(){
        data.clear();
        data=FXCollections.observableArrayList(rs.affichertous()); 
        
        cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        ctabl.setCellValueFactory(new PropertyValueFactory<>("tabl"));
        cdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        cheure.setCellValueFactory(new PropertyValueFactory<>("heure"));
        cnbpersonne.setCellValueFactory(new PropertyValueFactory<>("nbr_personnes"));
        cname.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.setItems(data);
    }

    @FXML
    private void Acceuil(ActionEvent event) {
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.GUI;

import com.picampers.Services.ReservationHebergementService;
import com.picampers.entities.ReservationHebergement;
import com.picampers.entities.Room;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Arij
 */
public class FXMLviewreservationController implements Initializable {

    @FXML
    private TableView<ReservationHebergement> table;
    @FXML
    private TableColumn<ReservationHebergement, Integer> cid;
    @FXML
    private TableColumn<ReservationHebergement, Integer> croom;
    @FXML
    private TableColumn<ReservationHebergement, String> ctitle;
    @FXML
    private TableColumn<ReservationHebergement, LocalDateTime> cstart;
    @FXML
    private TableColumn<ReservationHebergement, LocalDateTime> cend;
    @FXML
    private TableColumn<ReservationHebergement, String> cname;
    @FXML
    private TableColumn<ReservationHebergement, Boolean> callday;
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
    ReservationHebergementService rs=new ReservationHebergementService();
    ObservableList<ReservationHebergement> data = FXCollections.observableArrayList(); 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refresh();
    }    

    @FXML
    private void precedent(ActionEvent event) {
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

    @FXML
    private void update(ActionEvent event) {
        boolean all_day;
        if(rall.isSelected()){
            all_day=true;
        }
        else{
            all_day=false;
        }
        ReservationHebergement r=new ReservationHebergement(
                table.getSelectionModel().getSelectedItem().getId(),
                tftitre.getText(), 
                dpdebut.getValue().atStartOfDay(), 
                dpfin.getValue().atStartOfDay(),
                tfnom.getText(),
                all_day);
        if(table.getSelectionModel().getSelectedItem()!=null){
            rs.update(table.getSelectionModel().getSelectedItem().getId(), r);
            refresh();
        }
        
    }
    public void refresh(){
        data.clear();
        data=FXCollections.observableArrayList(rs.affichertous()); 
        cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        callday.setCellValueFactory(new PropertyValueFactory<>("all_day"));
        cend.setCellValueFactory(new PropertyValueFactory<>("nd"));
        cname.setCellValueFactory(new PropertyValueFactory<>("name"));
        croom.setCellValueFactory(new PropertyValueFactory<>("room"));
        cstart.setCellValueFactory(new PropertyValueFactory<>("start"));
        ctitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        table.setItems(data);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        if(table.getSelectionModel().getSelectedItem()!=null){
            rs.delete(table.getSelectionModel().getSelectedItem().getId());
        }
        refresh();
    }

    @FXML
    private void filldata(MouseEvent event) {
        ReservationHebergement r=table.getSelectionModel().getSelectedItem();
        if(r!=null){
            tfnom.setText(r.getName());
            tftitre.setText(r.getTitle());
            rall.setSelected(r.getAll_day());
            
            dpfin.setValue(r.getNd().toLocalDate());
            dpdebut.setValue(r.getStart().toLocalDate());
        
        }
    }
}

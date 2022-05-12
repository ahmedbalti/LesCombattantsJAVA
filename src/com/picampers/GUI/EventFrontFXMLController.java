/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.picampers.Gui;

import com.picampers.entities.Event;
import com.picampers.entities.Lieu;
import com.picampers.Services.ServiceEvent;
import com.picampers.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class EventFrontFXMLController implements Initializable {
@FXML
    private TableColumn<?, ?> DateEventCol;

    @FXML
    private TableColumn<?, ?> DescriptionEventCol;

    @FXML
    private TableColumn<?, ?> EmailEventCol;

    @FXML
    private TableView<?> EventTable;

    @FXML
    private TableColumn<?, ?> IDEventCol;

    @FXML
    private TableColumn<?, ?> LieuEventCol;

    @FXML
    private TableColumn<?, ?> NomEventCol;

    @FXML
    private TableColumn<?, ?> TypeEventCol;


  String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Lieu lieu = null ;
    Event event=null;
    Event event2=null;
    ObservableList<Lieu>  LieuList = FXCollections.observableArrayList();
    ObservableList<Event>  EventList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 

private void loadDateEvent() {
        
        Connection cnx = MyConnection.getInstance().getCnx(); 
      refreshTableEvent();
        
       NomEventCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        TypeEventCol.setCellValueFactory(new PropertyValueFactory<>("type"));
         DescriptionEventCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        DateEventCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        LieuEventCol.setCellValueFactory(new PropertyValueFactory<>("lieuid"));

        EmailEventCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        IDEventCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        
     
    }   
@FXML
    private void refreshTableEvent() {
       
ServiceEvent at= new ServiceEvent();
       
        at.afficher().stream().forEach((p) -> {EventList.add(p);});
        
   /* NomEventCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        TypeEventCol.setCellValueFactory(new PropertyValueFactory<>("type"));
         DescriptionEventCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        //DateEventCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        LieuEventCol.setCellValueFactory(new PropertyValueFactory<>("lieuid"));

        EmailEventCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        IDEventCol.setCellValueFactory(new PropertyValueFactory<>("id"));*/
       //EventTable.setItems(EventList);    
         
            
            
        
    }

public void start(Stage primaryStage) {
        try {
            //Parent root =FXMLLoader.load(getClass().getResource("FXMLEventFront.fxml"));


FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEventFXML.fxml"));

          
          FXMLEventController controller = loader.getController();
                      



Parent root = (Parent)loader.load();

            Scene scene = new Scene(root);

String css = this.getClass().getResource("stylesheetevent.css").toExternalForm();
   scene.getStylesheets().add(css);
            primaryStage.setScene(scene);
            primaryStage.setTitle("-----Gestion Lieu ------");
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    
}

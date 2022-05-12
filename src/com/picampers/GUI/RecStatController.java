/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.picampers.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class RecStatController implements Initializable {
@FXML
    private PieChart pie;
    @FXML
    private Label nh_lab;
    @FXML
    private Label nf_lab;
    @FXML
    private Label ne_lab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   int nho=FXMLReclamationController.nh , nfe=FXMLReclamationController.nf, nen=FXMLReclamationController.ne ; 
        System.out.println(FXMLReclamationController.nh);
        pie.setTitle("Stats");
        ObservableList <PieChart.Data> ol = FXCollections.observableArrayList(
        
        new PieChart.Data("Reclamation Service", nho),new PieChart.Data("Reclamation Hotel", nfe),new PieChart.Data("Reclamation Evennement"
 + "", nen)
                
                
           );
        nh_lab.setText(""+nho); nf_lab.setText(""+nfe); ne_lab.setText(""+nen);
        pie.setData(ol);
      
    }   
 @FXML
    private void back(ActionEvent event) 
         throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("FXMLReponse.fxml"));
        
        
        
        Parent root = loader.load();
        nh_lab.getScene().setRoot(root);
        
        
    } 
    
}

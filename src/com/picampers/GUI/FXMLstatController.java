/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.GUI;

import com.picampers.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author Arij
 */
public class FXMLstatController implements Initializable {

    @FXML
    private PieChart piechart;
ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        stat();
    }    
    private void stat()
    {
          
           Connection cnx = DataSource.getMyInstance().getMyConnexion();
      try {
           
          String query = "select count(*) As value,disponibilite As dispo from room group by disponibilite" ;
       
         PreparedStatement PreparedStatement = cnx.prepareStatement(query);
          ResultSet rs = PreparedStatement.executeQuery();
             rs.next();
             data.add(new PieChart.Data("Non disponible",rs.getInt("value"))) ;
             int x=rs.getInt("value");
             if(!rs.next()){
                 data.clear();
                 data.add(new PieChart.Data("disponible",x)) ;
             }
             else{
                
                data.add(new PieChart.Data("Disponible",rs.getInt("value"))) ;
             }
             
                 
        } catch (SQLException ex) {
            Logger.getLogger(FXMLstatController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        piechart.setTitle("**Statistiques nombres des chambres disponible**");
        piechart.setLegendSide(Side.LEFT);
        
        piechart.setData(data);
       
    
    }
}

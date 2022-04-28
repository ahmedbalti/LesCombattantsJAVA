/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.GUI;

import com.picampers.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class StatsController implements Initializable {
 
 String name="";   
 int nbr_cmt;  
@FXML private Button retour;
    @FXML
    private BarChart<String,Integer> sujetpluscom;
            
    @FXML
    private CategoryAxis xAxis;
    
    @FXML
    private NumberAxis yAxis;
    
        @FXML
    private BarChart<String,Integer> usrvote;
            
    @FXML
    private CategoryAxis x3Axis;
    
    @FXML
    private NumberAxis y3Axis;
 
    @FXML
    private BarChart<String,Integer> userpluscom;    
    
        @FXML
    private CategoryAxis x1Axis;
    
    @FXML
    private NumberAxis y1Axis;
    
    
    @FXML
    private BarChart<String,Integer> vote;    
    
        @FXML
    private CategoryAxis x4Axis;
    
    @FXML
    private NumberAxis y4Axis;    

    @FXML
    private BarChart<String,Integer> usrsuj;    
    
        @FXML
    private CategoryAxis x2Axis;
    
    @FXML
    private NumberAxis y2Axis;    
    
    
    private Connection conn;
    
    @FXML
    private void handleretouraction(ActionEvent event) throws IOException {
    
       Stage stage; 
       Parent root;
       stage=(Stage) retour.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("ListeHebergements.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }    

    
@FXML
    private void handlebtnvoteaction(ActionEvent event) throws IOException {
    
        sujetpluscom.setVisible(false);
        userpluscom.setVisible(false);
        usrsuj.setVisible(false);
        usrvote.setVisible(false);
        vote.setVisible(true);
    }       
        
    
    @FXML
    private void handlesujaction(ActionEvent event) throws IOException {
    
        sujetpluscom.setVisible(true);
        userpluscom.setVisible(false);
        usrsuj.setVisible(false);
        usrvote.setVisible(false);
        vote.setVisible(false);
    }       
    
    @FXML
    private void handlevoteaction(ActionEvent event) throws IOException {
    
        sujetpluscom.setVisible(false);
        userpluscom.setVisible(false);
        usrsuj.setVisible(false);
        usrvote.setVisible(true);
        vote.setVisible(false);        
    }     
    
 @FXML
    private void handleusrcom(ActionEvent event) throws IOException {
        sujetpluscom.setVisible(false);
        userpluscom.setVisible(true);
        usrsuj.setVisible(false);
        usrvote.setVisible(false); 
        vote.setVisible(false);
    }        
    
    
 @FXML
    private void handleusrsuj(ActionEvent event) throws IOException {
        sujetpluscom.setVisible(false);
        userpluscom.setVisible(false);
        usrsuj.setVisible(true);
        usrvote.setVisible(false);       
        vote.setVisible(false);
    }           
   /*
    String yourQuery = "select * from some_table";
PreparedStatement statement = connection.prepareStatement(yourQuery); 
statement.setMaxRows(1); 
rs = statement.executeQuery();
   */ 

public ArrayList<Integer> getNbrvote(){
         ArrayList<Integer> a1 = new ArrayList<Integer>();
        try {
                
            Statement stmt = null;
            stmt = conn.createStatement();
            String sql2 = "SELECT idsujet,count(*) as nbr FROM vote where type=0 group by idsujet order by nbr desc ";
            ResultSet rs = stmt.executeQuery(sql2);
            while(rs.next()){
                a1.add(rs.getInt("nbr"));     
          
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a1;
        
    }     

public ArrayList<String> getNamevote(){
         ArrayList<String> a1 = new ArrayList<String>();
        try {
                
            Statement stmt = null;
            stmt = conn.createStatement();
            String sql2 = "SELECT s.titre,v.idsujet,count(*) as nbr FROM vote v"
                    + ",sujet s where v.idsujet=s.id and v.type=0 group by idsujet order by nbr desc";
            ResultSet rs = stmt.executeQuery(sql2);
            while(rs.next()){
                a1.add(rs.getString("titre"));     
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a1;
        
    }         
    
    
    
    
public ArrayList<Integer> getNbrusrvote(){
         ArrayList<Integer> a1 = new ArrayList<Integer>();
        try {
                
            Statement stmt = null;
            stmt = conn.createStatement();
            String sql2 = "SELECT iduser,count(*) as nbr FROM vote where type=0 group by iduser order by nbr desc ";
            ResultSet rs = stmt.executeQuery(sql2);
            while(rs.next()){
                a1.add(rs.getInt("nbr"));     
          
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a1;
        
    }    

public ArrayList<String> getNameusrvote(){
         ArrayList<String> a1 = new ArrayList<String>();
        try {
                
            Statement stmt = null;
            stmt = conn.createStatement();
            String sql2 = "SELECT u.username,v.iduser,count(*) as nbr FROM vote v"
                    + ",utilisateur u where v.iduser=u.cin and v.type=0 group by iduser order by nbr desc";
            ResultSet rs = stmt.executeQuery(sql2);
            while(rs.next()){
                a1.add(rs.getString("username"));     
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a1;
        
    }     
    
    
    
public ArrayList<Integer> getNbrusrsuj(){
         ArrayList<Integer> a1 = new ArrayList<Integer>();
        try {
                
            Statement stmt = null;
            stmt = conn.createStatement();
            String sql2 = "SELECT iduser,count(*) as nbr FROM sujet group by iduser order by nbr desc ";
            ResultSet rs = stmt.executeQuery(sql2);
            while(rs.next()){
                a1.add(rs.getInt("nbr"));     
          
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a1;
        
    }    

public ArrayList<String> getNameusrsuj(){
         ArrayList<String> a1 = new ArrayList<String>();
        try {
                
            Statement stmt = null;
            stmt = conn.createStatement();
            String sql2 = "SELECT u.username,s.iduser,count(*) as nbr FROM sujet s"
                    + ",utilisateur u where s.iduser=u.cin group by iduser order by nbr desc";
            ResultSet rs = stmt.executeQuery(sql2);
            while(rs.next()){
                a1.add(rs.getString("username"));     
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a1;
        
    }  
    
    
public ArrayList<Integer> getNbrusrcom(){
         ArrayList<Integer> a1 = new ArrayList<Integer>();
        try {
                
            Statement stmt = null;
            stmt = conn.createStatement();
            String sql2 = "SELECT iduser,count(*) as nbr FROM commentaire group by iduser order by nbr desc ";
            ResultSet rs = stmt.executeQuery(sql2);
            while(rs.next()){
                a1.add(rs.getInt("nbr"));     
          
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a1;
        
    }    

public ArrayList<String> getNameusrcom(){
         ArrayList<String> a1 = new ArrayList<String>();
        try {
                
            Statement stmt = null;
            stmt = conn.createStatement();
            String sql2 = "SELECT u.username,c.iduser,count(*) as nbr FROM commentaire c"
                    + ",utilisateur u where c.iduser=u.cin group by iduser order by nbr desc";
            ResultSet rs = stmt.executeQuery(sql2);
            while(rs.next()){
                a1.add(rs.getString("username"));     
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a1;
        
    }  

    public ArrayList<String> getName(){
         ArrayList<String> a1 = new ArrayList<String>();
        try {
                 
            Statement stmt = null;
            stmt = conn.createStatement();
            String sql2 = "SELECT * FROM sujet order by nbr_cmt desc";
            ResultSet rs = stmt.executeQuery(sql2);
            while(rs.next()){
                a1.add(rs.getString("titre"));      
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a1;
        
    }
    
    public ArrayList<Integer> getNbrcmt(){
         ArrayList<Integer> a1 = new ArrayList<Integer>();
        try {
                 
            Statement stmt = null;
            stmt = conn.createStatement();
            String sql2 = "SELECT * FROM sujet order by nbr_cmt desc";
            ResultSet rs = stmt.executeQuery(sql2);
            while(rs.next()){
                a1.add(rs.getInt("nbr_cmt"));      
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a1;
        
    }
    
       public void setdatachart1(){
xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
   ""))); 
xAxis.setLabel("Titre du sujet");           
yAxis.setLabel("Nombre de commentaires");

 
sujetpluscom.setTitle("Les Sujets les plus commentés"); 

XYChart.Series<String, Integer> series1 = new XYChart.Series<>(); 
series1.setName(getName().get(0)); 
series1.getData().add(new XYChart.Data<>("", getNbrcmt().get(0))); 


XYChart.Series<String, Integer> series2 = new XYChart.Series<>(); 
series2.setName(getName().get(1)); 
series2.getData().add(new XYChart.Data<>("", getNbrcmt().get(1))); 


XYChart.Series<String, Integer> series3 = new XYChart.Series<>(); 
series3.setName(getName().get(2)); 
series3.getData().add(new XYChart.Data<>("", getNbrcmt().get(2))); 

XYChart.Series<String, Integer> series4 = new XYChart.Series<>(); 
series4.setName(getName().get(3)); 
series4.getData().add(new XYChart.Data<>("", getNbrcmt().get(3)));
    
XYChart.Series<String, Integer> series5 = new XYChart.Series<>(); 
series5.setName(getName().get(4)); 
series5.getData().add(new XYChart.Data<>("", getNbrcmt().get(4)));

sujetpluscom.getData().addAll(series1,series2,series3,series4,series5);       
    } 

       public void setdatachart2(){
x1Axis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
   ""))); 
x1Axis.setLabel("Utilisateur");           
y1Axis.setLabel("Nombre de commentaires");

 
userpluscom.setTitle("Les utilisateurs les plus actifs"); 

XYChart.Series<String, Integer> series1 = new XYChart.Series<>(); 
series1.setName(getNameusrcom().get(0)); 
series1.getData().add(new XYChart.Data<>("", getNbrusrcom().get(0))); 


XYChart.Series<String, Integer> series2 = new XYChart.Series<>(); 
series2.setName(getNameusrcom().get(1)); 
series2.getData().add(new XYChart.Data<>("", getNbrusrcom().get(1))); 


XYChart.Series<String, Integer> series3 = new XYChart.Series<>(); 
series3.setName(getNameusrcom().get(2)); 
series3.getData().add(new XYChart.Data<>("", getNbrusrcom().get(2))); 
/*
XYChart.Series<String, Integer> series4 = new XYChart.Series<>(); 
series4.setName(getNameusrcom().get(3)); 
series4.getData().add(new XYChart.Data<>("", getNbrusrcom().get(3)));
    
XYChart.Series<String, Integer> series5 = new XYChart.Series<>(); 
series5.setName(getNameusrcom().get(4)); 
series5.getData().add(new XYChart.Data<>("", getNbrusrcom().get(4)));
*/
userpluscom.getData().addAll(series1,series2,series3);       
    }        
       
 public void setdatachart3(){
x2Axis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
   ""))); 
x2Axis.setLabel("Utilisateur");           
y2Axis.setLabel("Nombre de Sujets");

  
usrsuj.setTitle("Les utilisateurs les plus actifs"); 

XYChart.Series<String, Integer> series1 = new XYChart.Series<>(); 
series1.setName(getNameusrsuj().get(0)); 
series1.getData().add(new XYChart.Data<>("", getNbrusrsuj().get(0))); 


XYChart.Series<String, Integer> series2 = new XYChart.Series<>(); 
series2.setName(getNameusrsuj().get(1)); 
series2.getData().add(new XYChart.Data<>("", getNbrusrsuj().get(1))); 


XYChart.Series<String, Integer> series3 = new XYChart.Series<>(); 
series3.setName(getNameusrsuj().get(2)); 
series3.getData().add(new XYChart.Data<>("", getNbrusrsuj().get(2))); 
/*
XYChart.Series<String, Integer> series4 = new XYChart.Series<>(); 
series4.setName(getNameusrsuj().get(3)); 
series4.getData().add(new XYChart.Data<>("", getNbrusrsuj().get(3)));
    
XYChart.Series<String, Integer> series5 = new XYChart.Series<>(); 
series5.setName(getNameusrsuj().get(4)); 
series5.getData().add(new XYChart.Data<>("", getNbrusrsuj().get(4)));
*/
usrsuj.getData().addAll(series1,series2,series3);       
    }        

 public void setdatachart4(){
x3Axis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
   ""))); 
x3Axis.setLabel("Utilisateur");           
y3Axis.setLabel("Nombre de Votes");

    
usrvote.setTitle("Les utilisateurs les plus actifs"); 

XYChart.Series<String, Integer> series1 = new XYChart.Series<>(); 
series1.setName(getNameusrvote().get(0)); 
series1.getData().add(new XYChart.Data<>("", getNbrusrvote().get(0))); 


XYChart.Series<String, Integer> series2 = new XYChart.Series<>(); 
series2.setName(getNameusrvote().get(1)); 
series2.getData().add(new XYChart.Data<>("", getNbrusrvote().get(1))); 


XYChart.Series<String, Integer> series3 = new XYChart.Series<>(); 
series3.setName(getNameusrvote().get(2)); 
series3.getData().add(new XYChart.Data<>("", getNbrusrvote().get(2))); 
/*
XYChart.Series<String, Integer> series4 = new XYChart.Series<>(); 
series4.setName(getNameusrvote().get(3)); 
series4.getData().add(new XYChart.Data<>("", getNbrusrvote().get(3)));
    
XYChart.Series<String, Integer> series5 = new XYChart.Series<>(); 
series5.setName(getNameusrvote().get(4)); 
series5.getData().add(new XYChart.Data<>("", getNbrusrvote().get(4)));
*/
usrvote.getData().addAll(series1,series2,series3);       
    }    
     
 public void setdatachart5(){
x4Axis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
   ""))); 
x4Axis.setLabel("Titre du Sujet");           
y4Axis.setLabel("Nombre de votes");

    
vote.setTitle("Les sujets avec le plus de votes"); 

XYChart.Series<String, Integer> series1 = new XYChart.Series<>(); 
series1.setName(getNamevote().get(0)); 
series1.getData().add(new XYChart.Data<>("", getNbrvote().get(0))); 


XYChart.Series<String, Integer> series2 = new XYChart.Series<>(); 
series2.setName(getNamevote().get(1)); 
series2.getData().add(new XYChart.Data<>("", getNbrvote().get(1))); 

/*
XYChart.Series<String, Integer> series3 = new XYChart.Series<>(); 
series3.setName(getNamevote().get(2)); 
series3.getData().add(new XYChart.Data<>("", getNbrvote().get(2))); 

XYChart.Series<String, Integer> series4 = new XYChart.Series<>(); 
series4.setName(getNamevote().get(3)); 
series4.getData().add(new XYChart.Data<>("", getNbrvote().get(3)));
    
XYChart.Series<String, Integer> series5 = new XYChart.Series<>(); 
series5.setName(getNamevote().get(4)); 
series5.getData().add(new XYChart.Data<>("", getNbrvote().get(4)));
*/
vote.getData().addAll(series1,series2);       
    }        
 
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     setdatachart1();   
    setdatachart2();
    setdatachart3();  
    setdatachart4();  
    setdatachart5();
    }
       
          public StatsController (){
        conn = DataSource.getMyInstance().getMyConnexion();
        
    }  
       
    }    
    


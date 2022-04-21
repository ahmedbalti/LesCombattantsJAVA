/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;




/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class VoteResultsController implements Initializable {

    

    
    
    
    
private static int idusr;

    public static int getIdusr() {
        return idusr;
    }

    public static void setIdusr(int idusr) {
        VoteResultsController.idusr = idusr;
    }

private static String usr;

    public static String getUsr() {
        return usr;
    }

    public static void setUsr(String usr) {
        VoteResultsController.usr = usr;
    }
    
    
    private static int val;
    
   public static void setVal(int val) {
     VoteResultsController.val = val;
    }

    public static int getVal() {
        return val;
    }
    
@FXML private Button retour;
    
private static String reponse1;

 public static void setReponse1(String reponse1) {
       VoteResultsController.reponse1 = reponse1;
    }

    public static String getReponse1() {
        return reponse1;
    }
    
private static String reponse2;

 public static void setReponse2(String reponse2) {
       VoteResultsController.reponse2 = reponse2;
    }

    public static String getReponse2() {
        return reponse2;
    }
    
private static String reponse3;

 public static void setReponse3(String reponse3) {
       VoteResultsController.reponse3 = reponse3;
    }

    public static String getReponse3() {
        return reponse3;
    }
    
private static String reponse4;

 public static void setReponse4(String reponse4) {
       VoteResultsController.reponse4 = reponse4;
    }

    public static String getReponse4() {
        return reponse4;
    }
    
private static String reponse5;

 public static void setReponse5(String reponse5) {
       VoteResultsController.reponse5 = reponse5;
    }

    public static String getReponse5() {
        return reponse5;
    }    
    
    
private static float pourcentage1;

 public static void setPourcentage1(float pourcentage1) {
       VoteResultsController.pourcentage1 = pourcentage1;
    }

    public static float getPourcentage1() {
        return pourcentage1;
    }
 
private static float pourcentage2;

 public static void setPourcentage2(float pourcentage2) {
       VoteResultsController.pourcentage2 = pourcentage2;
    }

    public static float getPourcentage2() {
        return pourcentage2;
    }
    
private static float pourcentage3;

 public static void setPourcentage3(float pourcentage3) {
       VoteResultsController.pourcentage3 = pourcentage3;
    }

    public static float getPourcentage3() {
        return pourcentage3;
    }
    
    
private static float pourcentage4;

 public static void setPourcentage4(float pourcentage4) {
       VoteResultsController.pourcentage4 = pourcentage4;
    }

    public static float getPourcentage4() {
        return pourcentage4;
    }
    
    
private static float pourcentage5;

 public static void setPourcentage5(float pourcentage5) {
       VoteResultsController.pourcentage5 = pourcentage5;
    }

    public static float getPourcentage5() {
        return pourcentage5;
    }    
    
    
@FXML
    private void handleretourAction(ActionEvent event) throws IOException {
   // System.out.println(idusr);
   // System.out.println(val);
   // System.out.println(usr);
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheHebergement.fxml"));
        HebergementController controller = loader.getController();
        controller.setVal(val);
        controller.setIdusr(idusr);
        controller.setUsr(usr);
        
    
      Stage stage; 
       Parent root;
       stage=(Stage) retour.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("AfficheHebergement.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
       
    }    
    
    
    
    
    
    @FXML
    private PieChart chart;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

chart.setData(getChart());

}
    
private ObservableList<PieChart.Data> getChart() {
    ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
 
if(pourcentage1!=0.0){
    list.addAll(   
    new PieChart.Data(reponse1+" "+pourcentage1+"%",pourcentage1));
}
        
if(pourcentage2!=0.0){
    list.addAll(   
    new PieChart.Data(reponse2+" "+pourcentage2+"%",pourcentage2));
}

if(pourcentage3!=0.0){
    list.addAll(   
    new PieChart.Data(reponse3+" "+pourcentage3+"%",pourcentage3));
}

if(pourcentage4!=0.0){
    list.addAll(   
    new PieChart.Data(reponse4+" "+pourcentage4+"%",pourcentage4));
}

if(pourcentage5!=0.0){
    list.addAll(   
    new PieChart.Data(reponse5+" "+pourcentage5+"%",pourcentage5));
}
     
 /*
    list.addAll(  
    new PieChart.Data(reponse1+" "+pourcentage1+"%",pourcentage1),
    new PieChart.Data(reponse2+" "+pourcentage2+"%",pourcentage2), 
    new PieChart.Data(reponse3+" "+pourcentage3+"%",pourcentage3),
    new PieChart.Data(reponse4+" "+pourcentage4+"%",pourcentage4),
    new PieChart.Data(reponse5+" "+pourcentage5+"%",pourcentage5));  
    */ 
    return list;
}
}

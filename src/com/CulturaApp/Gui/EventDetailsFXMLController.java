/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.CulturaApp.Gui;

import com.CulturaApp.Model.Event;
import com.CulturaApp.Model.Lieu;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class EventDetailsFXMLController  implements Initializable {
@FXML
    private Label EventName;
@FXML
    private Label EventLieutf;
 @FXML
    private ImageView QrCode;
@FXML
    private WebView mapwebview;

    private WebEngine webengine;
@FXML
    private Label EventDescTf;
@FXML
    private Label capacitetf;
@FXML
    private Label Dtaetf;
 @FXML
    private Label jrRestant;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    

       // url = this.getClass().getResource("map/index.html");
        

    }

 void setTextField(Event event2, Lieu lieu1)  {

        webengine = mapwebview.getEngine();
        EventName.setText(event2.getNom());
        EventLieutf.setText(lieu1.getNom());
capacitetf.setText((String.valueOf(lieu1.getCapacite())));
Dtaetf.setText(event2.getDate());
        EventDescTf.setText(event2.getDescription());
webengine.load("https://www.google.com/maps/@"+lieu1.getLongitude()+","+lieu1.getLatitude());
       // webengine.load("www.google.com/maps/@"+lieu1.getLongitude()+","+lieu1.getLatitude());
       // webengine.load("<iframe src='https://www.google.com/maps?q="+lieu1.getLongitude()+","+lieu1.getLatitude()+"&hl=es;z=14&output=embed'></iframe>");
 System.out.println(lieu1.getLongitude());

LocalDate dateAfter = LocalDate.parse(event2.getDate());
long noOfDaysBetween = ChronoUnit.DAYS.between(LocalDate.now(), dateAfter);
	jrRestant.setText("il reste "+String.valueOf(noOfDaysBetween)+" jour(s)");
	//displaying the number of days
	System.out.println(noOfDaysBetween);

    } 

    public void start(String name,String LieuName) {
        
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = name+" a "+LieuName;
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
            Logger.getLogger(com.CulturaApp.Gui.EventDetailsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         // QrCode; = new ImageView();
         QrCode.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        
     /*   StackPane root = new StackPane();
        root.getChildren().add( QrCode); 
Scene scene = new Scene(root, 350, 350);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();  */


    }
 private void ButnOpenMap(ActionEvent event) throws IOException {

  /*    FXMLLoader fxmlLoader = new FXMLLoader (getClass ().getResource ("testMap.fxml"));
Parent rootl = (Parent) fxmlLoader.load();
Stage stage = new Stage();
stage.initStyle(StageStyle.DECORATED);
stage.setTitle("Second Window");
stage.setScene(new Scene(rootl));
stage.show();
Double latitude =  Double.parseDouble(mapwebview.getEngine().executeScript("lat").toString());
             
  Double          longitude =  Double.parseDouble(mapwebview.getEngine().executeScript("lon").toString());
             
             
                     System.out.println("Lat AjoutCom: " + latitude);
                System.out.println("LOn AjoutCom" + longitude);
        stage.setResizable(false);*/


    }

   

}

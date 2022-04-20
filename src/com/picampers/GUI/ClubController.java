/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import com.picampers.Services.ClubService;
import com.picampers.entities.Club;
import com.picampers.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;
import com.picampers.IServices.IClubService;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ClubController implements Initializable {
    
int nombre=0;
private PopOver popOver;
private Stage stage;
private static int idusr;
private int selectedcom;    
private static int idcom;
String comusername;    
String texte; 
private String imageFile;

@FXML private TextField valeur;
private TextField descfield;
private Button btn;
private Button annulmodif;
@FXML private Button supprimer;
@FXML private Label lblid;
@FXML private Label lbliduser;
@FXML private Label lblnbr;
@FXML private Label lbltitre;
@FXML private Label lbldesc;
@FXML private Label lbldate;  
@FXML private Button retour;
@FXML private Button modifier;
private Button suppcomm;
private Button modifcomm;
private Button modibtn;
@FXML private ImageView img;
@FXML private Label lbluser;
private TextField modifield;      
private ObservableList<ObservableList> data;
private Connection conn; 
private String question;
private String choix1;
private String choix2;
private String choix3;
private String choix4;
private String choix5;
private static int val;

    public static int getIdusr() {
        return idusr;
    }

    public static void setIdusr(int idusr) {
        ClubController.idusr = idusr;
    }

private static String usr;

    public static String getUsr() {
        return usr;
    }

    public static void setUsr(String usr) {
        ClubController.usr = usr;
    }
    
    public static int getIdcom() {
        return idcom;
    }

    public static void setIdcom(int idcom) {
        ClubController.idcom = idcom;
    }

   public static void setVal(int val) {
       ClubController.val = val;
    }

    public static int getVal() {
        return val;
    }
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl7;
    @FXML
    private Label lbl8;
    @FXML
    private Button modifier1;
    @FXML
    private Button supprimer1;
    @FXML
    private Label lbl81;
    @FXML
    private Label lblieu;
    @FXML
    private Label lbtype;
    @FXML
    private Label lbllieu;
    @FXML
    private Label lbltype;
        

@FXML
private void handleSupprimerAction(ActionEvent event) throws IOException {
//int val=Integer.parseInt(valeur.getText());            
 IClubService iClubService=new ClubService();  
 iClubService.delete(val);
 Notifications notificationsBuilder = Notifications.create()
                .title("Picampers")
                .text("Sujet Supprimé")
                .hideAfter(Duration.seconds(3))
                .position(Pos.BASELINE_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                @Override
               public void handle(ActionEvent event) {
                   System.out.println("Cliquer sur la notification");
               }
           });
        notificationsBuilder.darkStyle();
        notificationsBuilder.showError();
 
        Stage stage;
        Parent root;
        stage=(Stage) supprimer.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ListeClubs.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
 }


@FXML
    private void handleretourAction(ActionEvent event) throws IOException {
    
       Stage stage; 
       Parent root;
       stage=(Stage) retour.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("ListeClubs.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }    

    
    
    private void handlemodibtnAction(ActionEvent event) throws IOException {
   
        try {                   
            Statement stmt = null;
            stmt = conn.createStatement();
            String sql="UPDATE commentaire SET description='"+modifield.getText()+"' where id="+selectedcom+"";
            //  String sql4 = "UPDATE sujet SET titre= 'change' where id="+id+"";
            stmt.executeUpdate(sql);
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheClub.fxml"));
        ClubController controller = loader.getController();
        controller.setVal(val);
        
        Stage stage;
        Parent root;
        stage=(Stage) suppcomm.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AfficheClub.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        } catch (SQLException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }}

    
    
  public int getId(String nom){
      int idusr=0;
        try {
 Statement stmt = null;
 conn = DataSource.getMyInstance().getMyConnexion();

          stmt = conn.createStatement();
          String sql2 = "SELECT * FROM utilisateur where username='"+nom+"'";
          ResultSet rs = stmt.executeQuery(sql2);
          while(rs.next()){
               idusr = rs.getInt("id");  
          } } catch (SQLException ex) {    
          Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
      }
    return idusr;
}  

   
   @FXML
    private void handlemodifieraction(ActionEvent event) throws IOException {
        
       IClubService iClubService=new ClubService();  
       FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierClub.fxml"));
       ModifierClubController controller = loader.getController();
       controller.setVal(val);
       controller.setUsr(usr);
       
       Stage stage; 
       Parent root;
       stage=(Stage) modifier.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("ModifierClub.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }    

   @FXML
    private void handlesupprimeraction(ActionEvent event) throws IOException {
 IClubService iSujetService=new ClubService();  
 iSujetService.delete(val);
        
        Stage stage;
        Parent root;
        stage=(Stage) supprimer.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ListeClubs.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
        
        
    }    
    
    
     
    
@FXML
    private void handlemodifierAction(ActionEvent event) throws IOException {
       
       IClubService iClubService=new ClubService();  
       FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierClub.fxml"));
       ModifierClubController controller = loader.getController();
       controller.setVal(val);
       controller.setUsr(usr);
       Stage stage; 
       Parent root;
       stage=(Stage) modifier.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("ModifierClub.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }   
  
    
    
    private void handleannulmodifAction(ActionEvent event) throws IOException {
       
        descfield.setVisible(true);
        btn.setVisible(true);  
        modifcomm.setVisible(true);
        modifield.setVisible(false);
        modibtn.setVisible(false);
        annulmodif.setVisible(false);     
    }    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
  
  IClubService iClubService=new ClubService(); 
  lblid.setText(String.valueOf(val));
  lbliduser.setText(String.valueOf(iClubService.afficher(val).get(0).getId()));
  //lbltag1.setText(iSujetService.afficher(val).get(0).get());
  //lbltag2.setText(iSujetService.afficher(val).get(0).getTag2());
  //lbltag3.setText(iSujetService.afficher(val).get(0).getTag3());
  //lblnbr.setText(String.valueOf(iClubService.afficher(val).get(0).get()));
  lbltitre.setText(iClubService.afficher(val).get(0).getNom());
  lbllieu.setText(iClubService.afficher(val).get(0).getLieu());
  lbltype.setText(iClubService.afficher(val).get(0).getType());
  lbldesc.setText(iClubService.afficher(val).get(0).getDescription());  
  lbldate.setText(String.valueOf(iClubService.afficher(val).get(0).getType())); 
//Image image1 = new Image(getClass().getResourceAsStream(String.valueOf(iSujetService.afficher(val).get(0).getImage())));
//img.setImage(image1);
  
  imageFile = iClubService.afficher(val).get(0).getImage();
  if(imageFile.equals("")){
  Image imgch=new Image("file:/C:/wamp/www/images/noIMG.PNG");    
  img.setImage(imgch);    
      
  }
  else
  {
  //System.out.println(imageFile);
  Image image1=new Image(imageFile);
  img.setImage(image1);
  }
  
 

  idusr=getId(usr);      
    
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.picampers.GUI;


import com.picampers.entities.Hebergement;
import com.picampers.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.picampers.IServices.IHebergementService;
import com.picampers.Services.HebergementService;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
/**
 *
 * @author Arij
 */
public class ListeHebergementsController implements Initializable {
 
private static int val;

    @FXML
    private Button ajouter;
@FXML private TextField searchSujet;
@FXML private Label lblusr;
@FXML private Button btnaffiche1;
    @FXML
    private Button btnHouses;  
    @FXML
    private Button btnHotels;  
private static int idusr = 1;


    public static int getIdusr() {
        return idusr;
    }

    public static void setIdusr(int idusr) {
        ListeHebergementsController.idusr = idusr;
    }

private static String usr;

    public static String getUsr() {
        return usr;
    }

    public static void setUsr(String usr) {
        ListeHebergementsController.usr = usr;
    }
    
    @FXML
    private TableColumn<Hebergement, String> namee;
    @FXML
    private TableColumn<Hebergement, Integer> nbrstars;
    @FXML
    private TableColumn<Hebergement, Integer> nbrrooms;
    @FXML
    private TableColumn<Hebergement, String> addresse;
    @FXML private TableColumn<Hebergement, String> mdescription;
    @FXML private TableColumn<Hebergement, String> typee;
    @FXML private TableColumn<Hebergement, String> mdisponibilite;
    @FXML private TableColumn<Hebergement, String> tabid;
    @FXML private TableView<Hebergement> table;
    
    
//@FXML private TableView table;
//@FXML private TableColumn<Sujet, String> tabtitre;
//@FXML private TableColumn<Sujet, String> tabdescription;
   private ObservableList<ObservableList> data;
   private ObservableList<Hebergement> listehebergements;
   private Connection conn;      

    public static void setVal(int val) {
        ListeHebergementsController.val = val;
    }

    public static int getVal() {
        return val;
    }
    @FXML
    private Button decon;
    @FXML
    private Button ajouter1;
    @FXML
    private Button btnTout;
    @FXML
    private Button retour;

    private void loadHebergements(){
      IHebergementService iHebergementService =new HebergementService();
      List<Hebergement> liste= new ArrayList();
      listehebergements = FXCollections.observableArrayList(iHebergementService.listehebergements());  
      tabid.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("id"));      
      namee.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("name"));
      nbrstars.setCellValueFactory(new PropertyValueFactory<Hebergement,Integer>("nb_stars"));
      nbrrooms.setCellValueFactory(new PropertyValueFactory<Hebergement,Integer>("nb_rooms"));
      addresse.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("address"));
      mdescription.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("description"));
      typee.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("type"));
      mdisponibilite.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("disponibilite"));
 
      table.setItems(listehebergements);  
    }
     
     
     void loadHotels(){
      IHebergementService iHebergementService =new HebergementService();
      listehebergements = FXCollections.observableArrayList(iHebergementService.listehebergements());  
      tabid.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("id"));      
      namee.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("name"));
      nbrstars.setCellValueFactory(new PropertyValueFactory<Hebergement,Integer>("nb_stars"));
      nbrrooms.setCellValueFactory(new PropertyValueFactory<Hebergement,Integer>("nb_rooms"));
      addresse.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("address"));
      mdescription.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("description"));
      typee.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("type"));
      mdisponibilite.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("disponibilite"));
 
      table.setItems(listehebergements);  
    }
          
     void loadHouses(){
      IHebergementService iHebergementService =new HebergementService();
      listehebergements = FXCollections.observableArrayList(iHebergementService.listehebergements());  
      tabid.setCellValueFactory(new PropertyValueFactory<>("id"));      
      namee.setCellValueFactory(new PropertyValueFactory<>("name"));
      nbrstars.setCellValueFactory(new PropertyValueFactory<>("nb_stars"));
      nbrrooms.setCellValueFactory(new PropertyValueFactory<>("nb_rooms"));
      addresse.setCellValueFactory(new PropertyValueFactory<>("address"));
      mdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
      typee.setCellValueFactory(new PropertyValueFactory<>("type"));
      mdisponibilite.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
      table.setItems(listehebergements);  
    }
       
/*
public int getId(String nom){
        try {
Statement stmt = null;
          stmt = conn.createStatement();
          String sql = "SELECT cin FROM utilisateur where username='"+nom+"'";
          ResultSet rs = stmt.executeQuery(sql);
          
          idusr=rs.getInt("cin");

          } catch (SQLException ex) {    
          Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
      }
    return idusr;
}*/
    
  public int getId(String nom){
      int idusr=0;
        try {
          Statement stmt = null;
          conn = DataSource.getMyInstance().getMyConnexion();
          stmt = conn.createStatement();
          String sql2 = "SELECT * FROM utilisateur where username='"+nom+"'";
          ResultSet rs = stmt.executeQuery(sql2);
          while(rs.next()){
             idusr = rs.getInt("cin");
          } } catch (SQLException ex) {    
          Logger.getLogger(Hebergement.class.getName()).log(Level.SEVERE, null, ex);
      }
    return idusr;
}  
  
    @FXML
    private void handleBtnaffiche1Action(ActionEvent event) throws IOException {
      // int val=Integer.parseInt(value.getText());
       val=14;
       IHebergementService iHebergementService=new HebergementService();  
       FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheHebergement.fxml"));
       HebergementController controller = loader.getController();
       controller.setVal(val);
       Stage stage; 
       Parent root;
       stage=(Stage) btnaffiche1.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("AfficheHebergement.fxml"));   
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }    
    
    private void handlestataction(ActionEvent event) throws IOException {
       Stage stage; 
       Parent root;
       stage=(Stage) btnHouses.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("AfficheHebergement.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
 }    
    
    @FXML
    private void handleBtnHotels(ActionEvent event) throws IOException {
        loadHotels();
 }    
    
    @FXML
    private void handleBtnHouses(ActionEvent event) throws IOException {
        loadHouses();
 }
    
    @FXML
    private void handleBtnTout(ActionEvent event) throws IOException {
        loadHebergements();
 }    
    
    
         @FXML
    private void handledeconAction(ActionEvent event) throws IOException {
       Stage stage; 
       Parent root;
       stage=(Stage) btnHotels.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("Connexion.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }
    
       @FXML
       private void handleAjouterAction(ActionEvent event) throws IOException {
        
       IHebergementService iHebergementService =new HebergementService();  
       FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutHebergement.fxml"));
       AjoutHebergementController controller = loader.getController();     
       Stage stage; 
       Parent root;
       stage=(Stage) ajouter.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("AjoutHebergement.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
 }
       
    @FXML
       private void handleAjouterHouseAction(ActionEvent event) throws IOException {
        
       IHebergementService iHebergementService =new HebergementService();  
       FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutHouse.fxml"));
       AjoutHebergementController controller = loader.getController();     
       Stage stage; 
       Parent root;
       stage=(Stage) ajouter.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("AjoutHouse.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
 }
    
    
    
    private void selectionItemTableViewSujet(Hebergement hebergement) {
       if (hebergement != null){
        System.out.println(String.valueOf(hebergement.getId()));
    }else {
        System.out.println("a");
        }
    }
     
    @FXML
    public void searchSujet(){
            searchSujet.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                 if(searchSujet.textProperty().get().isEmpty()){
            table.setItems(listehebergements);
            return;
        }
                 ObservableList<Hebergement> tableitems=FXCollections.observableArrayList();
                ObservableList<TableColumn<Hebergement, ?>> cols = table.getColumns();
                 for(int i =0; i<listehebergements.size();i++){
                     for(int j =0; j<listehebergements.size();j++){
                         TableColumn col = cols.get(j);
                         String cellValue = col.getCellData(listehebergements.get(i)).toString();
                         cellValue = cellValue.toLowerCase();
                         if(cellValue.contains(searchSujet.textProperty().get().toLowerCase())){
                             
                             tableitems.add(listehebergements.get(i));
                             break;  
                         }}}
                 table.setItems(tableitems);
            }});
}
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
IHebergementService iHebergementService=new HebergementService(); 
//lbltitre1.setText(String.valueOf(iSujetService.afficher(14).get(0).getTitre()));
//lbltitre2.setText(String.valueOf(iSujetService.afficher(53).get(0).getTitre()));       
   
loadHebergements(); 
lblusr.setText(usr);

table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)-> {
    try {
        System.out.println(newValue.getId());
        val=newValue.getId();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheHebergement.fxml"));
        HebergementController controller = loader.getController();
        controller.setVal(val);
        controller.setUsr(usr);
        Stage stage;
        Parent root;
        stage=(Stage) ajouter.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AfficheHebergement.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(ListeHebergementsController.class.getName()).log(Level.SEVERE, null, ex);
    }});
}
    @FXML
    private void ajouterroom(ActionEvent event){
    Stage closestage=(Stage)((Node)event.getSource()).getScene().getWindow();
                    closestage.close();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/picampers/GUI/FXMLroom.fxml"));
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
    private void Retour(ActionEvent event) {
                                            try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/picampers/GUI/HomeBack.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

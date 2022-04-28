/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.GUI;

import com.picampers.entities.Club;
import com.picampers.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import com.picampers.IServices.IClubService;
import com.picampers.Services.ClubService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
/**
 * FXML Controller class
 *
 * @author hajer
 */
public class ListeClubsController implements Initializable {

    private static int val;

    @FXML
    private Button ajouter;
    @FXML
    private TextField searchSujet;
    @FXML
    private Label lblusr;
    @FXML
    private Button btnaffiche1;
    @FXML
    private Button btnBars;
    @FXML
    private Button btnRestos;
    private static int idusr = 1;

    public static int getIdusr() {
        return idusr;
    }

    public static void setIdusr(int idusr) {
        ListeClubsController.idusr = idusr;
    }

    private static String usr;

    public static String getUsr() {
        return usr;
    }

    public static void setUsr(String usr) {
        ListeClubsController.usr = usr;
    }

    @FXML
    private TableColumn<?, ?> tabtitre;
    @FXML
    private TableColumn<?, ?> tabuser;
    @FXML
    private TableColumn<?, ?> tabtag1;
    @FXML
    private TableColumn<?, ?> tabtag2;
    @FXML
    private TableColumn<?, ?> tabtag3;
    @FXML
    private TableColumn<?, ?> tabid;
    @FXML
    private TableView<Club> table;

//@FXML private TableView table;
//@FXML private TableColumn<Sujet, String> tabtitre;
//@FXML private TableColumn<Sujet, String> tabdescription;
    private ObservableList<ObservableList> data;
    private ObservableList<Club> listeclubs;
    private Connection conn;

    public static void setVal(int val) {
        ListeClubsController.val = val;
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
    private Button ajouter11;

    void loadClubs() {
        IClubService iClubService = new ClubService();
        listeclubs = FXCollections.observableArrayList(iClubService.listeclubs());
        tabid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabtitre.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tabuser.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        tabtag1.setCellValueFactory(new PropertyValueFactory<>("description"));
        tabtag2.setCellValueFactory(new PropertyValueFactory<>("type"));
        tabtag3.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));

        table.setItems(listeclubs);
    }

    void loadRestos() {
        IClubService iClubService = new ClubService();
        listeclubs = FXCollections.observableArrayList(iClubService.listerestos());
        tabid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabtitre.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tabuser.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        tabtag1.setCellValueFactory(new PropertyValueFactory<>("description"));
        tabtag2.setCellValueFactory(new PropertyValueFactory<>("type"));
        tabtag3.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
        table.setItems(listeclubs);
    }

    void loadBars() {
        IClubService iClubService = new ClubService();
        listeclubs = FXCollections.observableArrayList(iClubService.listebars());
        tabid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabtitre.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tabuser.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        tabtag1.setCellValueFactory(new PropertyValueFactory<>("description"));
        tabtag2.setCellValueFactory(new PropertyValueFactory<>("type"));
        tabtag3.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
        table.setItems(listeclubs);
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
    public int getId(String nom) {
        int idusr = 0;
        try {
            Statement stmt = null;
            conn = DataSource.getMyInstance().getMyConnexion();
            stmt = conn.createStatement();
            String sql2 = "SELECT * FROM utilisateur where username='" + nom + "'";
            ResultSet rs = stmt.executeQuery(sql2);
            while (rs.next()) {
                idusr = rs.getInt("cin");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idusr;
    }

    @FXML
    private void handleBtnaffiche1Action(ActionEvent event) throws IOException {
        // int val=Integer.parseInt(value.getText());
        val = 14;
        IClubService iClubService = new ClubService();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheClub.fxml"));
        ClubController controller = loader.getController();
        controller.setVal(val);
        Stage stage;
        Parent root;
        stage = (Stage) btnaffiche1.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AfficheClub.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void handlestataction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) btnBars.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Stats.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleBtnBars(ActionEvent event) throws IOException {
        loadBars();
    }

    @FXML
    private void handleBtnRestos(ActionEvent event) throws IOException {
        loadRestos();
    }

    @FXML
    private void handleBtnTout(ActionEvent event) throws IOException {
        loadClubs();
    }

    @FXML
    private void handledeconAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) btnRestos.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Connexion.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleAjouterAction(ActionEvent event) throws IOException {

        IClubService iClubService = new ClubService();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutClub.fxml"));
        AjoutClubController controller = loader.getController();
        Stage stage;
        Parent root;
        stage = (Stage) ajouter.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AjoutClub.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleAjouterBarAction(ActionEvent event) throws IOException {

        IClubService iClubService = new ClubService();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutBar.fxml"));
        AjoutClubController controller = loader.getController();
        Stage stage;
        Parent root;
        stage = (Stage) ajouter.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AjoutBar.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void selectionItemTableViewSujet(Club club) {
        if (club != null) {
            System.out.println(String.valueOf(club.getId()));
        } else {
            System.out.println("a");
        }
    }

    @FXML
    public void searchSujet() {
        searchSujet.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (searchSujet.textProperty().get().isEmpty()) {
                    table.setItems(listeclubs);
                    return;
                }
                ObservableList<Club> tableitems = FXCollections.observableArrayList();
                ObservableList<TableColumn<Club, ?>> cols = table.getColumns();
                for (int i = 0; i < listeclubs.size(); i++) {
                    for (int j = 0; j < listeclubs.size(); j++) {
                        TableColumn col = cols.get(j);
                        String cellValue = col.getCellData(listeclubs.get(i)).toString();
                        cellValue = cellValue.toLowerCase();
                        if (cellValue.contains(searchSujet.textProperty().get().toLowerCase())) {

                            tableitems.add(listeclubs.get(i));
                            break;
                        }
                    }
                }
                table.setItems(tableitems);
                
            }
        });
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IClubService iClubService = new ClubService();
//lbltitre1.setText(String.valueOf(iSujetService.afficher(14).get(0).getTitre()));
//lbltitre2.setText(String.valueOf(iSujetService.afficher(53).get(0).getTitre()));       

        loadClubs();
        lblusr.setText(usr);

        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                System.out.println(newValue.getId());
                val = newValue.getId();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheClub.fxml"));
                ClubController controller = loader.getController();
                controller.setVal(val);
                controller.setUsr(usr);
                Stage stage;
                Parent root;
                stage = (Stage) ajouter.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("AfficheClub.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ListeClubsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
 @FXML
    private void ajoutertable(ActionEvent event) {
Stage closestage=(Stage)((Node)event.getSource()).getScene().getWindow();
                    closestage.close();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/picampers/GUI/FXMLtable.fxml"));
                        Scene scene = new Scene(root);
                        Stage primaryStage=new Stage();
                        primaryStage.setTitle("Signup");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ListeClubsController.class.getName()).log(Level.SEVERE, null, ex);
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

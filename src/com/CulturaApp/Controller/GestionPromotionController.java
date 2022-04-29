/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.CulturaApp.Controller;

//import com.CulturaApp.Gui.Promotion.DetController;
import com.CulturaApp.Model.Promotion;
import com.CulturaApp.Model.PromotionAffecte;
import com.CulturaApp.Model.User;
import com.CulturaApp.Services.ServiceAffecte;
import com.CulturaApp.Services.ServiceNotification;
import com.CulturaApp.Services.ServicePromotion;
import com.CulturaApp.Services.ServiceUser;
import com.CulturaApp.utils.Mailer;
import java.io.IOException;
import javafx.fxml.FXML;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
//import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
//import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
//import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.mail.MessagingException;
//import javafx.scene.input.InputMethodEvent;

/**
 * FXML Controller class
 *
 * @author Ramez
 */
public class GestionPromotionController implements Initializable {



    @FXML
    private TextField TFTitle;

    @FXML
    private TextField TFScore;

    @FXML
    private TextArea TADescription;

    @FXML
    private RadioButton Cadeau;

    @FXML
    private RadioButton Remise;

    @FXML
    private Label rejectPr;

    @FXML
    private TableView<PromotionAffecte> affectTable;

    @FXML
    private TableColumn<PromotionAffecte, String> userCol;

    @FXML
    private TableColumn<PromotionAffecte, String> promoCol;

    @FXML
    private TableColumn<PromotionAffecte, String> dateCol;

    @FXML
    private TableColumn<PromotionAffecte, String> idCo;


    @FXML
    private TextField tfuser;

    @FXML
    private TextField tfpromo;

    @FXML
    private DatePicker dpDate;


    @FXML
    private Button btnAff;

    @FXML
    private TableView<Promotion> PromoTable;
    
    @FXML
    private TableColumn<Promotion, String> idCol;

    @FXML
    private TableColumn<Promotion, String> titleCol;

    @FXML
    private TableColumn<Promotion, String> typeCol;

    @FXML
    private TableColumn<Promotion, String> descCol;

    @FXML
    private TableColumn<Promotion, String> scoreCol;

    @FXML
    private Label warningAff;

    @FXML
    private ImageView deleteBtn;

    @FXML
    private ImageView updatepromoBtn;

    @FXML
    private ImageView deleteAff;

    @FXML
    private ImageView updateAff;

    @FXML
    private PieChart PCpromo;

    @FXML
    private PieChart PCaffect;

    @FXML
    private Button detailBTN;

    @FXML
    private TextField searchPromoBTN;


    ToggleGroup group = new ToggleGroup();
    int idPR, idAff;


    ObservableList<PromotionAffecte> list = FXCollections.observableArrayList();
    ObservableList<Promotion> PromotionsList  = FXCollections.observableArrayList();
    ServiceAffecte sa = new ServiceAffecte();
    ServicePromotion sp  = new ServicePromotion();
    ServiceUser su = new ServiceUser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO

        Cadeau.setSelected(true);
        Cadeau.setToggleGroup(group);
        Remise.setToggleGroup(group);
        loadDate();
        deleteBtn.setVisible(false);
        updatepromoBtn.setVisible(false);
        deleteAff.setVisible(false);
        updateAff.setVisible(false);
        rejectPr.setVisible(false);
        warningAff.setVisible(false);
        dpDate.setValue(LocalDate.now());
        appendTable();
        statistic();
        searchPromo();

       
    }    

    private void appendTable() {
        try {
            list.clear();
            list = sa.afficherAff();
            affectTable.setItems(list);
        } catch (Exception e) {
          Logger.getLogger(GestionPromotionController.class.getName()).log(Level.SEVERE, null, e);
        }

        userCol.setCellValueFactory(new PropertyValueFactory<>("user"));
        promoCol.setCellValueFactory(new PropertyValueFactory<>("promotion"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("delai"));
        idCo.setCellValueFactory(new PropertyValueFactory<>("id"));
    }
 
    void refreshAff(){
        try {
            list.clear();
            list = sa.afficherAff();
            affectTable.setItems(list);
        } catch (Exception e) {
          Logger.getLogger(GestionPromotionController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void ajouterAffectation(ActionEvent event) {

           if(tfuser.getText().isEmpty() || tfpromo.getText().isEmpty()){
               warningAff.setText("Veuillez Remplir les cases vides");
               warningAff.setVisible(true);
           }
           else if(dpDate.getValue().isBefore(LocalDate.now())){
              warningAff.setText("Date est invalide");
              warningAff.setVisible(true);
           }
           else{
               //try {
                  if(sa.ajouterAff(new PromotionAffecte(Integer.parseInt(tfuser.getText()) , Integer.parseInt(tfpromo.getText()), dpDate.getValue()))){
                     refreshAff();
                      try {

//                          if(schedule.isSelected()){
//                                  System.out.println("hey");
//                                  Calendar c = Calendar.getInstance();
//                                  c.set(2022, 4, 27, 17, 21);
//                                  c.set(Calendar.HOUR_OF_DAY, 17);
//                                  c.set(Calendar.MINUTE, 29);
//                                  c.set(Calendar.SECOND, 17);
//                                  Timer timer = new Timer();
//                                  timer.schedule(new TimerTask() {
//                                     @Override
//                                     public void run() {
//                                     //Call your method here
//                                         try {
//                                             List<User> user = su.rechcercheUser(Integer.parseInt(tfuser.getText()));
//                                             System.out.println("sending...");
//                                             Mailer.sendMail(user.get(0), dpDate.getValue());
//                                             System.out.println("sent!!");
//                                         } catch (MessagingException e) {
//                                             ServiceNotification sn = new ServiceNotification();
//                                             sn.emailNotSent();
//                                         }
//                                       
//                                     }
//                                     }, c.getTime(), 86400000);
//                          }
                              
                          //else{
                            List<User> user = su.rechcercheUser(Integer.parseInt(tfuser.getText()));
                            Mailer.sendMail(user.get(0), dpDate.getValue());
                            ServiceNotification sn = new ServiceNotification();
                            sn.emailSent();
                          //}
                          
                      } catch (MessagingException e) {
                          ServiceNotification sn = new ServiceNotification();
                          sn.emailNotSent();
                      }
                      
                     warningAff.setVisible(false);
                   }else{
                        warningAff.setText("Promotion ou user est invalide");
                        warningAff.setVisible(true);
                    }
                   
                   
               //} catch ( e) {
               //}
              
           }
           
    }


    @FXML
    void AjouterPromotion(ActionEvent event) {
          ServicePromotion sp = new ServicePromotion();

          String type ;

          if(Cadeau.isSelected()){
            type = Cadeau.getText();
          }
          else{
            type = Remise.getText();
          } 
             
          if(TADescription.getText().isEmpty()){
              TADescription.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
              new animatefx.animation.Shake(TADescription).play();
          }
          else{
             TADescription.setStyle(null);
          }
    
          if(TFScore.getText().isEmpty()){
              TFScore.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
              new animatefx.animation.Shake(TFScore).play();
          }
          else{
             TFScore.setStyle(null);
          }

          if(TFTitle.getText().isEmpty()){
              TFTitle.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
              new animatefx.animation.Shake(TFTitle).play();
          }
          else{
             TFTitle.setStyle(null);
          }
          
          if(TADescription.getText().isEmpty() || TFScore.getText().isEmpty() || TFTitle.getText().isEmpty()){
            rejectPr.setVisible(true);
          }
          else{
              ServiceNotification sn = new ServiceNotification();
            sp.ajouterPromotion(new Promotion( Integer.parseInt( TFScore.getText()), type, TADescription.getText(), TFTitle.getText()));
            rejectPr.setVisible(false);
            sn.promotionAdded();
          }
           refreshPromo();
    }

    @FXML
    void score(KeyEvent event) {      
        try {
            Integer.parseInt(event.getCharacter());
        } catch (NumberFormatException e) {
            event.consume();
        }
    }

    private void loadDate() {
        try {
            PromotionsList.clear();
            PromotionsList = sp.afficherPromotions();
            PromoTable.setItems(PromotionsList);
        } catch (Exception e) {
          Logger.getLogger(GestionPromotionController.class.getName()).log(Level.SEVERE, null, e);
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("scoreMin"));
     

    }

    @FXML
    void edit(MouseEvent event) {

       Promotion pr = PromoTable.getSelectionModel().getSelectedItem();
       idPR = pr.getId();
       TFTitle.setText(pr.getTitle());
       TADescription.setText(pr.getDescription());
       TFScore.setText(String.valueOf(pr.getScoreMin()));
       if(pr.getType() == "Cadeau"){
          Cadeau.setSelected(true);
       }
       Remise.setSelected(true);
       deleteBtn.setVisible(true);
       updatepromoBtn.setVisible(true);
    }

    @FXML
    void updatePr(MouseEvent event) {

        String type ;

          if(Cadeau.isSelected()){
            type = Cadeau.getText();
          }
          else{
            type = Remise.getText();
          } 

         sp.modifierPromotion(new Promotion(idPR, Integer.parseInt(TFScore.getText()), type, TADescription.getText(), TFTitle.getText()));
         refreshPromo();
    }

    @FXML
    void delete(MouseEvent event) {
           Promotion pr = PromoTable.getSelectionModel().getSelectedItem();
           sp.supprimerPromotion(pr.getId());
           refreshPromo();
    }


    void refreshPromo(){
        try {
            PromotionsList.clear();
            PromotionsList = sp.afficherPromotions();
            PromoTable.setItems(PromotionsList);
        } catch (Exception e) {
          Logger.getLogger(GestionPromotionController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void statistic(){
        PCaffect.setTitle("Les promotions Affecté");
        //PCaffect.setStyle(".chart-pie-label {-fx-fill: #ff4f0a;}");
        PCpromo.setTitle("Les types des promotions");
        PCpromo.setClockwise(true);
        PCaffect.setClockwise(true);
        PCpromo.setLabelLineLength(50);
        PCaffect.setLabelLineLength(50);

      //Setting the labels of the pie chart visible  
      PCpromo.setLabelsVisible(true);
      PCaffect.setLabelsVisible(true);
      
      PCpromo.setData(sp.statistcs());
      PCaffect.setData(sa.statistcs());
    }


    @FXML
    void refreshTablepromo(MouseEvent event) {
        refreshPromo();
    }


    @FXML
    void selected(MouseEvent event) {
        PromotionAffecte aff = affectTable.getSelectionModel().getSelectedItem();
        System.out.println("Selected");
        idAff = aff.getId();
        tfuser.setText( String.valueOf(aff.getUser()));
        tfpromo.setText(String.valueOf(aff.getPromotion()));
        dpDate.setValue(aff.getDelai());
        
        deleteAff.setVisible(true);
        updateAff.setVisible(true);
    }

    @FXML
    void deleteAff(MouseEvent event) {
        PromotionAffecte pa = affectTable.getSelectionModel().getSelectedItem();
        sa.supprimerAff(idAff);
        refreshAff();
    }

    @FXML
    void updateAffectation(MouseEvent event) {

        
        sa.modifierAff(new PromotionAffecte(idAff, Integer.parseInt(tfuser.getText()), Integer.parseInt(tfpromo.getText()), dpDate.getValue()));
        refreshAff();
    }

    @FXML
    void detailsPressed(ActionEvent event) {

       DetailsController nextScene = new DetailsController(); 
           //DetController d = new DetController();
           PromotionAffecte aff = affectTable.getSelectionModel().getSelectedItem();
       
              List<Promotion> listePromo = sp.recherchePromotion(aff.getPromotion());

      
     
      List<User> listeUser = su.rechcercheUser(aff.getUser());
      
      
      
//      nextScene.setUser(listeUser.get(0));
//      nextScene.setPromotion(listePromo.get(0));

        try {

           //FXMLLoader loader = new FXMLLoader(getClass().getResource("com/CulturaApp/Gui/Promotion/Details.fxml"));
            //Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/CulturaApp/Gui/Promotion/Details.fxml"));
            //Parent root = (Parent) loader.load();
           //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/CulturaApp/Gui/Promotion/Details.fxml"));
           //nextScene = loader.getController();
           Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Details");
            stage.show();

            nextScene.setUser(listeUser.get(0));
            nextScene.setPromotion(listePromo.get(0));
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
      

       

    }


      
//    void searchPromo(InputMethodEvent event)  {
//        try {
//            PromotionsList.clear();
//            List<Promotion> recherche = sp.recherchePromotionByTitle(event.getCommitted());
//            for(Promotion lista : recherche){
//
//                 PromotionsList.add(lista);
//            }
//            PromoTable.setItems(PromotionsList);
//        } catch (Exception e) {
//          Logger.getLogger(GestionPromotionController.class.getName()).log(Level.SEVERE, null, e);
//        }
//
//    }

     public void searchPromo(){
          PromotionsList = sp.afficherPromotions();
          FilteredList<Promotion> filtereData = new FilteredList<>(PromotionsList, b -> true);

          searchPromoBTN.textProperty().addListener(((observable, oldValue, newValue) -> {
                 filtereData.setPredicate(Promotion -> {
                      if(newValue.isEmpty() || newValue == null){
                        return true;
                      }

                      String searchKeyword = newValue.toLowerCase();
                      if(Promotion.getTitle().toLowerCase().indexOf(searchKeyword) > -1){
                          return true;
                      }else if(Promotion.getType().toLowerCase().indexOf(searchKeyword) > -1){
                          return true;
                      }
                      else{
                          return false;
                      }
                 });
          }));
          SortedList<Promotion> sortedList = new SortedList<>(filtereData);

          sortedList.comparatorProperty().bind(PromoTable.comparatorProperty());

          PromoTable.setItems(sortedList);
     }
 
     @FXML
    void setTime(ActionEvent event) {
      
    }
      
    
}

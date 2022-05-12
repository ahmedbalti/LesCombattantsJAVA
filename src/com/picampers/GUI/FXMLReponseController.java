/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.GUI;

import Model.Reclamation;
import Model.ReponseReclamation;
import static com.picampers.GUI.FXMLReclamationController.ne;
import static com.picampers.GUI.FXMLReclamationController.nf;
import static com.picampers.GUI.FXMLReclamationController.nh;
import com.picampers.Services.ServiceReclamation;
import com.picampers.Services.ServiceReponseReclamation;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLReponseController implements Initializable {

     public static int  nh = 0 ,nf=0 , ne = 0 ;  
            ObservableList<String> list = FXCollections.observableArrayList("Reclamation Service","Reclamation Hotel","Reclamation Evennement");
    @FXML
    private TextField tfReclamationId;
    @FXML
    private TextArea taDescription;
    @FXML
    private TableColumn<ReponseReclamation, String> colDescription;
    @FXML
    private TextField tfType;
    @FXML
    private Button btnADD;
    @FXML
    private Button btnSHOW;
    @FXML
    private Button btnUPDATE;
    @FXML
    private Button btnDELETE;
    @FXML
    private Label erreur_titre;
    @FXML
    private TableView<ReponseReclamation> tvReponses;
    @FXML
    private TableColumn<ReponseReclamation, Integer> colReclamationId;
    private boolean verificationUsernom ;
    @FXML
    private TextField tfId;
    @FXML
    private ComboBox<?> cb_combo;
    @FXML
    private TextField rech1;

        ServiceReponseReclamation sr = new ServiceReponseReclamation();
     List<ReponseReclamation> reponses = FXCollections.observableArrayList();

    ObservableList<ReponseReclamation> reponses1=FXCollections.observableArrayList();



    //TableView<ReponseReclamation> reclam = FXMLReclamationController.getTable();

ResultSet rs = null;
     Connection coonx = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficher();
        selectid();
    }    

//    @FXML
//    private void test_titre(KeyEvent event) {
//         int nbNonChar = 0;
//            for (int i = 1; i < tfReclamationId.getText().trim().length(); i++) {
//                char ch = tfReclamationId.getText().charAt(i);
//                if (!Character.isLetter(ch)) {
//                    nbNonChar++;
//                }
//            }
//
//            if (nbNonChar == 0 && tfReclamationId.getText().trim().length() >=3) {
//            
//            erreur_titre.setText("Nom valide");
//            
//            verificationUsernom = true;
//            } else {
//              
//              erreur_titre.setText("Il faut au moins 3 caracteres");
//              verificationUsernom = false;
//
//            }
//    }

    @FXML
    private void ajouter(ActionEvent event) {
//          if(verifUserChampsajouter() && verificationUsernom){
        ServiceReponseReclamation sr = new ServiceReponseReclamation();
        sr.ajouter(new ReponseReclamation(0, taDescription.getText(), Integer.parseInt(tfReclamationId.getText())));


    }//}
// public boolean verifUserChampsajouter() {
//        int verif = 0;
//        String style = " -fx-border-color: red;";
//
//        String styledefault = "-fx-border-color: green;";
//
//        tfReclamationId.setStyle(styledefault);
//        taDescription.setStyle(styledefault);
//        
//       
//
//       if (tfReclamationId.getText().equals("")) {
//            tfReclamationId.setStyle(style);
//            verif = 1;
//        }
//        if (taDescription.getText().equals("")) {
//            taDescription.setStyle(style);
//            verif = 1;
//        }
//       
//        if (verif == 0) {
//            return true;
//        }
//        
//        JOptionPane.showMessageDialog(null, "Remplir tous les champs!");
//        return false;
//    }
    @FXML
    private void afficher() {
          ServiceReponseReclamation sr = new ServiceReponseReclamation();
//colTitre.setText(sr.afficher().toString());
//colDescription.setText(sr.afficher().toString());
//colType.setText(sr.afficher().toString());
//colDate.setText(sr.afficher().toString());

             List<ReponseReclamation> list = sr.afficher();

             colReclamationId.setCellValueFactory(new PropertyValueFactory<ReponseReclamation, Integer>("reclamation_id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<ReponseReclamation,String>("description"));

tvReponses.setItems((ObservableList<ReponseReclamation>) list);
       
    }

    @FXML
    private void modifier(ActionEvent event) {
         ServiceReponseReclamation sr = new ServiceReponseReclamation();
         sr.modifier(new ReponseReclamation(Integer.parseInt(tfId.getText()), taDescription.getText(), Integer.parseInt(tfReclamationId.getText())));
    }

    @FXML
    private void supprimer(ActionEvent event) {
         ServiceReponseReclamation sr = new ServiceReponseReclamation();
         sr.supprimer(new ReponseReclamation(Integer.parseInt(tfId.getText()), taDescription.getText(), Integer.parseInt(tfReclamationId.getText()))); 
    }


  private void selectid() {
        try {

             coonx = com.picampers.utils.MyConnection.getInstance().getCnx();

              rs = coonx.createStatement().executeQuery("select id from reclamation");
            
            ObservableList List = FXCollections.observableArrayList();
            while (rs.next()) {
            
            List.add(rs.getString(1));
            }
            
           cb_combo.setItems(List);
            
      } catch (Exception ex) {
            System.out.println("error while inserting record. ");
        }
    }
//    @FXML
//    private void recherche(ActionEvent event) {
//         ServiceReponseReclamation ps = new ServiceReponseReclamation();
//    List<ReponseReclamation> Reservation = ps.Recherche (Integer.parseInt(rech.getText()));
//    tvReponses.getItems().clear();
//    tvReponses.getItems().removeAll(Reservation);
//    tvReponses.getItems().addAll(Reservation);
//    }

@FXML
    void calcul(ActionEvent event) throws IOException{
        ServiceReclamation ser= new ServiceReclamation();
        
        List<Reclamation> li =ser.afficher(); 
        int i = 0; 
        
        for ( i=0 ; i<li.size();i++){
        if (li.get(i).getType().equals("Reclamation Service"))
        
        {nh=nh+1;}  ;
        if (li.get(i).getType().equals("Reclamation Hotel")){nf=nf+1 ; } 
        if (li.get(i).getType().equals("Reclamation Evennement")){ne=ne+1 ; }  }
        
        FXMLLoader loader = new FXMLLoader();
        
        System.out.println(this.nf);
        loader.setLocation(getClass().getResource("RecStat.fxml"));
         Parent root = loader.load();
        taDescription.getScene().setRoot(root);
    }
@FXML
    void pdf(ActionEvent event) {
Printer printer = Printer.getDefaultPrinter();
     Node node = new Circle(400, 800, 800);
 PrinterJob job = PrinterJob.createPrinterJob(printer);
 if (job != null) {
    //FXMLReclamationController t = new FXMLReclamationController();
    boolean success = job.printPage(this.tvReponses);
    if (success) {
        job.endJob();
    }
 }
    }
   

@FXML
    void excel(ActionEvent event) {
  String filename = "C:\\Users\\DELL\\Desktop\\Reponse.xlsx";
       try   
        {  
             
            //creating an instance of HSSFWorkbook class  
            HSSFWorkbook workbook = new HSSFWorkbook();  
            //invoking creatSheet() method and passing the name of the sheet to be created   
            HSSFSheet sheet = workbook.createSheet("reponse");   
            //creating the 0th row using the createRow() method  
            HSSFRow rowhead = sheet.createRow((short)0);  
            //creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method  
            rowhead.createCell((short)0).setCellValue("ID");  
            rowhead.createCell(Short.parseShort("1")).setCellValue("Description");  
            rowhead.createCell(Short.parseShort("2")).setCellValue("Reclamation_id");  

             
            for(int i=0;i<sr.afficher().size();i++){
                HSSFRow row = sheet.createRow((short)i+1);  
                //inserting data in the first row  
                row.createCell(Short.parseShort("0")).setCellValue(sr.afficher().get(i).getId());  
                row.createCell(Short.parseShort("1")).setCellValue(sr.afficher().get(i).getDescription());  
                row.createCell(Short.parseShort("2")).setCellValue(sr.afficher().get(i).getReclamationId());  

            }
            //creating the 1st row  
            
            
            FileOutputStream fileOut = new FileOutputStream(filename);  
            workbook.write(fileOut);  
            //closing the Stream  
            fileOut.close();  
            //closing the workbook  
              
            //prints the message on the console  
            System.out.println("Excel file has been generated successfully.");  
        }   
        catch (Exception e)   
        {  
            e.printStackTrace();  
        }    
    }

public void refresh(List<ReponseReclamation> data){
        reponses1.clear();
        reponses1=FXCollections.observableArrayList(data);
        tvReponses.setItems(reponses1);
    }
public void recherche_avance(List<ReponseReclamation> visites){
        refresh(visites);
        ObservableList<ReponseReclamation> reponsess1=FXCollections.observableArrayList(reponses1);
        
        FilteredList<ReponseReclamation> filterddata=new FilteredList<>(reponsess1,b->true);
        rech1.textProperty().addListener(
                (observable,oldValue,newValue)->{
                    filterddata.setPredicate(
                        u->{
                            if(u.getDescription().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                                return true;
                            }
                            
                           
                          
                            else{
                                return false;
                            }
                            
                        }
                            
                    );
                    tvReponses.setItems(filterddata);
                }
                
        );
        
    }

@FXML
    void tri(ActionEvent event) {
  recherche_avance(sr.sortByDescription());
    }


}

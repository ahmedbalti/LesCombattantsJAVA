/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Reclamation;
import Services.ServiceReclamation;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javax.swing.JOptionPane;
//import org.controlsfx.control.Rating;
import utils.Mailapi;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
/*import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.util.Date;*/


/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLReclamationController implements Initializable {
// @FXML Rating rating ;
    @FXML Label msg;
    

   public static int  nh = 0 ,nf=0 , ne = 0 ;  
            ObservableList<String> list = FXCollections.observableArrayList("Reclamation Service","Reclamation Hotel","Reclamation Evennement");
    


    @FXML
    private TextField tfTitre;
    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfDate;
    @FXML
    private TableView<Reclamation> tvReclamations;
    @FXML
    private TableColumn<Reclamation, String> colSituation;
    @FXML
    private TableColumn<Reclamation, String> colDescription;
    @FXML
    private TableColumn<Reclamation, String> colType;
    @FXML
    private TableColumn<Reclamation, String> colDate;
    @FXML
    private Button btnADD;
    @FXML
    private Button btnSHOW;
    @FXML
    private Button btnUPDATE;
    @FXML
    private Button btnDELETE;
    @FXML
    private TextField tfId;
    private Spinner<?> spId;
    @FXML
    private TextField rech;
    private Label erreur_titre;
    private boolean verificationUsernom ;

          int  index= -1; 
     List<Reclamation> reclamations = FXCollections.observableArrayList();
    ObservableList<Reclamation> reclamations1=FXCollections.observableArrayList();

        ServiceReclamation sr = new ServiceReclamation();


//new ArrayList();
 /*public static final String ACCOUNT_SID = "AC8b263dd209c062a0d924254c7377c4a5";
        public static final String AUTH_TOKEN = "d8d8152647bab4c9de43b4ac37dfe4d1";*/



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 /*rating.ratingProperty().addListener(new ChangeListener<Number>() {
            
       
            @Override
            public void changed(ObservableValue<? extends Number> url, Number t, Number t1){
            msg.setText("Rating : "+t1.toString());
            
        }

        
        });*/
        // TODO
//tvReclamations.setItems(reclamations);
//  tablereclamation.refresh();
        afficher();
    }    

 @FXML
    private void filldata(MouseEvent event) {
        if(tvReclamations.getSelectionModel().getSelectedItem()!=null){
            Reclamation t=tvReclamations.getSelectionModel().getSelectedItem();
            taDescription.setText(t.getDescription());
            tfType.setText(t.getType());
            tfDate.setText(t.getDate());
            tfTitre.setText(t.getSituation());

            
        }
    }

    @FXML
    private void ajouter(ActionEvent event) {
        //
        if(verifUserChampsajouter() && verificationUsernom){
        ServiceReclamation sr = new ServiceReclamation();
        sr.ajouter(new Reclamation(0, tfTitre.getText(), taDescription.getText(), tfDate.getText(), tfType.getText()));
       // Mailapi.send("bazdehmustapha3@gmail.com", "mailapi", "bazdeh.mustapha@esprit.tn" , "nouvelle reclamation", "votre réclamation a ete envoyé");

sendMail();
  TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Ajout reclam");
            tray.setMessage("Vous avez bien ajouter une reclamation");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
    }
else {
  Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout");
           // alert.setContentText(verifUserChampsajouter());
            alert.showAndWait();
}
    }

private  void sendMail(){
//from ="adresse";

//host ="localhost";
//sub="";
//content="xxxxxxxxxxx ";   
    Properties p =new Properties();
p.put("mail.smtp.auth","true");
p.put("mail.smtp.starttls.enable","true");
p.put("mail.smtp.host","smtp.gmail.com");
p.put("mail.smtp.port","587");
 Session s =Session.getInstance(p,new  Authenticator() {
@Override
protected PasswordAuthentication getPasswordAuthentication(){
return new PasswordAuthentication("culturaapplication@gmail.com","Cultura12345");
}
});
try{
    MimeMessage m =new MimeMessage(s);
m.setFrom("ahmed.balti@esprit.tn");
m.addRecipient(Message.RecipientType.TO, new InternetAddress("culturaapplication@gmail.com"));
m.setSubject("Reclamation Ajoutée");
m.setText("Une réclamation a été ajoutée avec succes , Veuillez la consulter le plutôt possible !");
Transport.send(m);
System.out.println("send");
}catch(Exception ex){
    ex.printStackTrace();
}
}
    @FXML
    private void afficher() {
       ServiceReclamation sr = new ServiceReclamation();
//colTitre.setText(sr.afficher().toString());
//colDescription.setText(sr.afficher().toString());
//colType.setText(sr.afficher().toString());
//colDate.setText(sr.afficher().toString());

             List<Reclamation> list = sr.afficher();
                   //  colTitre.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("id"));

        colSituation.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("situation"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("description"));
        colType.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("type"));
        colDate.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("date"));
tvReclamations.setItems((ObservableList<Reclamation>) list);
        //System.out.println(list);
       
    }

    @FXML
    private void modifier(ActionEvent event) {
        
        ServiceReclamation sr = new ServiceReclamation();
         sr.modifier(new Reclamation(Integer.parseInt(tfId.getText()), tfTitre.getText(), taDescription.getText(), tfDate.getText(), tfType.getText()));

 TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle("Modifier reclam");
                tray.setMessage("Vous avez bien modifier une reclamation");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(1000));
                recherche_avance(sr.afficher());
    }

    @FXML
    private void supprimer(ActionEvent event) {
        ServiceReclamation sr = new ServiceReclamation();
         sr.supprimer(new Reclamation(Integer.parseInt(tfId.getText()), tfTitre.getText(), taDescription.getText(), tfDate.getText(), tfType.getText()));

TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("supprimer reclam");
            tray.setMessage("Vous avez bien supprimer une reclamation");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(1000));
            recherche_avance(sr.afficher()); 
    }

    @FXML
    private void recherche(ActionEvent event) {
    
    ServiceReclamation ps = new ServiceReclamation();
    List<Reclamation> Reservation = ps.Recherche (rech.getText());
    tvReclamations.getItems().clear();
    tvReclamations.getItems().removeAll(Reservation);
    tvReclamations.getItems().addAll(Reservation);
    

    }
  public void refresh(List<Reclamation> data){
        reclamations1.clear();
        reclamations1=FXCollections.observableArrayList(data);
        tvReclamations.setItems(reclamations1);
    }

 public void recherche_avance(List<Reclamation> visites){
        refresh(visites);
        ObservableList<Reclamation> reclamationss1=FXCollections.observableArrayList(reclamations1);
        
        FilteredList<Reclamation> filterddata=new FilteredList<>(reclamationss1,b->true);
        rech.textProperty().addListener(
                (observable,oldValue,newValue)->{
                    filterddata.setPredicate(
                        u->{
                            if(u.getDescription().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                                return true;
                            }
                            else if(u.getDate().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                                return true;
                            }
                            else if(u.getSituation().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                                return true;
                            }
                            else if(u.getType().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                                return true;
                            }
                          
                            else{
                                return false;
                            }
                            
                        }
                            
                    );
                    tvReclamations.setItems(filterddata);
                }
                
        );
        
    }


    public boolean verifUserChampsajouter() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

        tfTitre.setStyle(styledefault);
        taDescription.setStyle(styledefault);
        tfType.setStyle(styledefault);
        tfDate.setStyle(styledefault);
        if (tfDate.getText().equals("")) {
            tfDate.setStyle(style);
            verif = 1;
        }

       if (tfTitre.getText().equals("")) {
            tfTitre.setStyle(style);
            verif = 1;
        }
        if (taDescription.getText().equals("")) {
            taDescription.setStyle(style);
            verif = 1;
        }
        if (tfType.getText().equals("")) {
            tfType.setStyle(style);
            verif = 1;
        }
        if (verif == 0) {
            return true;
        }
        
        JOptionPane.showMessageDialog(null, "Remplir tous les champs!");
        return false;
    }

    @FXML
    private void test_titre(KeyEvent event) {
    int nbNonChar = 0;
            for (int i = 1; i < tfTitre.getText().trim().length(); i++) {
                char ch = tfTitre.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && tfTitre.getText().trim().length() >=3) {
            
      //      erreur_titre.setText("Nom valide");
            
            verificationUsernom = true;
            } else {
              
             // erreur_titre.setText("Il faut au moins 3 caracteres");
              verificationUsernom = false;

            }


    }

@FXML
    private void Selected(MouseEvent event) {
         index=tvReclamations.getSelectionModel().getSelectedIndex();
        if (index<= -1)
        {return; } 
        tfType.setText(colType.getCellData(index).toString());
                tfDate.setText(colDate.getCellData(index).toString());
                tfTitre.setText(colSituation.getCellData(index).toString());
                taDescription.setText(colDescription.getCellData(index).toString());
    }


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
        
        loader.setLocation(getClass().getResource("RecStat.fxml"));
         Parent root = loader.load();
        tfType.getScene().setRoot(root);



    }
@FXML
    void pdf(ActionEvent event) {
Printer printer = Printer.getDefaultPrinter();
     Node node = new Circle(400, 800, 800);
 PrinterJob job = PrinterJob.createPrinterJob(printer);
 if (job != null) {
    boolean success = job.printPage(tvReclamations);
    if (success) {
        job.endJob();
    }
 }
    }

@FXML
    void excel(ActionEvent event) {
  String filename = "C:\\Users\\DELL\\Desktop\\Reclamation1.xlsx";
       try   
        {  
             
            //creating an instance of HSSFWorkbook class  
            HSSFWorkbook workbook = new HSSFWorkbook();  
            //invoking creatSheet() method and passing the name of the sheet to be created   
            HSSFSheet sheet = workbook.createSheet("reclamation");   
            //creating the 0th row using the createRow() method  
            HSSFRow rowhead = sheet.createRow((short)0);  
            //creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method  
            rowhead.createCell((short)0).setCellValue("ID");  
            rowhead.createCell(Short.parseShort("1")).setCellValue("Date");  
            rowhead.createCell(Short.parseShort("2")).setCellValue("Description");  
            rowhead.createCell(Short.parseShort("3")).setCellValue("Situation");  
            rowhead.createCell(Short.parseShort("4")).setCellValue("Type");  
            for(int i=0;i<sr.afficher().size();i++){
                HSSFRow row = sheet.createRow((short)i+1);  
                //inserting data in the first row  
                row.createCell(Short.parseShort("0")).setCellValue(sr.afficher().get(i).getId());  
                row.createCell(Short.parseShort("1")).setCellValue(sr.afficher().get(i).getDate());  
                row.createCell(Short.parseShort("2")).setCellValue(sr.afficher().get(i).getDescription());  
                row.createCell(Short.parseShort("3")).setCellValue(sr.afficher().get(i).getSituation());  
                row.createCell(Short.parseShort("4")).setCellValue(sr.afficher().get(i).getType());  
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

  @FXML
    void tri(ActionEvent event) {
  recherche_avance(sr.sortByDescription());
    }



    }

    


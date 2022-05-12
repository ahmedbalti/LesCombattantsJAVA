/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.picampers.Gui;

import com.picampers.entities.Event;
import com.picampers.entities.Lieu;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import com.picampers.Services.ServiceEvent;
import com.picampers.Services.ServiceLieu;
import com.picampers.utils.MyConnection;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TableColumn;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.stage.StageStyle;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class FXMLEventController implements Initializable {
private Connection cnx = MyConnection.getInstance().getCnx();
@FXML
    private TextField Searchevent;
    @FXML
    private Button BTNAjouter;

    @FXML
    private TextField TFAdresse;

    @FXML
    private TextField TFCapacite;

    @FXML
    private TextField TFLatitude;
@FXML
    private ImageView logo;
    @FXML
    
    private TextField TFNom;

    @FXML
    private TextField TFVille;
@FXML
    private TextField TFLongitude;
  @FXML
    private TableView<Lieu> LieuxTable;
@FXML
    private TableColumn<Lieu, String> AdresseCol;
@FXML
    private TableColumn<Lieu, String> EditCol;
    @FXML
    private ImageView deletebtn1;

    @FXML
    private TableColumn<Lieu, String> CapaciteCol;
@FXML
    private TableColumn<?, ?> IdCol;
    @FXML
    private TableColumn<Lieu, String> LatitudeCol;

    @FXML
    private TableColumn<Lieu, String> LongitudeCol;

    @FXML
    private TableColumn<Lieu, String> NomCol;
    @FXML
    private TableColumn<Lieu, String> VilleCol;

@FXML
    private TableColumn<Event, String> NomEventCol;
@FXML
    private TableColumn<Event, String> TypeEventCol;
@FXML
    private TableColumn<Event, String> DescriptionEventCol;
    @FXML
    private TableColumn<Event, String> EmailEventCol;

    @FXML
    private TableColumn<Event, String> IDEventCol;
    @FXML
    private TableColumn<Event, String> DateEventCol;
  @FXML
    private TableColumn<Event, String> LieuEventCol;

    @FXML
    private TableView<Event> EventTable;

@FXML
    private Button modifierEventbtn;
@FXML
    private Button 
Detailseventbtn ;
    @FXML
    private DatePicker tfdateevent;

    @FXML
    private TextArea tfdescriptionevent;

    @FXML
    private TextField tfemailevent;

    @FXML
    private TextField tfidevent;

    @FXML
    private TextField tfidlieu;

    @FXML
    private TextField tflieuidevent;

    @FXML
    private TextField tfnomevent;

    @FXML
    private ComboBox<String> tftypeevent;
@FXML
    private ComboBox<String> idlieu2;
   @FXML
    private Button btndelLieu;
@FXML
    private Button exportExcel;
@FXML
    private Label idevent;
@FXML
    private Button Dark;
    @FXML
    private Button btnupLieu;
@FXML
    private PieChart PieChartEvent;
   @FXML
    private Pagination pagination;
   
    private final static int rowsPerPage = 10;  
private final static int dataSize = 100;
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Lieu lieu = null ;
    Event event=null;
    Event event2=null;
    ObservableList<Lieu>  LieuList = FXCollections.observableArrayList();
    ObservableList<Event>  EventList = FXCollections.observableArrayList();
int d;
/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
 loadDate();
loadDateEvent();
ObservableList<String> list = FXCollections.observableArrayList("Soirée","Conference","Arabesque");
         tftypeevent.setItems(list);

EventTable.setRowFactory( tv -> {
    TableRow<Event> row = new TableRow<>();
    row.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
            Event rowData = row.getItem();
            System.out.println(rowData.getNom());
event2 = EventTable.getSelectionModel().getSelectedItem();
                         

          

          
Stage stage = new Stage();
Stage stage2 = new Stage();

          FXMLLoader loader = new FXMLLoader(getClass().getResource("EventDetailsFXML.fxml"));

          ServiceLieu sl = new ServiceLieu();
          Parent root=null;
            try {
                root = (Parent)loader.load();
            } catch (IOException ex) {
                Logger.getLogger(FXMLEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
          EventDetailsFXMLController controller = loader.getController();
Lieu lieu1=sl.affichlieusingle(event2.getLieuid());
          controller.setTextField(event2,lieu1);
          stage.setScene(new Scene(root));
          stage.setTitle("Gestion Promotion");
          stage.show();
controller.start(event2.getNom(),lieu1.getNom());
        }
    });
    return row ;
});
//statistic();

ObservableList<PieChart.Data> PieChartData=
         FXCollections.observableArrayList(
         new PieChart.Data("Soirée",10),
            new PieChart.Data("Conference",5));
PieChartEvent.setData(PieChartData);
PieChartEvent.setTitle("type evenement");

}

  @FXML
    void Dark(ActionEvent event)throws IOException {


    }


 void statistic(){
ServiceEvent se = new ServiceEvent();
        PieChartEvent.setTitle("type evenement ");
       // PCpromo.setTitle("Les types des promotions");
//        PCpromo.setClockwise(true);
//        PCaffect.setClockwise(true);
//        PCpromo.setLabelLineLength(50);
//        PCaffect.setLabelLineLength(50);

      //Setting the labels of the pie chart visible
//      PCpromo.setLabelsVisible(true);
//      PCaffect.setLabelsVisible(true);

//       PieChartEvent.setData(se.statistcs());
      //PCaffect.setData(sa.statistcs());
    }
@FXML
void detailsEvent(ActionEvent event) throws IOException
{
event2 = EventTable.getSelectionModel().getSelectedItem();
                         

          

          
Stage stage = new Stage();
Stage stage2 = new Stage();

          FXMLLoader loader = new FXMLLoader(getClass().getResource("EventDetailsFXML.fxml"));

          ServiceLieu sl = new ServiceLieu();
          Parent root = (Parent)loader.load();
          EventDetailsFXMLController controller = loader.getController();
Lieu lieu1=sl.affichlieusingle(event2.getLieuid());
          controller.setTextField(event2,lieu1);
          stage.setScene(new Scene(root));
          stage.setTitle("Details Evenement");
          stage.show();
controller.start(event2.getNom(),lieu1.getNom());
}

@FXML
void detailsEvent2() throws IOException
{
event2 = EventTable.getSelectionModel().getSelectedItem();
                         

          

          
Stage stage = new Stage();
Stage stage2 = new Stage();

          FXMLLoader loader = new FXMLLoader(getClass().getResource("EventDetailsFXML.fxml"));

          ServiceLieu sl = new ServiceLieu();
          Parent root = (Parent)loader.load();
          EventDetailsFXMLController controller = loader.getController();
Lieu lieu1=sl.affichlieusingle(event2.getLieuid());
          controller.setTextField(event2,lieu1);
          stage.setScene(new Scene(root));
          stage.setTitle("Gestion Promotion");
          stage.show();
controller.start(event2.getNom(),lieu1.getNom());
}
@FXML
    void AjouterEvent2(ActionEvent event) {
ServiceEvent se = new ServiceEvent();
//Event p = new Event(7, "hadhra", "party", "a9wa hadhra fik ya tounes", "yassinetilouche@gmail.com",null);
//se.ajouter(new Event(16, "hadhra", "party", "a9wa hadhra fik ya tounes", "yassinetilouche@gmail.com"));
if(validateEmaill()||validateNumber())
{
se.ajouter(new Event(Integer.parseInt(tflieuidevent.getText()), tfnomevent.getText(),tftypeevent.getValue(),tfdescriptionevent.getText(),tfemailevent.getText(),tfdateevent.getValue().toString()));
 refreshTableEvent();}   
 }
  @FXML
    void modifierEvent(ActionEvent event) {
ServiceEvent se = new ServiceEvent();
if(validateEmaill()||validateNumber()){
se.modifier(new Event(Integer.parseInt(idevent.getText()),Integer.parseInt(tflieuidevent.getText()), tfnomevent.getText(),tftypeevent.getValue(),tfdescriptionevent.getText(),tfemailevent.getText(),tfdateevent.getValue().toString()));
 refreshTableEvent();  
  }
}
    @FXML
    void ajouterEvent(ActionEvent event) {

        ServiceLieu se = new ServiceLieu();
if(validateFieldsLieu() ){
        se.ajouter(new Lieu(TFNom.getText(),TFVille.getText() , TFAdresse.getText(),Integer.parseInt(TFCapacite.getText()), Double.parseDouble(TFLongitude.getText()),Double.parseDouble(TFLatitude.getText())  ));
 }refreshTable();
      //  se.ajouter(new Lieu(TFNom.getText(),TFVille.getText() , TFAdresse.getText(),Integer.parseInt(TFCapacite.getText()), Double.parseDouble(TFLongitutde.getText()) , Double.parseDouble(TFLatitude.getText()) ));

    }

    @FXML
    void capacite(KeyEvent event) {
        try {
            Integer.parseInt(event.getCharacter());
        } catch (NumberFormatException e) {
            event.consume();
        }
    }
 @FXML
    private void refreshTableEvent() {
       
ServiceEvent at= new ServiceEvent();
       
        at.afficher().stream().forEach((p) -> {EventList.add(p);});
        
   /* NomEventCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        TypeEventCol.setCellValueFactory(new PropertyValueFactory<>("type"));
         DescriptionEventCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        //DateEventCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        LieuEventCol.setCellValueFactory(new PropertyValueFactory<>("lieuid"));

        EmailEventCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        IDEventCol.setCellValueFactory(new PropertyValueFactory<>("id"));*/
       EventTable.setItems(EventList);    
            FilteredList<Event> filteredData = new FilteredList<>(EventList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		Searchevent.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(event -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (event.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (event.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(event.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Event> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(EventTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		EventTable.setItems(sortedData);
            
            
        
    }
 @FXML
    private void refreshTable() {
       
ServiceLieu ag= new ServiceLieu();
       
        ag.afficher().stream().forEach((p) -> {LieuList.add(p);});
        
     /*NomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
     VilleCol.setCellValueFactory(new PropertyValueFactory<>("ville"));
     AdresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
     CapaciteCol.setCellValueFactory(new PropertyValueFactory<>("Capacite"));
     // idLogo.setCellValueFactory(new PropertyValueFactory<>("logoAgence"));*/
       LieuxTable.setItems(LieuList);    
             // Wrap the ObservableList in a FilteredList (initially display all data).
        
            
            
        
    }

 
 @FXML
    void DeleteEvent(MouseEvent event) {
ServiceEvent ag= new ServiceEvent();
ag.supprimer(Integer.parseInt(idevent.getText()) );
    refreshTableEvent();
    }


private void loadDate() {
        
         Connection cnx = MyConnection.getInstance().getCnx();
      refreshTable();
        
        IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomCol.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        VilleCol.setCellValueFactory(new PropertyValueFactory<>("ville"));
        AdresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        CapaciteCol.setCellValueFactory(new PropertyValueFactory<>("capacite"));

        LongitudeCol.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        LatitudeCol.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        
        
     
    }
private void loadDateEvent() {
        
        Connection cnx = MyConnection.getInstance().getCnx(); 
      refreshTableEvent();
        
       NomEventCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        TypeEventCol.setCellValueFactory(new PropertyValueFactory<>("type"));
         DescriptionEventCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        DateEventCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        LieuEventCol.setCellValueFactory(new PropertyValueFactory<>("lieuid"));

        EmailEventCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        IDEventCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        
     
    }
@FXML
    void DeleteLieu(ActionEvent event) {
ServiceLieu ag= new ServiceLieu();
ag.supprimer(Integer.parseInt(tfidlieu.getText()) );
    refreshTable();
    }

    @FXML
    void UpdateLieu(ActionEvent event) {
ServiceLieu ag= new ServiceLieu();

   int id= Integer.parseInt(tfidlieu.getText());
//int id= lieu.getId();
System.out.println(id);
String Nom =TFNom.getText();
String adresse=TFAdresse.getText(); 
String Ville=TFVille.getText();
int capacite=Integer.parseInt(TFCapacite.getText());
//Double longitude=Double.parseDouble(TFLongitutde.getText());
//System.out.println(longitude);

        ag.modifier(new Lieu(id,Nom,Ville,adresse,capacite,36.5874,17.1254));
       // ag.modifier(new Lieu(Integer.parseInt(tfidlieu.getText()),TFNom.getText(),TFVille.getText() , TFAdresse.getText(),Integer.parseInt(TFCapacite.getText()), Double.parseDouble(TFLongitutde.getText()) , Double.parseDouble(TFLatitude.getText()))) ;
   refreshTable();
 }

  @FXML
    void handleMouseAction(MouseEvent event) {
Lieu lieu= LieuxTable.getSelectionModel().getSelectedItem();
tfidlieu.setText(""+lieu.getId());
TFNom.setText(lieu.getNom());
TFVille.setText(lieu.getVille());
TFAdresse.setText(lieu.getAdresse());
TFCapacite.setText(""+lieu.getCapacite());
TFLongitude.setText(""+Double.toString(lieu.getLongitude()));
TFLatitude.setText(""+lieu.getLatitude());
   
}
   

  
@FXML   
    void handleMouseEventAction(MouseEvent event) {
Event event2= EventTable.getSelectionModel().getSelectedItem();
idevent.setText(""+event2.getId());
tfnomevent.setText(""+event2.getNom());
tftypeevent.getSelectionModel().select(event2.getType());
tfdescriptionevent.setText(event2.getDescription());
tfemailevent.setText(event2.getEmail());
tflieuidevent.setText(""+event2.getLieuid());

}

private boolean validateEmaill(){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(tfemailevent.getText());
        if(m.find() && m.group().equals(tfemailevent.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Email");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid Email");
                alert.showAndWait();
            
            return false;            
        }
    }
 private boolean validateNumber(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(tflieuidevent.getText());
        if(m.find() && m.group().equals(tflieuidevent.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Number");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid Number");
                alert.showAndWait();
            
            return false;            
        }
    }
private boolean validateFieldsLieu(){
        if( TFNom.getText().isEmpty()){
            
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Fields");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Into The Fields");
                alert.showAndWait();
                
                return false;
        }
        if( TFVille.getText().isEmpty()){
            
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Fields");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Into The Fields");
                alert.showAndWait();
                
                return false;
        }
        if( TFAdresse.getText().isEmpty()){
            
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Fields");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Into The Fields");
                alert.showAndWait();
                
                return false;
        }
        
        
        return true;
    }

@FXML
    private void exportExcel(ActionEvent event) throws SQLException, IOException {
       Connection cnx = MyConnection.getInstance().getCnx();
        String query = "select * from lieu ";
       // System.out.println(getnom.getText());
         PreparedStatement pst = cnx.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
           
            
            
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Détails_Lieu");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("id_Lieu");
            header.createCell(1).setCellValue("nom_Lieu");
            header.createCell(2).setCellValue("Ville_Lieu");

            header.createCell(3).setCellValue("adresse_Lieu");            
            header.createCell(4).setCellValue("capacite_Lieu");
         
            
            int index = 1;
            while(rs.next()){
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(String.valueOf(rs.getInt("id")));
                row.createCell(1).setCellValue(rs.getString("nom"));
                row.createCell(2).setCellValue(rs.getString("nom"));                      
                row.createCell(3).setCellValue(rs.getString("adresse"));
                row.createCell(4).setCellValue(String.valueOf(rs.getInt("capacite")));
               
                index++;
            }
            
            FileOutputStream file = new FileOutputStream("Détails_Lieu.xlsx");
            wb.write(file);
            file.close();
          
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Exportation effectuée!!!");
            alert.showAndWait();
            pst.close();
            rs.close();
            
            File myfFile = new File("C:\\Users\\Yassine\\Desktop");
            Desktop.getDesktop().open(myfFile);
           
    }

   public int Dark1() {

return d;    }
}

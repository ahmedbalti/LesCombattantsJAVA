/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.picampers.Services;

import com.picampers.entities.Event;
import com.picampers.IServices.IService;

import com.picampers.utils.MyConnection;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
/**
 *
 * @author Ramez
 */
public class ServiceEvent implements IService<Event> {

    private Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void ajouter(Event t) {
        try{
            //String query = "INSERT INTO `event`(`nom`, `description`, `type`, `lieuid`,'email') VALUES ('"+t.getNom()+"','"+t.getDescription()+"','"+t.getType()+"','"+t.getLieuid()+"','"+t.getEmail()+"')";
           // String query = "INSERT INTO `event`(`nom`, `type`, `description`, `lieuid`, `email`,'date') VALUES ('"+t.getNom()+"','"+t.getType()+"','"+t.getDescription()+"','"+t.getLieuid()+"','"+t.getEmail()+"','"+t.getDate()+"')";
           String query = "INSERT INTO `event`(`nom`, `type`, `description`, `lieuid`,`date`, `email`) VALUES ('"+t.getNom()+"','"+t.getType()+"','"+t.getDescription()+"','"+t.getLieuid()+"','" +t.getDate()+"', '"+t.getEmail()+"')";
 Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
            System.out.println("event ajouté");
        } 
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
Email from = new Email("CulturaApplication@gmail.com");
                String subject = "Ajout d'un evenement";
                Email to = new Email(t.getEmail());
                Content content = new Content("text/plain", "Votre evenement a ete ajouté sous le nom "+t.getNom());
                Mail mail = new Mail(from, subject, to, content);

                SendGrid sg = new SendGrid("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");
                Request request = new Request();

                try {
                    request.setMethod(Method.POST);
                    request.setEndpoint("mail/send");
                    request.setBody(mail.build());
                    Response response = sg.api(request);
                    System.out.println(response.getStatusCode());
                    System.out.println(response.getBody());
                    System.out.println(response.getHeaders());
                } catch (IOException ex) {
                    System.out.println("message non envoyé");
                }
    }
@Override
    public List<Event> afficher() {
     List<Event> events = new ArrayList();
        try {
       
        String query ="SELECT * FROM `event`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            events.add(new Event(rs.getInt("id"),rs.getInt("lieuid"), rs.getString("nom"), rs.getString("type"), rs.getString("description"),rs.getString("email"),rs.getString("date")));
        }
        
        
        
        return events;
    } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return events;
    }
    
        

    @Override
    public void modifier(Event t) {
            try {
           
                   //String  req = "UPDATE Categorie SET libelle='"+C.getLibelle()+"',description ='"+C.getDescription()+"' WHERE `id`="+C.getId();
                   String req = "UPDATE `event` SET `nom`='"+t.getNom()+"',`description`='"+t.getDescription()+"',`type`='"+t.getType()+"',`lieuid`='"+t.getLieuid()+"',`email`='"+t.getEmail()+"' WHERE `id`='"+t.getId()+"'";
                   PreparedStatement pstm = cnx.prepareStatement(req);
                   
                  
                   pstm.executeUpdate(req);
                   System.out.println("evenement Modifie avec succes.");
               }
           
           catch (SQLException ex) {
               System.out.println(ex.getMessage());
           }
       }
    
    public void supprimer(int id) {
try {
          String query ="DELETE FROM event WHERE id="+id;
         Statement st = cnx.createStatement();
         st.executeUpdate(query);
          System.out.println(" evenement supprimé avec success");
      }
      catch(SQLException e){
        System.out.println(e.getMessage());}
   } 

    @Override
    public void supprimer (Event t){
    }
    public List<Event> afficherTri() {
     List<Event> events = new ArrayList();
        try {
       
        String query ="SELECT * FROM `event`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            events.add(new Event(rs.getInt("lieuid"), rs.getString("nom"), rs.getString("type"), rs.getString("description"),rs.getString("email"),rs.getString("date")));
        }
        
        
        
        return events;
    } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return events;
    }
//public List<Long> statistcs(){
//        ArrayList<Long> list = (ObservableList<Event>) this.afficher();
//       // ObservableList<Data> stats = FXCollections.observableArrayList();
//        long r = list.stream().filter(Promotion -> Promotion.getType().equals("Soiree")).count(), c = list.stream().filter(Promotion -> Promotion.getType().equals("Conference")).count();
//System.out.println(r);
//        //stats.addAll(new PieChart.Data("Soiree", c), new PieChart.Data("Conference", r));
//List<r,c>;
//        return List;
//
//    }
}

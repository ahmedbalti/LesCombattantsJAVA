/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.picampers.Services;

//import com.picampers.entities.
import com.picampers.IServices.IService;
import com.picampers.entities.Lieu;
import com.picampers.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Yassine
 */

public class ServiceLieu implements IService<Lieu>  {
    private Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void ajouter(Lieu t) {
        try{
            //String query = "INSERT INTO `Lieu`(`nom`, `description`, `type`, `lieuid`,'email') VALUES ('"+t.getNom()+"','"+t.getDescription()+"','"+t.getType()+"','"+t.getLieuid()+"','"+t.getEmail()+"')";
            String query = "INSERT INTO `lieu`(`nom`, `ville`, `adresse`, `capacite`, `longitude`,`latitude`) VALUES ('"+t.getNom()+"','"+t.getVille()+"','"+t.getAdresse()+"','"+t.getCapacite()+"','"+t.getLongitude()+"','"+t.getLatitude()+"')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
        } 
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public List<Lieu> afficher() {
        List<Lieu> lieux = new ArrayList();
        try {
       
        String query ="SELECT * FROM `lieu`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            lieux.add(new Lieu( rs.getInt("id"),rs.getString("nom"), rs.getString("ville"), rs.getString("adresse"),rs.getInt("capacite"),rs.getDouble("longitude"),rs.getDouble("latitude")));
        }
        
        
        
        return lieux;
    } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return lieux;
    }

    @Override
    public void modifier(Lieu t) {
        try {
           
                   //String  req = "UPDATE Categorie SET libelle='"+C.getLibelle()+"',description ='"+C.getDescription()+"' WHERE `id`="+C.getId();
                   String req = "UPDATE `lieu` SET `nom`='"+t.getNom()+"',`ville`='"+t.getVille()+"',`adresse`='"+t.getAdresse()+"',`capacite`='"+t.getCapacite()+"',`longitude`='"+t.getLongitude()+"',`latitude`='"+t.getLatitude()+"' WHERE `id`='"+t.getId()+"'";
                   PreparedStatement pstm = cnx.prepareStatement(req);
                   
                  
                   pstm.executeUpdate(req);
                   System.out.println("Lieu Modifie avec succes.");
               }
           
           catch (SQLException ex) {
               System.out.println(ex.getMessage());
           }
        
    }

    public void supprimer(int id) {
        try {
          String query ="DELETE FROM lieu WHERE id="+id;
        
String query2 ="DELETE FROM event WHERE lieuid="+id;
         Statement st = cnx.createStatement();
         st.executeUpdate(query);
st.executeUpdate(query2);
          System.out.println(" evenement supprimé avec success");
      }
      catch(SQLException e){
        System.out.println(e.getMessage());}
   }

    @Override
    public void supprimer(Lieu t){
    }

   public Lieu affichlieusingle(int id){

       Lieu lieu = null;
        try {
       
        String query ="SELECT * FROM `lieu`where id="+ id;
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            lieu = new Lieu( rs.getInt("id"),rs.getString("nom"), rs.getString("ville"), rs.getString("adresse"),rs.getInt("capacite"),rs.getDouble("longitude"),rs.getDouble("latitude"));
        }
        
        
        
        return lieu;
    } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return lieu;

}

    
    public List<Lieu> afficherTri() {
        List<Lieu> lieux = new ArrayList();
        try {
       
        String query ="SELECT * FROM `lieu` ORDER BY capacite DESC";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            lieux.add(new Lieu(rs.getInt("id"), rs.getString("nom"), rs.getString("ville"), rs.getString("adresse"),rs.getInt("capacite"),rs.getDouble("longitude"),rs.getDouble("latitude")));
        }
        
        
        
        return lieux;
    } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return lieux;
    }
    }

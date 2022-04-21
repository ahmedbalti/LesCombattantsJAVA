/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.picampers.Services;

import com.picampers.IServices.IHebergementService;
import com.picampers.entities.Hebergement;
import com.picampers.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arij
 */
  public class HebergementService implements IHebergementService {
    private Connection conn;
    @Override
      public void add(Hebergement c){
          
      try {
          String query="INSERT INTO image(file) VALUES (?)";
          PreparedStatement ps=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
          ps.setString (1,c.getImage());
          int rowsInserted =  ps.executeUpdate();
          ResultSet rs=ps.getGeneratedKeys();
          if (rs.next()) {
                     int  id_img=rs.getInt(1); 
                     System.out.println("id img"+id_img);
                     c.setCover_id(id_img);
          }
             
      }  catch (SQLException ex) {
          Logger.getLogger(Hebergement.class.getName()).log(Level.SEVERE, null, ex);
      }
          
      try {
          java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
             String query="INSERT INTO hebergement(cover_id,name,nb_stars, nb_rooms,address,description,type,disponibilite) VALUES (?,?,?,?,?,?,?,?)";
                
          PreparedStatement ps=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
          
          ps.setInt (1,c.getCover_id());
          ps.setString (2,c.getName());
          ps.setInt  (3,c.getNbStars());
          ps.setInt (4,c.getNbRooms());
          ps.setString (5,c.getAddress());
          ps.setString (6,c.getDescription());
          ps.setString (7,c.getType());
          ps.setInt (8,c.isDisponibilite());
          
   int rowsInserted =  ps.executeUpdate();
   
            ResultSet rs=ps.getGeneratedKeys();
             if (rs.next()) {
                     int  generatedkey=rs.getInt(1);   
                      c.setId(generatedkey);
                  }
      
                if (rowsInserted > 0) System.out.println("L'hebergement est ajouté !");
      } catch (SQLException ex) {
          Logger.getLogger(Hebergement.class.getName()).log(Level.SEVERE, null, ex);
      }
      }
      
     
  
      
    @Override
   public void delete(int id){   
     /* try {
          Statement stmt = null;
          stmt = conn.createStatement();
          
      //    String sql = "DELETE FROM table WHERE id = "+id+"";  
        //  int rowsTablDeleted =  stmt.executeUpdate(sql);
          
        
          String sql3 = "DELETE FROM hebergement WHERE id = "+id+"";  
          int rowsDeleted =  stmt.executeUpdate(sql3);
     if (rowsDeleted > 0) 
    System.out.println("L'hebergement est supprimé !");  
     
     
      } catch (SQLException ex) {
          Logger.getLogger(Hebergement.class.getName()).log(Level.SEVERE, null, ex);
      }*/
     String req = "DELETE FROM `hebergement` WHERE id=?";

        try {
            PreparedStatement pst = conn.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();

            System.out.println("heb Supprime avec Succes");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

           
    }    
      
      
      
public void update(int id,String type, String name,int nb_stars, int nb_rooms, String address, String description,int cover_id, String img){   
    
      try {
          Statement stmt = null;
          stmt = conn.createStatement();
          
           String sql3="UPDATE image SET file='"+img+"' WHERE id="+cover_id+"";
        //  String sql4 = "UPDATE sujet SET titre= 'change' where id="+id+"";
          stmt.executeUpdate(sql3);
          
          int rowsUpdatedImg =  stmt.executeUpdate(sql3);
          
          
        String sql4="UPDATE hebergement SET type='"+type+"',name='"+name+"',nb_stars='"+nb_stars+"', nb_rooms='"+nb_rooms+"',address='"+address+"',description='"+description+"' WHERE id="+id+"";
        //  String sql4 = "UPDATE sujet SET titre= 'change' where id="+id+"";
          stmt.executeUpdate(sql4);
          
          int rowsUpdated =  stmt.executeUpdate(sql4);
          if (rowsUpdated > 0)
              System.out.println("L'Hebergement est modifié !");     
      } catch (SQLException ex) {
          Logger.getLogger(Hebergement.class.getName()).log(Level.SEVERE, null, ex);
      }} 
      
      
    public ArrayList<Hebergement> afficher(int ids){
        
   
      ArrayList<Hebergement> sujetList = new ArrayList<>();
        
              try {
Statement stmt = null;
          stmt = conn.createStatement();
          String sql2 = "SELECT hebergement.*,image.file FROM hebergement INNER JOIN image ON hebergement.cover_id=image.id WHERE hebergement.id="+ids+"";
          
         ResultSet resultSet = stmt.executeQuery(sql2);
          
     if (!resultSet.next() ) {
         Hebergement hebergement = new Hebergement();
    sujetList.add(hebergement);
} else {
 Hebergement hebergement = new Hebergement();
                 hebergement.setId(resultSet.getInt("id"));
                //sujet.setIduser(resultSet.getInt("iduser"));
                hebergement.setCover_id(resultSet.getInt("cover_id"));
                hebergement.setName(resultSet.getString("name")); 
                hebergement.setNbStars(resultSet.getInt ("nb_stars"));
                hebergement.setNbRooms(resultSet.getInt("nb_rooms"));
                hebergement.setAddress(resultSet.getString("address"));   
                hebergement.setDescription(resultSet.getString("description"));
                hebergement.setType(resultSet.getString("type"));
                hebergement.setImage(resultSet.getString("file"));
                hebergement.setDisponibilite(resultSet.getInt("disponibilite"));
                     
               
                     
               
     sujetList.add(hebergement);
                
             /* System.out.print("ID: " + id+",id utilisateur: " + iduser+",tag 1: " + tag1+",tag 2: " + tag2+",tag 3: "
                      + tag3+",nombre commentaires : " + nbr_cmt+",titre: " +titre+",description: " + description+",date : "
                      +datesql+",image : "+ img +"\n");   */
             
          } } catch (SQLException ex) {    
          Logger.getLogger(Hebergement.class.getName()).log(Level.SEVERE, null, ex);
      }
        return sujetList;       
    }
   
   
    public void affichertous(){
        
        
        
        
      try {
          Statement stmt = null;
          stmt = conn.createStatement();
          String sql2 = "SELECT * FROM hebergement";
          ResultSet rs = stmt.executeQuery(sql2);
          while(rs.next()){
              int id  = rs.getInt("id");
              int iduser = rs.getInt("iduser");
              String tag1 = rs.getString("tag1");
              String tag2 = rs.getString("tag2");
              String tag3 = rs.getString("tag3");
              int nbr_cmt  = rs.getInt("nbr_cmt");
              String titre = rs.getString("titre");
              String description = rs.getString("description");
              Date datesql = rs.getDate("date");
              String img = rs.getString("img");
              
              System.out.print("ID: " + id+",id utilisateur: " + iduser+",tag 1: " + tag1+",tag 2: " + tag2+",tag 3: "
                      + tag3+",nombre commentaires : " + nbr_cmt+",titre: " +titre+",description: " + description+",date : "
                      +datesql+",image : "+ img +"\n");
              
          } } catch (SQLException ex) {    
          Logger.getLogger(Hebergement.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
   
   
        public HebergementService (){
        conn = DataSource.getMyInstance().getMyConnexion();
        
    }
    
           
  
    public List<Hebergement> listehebergements() {
        
         String sql = "SELECT * FROM hebergement ORDER BY name ASC";
        List<Hebergement> Hebergements = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
               
                Hebergements.add(new Hebergement(resultSet.getInt("id"),resultSet.getInt("cover_id"),resultSet.getString("name"),
                        resultSet.getInt("nb_stars"),resultSet.getInt("nb_rooms"),resultSet.getString("address"),
                        resultSet.getString("description"),resultSet.getString("type"),resultSet.getInt("disponibilite")));      
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Hebergements;
    }
    
      
    public List<Hebergement> listehotels() {
        
        
         String sql = "SELECT * FROM hebergement where type='Hotel'";
        List<Hebergement> Hebergements = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                 Hebergement hebergement = new Hebergement();
                hebergement.setId(resultSet.getInt("id"));
                //sujet.setIduser(resultSet.getInt("iduser"));
                hebergement.setCover_id(resultSet.getInt("cover_id"));
                hebergement.setName(resultSet.getString("name")); 
                hebergement.setNbStars(resultSet.getInt("nb_stars"));
                hebergement.setNbRooms(resultSet.getInt("nb_rooms"));
                hebergement.setAddress(resultSet.getString("address"));   
                hebergement.setDescription(resultSet.getString("description"));
                hebergement.setType(resultSet.getString("type"));
                hebergement.setDisponibilite(resultSet.getInt("disponibilite"));
                     
               Hebergements.add(hebergement);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Hebergements;
    }
    
        public List<Hebergement> listehouses() {
        
        
         String sql = "SELECT * FROM hebergement where type='House'";
        List<Hebergement> Hebergements = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Hebergement hebergement = new Hebergement();
                hebergement.setId(resultSet.getInt("id"));
                //sujet.setIduser(resultSet.getInt("iduser"));
                hebergement.setCover_id(resultSet.getInt("cover_id"));
                hebergement.setName(resultSet.getString("name"));  
                hebergement.setNbStars(resultSet.getInt("nb_stars"));
                hebergement.setNbRooms(resultSet.getInt("nb_rooms"));
                hebergement.setAddress(resultSet.getString("address"));   
                hebergement.setDescription(resultSet.getString("description"));
                hebergement.setType(resultSet.getString("type"));
                hebergement.setDisponibilite(resultSet.getInt("disponibilite"));
                     
               Hebergements.add(hebergement);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Hebergements;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.Services;

import com.picampers.entities.Club;
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
import com.picampers.IServices.IClubService;


/**
 *
 * @author Lenovo
 */
public class ClubService implements IClubService {
    private Connection conn;
    @Override
      public void add(Club c){
          
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
          Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
      }
          
      try {
          java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
             String query="INSERT INTO club(cover_id,nom,lieu,description,type,disponibilite) VALUES (?,?,?,?,?,?)";
                
          PreparedStatement ps=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
          
          ps.setInt (1,c.getCover_id());
          ps.setString (2,c.getNom());
          ps.setString (3,c.getLieu());
          ps.setString (4,c.getDescription());
          ps.setString (5,c.getType());
          ps.setInt (6,c.isDisponibilite());
          int rowsInserted =  ps.executeUpdate();
 
   
            ResultSet rs=ps.getGeneratedKeys();
             if (rs.next()) {
                     int  generatedkey=rs.getInt(1);   
                      c.setId(generatedkey);
                  }
      
                if (rowsInserted > 0) System.out.println("Le sujet est ajouté !");
      } catch (SQLException ex) {
          Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
      }
      }
      
      
   public void delete(int id){   
      try {
          Statement stmt = null;
          stmt = conn.createStatement();
          
          String sql = "DELETE FROM tabl WHERE club = "+id+"";  
          int rowsTablDeleted =  stmt.executeUpdate(sql);
          
         
          String sql3 = "DELETE FROM club WHERE id = "+id+"";  
          int rowsDeleted =  stmt.executeUpdate(sql3);
     if (rowsDeleted > 0) 
    System.out.println("Le club est supprimé !");  
     
     
      } catch (SQLException ex) {
          Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
      }
           
    }    
      
      
      
public void update(int id,String type, String nom, String lieu, String description,int cover_id, String img){   
    
      try {
          Statement stmt = null;
          stmt = conn.createStatement();
          
           String sql3="UPDATE image SET file='"+img+"' WHERE id="+cover_id+"";
        //  String sql4 = "UPDATE sujet SET titre= 'change' where id="+id+"";
          stmt.executeUpdate(sql3);
          
          int rowsUpdatedImg =  stmt.executeUpdate(sql3);
          
          
        String sql4="UPDATE club SET type='"+type+"',nom='"+nom+"',lieu='"+lieu+"',description='"+description+"' WHERE id="+id+"";
        //  String sql4 = "UPDATE sujet SET titre= 'change' where id="+id+"";
          stmt.executeUpdate(sql4);
          
          int rowsUpdated =  stmt.executeUpdate(sql4);
          if (rowsUpdated > 0)
              System.out.println("Le sujet est modifié !");     
      } catch (SQLException ex) {
          Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
      }} 
      
      
    public ArrayList<Club> afficher(int ids){
        
   
      ArrayList<Club> sujetList = new ArrayList<>();
        
              try {
Statement stmt = null;
          stmt = conn.createStatement();
          String sql2 = "SELECT club.*,image.file FROM club INNER JOIN image ON club.cover_id=image.id WHERE club.id="+ids+"";
          ResultSet resultSet = stmt.executeQuery(sql2);
          
     if (!resultSet.next() ) {
         Club club = new Club();
    sujetList.add(club);
} else {
 Club club = new Club();
                club.setId(resultSet.getInt("id"));
                //sujet.setIduser(resultSet.getInt("iduser"));
                club.setCover_id(resultSet.getInt("cover_id"));
                club.setNom(resultSet.getString("nom"));                
                club.setLieu(resultSet.getString("lieu"));   
                club.setDescription(resultSet.getString("description"));
                club.setType(resultSet.getString("type"));
                club.setImage(resultSet.getString("file"));
                club.setDisponibilite(resultSet.getInt("disponibilite"));
                     
               
     sujetList.add(club);
                
             /* System.out.print("ID: " + id+",id utilisateur: " + iduser+",tag 1: " + tag1+",tag 2: " + tag2+",tag 3: "
                      + tag3+",nombre commentaires : " + nbr_cmt+",titre: " +titre+",description: " + description+",date : "
                      +datesql+",image : "+ img +"\n");   */
             
          } } catch (SQLException ex) {    
          Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
      }
        return sujetList;       
    }
   
   
    public void affichertous(){
        
        
        
        
      try {
          Statement stmt = null;
          stmt = conn.createStatement();
          String sql2 = "SELECT * FROM club";
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
          Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
   
   
        public ClubService (){
        conn = DataSource.getMyInstance().getMyConnexion();
        
    }
    
           
  
    public List<Club> listeclubs() {
        
        
         String sql = "SELECT * FROM club ORDER BY nom ASC, lieu DESC";
        List<Club> Clubs = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Club club = new Club();
                club.setId(resultSet.getInt("id"));
                //sujet.setIduser(resultSet.getInt("iduser"));
                club.setCover_id(resultSet.getInt("cover_id"));
                club.setNom(resultSet.getString("nom"));                
                club.setLieu(resultSet.getString("lieu"));   
                club.setDescription(resultSet.getString("description"));
                club.setType(resultSet.getString("type"));
                club.setDisponibilite(resultSet.getInt("disponibilite"));
                     
               Clubs.add(club);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Clubs;
    }
    
      
    public List<Club> listerestos() {
        
        
         String sql = "SELECT * FROM club where type='Restaurent'";
        List<Club> Clubs = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Club club = new Club();
                club.setId(resultSet.getInt("id"));
                //sujet.setIduser(resultSet.getInt("iduser"));
                club.setCover_id(resultSet.getInt("cover_id"));
                club.setNom(resultSet.getString("nom"));                
                club.setLieu(resultSet.getString("lieu"));   
                club.setDescription(resultSet.getString("description"));
                club.setType(resultSet.getString("type"));
                club.setDisponibilite(resultSet.getInt("disponibilite"));
                     
               Clubs.add(club);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Clubs;
    }
    
        public List<Club> listebars() {
        
        
         String sql = "SELECT * FROM club where type='Bar'";
        List<Club> Clubs = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Club club = new Club();
                club.setId(resultSet.getInt("id"));
                //sujet.setIduser(resultSet.getInt("iduser"));
                club.setCover_id(resultSet.getInt("cover_id"));
                club.setNom(resultSet.getString("nom"));                
                club.setLieu(resultSet.getString("lieu"));   
                club.setDescription(resultSet.getString("description"));
                club.setType(resultSet.getString("type"));
                club.setDisponibilite(resultSet.getInt("disponibilite"));
                     
               Clubs.add(club);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Clubs;
    }
    
    
}

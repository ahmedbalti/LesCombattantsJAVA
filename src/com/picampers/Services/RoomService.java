/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.Services;

import com.picampers.IServices.IRoomService;
import com.picampers.entities.Hebergement;
import com.picampers.entities.Room;
import com.picampers.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.Comparator;
/**
 *
 * @author arij
 */
public class RoomService implements IRoomService{
    private Connection conn;
    public RoomService(){
        conn = DataSource.getMyInstance().getMyConnexion();
    }
    @Override
    public void add(Room r) {
          try {
          String query="INSERT INTO image(file) VALUES (?)";
          PreparedStatement ps=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
          ps.setString (1,r.getImage());
          int rowsInserted =  ps.executeUpdate();
          ResultSet rs=ps.getGeneratedKeys();
          if (rs.next()) {
                     int  id_img=rs.getInt(1); 
                     System.out.println("id img"+id_img);
                     r.setId_cover(id_img);
          }
             
      }  catch (SQLException ex) {
          Logger.getLogger(Hebergement.class.getName()).log(Level.SEVERE, null, ex);
      }
          
      try {
          java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
             String query="INSERT INTO room(`cover_id`,"
                     + " `hebergement`, `number`, "
                     + "`etage`, `nb_lit`, `price`, "
                     + "`description`, `disponibilite`, "
                     + "`emplacement`, `type`) VALUES (?,?,?,?,?,?,?,?,?,?)";
                
          PreparedStatement ps=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
          
          ps.setInt (1,r.getId_cover());
          ps.setInt (2,r.getHebergement());
          ps.setInt  (3,r.getNumber());
          ps.setInt (4,r.getEtage());
          ps.setInt (5,r.getNb_lit());
          ps.setString (6,r.getPrice());
          ps.setString (7,r.getDescription());
          ps.setBoolean(8,r.getDisponibilite());
          ps.setString(9,r.getEmplacement());
          ps.setString(10,r.getType());
          
   int rowsInserted =  ps.executeUpdate();
   
            ResultSet rs=ps.getGeneratedKeys();
             if (rs.next()) {
                     int  generatedkey=rs.getInt(1);   
                      r.setId(generatedkey);
                  }
      
                if (rowsInserted > 0) System.out.println("room est ajouté !");
      } catch (SQLException ex) {
          Logger.getLogger(Hebergement.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    @Override
    public void delete(int id) {
        String req = "DELETE FROM `room` WHERE id=?";

        try {
            PreparedStatement pst = conn.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();

            System.out.println("room Supprime avec Succes");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(int id, Room r) {
        try {
          Statement stmt = null;
          stmt = conn.createStatement();
          
           String sql3="UPDATE image SET file='"+r.getImage()+"' WHERE id="+r.getId_cover()+"";
        //  String sql4 = "UPDATE sujet SET titre= 'change' where id="+id+"";
          stmt.executeUpdate(sql3);
          
          int rowsUpdatedImg =  stmt.executeUpdate(sql3);
          int x=r.getDisponibilite()?1:0;
          
        String sql4="UPDATE `room` SET "
                + "`hebergement`='"+r.getHebergement()+"',"
                + "`number`='"+r.getNumber()+"',"
                + "`etage`='"+r.getEtage()+"',"
                + "`nb_lit`='"+r.getNb_lit()+"',"
                + "`price`='"+r.getPrice()+"',`description`='"+r.getDescription()+"',"
                + "`disponibilite`='"+x+"',`emplacement`='"+r.getEmplacement()+"',"
                + "`type`='"+r.getType()+"' WHERE id="+id;
          stmt.executeUpdate(sql4);
          
          int rowsUpdated =  stmt.executeUpdate(sql4);
          if (rowsUpdated > 0)
              System.out.println("room est modifié !");     
      } catch (SQLException ex) {
          Logger.getLogger(Hebergement.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    @Override
    public Room afficher(int ids) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Room> affichertous() {
         String sql = "SELECT * FROM room ";
        List<Room> rooms = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
        
                rooms.add(new Room(resultSet.getInt("id"),
                        resultSet.getInt("hebergement"),resultSet.getInt("number"),resultSet.getInt("etage"),
                        resultSet.getInt("nb_lit"),resultSet.getString("price"),
                        resultSet.getString("emplacement"),resultSet.getString("description"),
                        resultSet.getString("type"),resultSet.getBoolean("disponibilite"),resultSet.getInt("cover_id")));      
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rooms;
    }
    public List<Room> afficherparhebergement(int id_hebrgement) {
         String sql = "SELECT * FROM room where hebergement="+id_hebrgement+" AND disponibilite=1";
        List<Room> rooms = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
        
                rooms.add(new Room(resultSet.getInt("id"),
                        resultSet.getInt("hebergement"),resultSet.getInt("number"),resultSet.getInt("etage"),
                        resultSet.getInt("nb_lit"),resultSet.getString("price"),
                        resultSet.getString("emplacement"),resultSet.getString("description"),
                        resultSet.getString("type"),resultSet.getBoolean("disponibilite"),resultSet.getInt("cover_id")));      
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rooms;
    }
    public List<Room> sortByEtage(){
         List<Room> rooms=affichertous();
         List<Room> resultat=rooms.stream().sorted(Comparator.comparing(Room::getEtage)).collect(Collectors.toList());
         return resultat;
     }
}

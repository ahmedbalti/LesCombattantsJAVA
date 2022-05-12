/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.Services;

import com.picampers.IServices.ITableService;
import com.picampers.entities.Table;
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
public class TableService implements ITableService{
     private Connection conn;
    public TableService(){
        conn = DataSource.getMyInstance().getMyConnexion();
    }
    @Override
    public void add(Table r) {
          try {
          String query="INSERT INTO image(file) VALUES (?)";
          PreparedStatement ps=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
          ps.setString (1,r.getImage());
          int rowsInserted =  ps.executeUpdate();
          ResultSet rs=ps.getGeneratedKeys();
          if (rs.next()) {
                     int  id_img=rs.getInt(1); 
                     System.out.println("id img"+id_img);
                     r.setCover_id(id_img);
          }
             
      }  catch (SQLException ex) {
          Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
      }
          
      try {
          java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
             String query="INSERT INTO `tabl`"
                     + "(`cover_id`, `club`,"
                     + " `numero`, `position`, `forme`,"
                     + " `type`, `disponibilite`) VALUES (?,?,?,?,?,?,?)";
                
          PreparedStatement ps=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
          
          ps.setInt (1,r.getCover_id());
          ps.setInt (2,r.getClub());
          ps.setInt  (3,r.getNumero());
          ps.setString(4,r.getPosition());
          ps.setString (5,r.getForme());
          ps.setString (6,r.getType());
          ps.setBoolean(7,r.isDisponibilite());
          
          
   int rowsInserted =  ps.executeUpdate();
   
            ResultSet rs=ps.getGeneratedKeys();
             if (rs.next()) {
                     int  generatedkey=rs.getInt(1);   
                      r.setId(generatedkey);
                  }
      
                if (rowsInserted > 0) System.out.println("table est ajouté !");
      } catch (SQLException ex) {
          Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    @Override
    public void delete(int id) {
        String req = "DELETE FROM `tabl` WHERE id=?";

        try {
            PreparedStatement pst = conn.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();

            System.out.println("table Supprime avec Succes");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(int id, Table r) {
        try {
          Statement stmt = null;
          stmt = conn.createStatement();
          
           String sql3="UPDATE image SET file='"+r.getImage()+"' WHERE id="+r.getCover_id()+"";
        //  String sql4 = "UPDATE sujet SET titre= 'change' where id="+id+"";
          stmt.executeUpdate(sql3);
          
          int rowsUpdatedImg =  stmt.executeUpdate(sql3);
          int x=r.isDisponibilite()?1:0;
          
        String sql4="UPDATE `tabl` SET "
                + "`club`='"+r.getClub()+"',"
                + "`numero`='"+r.getNumero()+"',"
                + "`position`='"+r.getPosition()+"',"
                + "`forme`='"+r.getForme()+"',"
                + "`type`='"+r.getType()+"',`disponibilite`='"+x+"' WHERE id="+id;
          stmt.executeUpdate(sql4);
          
          int rowsUpdated =  stmt.executeUpdate(sql4);
          if (rowsUpdated > 0)
              System.out.println("table est modifié !");     
      } catch (SQLException ex) {
          Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    @Override
    public Table afficher(int ids) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Table> affichertous() {
         String sql = "SELECT * FROM tabl ";
        List<Table> rooms = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
        
                rooms.add(new Table(resultSet.getInt("id"),
                        resultSet.getInt("cover_id"),resultSet.getInt("club"),
                        resultSet.getInt("numero"),
                        resultSet.getString("position"),resultSet.getString("forme"),
                        resultSet.getString("type"),resultSet.getBoolean("disponibilite")
                        ));      
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rooms;
    }
    public List<Table> affichertous(int club) {
         String sql = "SELECT * FROM tabl where club="+club+" AND disponibilite=1";
        List<Table> rooms = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
        
                rooms.add(new Table(resultSet.getInt("id"),
                        resultSet.getInt("cover_id"),resultSet.getInt("club"),
                        resultSet.getInt("numero"),
                        resultSet.getString("position"),resultSet.getString("forme"),
                        resultSet.getString("type"),resultSet.getBoolean("disponibilite")
                        ));      
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rooms;
    }
    public Table getTableById(int id){
        List<Table> tables=affichertous();
        return tables.stream().filter(u->u.getId()==id).findFirst().orElse(null);
        
    }
public List<Table> sortByEtage(){
         List<Table> tables=affichertous();
         List<Table> resultat=tables.stream().sorted(Comparator.comparing(Table::getNumero)).collect(Collectors.toList());
         return resultat;
     }
}

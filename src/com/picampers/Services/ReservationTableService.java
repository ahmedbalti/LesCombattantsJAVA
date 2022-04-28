/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.Services;

import com.picampers.IServices.IReservationTableService;
import com.picampers.entities.ReservationTable;
import com.picampers.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mortadha
 */
public class ReservationTableService implements IReservationTableService{
private Connection conn;
    public ReservationTableService(){
        conn = DataSource.getMyInstance().getMyConnexion();
        }
    @Override
    public void add(ReservationTable r) {
        
        try {
            String query="INSERT INTO `reservation`(`tabl`, `date`, `heure`, `nbr_personnes`, `name`) "
                    + "VALUES ('"+r.getTabl()+"','"+r.getDate()+"',"
                    + "'"+r.getHeure()+"','"+r.getNbr_personnes()+"',"
                    + "'"+r.getName()+"')";
            Statement st;
            st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            String query="DELETE FROM `reservation` WHERE id="+id;
            Statement st;
            st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(int id, ReservationTable r) {
        
        try {
            String query="UPDATE `reservation` SET "
                    + "`tabl`='"+r.getTabl()+"',"
                    + "`date`='"+r.getDate()+"',"
                    + "`heure`='"+r.getHeure()+"',"
                    + "`nbr_personnes`='"+r.getNbr_personnes()+"',"
                    + "`name`='"+r.getName()+"' WHERE id="+id;
            Statement st;
            st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ReservationTable afficher(int ids) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReservationTable> affichertous() {
        List<ReservationTable> lu=new ArrayList<>();
        try {
            String query="SELECT * FROM `reservation`";
            Statement st;
            st=conn.createStatement();
            
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                ReservationTable r =new ReservationTable();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setDate(rs.getDate("date"));
                r.setTabl(rs.getInt("tabl"));
                r.setHeure(rs.getTime("heure"));
                r.setNbr_personnes(rs.getString("nbr_personnes"));
                lu.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }
    
    
}

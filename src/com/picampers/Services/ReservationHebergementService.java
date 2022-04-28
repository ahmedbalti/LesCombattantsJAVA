/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.Services;

import com.picampers.IServices.IReservationHebergementService;
import com.picampers.entities.ReservationHebergement;
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
public class ReservationHebergementService implements IReservationHebergementService {
    private Connection conn;
    public ReservationHebergementService(){
        conn = DataSource.getMyInstance().getMyConnexion();
    }
    @Override
    public void add(ReservationHebergement r) {
        int x=r.getAll_day()?1:0;
        try {
            String query="INSERT INTO `calendar`"
                    + "(`room`, `title`,"
                    + " `start`, `end`,"
                    + " `name`, `all_day`) "
                    + "VALUES ('"+r.getRoom()+"','"+r.getTitle()+"',"
                    + "'"+r.getStart()+"','"+r.getNd()+"',"
                    + "'"+r.getName()+"','"+x+"')";
            Statement st;
            st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationHebergementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            String query="DELETE FROM `calendar` WHERE id="+id;
            Statement st;
            st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationHebergementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(int id, ReservationHebergement r) {
        int x=r.getAll_day()?1:0;
        try {
            String query="UPDATE `calendar` SET "
                    + "`room`='"+r.getRoom()+"',"
                    + "`title`='"+r.getTitle()+"',"
                    + "`start`='"+r.getStart()+"',"
                    + "`end`='"+r.getNd()+"',"
                    + "`name`='"+r.getName()+"',"
                    + "`all_day`='"+x+"' WHERE id="+id;
            Statement st;
            st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationHebergementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ReservationHebergement afficher(int ids) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReservationHebergement> affichertous() {
        List<ReservationHebergement> lu=new ArrayList<>();
        try {
            String query="SELECT * FROM `calendar`";
            Statement st;
            st=conn.createStatement();
            
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                ReservationHebergement r =new ReservationHebergement();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setStart(rs.getDate("start").toLocalDate().atStartOfDay());
                r.setNd(rs.getDate("end").toLocalDate().atStartOfDay());
                r.setTitle(rs.getString("title"));
                r.setAll_day(rs.getBoolean("all_day"));
                r.setRoom(rs.getInt("room"));
                lu.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationHebergementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }
    
}

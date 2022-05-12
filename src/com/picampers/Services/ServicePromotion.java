/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.picampers.Services;

import com.picampers.IServices.IPromotion;
import com.picampers.entities.Promotion;
import com.picampers.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;

/**
 *
 * @author Ramez
 */
public class ServicePromotion implements IPromotion {

    private final Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void ajouterPromotion(Promotion t) {
        try{
            String query = "INSERT INTO `promotion`(`type`, `description`, `score_min`, `title`) VALUES ('"+t.getType()+"','"+t.getDescription()+"','"+t.getScoreMin()+"','"+t.getTitle()+"')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
            System.out.println("Promotion ajoute avec succes.");
        } 
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Promotion> afficherPromotions() {
        //ObservableList<Promotion> promotions = (ObservableList<Promotion>) new ArrayList(); //FXCollections.observableArrayList();
        ObservableList<Promotion> promotions = FXCollections.observableArrayList();
        try {
       
        String query ="SELECT * FROM `promotion`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            promotions.add(new Promotion(rs.getInt(1), rs.getInt("score_min"), rs.getString("type"), rs.getString("description"), rs.getString("title")));
        }
        
        
        
        return promotions;
    } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return promotions;
    }
        

    @Override
    public void modifierPromotion(Promotion t) {
            try {
           
                   //String  req = "UPDATE Categorie SET libelle='"+C.getLibelle()+"',description ='"+C.getDescription()+"' WHERE `id`="+C.getId();
                   String req = "UPDATE `promotion` SET `type`='"+t.getType()+"',`description`='"+t.getDescription()+"',`score_min`='"+t.getScoreMin()+"',`title`='"+t.getTitle()+"' WHERE `id`='"+t.getId()+"'";
                   PreparedStatement pstm = cnx.prepareStatement(req);
                   
                  
                   pstm.executeUpdate(req);
                   System.out.println("Promotion Modifie avec succes.");
               }
           
           catch (SQLException ex) {
               System.out.println(ex.getMessage());
           }
       }
    
    @Override
    public void supprimerPromotion(int id) {
        try {
            String query2 = "DELETE from promotion_affecte where id_promo_id =" +id;
            String query ="DELETE FROM promotion WHERE id="+id;
            Statement st = cnx.createStatement();
            st.executeUpdate(query2);
            st.executeUpdate(query);
            System.out.println(" Promotion supprimé avec success");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
   }

    @Override
    public List<Promotion> recherchePromotion(int id){

         ObservableList<Promotion> list = this.afficherPromotions();
         return list.stream().filter(Promotion -> Promotion.getId()== id).collect(Collectors.toList());
    }


    
    /*@Override
    public Promotion rechercheByID(int id) {
      Promotion p = null;

        try {
            String req = "SELECT * FROM promotion WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            if(res.next())
            {
               p = new Promotion(rs.getInt(1), rs.getInt("score_min"), rs.getString("type"), rs.getString("description"), rs.getString("title"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
 }*/

    @Override
    public ObservableList<Data> statistcs(){
        ObservableList<Promotion> list = this.afficherPromotions();
        ObservableList<Data> stats = FXCollections.observableArrayList();
        long r = list.stream().filter(Promotion -> Promotion.getType().equals("Remise")).count(), c = list.stream().filter(Promotion -> Promotion.getType().equals("Cadeau")).count();
                
        stats.addAll(new PieChart.Data("Cadeau", c), new PieChart.Data("Remise", r));
        return stats;
       
    }

    int getPromotions(){
        return this.afficherPromotions().size();
    }

    @Override
    public List<Promotion> recherchePromotionByTitle(String title){
         ObservableList<Promotion> list = this.afficherPromotions();
         return list.stream().filter(Promotion -> Promotion.getTitle().equals(title)).collect(Collectors.toList());
    }
    
}

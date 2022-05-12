/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.picampers.Services;

import com.picampers.IServices.IAffectation;
import com.picampers.entities.PromotionAffecte;
import com.picampers.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
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
public class ServiceAffecte implements IAffectation {
    
    private final Connection cnx = MyConnection.getInstance().getCnx();
   
    @Override
    public boolean ajouterAff(PromotionAffecte t){
        try{
            
            String query = "INSERT INTO `promotion_affecte`(`id_user_id`, `id_promo_id`, `delai`) VALUES ('"+t.getUser()+"','"+t.getPromotion()+"','"+t.getDelai().toString() + "')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
            System.out.println("Promotion affecte avec succes.");
            return true;
        } 
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
   
    @Override
    public ObservableList<PromotionAffecte> afficherAff(){
       ObservableList<PromotionAffecte> affect = FXCollections.observableArrayList();
        try {
       
        String query ="SELECT * FROM `promotion_affecte`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            affect.add(new PromotionAffecte(rs.getInt(1), rs.getInt("id_user_id"), rs.getInt("id_promo_id"), rs.getDate("delai").toLocalDate()  ));
        }
        
        
        
        return affect;
    } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return affect;
    }

    @Override
    public void  modifierAff(PromotionAffecte t){
        try {
            String req = "UPDATE `promotion_affecte` SET `id_user_id`='"+t.getUser()+"',`id_promo_id`='"+t.getPromotion()+"',`delai`='"+t.getDelai() + "' WHERE `id`='"+t.getId() + "'";
                   PreparedStatement pstm = cnx.prepareStatement(req);
                   
                  
                   pstm.executeUpdate(req);
                   System.out.println("Affectation Modifie avec succes.");
        } catch (Exception e) {
        }
    }

    @Override
    public void supprimerAff(int id){
        try {
            String query ="DELETE FROM promotion_affecte WHERE id="+id;
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println(" Affectation supprimé avec success");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<PromotionAffecte> rechercheAffectes(int id){

         //Comparator<PromotionAffecte> compare = Comparator.comparing(PromotionAffecte::getId);
         List<PromotionAffecte> liste = this.afficherAff();
         
         return liste.stream().filter(PromotionAffecte -> PromotionAffecte.getId() == id).collect(Collectors.toList());//liste.stream().filter(---).collect();
        //return null;
    } 
    
    @Override
    public List<PromotionAffecte> trierAffecte(){
         
           List<PromotionAffecte> liste = this.afficherAff();
           return liste.stream().sorted(Comparator.comparing(PromotionAffecte -> PromotionAffecte.getUser())).collect(Collectors.toList());
    }

    @Override
     public List<PromotionAffecte> trierDate(){
          List<PromotionAffecte> liste = this.afficherAff();
          return liste.stream().sorted(Comparator.comparing(PromotionAffecte -> PromotionAffecte.getDelai())).collect(Collectors.toList());
     }

    @Override
    public ObservableList<Data> statistcs(){
       ObservableList<PromotionAffecte> list = this.afficherAff();
       ObservableList<Data> stats = FXCollections.observableArrayList();
       ServicePromotion sp = new ServicePromotion();
       
       long p = Long.valueOf(sp.getPromotions()), a = Long.valueOf(list.size());
       
       
       stats.addAll(new PieChart.Data("Promotion affecte", a), new PieChart.Data("Promotion non affecte", p - a));

       return stats;
    }
}

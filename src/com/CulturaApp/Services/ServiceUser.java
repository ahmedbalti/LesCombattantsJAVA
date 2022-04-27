/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CulturaApp.Services;

import com.CulturaApp.Model.Promotion;
import com.CulturaApp.Model.User;
import com.CulturaApp.utils.MyDb;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ramez
 */
public class ServiceUser {
   private final Connection cnx = MyDb.getInstance().getCnx();

   public ObservableList<User> afficherUser() {

       ObservableList<User> user = FXCollections.observableArrayList();
        try {
       
        String query ="SELECT * FROM `utilisateur`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            user.add(new User(rs.getInt(1), rs.getInt("score"), rs.getInt("num_tel"), rs.getString("nom"), rs.getString("prenom") , rs.getString("email"), rs.getString("password"), rs.getString("adresse"), rs.getString("type") ));
        }
        
        
        
        return user;
    } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return user;
   }

   public List<User> rechcercheUser(int id){
       ObservableList<User> user = this.afficherUser();

       return user.stream().filter(User -> User.getId() == id).collect(Collectors.toList());
   } 
    
}

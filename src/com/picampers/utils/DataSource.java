/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author esprit
 */
public class DataSource {
    
    
    private String URL ="jdbc:mysql://localhost:3306/cultura2";
    final String USER="root";
    final String PWD="";
    private static Connection cnx;
    private static DataSource instance;

    private DataSource() {
        try{
            cnx=DriverManager.getConnection(URL, USER,PWD);
        }catch(SQLException ex){
                System.out.println(ex.getMessage());
        }
    }
    public static DataSource getMyInstance(){
        if(instance==null){
            instance=new DataSource();
        }
        else{
            System.out.println("deja connecte");
        }
        return instance;
    }

    public static Connection getMyConnexion() {
        return cnx;
    }
    
    
}

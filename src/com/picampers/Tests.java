/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers;

import com.picampers.Services.HebergementService;
import com.picampers.Services.RoomService;
import com.picampers.entities.Room;

/**
 *
 * @author Mortadha
 */
public class Tests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        RoomService rs=new RoomService();
        HebergementService hs=new HebergementService();
        Room r=new Room("test2", 15, 22, 12, 50, "250", "test", "test", "test", true,24);
        //rs.add(r);
        //rs.update(2, r);
        //rs.delete(2);
        System.out.println(hs.getHebergementByName("Africa"));
    }
    
}

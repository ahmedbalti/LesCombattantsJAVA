/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.IServices;

import com.picampers.entities.Hebergement;
import com.picampers.entities.Room;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mortadha
 */
public interface IRoomService {
    public void add(Room r);
    public void delete(int id);
    public void update(int id,Room r);
    public Room afficher(int ids);
    public List<Room> affichertous();
    
}

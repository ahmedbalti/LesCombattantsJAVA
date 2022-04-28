/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.IServices;

import com.picampers.entities.Club;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface IClubService {
public void add(Club c);
public void delete(int id);
public void update(int id,String type, String nom, String lieu, String description,int cover_id, String img);
public ArrayList<Club> afficher(int ids);
public void affichertous();
public List<Club> listeclubs();
public List<Club> listerestos();
public List<Club> listebars();
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.picampers.IServices;

import com.picampers.entities.Hebergement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Arij
 */
public interface IHebergementService {
public void add(Hebergement c);
public void delete(int id);
public void update(int id,String type, String name,int nb_stars,int nb_rooms ,String address, String description,int cover_id, String img);
public ArrayList<Hebergement> afficher(int ids);
public void affichertous();
public List<Hebergement> listehebergements();
public List<Hebergement> listehotels();
public List<Hebergement> listehouses();
}
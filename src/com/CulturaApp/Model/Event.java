/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.CulturaApp.Model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Ramez
 */
public class Event {

    private int id,lieuid ;
    private String nom, type,description, email;
    private String date;
    

    public Event() {
    }

    public Event(int id, int lieuid, String nom, String type, String description, String email,String date) {
        this.id = id;
        this.lieuid = lieuid;
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.email = email;
        this.date = date;
    }

public Event(int lieuid, String nom, String type, String description, String email,String date) {
        this.lieuid = lieuid;
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.email = email;
        this.date = date;
    }

   /* public Event(int lieuid, String nom, String type, String description, String email,Date date) {
        this.lieuid = lieuid;
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.email = email;
        this.date = date;
    }*/

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLieuid() {
        return lieuid;
    }

    public void setLieuid(int lieuid) {
        this.lieuid = lieuid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", lieuid=" + lieuid + ", nom=" + nom + ", type=" + type + ", description=" + description + ", email=" + email + ", date=" + date + '}';
    }

   

    

    
  
    
    
    

}

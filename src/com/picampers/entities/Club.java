/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.entities;

import java.util.Date;


/**
 *
 * @author Lenovo
 */
public class Club {
  private int id;
  private int cover_id;
  private String nom;
  private String lieu;
  private String description;
  private String type;
  private int disponibilite;
    private String image;

      public Club(){
          
      }

    public Club(int id, int cover_id, String nom, String lieu, String description, String type, int disponibilite) {
        this.id = id;
        this.cover_id = cover_id;
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.type = type;
        this.disponibilite = disponibilite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCover_id() {
        return cover_id;
    }

    public void setCover_id(int cover_id) {
        this.cover_id = cover_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(int disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Club{" + "id=" + id + ", cover_id=" + cover_id + ", nom=" + nom + ", lieu=" + lieu + ", description=" + description + ", type=" + type + ", disponibilite=" + disponibilite + ", image=" + image + '}';
    }
  

    
    
}

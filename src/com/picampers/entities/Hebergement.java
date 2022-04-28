/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.picampers.entities;

/**
 *
 * @author Arij
 */
public class Hebergement {
    private int id;
  private int cover_id;
  private String name;
  private int  nb_stars;
  private int  nb_rooms;
  private String address;
  private String description;
  private String type;
  private int disponibilite;
  private String image;

      public Hebergement(){
          
      }

    public Hebergement(int id, int cover_id, String name, int nb_stars, int  nb_rooms, String address, String description, String type, int disponibilite) {
        this.id = id;
        this.cover_id = cover_id;
        this.name = name;
        this.nb_stars = nb_stars;
        this.nb_rooms = nb_rooms;
        this.address= address;
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

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }
    
    public int  getNbStars() {
        return nb_stars;
    }

    public void setNbStars(int  nb_stars) {
        this.nb_stars = nb_stars;
    }

    
    public int  getNbRooms() {
        return nb_rooms;
    }

    public void setNbRooms(int nb_rooms) {
        this.nb_rooms = nb_rooms;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        return "Hebergement{" + "id=" + id + ", cover_id=" + cover_id + ", name=" + name + ", nb_stars=" + nb_stars + ", nb_rooms=" + nb_rooms + ", address=" + address + ", description=" + description + ", type=" + type + ", disponibilite=" + disponibilite + ", image=" + image + '}';
    }

    public int getNb_stars() {
        return nb_stars;
    }

    public void setNb_stars(int nb_stars) {
        this.nb_stars = nb_stars;
    }

    public int getNb_rooms() {
        return nb_rooms;
    }

    public void setNb_rooms(int nb_rooms) {
        this.nb_rooms = nb_rooms;
    }
    
    
}

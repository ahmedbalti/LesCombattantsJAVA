/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.picampers.entities;

/**
 *
 * @author Arij
 */
public class Room {
    private int id;
    private String image;
    private int hebergement;
    private int number;
    private int etage;
    private int nb_lit;
    private String price;
    private String emplacement;
    private String description;
    private String type;
    private Boolean disponibilite;
    private int id_cover;

    public Room() {
    }

    public Room(String image, int hebergement, int number, int etage, int nb_lit, String price, String emplacement, String description, String type, Boolean disponibilite, int id_cover) {
        this.image = image;
        this.hebergement = hebergement;
        this.number = number;
        this.etage = etage;
        this.nb_lit = nb_lit;
        this.price = price;
        this.emplacement = emplacement;
        this.description = description;
        this.type = type;
        this.disponibilite = disponibilite;
        this.id_cover = id_cover;
    }
    public Room(String image, int hebergement, int number, int etage, int nb_lit, String price, String emplacement, String description, String type, Boolean disponibilite) {
        this.image = image;
        this.hebergement = hebergement;
        this.number = number;
        this.etage = etage;
        this.nb_lit = nb_lit;
        this.price = price;
        this.emplacement = emplacement;
        this.description = description;
        this.type = type;
        this.disponibilite = disponibilite;
    }
    public Room(int id,  int hebergement, int number, int etage, int nb_lit, String price, String emplacement, String description, String type, Boolean disponibilite, int id_cover) {
        this.id = id;
        this.hebergement = hebergement;
        this.number = number;
        this.etage = etage;
        this.nb_lit = nb_lit;
        this.price = price;
        this.emplacement = emplacement;
        this.description = description;
        this.type = type;
        this.disponibilite = disponibilite;
        this.id_cover = id_cover;
    }
    public Room(int id, String image, int hebergement, int number, int etage, int nb_lit, String price, String emplacement, String description, String type, Boolean disponibilite, int id_cover) {
        this.id = id;
        this.image = image;
        this.hebergement = hebergement;
        this.number = number;
        this.etage = etage;
        this.nb_lit = nb_lit;
        this.price = price;
        this.emplacement = emplacement;
        this.description = description;
        this.type = type;
        this.disponibilite = disponibilite;
        this.id_cover = id_cover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getHebergement() {
        return hebergement;
    }

    public void setHebergement(int hebergement) {
        this.hebergement = hebergement;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public int getNb_lit() {
        return nb_lit;
    }

    public void setNb_lit(int nb_lit) {
        this.nb_lit = nb_lit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
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

    public Boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public int getId_cover() {
        return id_cover;
    }

    public void setId_cover(int id_cover) {
        this.id_cover = id_cover;
    }

    @Override
    public String toString() {
        return "Room{" + "id=" + id + ", image=" + image + ", hebergement=" + hebergement + ", number=" + number + ", etage=" + etage + ", nb_lit=" + nb_lit + ", price=" + price + ", emplacement=" + emplacement + ", description=" + description + ", type=" + type + ", disponibilite=" + disponibilite + ", id_cover=" + id_cover + '}'+"\n";
    }
    
}
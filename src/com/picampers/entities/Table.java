/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.entities;

/**
 *
 * @author arij
 */
public class Table {
    private int id;
    private int cover_id;
    private int club;
    private int numero ;
    
    private String position;
    private String forme;
    private String type;
    private boolean disponibilite;
    private String image;

    public Table() {
    }

    public Table(int cover_id, int club, int numero, String position, String forme, String type, boolean disponibilite, String image) {
        this.cover_id = cover_id;
        this.club = club;
        this.numero = numero;
        this.position = position;
        this.forme = forme;
        this.type = type;
        this.disponibilite = disponibilite;
        this.image = image;
    }

    public Table(int club, int numero, String position, String forme, String type, boolean disponibilite, String image) {
        this.club = club;
        this.numero = numero;
        this.position = position;
        this.forme = forme;
        this.type = type;
        this.disponibilite = disponibilite;
        this.image = image;
    }

    public Table(int id, int cover_id, int club, int numero, String position, String forme, String type, boolean disponibilite) {
        this.id = id;
        this.cover_id = cover_id;
        this.club = club;
        this.numero = numero;
        this.position = position;
        this.forme = forme;
        this.type = type;
        this.disponibilite = disponibilite;
    }

    public Table(int id, int cover_id, int club, int numero, String position, String forme, String type, boolean disponibilite, String image) {
        this.id = id;
        this.cover_id = cover_id;
        this.club = club;
        this.numero = numero;
        this.position = position;
        this.forme = forme;
        this.type = type;
        this.disponibilite = disponibilite;
        this.image = image;
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

    public int getClub() {
        return club;
    }

    public void setClub(int club) {
        this.club = club;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getForme() {
        return forme;
    }

    public void setForme(String forme) {
        this.forme = forme;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
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
        return "Table{" + "id=" + id + ", cover_id=" + cover_id + ", club=" + club + ", numero=" + numero + ", position=" + position + ", forme=" + forme + ", type=" + type + ", disponibilite=" + disponibilite + ", image=" + image + '}'+"\n";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

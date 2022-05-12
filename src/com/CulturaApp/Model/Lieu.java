/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CulturaApp.Model;

/**
 *
 * @author Yassine
 */
public class Lieu {
    private int id,capacite ;
    private String nom,ville,adresse;
    private double longitude,latitude;

    public Lieu(int id,String nom, String ville, String adresse,int capacite, double longitude, double latitude ) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.capacite = capacite;
        this.nom = nom;
        this.ville = ville;
        this.adresse = adresse;
    }

    public Lieu(String nom, String ville, String adresse,int capacite, double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.capacite = capacite;
        this.nom = nom;
        this.ville = ville;
        this.adresse = adresse;
        
    }

    @Override
    public String toString() {
        return "Lieu{" + "id=" + id + ", longitude=" + longitude + ", latitude=" + latitude + ", capacite=" + capacite + ", nom=" + nom + ", ville=" + ville + ", adresse=" + adresse + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}

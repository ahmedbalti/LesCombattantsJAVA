/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.entities;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Mortadha
 */
public class ReservationTable {
    private int id;
    private int tabl;
    private Date date;
    private Time heure;
    private String nbr_personnes;
    private String name;

    public ReservationTable() {
    }

    public ReservationTable(int tabl, Date date, Time heure, String nbr_personnes, String name) {
        this.tabl = tabl;
        this.date = date;
        this.heure = heure;
        this.nbr_personnes = nbr_personnes;
        this.name = name;
    }

    public ReservationTable(int id, int tabl, Date date, Time heure, String nbr_personnes, String name) {
        this.id = id;
        this.tabl = tabl;
        this.date = date;
        this.heure = heure;
        this.nbr_personnes = nbr_personnes;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTabl() {
        return tabl;
    }

    public void setTabl(int tabl) {
        this.tabl = tabl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public String getNbr_personnes() {
        return nbr_personnes;
    }

    public void setNbr_personnes(String nbr_personnes) {
        this.nbr_personnes = nbr_personnes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ReservationTable{" + "id=" + id + ", tabl=" + tabl + ", date=" + date + ", heure=" + heure + ", nbr_personnes=" + nbr_personnes + ", name=" + name + '}'+"\n";
    }
    
}

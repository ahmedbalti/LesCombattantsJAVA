package Model;

import java.time.LocalDate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mustapha
 */
public class Reclamation {
 private int id ;
 private String situation ,description , type ;
private String date;

    public Reclamation() {
    }

    public Reclamation(int id, String situation, String description ,String date, String type) {
        this.id = id;
        this.situation = situation;
        this.description = description;
this.date = date;
 this.type = type;
    }


public Reclamation( String situation, String description , String type) {
       
        this.situation = situation;
        this.description = description;
 this.type = type;
    }

    



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }


 public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", situation=" + situation + ", description=" + description +", date="+ date + ", type=" + type + "\n";
    }
 
 


}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.picampers.entities;

//import java.util.Date;
import java.util.logging.Logger;
import java.time.LocalDate;
/**
 *
 * @author Ramez
 */
public class PromotionAffecte {
    private int id, user, promotion;
    private LocalDate delai;

    public PromotionAffecte(int id, int user, int promotion, LocalDate delai) {
        this.id = id;
        this.user = user;
        this.promotion = promotion;
        this.delai = delai;
    }


    public PromotionAffecte(int user, int promotion, LocalDate delai) {
        this.user = user;
        this.promotion = promotion;
        this.delai = delai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public LocalDate getDelai() {
        return delai;
    }

    public void setDelai(LocalDate delai) {
        this.delai = delai;
    }

   @Override
    public String toString() {
        return "PromotionAffecte{" + "id=" + id + ", User=" + user + ", Promotion=" + promotion + ", Delai=" + delai + '}';
    }

        
    
    


}

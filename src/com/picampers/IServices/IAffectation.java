/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.picampers.IServices;

import com.picampers.entities.PromotionAffecte;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;

/**
 *
 * @author Ramez
 */
public interface IAffectation {
    public boolean ajouterAff(PromotionAffecte t);
    public ObservableList<PromotionAffecte> afficherAff();
    public void  modifierAff(PromotionAffecte t);
    public void supprimerAff(int id);
    public List<PromotionAffecte> rechercheAffectes(int id);
    public List<PromotionAffecte> trierAffecte();
    public List<PromotionAffecte> trierDate();
    public ObservableList<Data> statistcs();
}

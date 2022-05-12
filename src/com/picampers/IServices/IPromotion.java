/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.picampers.IServices;

import com.picampers.entities.Promotion;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;

/**
 *
 * @author Ramez
 */
public interface IPromotion {

    public void ajouterPromotion(Promotion t);
    public ObservableList<Promotion> afficherPromotions();
    public void  modifierPromotion(Promotion t);
    public void supprimerPromotion(int id);
    public List<Promotion> recherchePromotion(int id);
    public List<Promotion> recherchePromotionByTitle(String title);
    public ObservableList<Data> statistcs();
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package com.CulturaApp;
import com.CulturaApp.Model.Event;
import com.CulturaApp.Model.Lieu;
import com.CulturaApp.Services.ServiceEvent;
import com.CulturaApp.Services.ServiceLieu;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 *
 * @author Ramez
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        ServiceEvent sp = new ServiceEvent();
        ServiceLieu sL = new ServiceLieu();
        //ADD

        System.out.println("Ajout : \n");
        //int score = System.in.read();
        //String type = System.in.readline(), desc = System.in.read(), title = System.in.read();
LocalDate d=LocalDate.of(2019, 01, 10);
        Event p = new Event(7, "hadhra", "party", "a9wa hadhra fik ya tounes", "yassinetilouche.com","19-09-2020");
        sp.ajouter(p);
        
        Lieu L=new Lieu("centre Ville8578","tunis","beb",500,36.5874,17.1254);
        sL.ajouter(L);
        //SHOW
        System.out.println( sp.afficher());
        
        //UPDATE
        sp.modifier(new Event(109,18, "hadhra985", "party", "a9wa hadhra fik ya tounes", "yassinetilouche@gmail.com","19-09-2020"));
        sL.modifier(new Lieu(15,"centre Ville14","tunis","beb",500,36.5874,17.1254));
        sL.supprimer(14);
        System.out.println( sL.afficherTri());
        //sp.supprimer(108);
        
    }

}

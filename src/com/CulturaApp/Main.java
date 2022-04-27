/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package com.CulturaApp;
import com.CulturaApp.Model.Promotion;
import com.CulturaApp.Model.PromotionAffecte;
import com.CulturaApp.Services.ServiceAffecte;
import com.CulturaApp.Services.ServicePromotion;
import com.CulturaApp.utils.Mailer;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;
import javax.mail.MessagingException;

/**
 *
 * @author Ramez
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MessagingException {
        // TODO code application logic here
//        int score, id;
//        String type, desc, title;
//        
//        Scanner myObj = new Scanner(System.in);
//        ServicePromotion sp = new ServicePromotion();
//        ServiceAffecte sa = new ServiceAffecte();

        //ADD

//        System.out.println("Ajout : \n");
//        
//        System.out.println("Score Minimum :");
//        score = Integer.parseInt(myObj.nextLine());
//        System.out.println("\nType :");
//        type = myObj.nextLine();
//        System.out.println("\nDescription :");
//        desc = myObj.nextLine();
//        System.out.println("\n Title : :");
//        title = myObj.nextLine();
//        Promotion p = new Promotion(score, type, desc, title);
//        sp.ajouter(p);

        //SHOW
//        System.out.println( sp.afficher());
        
        //UPDATE
//        System.out.println("Modifier : \n");
//        id = Integer.parseInt(myObj.nextLine());
//        
//        System.out.println("Score Minimum :");
//        score = Integer.parseInt(myObj.nextLine());
//        System.out.println("\nType :");
//        type = myObj.nextLine();
//        System.out.println("\nDescription :");
//        desc = myObj.nextLine();
//        System.out.println("\n Title : :");
//        title = myObj.nextLine();
//        sp.modifier(new Promotion(id, score, type, desc, title));
        //sp.modifier(sp.rechercheByID(id));


       //DELETE
       //sp.supprimer(10);

       //CREATE
       //PromotionAffecte pa = new PromotionAffecte(2, 12, LocalDate.of(2050, 11, 1));
       //sa.ajouter(pa);

       //READ
      // System.out.println(sa.afficherAff());

      //UPDATE
//      PromotionAffecte pa = new PromotionAffecte(4, 2, 12, LocalDate.of(2060, 5, 1));
//      sa.modifier(pa);


     //DELETE
       //sa.supprimer(1);


       // System.out.println(sp.recherchePromotion(9));
       // System.out.println(sa.trierDate());

        
            //Mailer.sendMail("ramez.wechteti1@gmail.com");
         
    }

}

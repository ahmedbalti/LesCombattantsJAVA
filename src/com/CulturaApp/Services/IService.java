/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.CulturaApp.Services;

import java.util.List;
/**
 *
 * @author Ramez
 */
public interface IService<T> {

    public void ajouter(T t);
    public List<T> afficher();
       public void  modifier (T t);
public void supprimer (int id);
public List<T> afficherTri();


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

/**
 *
 * @author DELL
 */
public interface IService <T> {
    
    public void  ajouter(T t);
    public List<T> afficher();
    public void  modifier (T t);
    public void supprimer (T t);
    /*
    ....
TO DO  
tri 
recherche 
.... 
    */

}
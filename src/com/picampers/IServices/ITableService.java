/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.IServices;

import com.picampers.entities.Table;
import java.util.List;

/**
 *
 * @author Mortadha
 */
public interface ITableService {
    public void add(Table r);
    public void delete(int id);
    public void update(int id,Table r);
    public Table afficher(int ids);
   public List<Table> affichertous();
}

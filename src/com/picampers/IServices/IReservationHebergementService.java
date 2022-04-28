/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.IServices;

import com.picampers.entities.ReservationHebergement;
import java.util.List;

/**
 *
 * @author Mortadha
 */
public interface IReservationHebergementService {
    public void add(ReservationHebergement r);
    public void delete(int id);
    public void update(int id,ReservationHebergement r);
    public ReservationHebergement afficher(int ids);
    public List<ReservationHebergement> affichertous();
}

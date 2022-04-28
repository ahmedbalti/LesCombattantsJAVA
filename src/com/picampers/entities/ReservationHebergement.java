/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.entities;

import java.time.LocalDateTime;

/**
 *
 * @author Mortadha
 */
public class ReservationHebergement {
    private int id;
    private int room;
    private String title;
    private LocalDateTime start;
    private LocalDateTime nd;
    private String name;
    private Boolean all_day;

    public ReservationHebergement() {
    }

    public ReservationHebergement(int room, String title, LocalDateTime start, LocalDateTime nd, String name, Boolean all_day) {
        this.room = room;
        this.title = title;
        this.start = start;
        this.nd = nd;
        this.name = name;
        this.all_day = all_day;
    }

    public ReservationHebergement(int id, int room, String title, LocalDateTime start, LocalDateTime nd, String name, Boolean all_day) {
        this.id = id;
        this.room = room;
        this.title = title;
        this.start = start;
        this.nd = nd;
        this.name = name;
        this.all_day = all_day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getNd() {
        return nd;
    }

    public void setNd(LocalDateTime nd) {
        this.nd = nd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAll_day() {
        return all_day;
    }

    public void setAll_day(Boolean all_day) {
        this.all_day = all_day;
    }

    @Override
    public String toString() {
        return "ReservationHebergement{" + "id=" + id + ", room=" + room + ", title=" + title + ", start=" + start + ", nd=" + nd + ", name=" + name + ", all_day=" + all_day + '}'+"\n";
    }
    
}

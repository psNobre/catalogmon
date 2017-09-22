package com.example.ufc147nobre.myapplication.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by ufc147.nobre on 20/09/2017.
 */

public class Monster implements Serializable {

    private String name;
    private int imgId;
    private String id;
    private boolean favorite;
    private String description;
    private Date date;

    public Monster(String name, int imgId) {
        this.name = name;
        this.favorite = false;
        this.imgId = imgId;
        this.id = UUID.randomUUID().toString();
        date = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomDate() {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd, MMM yyyy");
        String dateString = formatDate.format(date);
        return dateString;
    }
    public String getCustomHour() {
        SimpleDateFormat formatDate = new SimpleDateFormat("hh:mm a");
        String dateString = formatDate.format(date);
        return dateString;
    }
}

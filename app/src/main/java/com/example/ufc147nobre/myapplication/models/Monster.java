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

    private int id;
    private String name;
    private boolean favorite;
    private String imgPath;
    private String description;
    private Date createDate;
    private Date updateDate;

    public Monster() {
    }

    public Monster(String name, String imgPath) {
        this.name = name;
        this.favorite = false;
        this.imgPath = imgPath;
        this.description = "Default description";
        this.createDate = new Date();
        this.updateDate = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCustomDate(Date date) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd, MMM yyyy");
        String dateString = formatDate.format(date);
        return dateString;
    }
    public String getCustomHour(Date date) {
        SimpleDateFormat formatDate = new SimpleDateFormat("hh:mm a");
        String dateString = formatDate.format(date);
        return dateString;
    }
}

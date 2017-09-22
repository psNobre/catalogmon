package com.example.ufc147nobre.myapplication.models;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by ufc147.nobre on 20/09/2017.
 */

public class Monster implements Serializable {

    private String name;
    private int imgId;
    private String id;
    private String description;

    public Monster(String name, int imgId) {
        this.name = name;
        this.imgId = imgId;
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgPath() {
        return imgId;
    }

    public void setImgPath(int imgPath) {
        this.imgId = imgPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}

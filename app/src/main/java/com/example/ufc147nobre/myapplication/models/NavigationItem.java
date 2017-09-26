package com.example.ufc147nobre.myapplication.models;

import java.io.Serializable;

/**
 * Created by ufc147.nobre on 26/09/2017.
 */

public class NavigationItem implements Serializable {

    private int iconId;
    private String name;

    public NavigationItem(int iconId, String name) {
        this.iconId = iconId;
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

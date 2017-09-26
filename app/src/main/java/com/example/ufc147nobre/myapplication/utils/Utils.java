package com.example.ufc147nobre.myapplication.utils;

import android.app.Activity;
import android.graphics.Matrix;
import android.widget.ImageView;

import com.example.ufc147nobre.myapplication.R;
import com.example.ufc147nobre.myapplication.models.NavigationItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ufc147.nobre on 21/09/2017.
 */

public class Utils {

    public static void makeImageViewTopCrop(ImageView image, Activity activity){
        final Matrix matrix = image.getImageMatrix();
        final float imageWidth = image.getDrawable().getIntrinsicWidth();
        final int screenWidth = activity.getResources().getDisplayMetrics().widthPixels;
        final float scaleRatio = screenWidth / imageWidth;
        matrix.postScale(scaleRatio, scaleRatio);
        image.setImageMatrix(matrix);
    }

    public static List<NavigationItem> getNavList(){
        List<NavigationItem> navigationItems = new ArrayList<>();
        navigationItems.add(new NavigationItem(R.drawable.ic_home_black_24dp, "Home"));
        navigationItems.add(new NavigationItem(R.drawable.ic_star_black_24dp, "Favorites"));
        navigationItems.add(new NavigationItem(R.drawable.ic_help_outline_black_24dp, "Help"));

        return navigationItems;
    }
}

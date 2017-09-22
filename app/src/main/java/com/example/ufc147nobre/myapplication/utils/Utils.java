package com.example.ufc147nobre.myapplication.utils;

import android.app.Activity;
import android.graphics.Matrix;
import android.widget.ImageView;

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
}

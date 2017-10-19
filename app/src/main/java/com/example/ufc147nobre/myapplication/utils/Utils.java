package com.example.ufc147nobre.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.widget.ImageView;

import com.example.ufc147nobre.myapplication.R;
import com.example.ufc147nobre.myapplication.models.Monster;
import com.example.ufc147nobre.myapplication.models.NavigationItem;
import com.example.ufc147nobre.myapplication.persistence.DataBaseController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

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

    public static void initDB(Context context) {
        DataBaseController dataBaseController = new DataBaseController(context);

        Monster monster1 = new Monster("Cthulhu", String.valueOf(R.drawable.cthulhu));
        Monster monster2 = new Monster("Dagon", String.valueOf(R.drawable.dagon));
        Monster monster3 = new Monster("Jormungandr", String.valueOf(R.drawable.jormungandr));
        Monster monster4 = new Monster("Fenrir", String.valueOf(R.drawable.fenrir));
        Monster monster5 = new Monster("Manticore", String.valueOf(R.drawable.manticore));

        dataBaseController.insertMonster(monster1);
        dataBaseController.insertMonster(monster2);
        dataBaseController.insertMonster(monster3);
        dataBaseController.insertMonster(monster4);
        dataBaseController.insertMonster(monster5);
    }

    public static String httpPost(Monster monster){
        URL url;
        String response = "";

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("id", monster.getId());
            jsonObject.put("name", monster.getName());
            jsonObject.put("favorite", monster.isFavorite());
            jsonObject.put("imgPath", monster.getImgPath());
            jsonObject.put("description", monster.getDescription());
            jsonObject.put("createDate", monster.getCreateDate());
            jsonObject.put("updateDate", monster.getUpdateDate());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            url = new URL("http://136.166.96.168:9200/catalogmon/monster/");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");

            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(jsonObject.toString());
            wr.flush();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response += line;
                }
            }
            else {
                response="";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

}

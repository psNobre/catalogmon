package com.example.ufc147nobre.myapplication.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ufc147nobre.myapplication.R;
import com.example.ufc147nobre.myapplication.models.Monster;
import com.example.ufc147nobre.myapplication.utils.Utils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ufc147.nobre on 20/09/2017.
 */

public class CustomAdapter extends BaseAdapter {

    private final List<Monster> monsters;
    private final Activity activity;

    public CustomAdapter(List<Monster> monsters, Activity activity) {
        this.monsters = monsters;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return monsters.size();
    }

    @Override
    public Object getItem(int position) {
        return monsters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.cardview_item, parent, false);

        Monster monster = monsters.get(position);

        TextView name = (TextView)
                view.findViewById(R.id.monster_name2);
        TextView description = (TextView)
                view.findViewById(R.id.monster_description2);
        ImageView image = (ImageView)
                view.findViewById(R.id.monster_image2);

        name.setText(monster.getName());
        description.setText(monster.getId());

        image.setImageResource(monster.getImgPath());
        Utils.makeImageViewTopCrop(image,activity);

        return view;
    }


}

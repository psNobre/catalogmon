package com.example.ufc147nobre.myapplication.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ufc147nobre.myapplication.R;
import com.example.ufc147nobre.myapplication.activities.MainActivity;
import com.example.ufc147nobre.myapplication.models.Monster;
import com.example.ufc147nobre.myapplication.persistence.DataBaseController;
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

        final Monster monster = monsters.get(position);

        TextView name = (TextView)
                view.findViewById(R.id.monster_name);
        TextView date = (TextView)
                view.findViewById(R.id.monster_date);
        TextView hour = (TextView)
                view.findViewById(R.id.monster_hour);
        ImageView image = (ImageView)
                view.findViewById(R.id.monster_image);
        ImageView favoriteImg = (ImageView)
                view.findViewById(R.id.monster_favorite);

        favoriteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!monster.isFavorite()){
                    monster.setFavorite(true);
                }else {
                    monster.setFavorite(false);
                }

                DataBaseController dataBaseController = new DataBaseController(activity);
                dataBaseController.updateMonster(monster);

                notifyDataSetChanged();
            }
        });

        name.setText(monster.getName());
        date.setText(monster.getCustomDate(monster.getUpdateDate()));
        hour.setText(monster.getCustomHour(monster.getUpdateDate()));

        if (monster.isFavorite()){
            favoriteImg.setBackgroundResource(R.drawable.ic_star_black_24dp);
            favoriteImg.setBackgroundTintList(activity.getResources().getColorStateList(R.color.yellow));
        }else {
            favoriteImg.setBackgroundResource(R.drawable.ic_star_border_black_24dp);
        }

        image.setImageResource(Integer.valueOf(monster.getImgPath()));
        Utils.makeImageViewTopCrop(image,activity);

        return view;
    }


}

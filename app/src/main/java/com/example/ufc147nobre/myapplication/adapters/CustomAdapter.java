package com.example.ufc147nobre.myapplication.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ufc147nobre.myapplication.R;
import com.example.ufc147nobre.myapplication.activities.MainActivity;
import com.example.ufc147nobre.myapplication.activities.ScrollingActivity;
import com.example.ufc147nobre.myapplication.models.Monster;
import com.example.ufc147nobre.myapplication.persistence.DataBaseController;
import com.example.ufc147nobre.myapplication.utils.Utils;

import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ufc147.nobre on 20/09/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<Monster> monsters;
    private final Activity activity;

    public CustomAdapter(List<Monster> monsters, Activity activity) {
        this.monsters = monsters;
        this.activity = activity;
    }

    public void reloadList(List<Monster> monsters){
        this.monsters = monsters;
        notifyDataSetChanged();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Monster monster = monsters.get(position);

        holder.favoriteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!monster.isFavorite()){
                    monster.setFavorite(true);
                    monster.setUpdateDate(new Date());
                    Toast.makeText(activity, monster.getName()+" is favorite now.", Toast.LENGTH_SHORT).show();
                }else {
                    monster.setFavorite(false);
                }

                DataBaseController dataBaseController = new DataBaseController(activity);
                dataBaseController.updateMonster(monster);

                notifyItemChanged(position);
            }
        });

        holder.name.setText(monster.getName());
        holder.date.setText(monster.getCustomDate(monster.getUpdateDate()));
        holder.hour.setText(monster.getCustomHour(monster.getUpdateDate()));

        if (monster.isFavorite()){
            holder.favoriteImg.setBackgroundResource(R.drawable.ic_star_black_24dp);
            holder.favoriteImg.setBackgroundTintList(activity.getResources().getColorStateList(R.color.yellow));
        }else {
            holder.favoriteImg.setBackgroundResource(R.drawable.ic_star_border_black_24dp);
            holder.favoriteImg.setBackgroundTintList(activity.getResources().getColorStateList(R.color.white));
        }

        Glide.with(activity).load(monster.getImgPath()).centerCrop().into(holder.image);
    }

    @Override
    public long getItemId(int position) {
       return (long) monsters.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return monsters.size();
    }

    @Override
    public void onViewRecycled(MyViewHolder holder) {
        super.onViewRecycled(holder);
        Glide.clear(holder.image);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView date;
        TextView hour;
        ImageView image;
        ImageView favoriteImg;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = (TextView)
                    itemView.findViewById(R.id.monster_name);
            date = (TextView)
                    itemView.findViewById(R.id.monster_date);
            hour = (TextView)
                    itemView.findViewById(R.id.monster_hour);
            image = (ImageView)
                    itemView.findViewById(R.id.monster_image);
            favoriteImg = (ImageView)
                    itemView.findViewById(R.id.monster_favorite);
        }

        final Intent intent = new Intent(activity, ScrollingActivity.class);

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            intent.putExtra("monster", monsters.get(position));
            activity.startActivity(intent);
        }
    }
}

package com.example.ufc147nobre.myapplication.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ufc147nobre.myapplication.R;
import com.example.ufc147nobre.myapplication.models.NavigationItem;

import java.util.List;

/**
 * Created by ufc147.nobre on 26/09/2017.
 */

public class NavigationAdapter extends BaseAdapter {

    final private List<NavigationItem> navigationItems;
    final private Activity activity;

    public NavigationAdapter(List<NavigationItem> navigationItems, Activity activity) {
        this.navigationItems = navigationItems;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return navigationItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navigationItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.navigation_item, parent, false);

        ImageView icon = (ImageView) view.findViewById(R.id.icon_nav);
        TextView name = (TextView) view.findViewById(R.id.name_nav);

        NavigationItem navigationItem = navigationItems.get(position);

        name.setText(navigationItem.getName());
        icon.setImageResource(navigationItem.getIconId());

        return view;
    }
}

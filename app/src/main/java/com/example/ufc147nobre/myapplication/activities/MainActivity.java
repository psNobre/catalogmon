package com.example.ufc147nobre.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ufc147nobre.myapplication.R;
import com.example.ufc147nobre.myapplication.adapters.CustomAdapter;
import com.example.ufc147nobre.myapplication.adapters.NavigationAdapter;
import com.example.ufc147nobre.myapplication.models.Monster;
import com.example.ufc147nobre.myapplication.models.NavigationItem;
import com.example.ufc147nobre.myapplication.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ListView navListView;
    private ImageView navMenu;
    private CustomAdapter adapter;
    private NavigationAdapter navigationAdapter;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listView = (ListView) findViewById(R.id.listView);

        navMenu = (ImageView) findViewById(R.id.nav_menu);
        navListView = (ListView) findViewById(R.id.left_drawer);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final List<Monster> monsters = getMonstersList();
        final List<NavigationItem> navigationItems = Utils.getNavList();

        adapter = new CustomAdapter(monsters, this);
        listView.setAdapter(adapter);

        navigationAdapter = new NavigationAdapter(navigationItems, this);
        navListView.setAdapter(navigationAdapter);

        final Intent intent = new Intent(this, ScrollingActivity.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("monster", monsters.get(position));
                startActivity(intent);
            }
        });

        navMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        navListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerLayout.closeDrawer(Gravity.LEFT);

                if (navigationItems.get(position).getIconId() == R.drawable.ic_home_black_24dp){
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_LONG).show();

                } else if (navigationItems.get(position).getIconId() == R.drawable.ic_star_black_24dp){
                    Toast.makeText(MainActivity.this, "Favoritos", Toast.LENGTH_LONG).show();

                } else if (navigationItems.get(position).getIconId() == R.drawable.ic_help_outline_black_24dp){
                    Toast.makeText(MainActivity.this, "Help", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private List<Monster> getMonstersList(){
        Monster monster = new Monster("Cthulhu", R.drawable.cthulhu);
        Monster monster2 = new Monster("Dagon", R.drawable.dagon);
        Monster monster3 = new Monster("Jormungandr", R.drawable.jormungandr);
        Monster monster4 = new Monster("Fenrir", R.drawable.fenrir);
        Monster monster5 = new Monster("Manticore", R.drawable.manticore);
        List<Monster> monsters = new ArrayList<>();
        monsters.add(monster);
        monsters.add(monster2);
        monsters.add(monster3);
        monsters.add(monster4);
        monsters.add(monster5);

        return monsters;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.version:
                Toast.makeText(MainActivity.this, "Version 1.0.0", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
}

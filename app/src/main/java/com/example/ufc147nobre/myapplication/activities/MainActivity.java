package com.example.ufc147nobre.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.ufc147nobre.myapplication.persistence.DataBaseController;
import com.example.ufc147nobre.myapplication.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListView navListView;
    private ImageView navMenu;
    private CustomAdapter adapter;
    private NavigationAdapter navigationAdapter;
    private DrawerLayout drawerLayout;
    private FloatingActionButton floatingActionButton;

    private List<Monster> monsters;
    private List<Monster> favoriteMonster;

    DataBaseController dataBaseController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        dataBaseController = new DataBaseController(getBaseContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView = (RecyclerView) findViewById(R.id.listView);

        navMenu = (ImageView) findViewById(R.id.nav_menu);
        navListView = (ListView) findViewById(R.id.left_drawer);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_button);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (dataBaseController.loadMonsters() != null){
            monsters = dataBaseController.loadMonsters();
        }else {
            monsters = new ArrayList<>();
        }

        final List<NavigationItem> navigationItems = Utils.getNavList();

        adapter = new CustomAdapter(monsters, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        navigationAdapter = new NavigationAdapter(navigationItems, this);
        navListView.setAdapter(navigationAdapter);

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
                    adapter.reloadList(monsters);

                } else if (navigationItems.get(position).getIconId() == R.drawable.ic_star_black_24dp){
                    favoriteMonster = getFavoriteMonsters(monsters);
                    adapter.reloadList(favoriteMonster);

                } else if (navigationItems.get(position).getIconId() == R.drawable.ic_help_outline_black_24dp){
                    Toast.makeText(MainActivity.this, "Help", Toast.LENGTH_LONG).show();

                }
            }
        });

        final Intent createMonster = new Intent(this, RegisterMonsterActivity.class);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(createMonster);
            }
        });
    }

    private List<Monster> getFavoriteMonsters(List<Monster> monsters) {
        List<Monster> favoriteMonsters = new ArrayList<>();

        for (Monster monster: monsters) {
            if (monster.isFavorite()){
                favoriteMonsters.add(monster);
            }
        }

        return favoriteMonsters;
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
                Utils.initDB(this);
                break;
        }
        return true;
    }

}

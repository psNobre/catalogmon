package com.example.ufc147nobre.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ufc147nobre.myapplication.R;
import com.example.ufc147nobre.myapplication.adapters.CustomAdapter;
import com.example.ufc147nobre.myapplication.models.Monster;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final List<Monster> monsters = getMonstersList();

        adapter = new CustomAdapter(monsters, this);
        listView.setAdapter(adapter);

        final Intent intent = new Intent(this, ScrollingActivity.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("monster", monsters.get(position));
                startActivity(intent);
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
}

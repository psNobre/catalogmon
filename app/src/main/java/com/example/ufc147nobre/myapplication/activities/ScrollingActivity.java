package com.example.ufc147nobre.myapplication.activities;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ufc147nobre.myapplication.R;
import com.example.ufc147nobre.myapplication.models.Monster;
import com.example.ufc147nobre.myapplication.utils.Utils;

public class ScrollingActivity extends AppCompatActivity {

    private ImageView imageView;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    private Monster monster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        imageView = (ImageView) findViewById(R.id.imageView);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        getWindow().setStatusBarColor(Color.TRANSPARENT);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ScrollingActivity.super.onBackPressed();
                }
            });
        }

        monster = (Monster) getIntent().getExtras().getSerializable("monster");

        collapsingToolbarLayout.setTitle(monster.getName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Glide.with(this).load(monster.getImgPath()).centerCrop().into(imageView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Glide.clear(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit_monster:
                Toast.makeText(this, "Edit Mode", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}

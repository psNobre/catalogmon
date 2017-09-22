package com.example.ufc147nobre.myapplication.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

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
        imageView.setImageResource(monster.getImgId());
    }
}

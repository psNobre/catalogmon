package com.example.ufc147nobre.myapplication.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.ufc147nobre.myapplication.R;
import com.example.ufc147nobre.myapplication.models.Monster;
import com.example.ufc147nobre.myapplication.persistence.DataBaseController;
import com.example.ufc147nobre.myapplication.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RegisterMonsterActivity extends AppCompatActivity {

    private final int PICK_PHOTO_FOR_MONSTER = 1;

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imageView;
    private DataBaseController dataBaseController;
    private EditText nameMonster;
    private EditText descriptionMonster;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_monster);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        imageView = (ImageView) findViewById(R.id.default_image_view);

        imageView.setDrawingCacheEnabled(true);

        nameMonster = (EditText) findViewById(R.id.edit_monster_name);
        descriptionMonster = (EditText) findViewById(R.id.edit_monster_description);
        dataBaseController = new DataBaseController(this);

        getWindow().setStatusBarColor(Color.TRANSPARENT);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {

            toolbar.setTitle("New Monster");

            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RegisterMonsterActivity.super.onBackPressed();
                }
            });
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.save_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
            }
        });

        Glide.with(this).load(R.drawable.default_profile).centerCrop().into(imageView);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Glide.clear(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_register_monster, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.done:
                saveMonster();
                break;
        }
        return true;
    }

    private void saveMonster() {
        String name = nameMonster.getText().toString();
        String description = descriptionMonster.getText().toString();

        OutputStream output;
        File filepath = Environment.getExternalStorageDirectory();

        File dir = new File(filepath.getAbsolutePath()
                + "/catalogmon/monsters");
        dir.mkdirs();

        File file = new File(dir, name+".jpg");

        try {
            output = new FileOutputStream(file);

            imageView.getDrawingCache().compress(Bitmap.CompressFormat.JPEG, 100, output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Monster monster = new Monster(name, file.getAbsolutePath());
        monster.setDescription(description);


        if (!name.equals("") &&!description.equals("")){
            dataBaseController.insertMonster(monster);
            Toast.makeText(RegisterMonsterActivity.this, "Save Monster", Toast.LENGTH_SHORT).show();
            onBackPressed();

        }else {
            new AlertDialog.Builder(RegisterMonsterActivity.this)
                    .setTitle("Monster fileds are null")
                    .setMessage("Please, fill name and description of the monster.")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show();
        }

    }

    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_MONSTER);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PHOTO_FOR_MONSTER && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return;
            }

            Uri uri = data.getData();

            progressBar.setVisibility(View.VISIBLE);

            Glide.with(this).load(uri).listener(new RequestListener<Uri, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }
            }).centerCrop().into(imageView);
        }
    }
}

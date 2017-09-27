package com.example.ufc147nobre.myapplication.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ufc147nobre.myapplication.models.Monster;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ufc147.nobre on 27/09/2017.
 */

public class DataBaseController {

    private SQLiteDatabase db;
    private DataBaseHelper dataBase;

    public DataBaseController(Context context){
        dataBase = new DataBaseHelper(context);
    }

    public List<Monster> loadMonsters(){
        Cursor cursor;

        String[] projections = {DataBaseHelper.ID,DataBaseHelper.MONSTER_NAME,DataBaseHelper.MONSTER_IMAGE_PATH,DataBaseHelper.MONSTER_FAVORITE,
                DataBaseHelper.MONSTER_DESCRIPTION, DataBaseHelper.MONSTER_CREATE_DATE,DataBaseHelper.MONSTER_UPDATE_DATE};

        db = dataBase.getReadableDatabase();

        cursor = db.query(DataBaseHelper.TABLE, projections, null, null, null, null, null, null);

        List<Monster> monsters = new ArrayList<>();
        Monster monster;

        if(cursor!=null){
            cursor.moveToFirst();
        }

        while(!cursor.isAfterLast()) {
            monster = new Monster();

            monster.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.ID)));
            monster.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.MONSTER_NAME)));

            monster.setFavorite(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.MONSTER_FAVORITE))== 1);

            monster.setImgPath(cursor.getString(cursor.getColumnIndex(DataBaseHelper.MONSTER_IMAGE_PATH)));
            monster.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseHelper.MONSTER_DESCRIPTION)));

            Date createDate = new Date(Long.parseLong(cursor.getString(cursor.getColumnIndex(DataBaseHelper.MONSTER_CREATE_DATE))));
            Date updateDate = new Date(Long.parseLong(cursor.getString(cursor.getColumnIndex(DataBaseHelper.MONSTER_UPDATE_DATE))));

            monster.setCreateDate(createDate);
            monster.setUpdateDate(updateDate);
            monsters.add(monster);

            cursor.moveToNext();
        }

        db.close();

        return monsters;
    }

    public String insertMonster(Monster monster){
        ContentValues values;
        long result;

        db = dataBase.getWritableDatabase();
        values = new ContentValues();
        values.put(DataBaseHelper.MONSTER_NAME, monster.getName());
        values.put(DataBaseHelper.MONSTER_FAVORITE, monster.isFavorite());
        values.put(DataBaseHelper.MONSTER_IMAGE_PATH, monster.getImgPath());
        values.put(DataBaseHelper.MONSTER_DESCRIPTION, monster.getDescription());
        values.put(DataBaseHelper.MONSTER_CREATE_DATE, monster.getCreateDate().getTime());
        values.put(DataBaseHelper.MONSTER_UPDATE_DATE, monster.getUpdateDate().getTime());

        result = db.insert(DataBaseHelper.TABLE, null, values);
        db.close();

        if (result == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public void updateMonster(Monster monster){
        ContentValues values;
        String where;

        db = dataBase.getWritableDatabase();

        where = DataBaseHelper.ID + "=" + monster.getId();

        values = new ContentValues();
        values.put(DataBaseHelper.MONSTER_NAME, monster.getName());
        values.put(DataBaseHelper.MONSTER_FAVORITE, monster.isFavorite());
        values.put(DataBaseHelper.MONSTER_IMAGE_PATH, monster.getImgPath());
        values.put(DataBaseHelper.MONSTER_DESCRIPTION, monster.getDescription());
        values.put(DataBaseHelper.MONSTER_CREATE_DATE, monster.getCreateDate().getTime());
        values.put(DataBaseHelper.MONSTER_UPDATE_DATE, monster.getUpdateDate().getTime());

        db.update(DataBaseHelper.TABLE, values, where, null);
        db.close();
    }

    public void deleteMonster(int id){
        String where = DataBaseHelper.ID + "=" + id;
        db = dataBase.getReadableDatabase();
        db.delete(DataBaseHelper.TABLE,where,null);
        db.close();
    }

}

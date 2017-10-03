package com.example.ufc147nobre.myapplication.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ufc147.nobre on 27/09/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATA_BASE_NAME = "catalogmon.db";
    private static final int VERSION = 1;

    public static final String TABLE = "monsters";

    public static final String ID = "_id";
    public static final String MONSTER_NAME = "name";
    public static final String MONSTER_FAVORITE = "favorite";
    public static final String MONSTER_IMAGE_PATH = "image_path";
    public static final String MONSTER_DESCRIPTION = "description";
    public static final String MONSTER_CREATE_DATE = "create_date";
    public static final String MONSTER_UPDATE_DATE = "update_date";

    public DataBaseHelper(Context context) {
        super(context, DATA_BASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABLE+"("
                + ID + " integer primary key autoincrement,"
                + MONSTER_NAME + " TEXT,"
                + MONSTER_FAVORITE + " BOOLEAN,"
                + MONSTER_IMAGE_PATH + " TEXT,"
                + MONSTER_DESCRIPTION + " TEXT,"
                + MONSTER_CREATE_DATE + " DATETIME,"
                + MONSTER_UPDATE_DATE + " DATETIME"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}

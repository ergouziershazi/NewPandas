package com.newpandas.model.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/7/20.
 */

public class MSql extends SQLiteOpenHelper {
    public MSql(Context context) {
        super(context,"ipanda",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table panda (collect int, imageurl varchar(300),movietime varchar(20),moviename varchar(50),moviedate varchar(20),movieurl varchar(300))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

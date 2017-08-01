package com.newpandas.model.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.newpandas.app.App;

import java.util.ArrayList;

/**
 * Created by 联想 on 2017/7/20.
 */

public class SqlUtils {

    private final MSql mSql;
    private static SqlUtils utils;
    private ArrayList<SqliteBean> list = new ArrayList<SqliteBean>();

    private SqlUtils() {
        mSql = new MSql(App.context);
    }

    public static SqlUtils getInstance() {
        if (utils == null) {
            synchronized (SqlUtils.class) {
                if (utils == null) {
                     utils = new SqlUtils();
                }
            }
        }
        return utils;
    }

    public void add(int collect,String imageurl,String movietime,String moviename,String moviedate,String movieurl) {
        SQLiteDatabase db = mSql.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("collect", collect);
        values.put("imageurl",imageurl);
        values.put("movietime",movietime);
        values.put("moviename",moviename);
        values.put("moviedate",moviedate);
        values.put("movieurl",movieurl);

        db.insert("panda", null, values);
        db.close();
    }

    public void delete(String moviename) {
        SQLiteDatabase db = mSql.getReadableDatabase();
        db.delete("panda", "moviename=?", new String[]{moviename});
        db.close();
    }

    public ArrayList<SqliteBean> query() {
        SQLiteDatabase db = mSql.getReadableDatabase();
        Cursor cursor = db.query("panda", null, null, null, null, null, null);
        list.clear();
        while (cursor.moveToNext()) {
            int collect = cursor.getInt(cursor.getColumnIndex("collect"));
            String iamgeurl = cursor.getString(cursor.getColumnIndex("imageurl"));
            String movietime = cursor.getString(cursor.getColumnIndex("movietime"));
            String moviename = cursor.getString(cursor.getColumnIndex("moviename"));
            String moviedate = cursor.getString(cursor.getColumnIndex("moviedate"));
            String movieurl = cursor.getString(cursor.getColumnIndex("movieurl"));
            SqliteBean bb = new SqliteBean();
            bb.setCollect(collect);
            bb.setImageurl(iamgeurl);
            bb.setMovietime(movietime);
            bb.setMoviename(moviename);
            bb.setMoviedate(moviedate);
            bb.setMovieurl(movieurl);
            Log.d("SqlUtils", moviedate + "=========" + moviename);
            list.add(bb);
        }
        cursor.close();
        db.close();
        return list;
    }
}

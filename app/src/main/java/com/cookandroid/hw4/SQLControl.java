package com.cookandroid.hw4;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLControl {
    myDBHelper helper;
    SQLiteDatabase sqLiteDatabase;

    public SQLControl(myDBHelper Helper){
        this.helper = Helper;
    }

    public void insert(String movieName, Integer likeNum){
        sqLiteDatabase = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("movieName", movieName);
        values.put("likeNum", likeNum);

        sqLiteDatabase.insert("voteMovie", null, values);
    }

    public String select(int pos) {
        sqLiteDatabase = helper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("voteMovie", null, null, null, null, null, null, null);

        String data;

        cursor.moveToPosition(pos);
        data= cursor.getString(cursor.getColumnIndex("likeNum"));
        cursor.close();
        return data;
    }

    public void update(String movieName, String likeNum){
        Integer a = Integer.parseInt(likeNum);
        sqLiteDatabase = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("likeNum", a+1);
        sqLiteDatabase.update("voteMovie",values,"movieName=?", new String[]{movieName});
    }

}


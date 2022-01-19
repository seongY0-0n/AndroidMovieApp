package com.cookandroid.hw4;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class myDBHelper extends SQLiteOpenHelper {
    public myDBHelper(Context context) {
        super(context, "voteMovie", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("데이터베이스실습앱", "MyDBHelper: onCreate() called");
        sqLiteDatabase.execSQL("CREATE TABLE voteMovie(movieName CHAR(10) PRIMARY KEY, likeNum INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i("데이터베이스실습앱", "MyDBHelper: onUpgrade() called");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS voteMovie");
        onCreate(sqLiteDatabase);
    }
}

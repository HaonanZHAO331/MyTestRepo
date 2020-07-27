package com.example.sqlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "user_data.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "user_info";

    public UserDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //String sql = "DROP TABLE IF EXISTS" + TABLE_NAME + ";";
        String create_sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(Id integer primary key autoincrement, Name char(10), Age char(10), Height char(10), Weight char(10))";
        sqLiteDatabase.execSQL(create_sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String upgrade_sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(upgrade_sql);
        onCreate(sqLiteDatabase);
    }
}

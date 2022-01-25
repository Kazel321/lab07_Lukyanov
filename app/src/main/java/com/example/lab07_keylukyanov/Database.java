package com.example.lab07_keylukyanov;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE my_test (my_key TEXT, my_value TEXT)";
        db.execSQL(sql);
    }

    public void do_insert(String key, String value)
    {
        String sql = "INSERT INTO my_test VALUES('" + key + "','" + value + "';";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public String do_select(String key)
    {
        String sql = "SELECT my_value FROM my_test WHERE my_key = '" + key + "';";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery(sql, null);

        if (cur.moveToFirst() == true)
            return cur.getString(0);

        return "(!) not found";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}

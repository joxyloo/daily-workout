package com.example.user.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


/**
 * Created by Risky-PC on 26/12/2017.
 */

public class DbCenter {
    Cursor cursor;
    Helper dbcenter;
    public ArrayList<String> getData(Context context, String query){
        ArrayList<String> Data = new ArrayList<>();
        dbcenter = new Helper(context);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            Data.add(cursor.getString(0));
        }
        return Data;
    }

    public String getDataString(Context context, String query){
        String Data;
        Cursor cursor;
        Helper dbcenter;
        dbcenter = new Helper(context);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        Data=cursor.getString(0);
        return Data;
    }


}

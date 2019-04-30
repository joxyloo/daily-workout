package com.example.user.myapplication;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class Helper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "workoutDb.db";
    private static final int DATABASE_VERSION = 1;

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
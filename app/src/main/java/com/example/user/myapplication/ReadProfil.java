package com.example.user.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReadProfil extends AppCompatActivity {

    protected Cursor cursor;
    Helper dbHelper;
    Button btn1;
    TextView text1, text2, text3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_profil);

        dbHelper = new Helper(this);
        text1 = (TextView) findViewById(R.id.textViewname);
        text2 = (TextView) findViewById(R.id.textViewWeight);
        text3 = (TextView) findViewById(R.id.textViewHeight);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM table_profil WHERE name = '"+ getIntent().getStringExtra("name")+"'",null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
        // Log.d("readdd", "onCreate: "+cursor.getColumnNames());

        }

    }
}

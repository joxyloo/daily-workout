package com.example.user.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateProfil extends AppCompatActivity {

    protected Cursor cursor;
    Helper dbHelper;
    Button btn1;
    EditText text1, text2, text3,text4;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbHelper = new Helper(this);
        text1 = (EditText) findViewById(R.id.editTextId);
        text2 = (EditText) findViewById(R.id.editTextName);
        text3 = (EditText) findViewById(R.id.editTextWeight);
        text4 = (EditText) findViewById(R.id.editTextHeight);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM table_profil WHERE name ='"+getIntent().getStringExtra("name")+"'",null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            name = cursor.getString(0).toString();
            text2.setText(name);
            text3.setText(cursor.getString(1).toString());
            text4.setText(cursor.getString(2).toString());

        }
        btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update table_profil set name='"+
                text2.getText().toString()+"',weight='"+
                text3.getText().toString()+"',height='"+
                text4.getText().toString()+"' where name='"+
                name+"'"
                );
            Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_LONG).show();
            MyProfilActivity.myProfilActivity.RefreshList();
            finish();
            }
        });

    }
}
//id_profil
package com.example.user.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateProfil extends AppCompatActivity {
    protected Cursor cursor;
    Helper dbHelper;
    Button button;
    EditText text1, text2, text3, text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profil);

        dbHelper = new Helper(this);
        text1 = (EditText) findViewById(R.id.editTextId);
        text2 = (EditText) findViewById(R.id.editTextName);
        text3 = (EditText) findViewById(R.id.editTextWeight);
        text4 = (EditText) findViewById(R.id.editTextHeight);
        button = (Button) findViewById(R.id.btn_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into table_profil(name, weight, height) values('"+
                        text2.getText().toString() + "','" +
                        text3.getText().toString() + "','" +
                        text4.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
//                MyProfilActivity.myProfilActivity.RefreshList();
                finish();
            }
        });

    }

}

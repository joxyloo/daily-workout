package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfilActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_myprofil, btn_reminder, btn_program;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        btn_myprofil = (Button) findViewById(R.id.btn_myprofil);
        btn_myprofil.setOnClickListener(this);

        btn_reminder = (Button) findViewById(R.id.btn_reminder);
        btn_reminder.setOnClickListener(this);

        btn_program= (Button) findViewById(R.id.btn_program);
        btn_program.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_myprofil:
                Intent my_profil = new Intent(ProfilActivity.this, MyProfilActivity.class);
                startActivity(my_profil);
                break;

            case R.id.btn_reminder:
                Intent reminder = new Intent(ProfilActivity.this, AlarmActivity.class);
                startActivity(reminder);
                break;

            case R.id.btn_program:
                Intent program = new Intent(ProfilActivity.this, pilihLatihan.class);
                startActivity(program);
                break;
        }
    }
}

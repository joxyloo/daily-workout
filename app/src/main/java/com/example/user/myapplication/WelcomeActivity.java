package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_startprogram, btn_tutorial, btn_profil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btn_startprogram = (Button) findViewById(R.id.btn_startprogram);
        btn_startprogram.setOnClickListener(this);

        btn_tutorial = (Button) findViewById(R.id.btn_tutorial);
        btn_tutorial.setOnClickListener(this);

        btn_profil = (Button) findViewById(R.id.btn_profil);
        btn_profil.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_startprogram :
                Intent startprogram = new Intent(WelcomeActivity.this, pilihLatihan.class);
                startActivity(startprogram);
                break;

            case R.id.btn_tutorial :
                Intent tutorial = new Intent(WelcomeActivity.this, TutorialActivity.class);
                tutorial.putExtra("from", "tutorial");
                startActivity(tutorial);
                break;

            case R.id.btn_profil :
                Intent profil = new Intent(WelcomeActivity.this, ProfilActivity.class);
                startActivity(profil);
                break;
        }
    }
}

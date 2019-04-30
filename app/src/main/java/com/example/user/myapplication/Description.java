package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class Description extends AppCompatActivity {

    private ImageView img;
    private TextView txt,txtTitle;
    private Workout workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        this.setTitle("Instruction");

        img = findViewById(R.id.img_description);
        txt = findViewById(R.id.txt_description);
        txtTitle=findViewById(R.id.txt_desTitle);

        workout = (Workout) getIntent().getSerializableExtra("workout");

        txt.setText(workout.getDescription());
        img.setImageResource(workout.getImg());
        txtTitle.setText(workout.getWorkoutName());





    }
}

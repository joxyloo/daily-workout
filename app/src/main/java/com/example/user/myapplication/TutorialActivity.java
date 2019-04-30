package com.example.user.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class TutorialActivity extends AppCompatActivity implements Serializable{

    WorkoutAdapter adapter;
    ArrayList<Workout> listWorkout = new ArrayList<>(); //from database
    RecyclerView recyclerView;
    SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
    String from = "", type = "";
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_workout);

        type = getIntent().getStringExtra("day");

        if (type.equals("abs") || type.equals("arms") || type.equals("thigh")){
            this.setTitle(type.toUpperCase() + " TRAINING");
        }
        else{
            this.setTitle("DAY " + type);
        }

        fab = findViewById(R.id.fabtn);

        //recycler view

        recyclerView = findViewById(R.id.recyclerView);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TutorialActivity.this);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(TutorialActivity.this, Description.class);
                intent.putExtra("workout", listWorkout.get(position));
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


        from = getIntent().getStringExtra("from");

        if(from.equals("programDay")) {

            ArrayList<Workout> workoutFromIntent = (ArrayList<Workout>) getIntent().getSerializableExtra("currentWorkout");
//            Toast.makeText(TutorialActivity.this, "Workout:" + listWorkout.size() + "day:" + getIntent().getStringExtra("day"), Toast.LENGTH_SHORT ).show();

            String day = getIntent().getStringExtra("day");
            if(day.equals("arms") || day.equals("abs") || day.equals("thigh") || day.equals("customise") ){

                listWorkout = workoutFromIntent;
            }
            else{

                for(int i = 0; i < workoutFromIntent.size(); i++){

                    if(day.equals("3") || day.equals("7")){
                        if((i == 0 || i % 3 == 0) && i< (workoutFromIntent.size()/2)){
                            listWorkout.add(workoutFromIntent.get(i));
                        }
                    }
                    else {
                        if (i == 0 || i % 3 == 0) {
                            listWorkout.add(workoutFromIntent.get(i));
                        }
                    }
                }

            }

        }

        adapter = new WorkoutAdapter(listWorkout);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TutorialActivity.this, CountDown.class);
                intent.putExtra("type",type);
                startActivity(intent);
            }
        });
    }

    public void onBackPressed() {
        listWorkout = new ArrayList<>();


    }

}

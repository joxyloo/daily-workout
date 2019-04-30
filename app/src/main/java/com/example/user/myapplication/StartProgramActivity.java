package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.invoke.WrongMethodTypeException;
import java.util.ArrayList;

public class StartProgramActivity extends AppCompatActivity  implements View.OnClickListener{

    private Button btn1, btn2,btn3, btn4, btn5, btn6, btn7;
    private ArrayList<Workout> workoutList;
    private int interval = 0;
    private String type = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_program);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
    }

    public void onClick(View view) {

        switch (view.getId()){

            case(R.id.btn1):

                type = "1";
                getData();
                Intent intent1 = new Intent(StartProgramActivity.this, TutorialActivity.class);
//                intent1.putExtra("type", "1");
                intent1.putExtra("day",type);
                intent1.putExtra("from", "programDay");
                intent1.putExtra("currentWorkout", workoutList);
               // Toast  .makeText(StartProgramActivity.this, "size" + workoutList.size(), Toast.LENGTH_SHORT).show();

                startActivity(intent1);
                break;

            case(R.id.btn2):
                type = "2";
                getData();
                Intent intent2 = new Intent(StartProgramActivity.this, TutorialActivity.class);
                intent2.putExtra("day",type);
                intent2.putExtra("from", "programDay");
                intent2.putExtra("currentWorkout", workoutList);
                startActivity(intent2);
                break;

            case(R.id.btn3):
                type = "3";
                getData();
                Intent intent3 = new Intent(StartProgramActivity.this, TutorialActivity.class);
                intent3.putExtra("day",type);
                intent3.putExtra("from", "programDay");
                intent3.putExtra("currentWorkout", workoutList);
                startActivity(intent3);
                break;

            case(R.id.btn4):
                type = "4";
                getData();
                Intent intent4 = new Intent(StartProgramActivity.this, TutorialActivity.class);
                intent4.putExtra("day",type);
                intent4.putExtra("from", "programDay");
                intent4.putExtra("currentWorkout", workoutList);
                startActivity(intent4);
                break;

            case(R.id.btn5):
                type = "5";
                getData();
                Intent intent5 = new Intent(StartProgramActivity.this, TutorialActivity.class);
                intent5.putExtra("day",type);
                intent5.putExtra("from", "programDay");
                intent5.putExtra("currentWorkout", workoutList);
                startActivity(intent5);
                break;

            case(R.id.btn6):
                type = "6";
                getData();
                Intent intent6 = new Intent(StartProgramActivity.this, TutorialActivity.class);
                intent6.putExtra("day",type);
                intent6.putExtra("from", "programDay");
                intent6.putExtra("currentWorkout", workoutList);
                startActivity(intent6);
                break;

            case(R.id.btn7):
                type = "7";
                getData();
                Intent intent7 = new Intent(StartProgramActivity.this, TutorialActivity.class);
                intent7.putExtra("day",type);
                intent7.putExtra("from", "programDay");
                intent7.putExtra("currentWorkout", workoutList);
                startActivity(intent7);
                break;

        }

    }

    public ArrayList<Workout> getData(){

        workoutList = new ArrayList<>();

        ArrayList<String> workoutName =  new ArrayList<>(), imgStr = new ArrayList<>(), description = new ArrayList<>();
        ArrayList<Integer> imgRes = new ArrayList<>();

        if (type.equals("1")) { //10x3, total 15 min
            workoutName = new DbCenter().getData(StartProgramActivity.this, "select workout_name from table_workout where id in (1,3,5,11,13,14,21,22,23,25)");
            imgStr = new DbCenter().getData(StartProgramActivity.this, "select image from table_workout where id in (1,3,5,11,13,14,21,22,23,25)");
            description = new DbCenter().getData(StartProgramActivity.this, "select description from table_workout where id in (1,3,5,11,13,14,21,22,23,25)");
        }
         else if (type.equals("2")) { //abs, 5x3, tottal 7.5 min
            workoutName = new DbCenter().getData(StartProgramActivity.this, "select workout_name from table_workout where id in (1,3,2,5,7)");
            imgStr = new DbCenter().getData(StartProgramActivity.this, "select image from table_workout where id in (1,3,2,5,7)");
            description = new DbCenter().getData(StartProgramActivity.this, "select description from table_workout where id in (1,3,2,5,7)");
        } else if (type.equals("3")) { //10x3x2, total 30 min
            workoutName = new DbCenter().getData(StartProgramActivity.this, "select workout_name from table_workout where id in (12,28,7,24,13,11,26,25,23,8)");
            imgStr = new DbCenter().getData(StartProgramActivity.this, "select image from table_workout where id in (12,28,7,24,13,11,26,25,23,8)");
            description = new DbCenter().getData(StartProgramActivity.this, "select description from table_workout where id in (12,28,7,24,13,11,26,25,23,8)");
        } else if (type.equals("4")) { //arm, 5x3, tottal 7.5 min
            workoutName = new DbCenter().getData(StartProgramActivity.this, "select workout_name from table_workout where id in (18,17,19,16,15)");
            imgStr = new DbCenter().getData(StartProgramActivity.this, "select image from table_workout where id in (18,17,19,16,15)");
            description = new DbCenter().getData(StartProgramActivity.this, "select description from table_workout where id in (18,17,19,16,15)");
        } else if (type.equals("5")) {  //10x3, total 15 min
            workoutName = new DbCenter().getData(StartProgramActivity.this, "select workout_name from table_workout where id in (28,11,29,14,23,9,10,18,21,22)");
            imgStr = new DbCenter().getData(StartProgramActivity.this, "select image from table_workout where id in (28,11,29,14,23,9,10,18,21,22)");
            description = new DbCenter().getData(StartProgramActivity.this, "select description from table_workout where id in (28,11,29,14,23,9,10,18,21,22)");
        } else if (type.equals("6")) { //thigh, 5x3, tottal 7.5 min
            workoutName = new DbCenter().getData(StartProgramActivity.this, "select workout_name from table_workout where id in (28,29,30,23,26)");
            imgStr = new DbCenter().getData(StartProgramActivity.this, "select image from table_workout where id in (28,29,30,23,26)");
            description = new DbCenter().getData(StartProgramActivity.this, "select description from table_workout where id in (28,29,30,23,26)");
        } else if (type.equals("7")) { //10x3x2, total 30 min
            workoutName = new DbCenter().getData(StartProgramActivity.this, "select workout_name from table_workout where id in (");
            imgStr = new DbCenter().getData(StartProgramActivity.this, "select image from table_workout where id in (12,28,30,25,7,8,24,27,4,5)");
            description = new DbCenter().getData(StartProgramActivity.this, "select description from table_workout where id i12,28,30,25,7,8,24,27,4,5)n (12,28,30,25,7,8,24,27,4,5)");
        }


        for (int i = 0; i < imgStr.size(); i++) {
            imgRes.add(getResources().getIdentifier(imgStr.get(i), "drawable", getPackageName()));
        }

        for (int i = 0; i < workoutName.size(); i++) {

            Workout workout = new Workout(i, workoutName.get(i), imgRes.get(i), description.get(i));


                workoutList.add(workout);
                workoutList.add(workout);
                workoutList.add(workout);

        }

        if (type.equals("3") || type.equals("7")) {
            int size = workoutList.size();
            for (int j = 0; j < size; j++) {
                workoutList.add(workoutList.get(j));
            }
        }

        interval = workoutList.size();

        return  workoutList;


    }

}

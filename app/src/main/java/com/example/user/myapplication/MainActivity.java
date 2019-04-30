package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DailyWorkoutAdapter adapter;
    private ArrayList<DailyWorkout> dailyWorkoutsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addData();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new DailyWorkoutAdapter(dailyWorkoutsArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        // tempat untuk ingin menampilakan toast
    }

    void addData(){
        dailyWorkoutsArrayList = new ArrayList<>();
        dailyWorkoutsArrayList.add(new DailyWorkout("1", "bicycle crunches", R.drawable.img_bicycle_crunches));
        dailyWorkoutsArrayList.add(new DailyWorkout("2", "burpees", R.drawable.img_burpees));
        dailyWorkoutsArrayList.add(new DailyWorkout("3", "commando", R.drawable.img_commando));
        dailyWorkoutsArrayList.add(new DailyWorkout("4", "crunche", R.drawable.img_crunch));
        dailyWorkoutsArrayList.add(new DailyWorkout("5", "elbow plank with leg lift", R.drawable.img_elbow_plankwith_leg_lift));
        dailyWorkoutsArrayList.add(new DailyWorkout("6", "elbow plank", R.drawable.img_elbow_plank));
        dailyWorkoutsArrayList.add(new DailyWorkout("7", "hammer curl", R.drawable.img_hammer_curl));
        dailyWorkoutsArrayList.add(new DailyWorkout("8", "high kness", R.drawable.img_high_kness));
        dailyWorkoutsArrayList.add(new DailyWorkout("9", "ide elbow plank with a twist", R.drawable.img_ide_elbow_plank_with_a_twist));
        dailyWorkoutsArrayList.add(new DailyWorkout("10", "jumping jack", R.drawable.img_jumping_jack));
        dailyWorkoutsArrayList.add(new DailyWorkout("11", "jumping squats", R.drawable.img_jumping_squats));
        dailyWorkoutsArrayList.add(new DailyWorkout("12", "leg raise", R.drawable.img_leg_raise));
        dailyWorkoutsArrayList.add(new DailyWorkout("13", "lunges", R.drawable.img_lunges));
        dailyWorkoutsArrayList.add(new DailyWorkout("14", "lying overhead reach", R.drawable.img_lying_overhead_reach));
        dailyWorkoutsArrayList.add(new DailyWorkout("15", "pushup", R.drawable.img_pushup));
        dailyWorkoutsArrayList.add(new DailyWorkout("16", "reverse lunges", R.drawable.img_reverse_lunges));
        dailyWorkoutsArrayList.add(new DailyWorkout("17", "russian twist", R.drawable.img_russian_twist));
        dailyWorkoutsArrayList.add(new DailyWorkout("18", "side lunges", R.drawable.img_side_lunges));
        dailyWorkoutsArrayList.add(new DailyWorkout("19", "squats", R.drawable.img_squats));
        dailyWorkoutsArrayList.add(new DailyWorkout("20", "star jump", R.drawable.img_star_jump));
    }
}

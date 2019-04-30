package com.example.user.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.media.MediaDrm;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;

import pl.droidsonroids.gif.GifDrawable;

import static com.example.user.myapplication.R.id.img;
import static com.example.user.myapplication.R.id.itm_mute;

public class CountDown extends AppCompatActivity {

    private CountDownTimer timer, restTimer;
    private TextView txtTime, txtName;
    private ImageButton btn;
    private ImageView img;
    private Boolean isPaused = false, restIsPaused = false, isMuted = false, timerRunning = false, restTimerRunning = false, isPlaying = false;
    final int[] n = {1};  //counter
    private String type = "";
    private ArrayList<Workout> workoutList = new ArrayList<>();
    private int interval, progress = 1, finishedProgress = 0; //no of exercise
    private ProgressBar progressBar;
    private MediaPlayer bg,mediaplayer;
    private Menu menu;
    private MenuItem itmMute;
    private int restPeriod = 10000, activePeriod = 20000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

        txtTime = findViewById(R.id.txt_countDown);
        txtName = findViewById(R.id.txt_name);
        btn = findViewById(R.id.btn);
        img = findViewById(R.id.img);
        progressBar = findViewById(R.id.progressBar);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(timerRunning == true) {

                    if (isPaused == false) {
                        Toast.makeText(CountDown.this, "Timer will be reset everytime you pause. Keep going!", Toast.LENGTH_SHORT).show();
                        isPaused = true;
                        timer.cancel();

                        progressBar.setProgress(finishedProgress);

                        bg.pause();
                        btn.setImageResource(android.R.drawable.ic_media_play);
                    } else {
                        isPaused = false;
                        timer.start();

                       if (isMuted == false)
                            bg.start();
                        btn.setImageResource(android.R.drawable.ic_media_pause);
                    }
                }

                else if (restTimerRunning == true){

                    if (restIsPaused == false) {
//                        Toast.makeText(CountDown.this, "Timer will be reset everytime you pause. Keep going!", Toast.LENGTH_SHORT).show();
                        restIsPaused = true;
                        restTimer.cancel();

                        progressBar.setProgress(finishedProgress);

                        bg.pause();
                        btn.setImageResource(android.R.drawable.ic_media_play);
                    } else {
                        restIsPaused = false;
                        restTimer.start();

                        if (isMuted == false)
                            bg.start();
                        btn.setImageResource(android.R.drawable.ic_media_pause);
                    }

                }
            }
        });

        getData();

        this.setTitle("WORKOUT " + 1 + "/" + workoutList.size());

        img.setImageResource(workoutList.get(0).getImg());
        txtName.setText(workoutList.get(0).getWorkoutName());

        progressBar.setMax(interval*20);
        progressBar.setProgress(0);

        startTimer(workoutList);

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        itmMute = menu.findItem(R.id.itm_mute); //menu item need to be refered by this, not by findViewbyId
//        itmInfo = menu.findItem(R.id.itm_info);

        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case (R.id.itm_mute):
                if (isMuted == false){
                    bg.pause();
                    isMuted = true;
                    itmMute.setIcon(android.R.drawable.ic_lock_silent_mode);
                }
                else if (isMuted == true){
                    bg.start();
                    isMuted = false;
                    itmMute.setIcon(getResources().getDrawable(android.R.drawable.ic_lock_silent_mode_off));
                }

                break;

//            case (R.id.itm_info):
//                Intent intentInfo = new Intent(CountDown.this, TutorialActivity.class);
//                intentInfo.putExtra("currentWorkout", workoutList);
//                intentInfo.putExtra("from", "programDay");
//                intentInfo.putExtra("day",type);
//                startActivity(intentInfo);
//                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("End current workout session?");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(timerRunning == true)
                    timer.cancel();
                if(restTimerRunning == true)
                    restTimer.cancel();
                bg.stop();
                CountDown.this.finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setCancelable(false);
        builder.show();

    }

    @Override
    protected void onUserLeaveHint() { //when home key is pressed

        if(timerRunning == true)
            timer.cancel();
        if(restTimerRunning == true)
            restTimer.cancel();

        btn.setImageResource(android.R.drawable.ic_media_play);
        isPaused = true;
        bg.stop();
//        this.finish();

        super.onUserLeaveHint();
    }

    public void startTimer(final ArrayList<Workout> workoutList) {

        timerRunning = true;
        final int[] n = {1};

        mediaplayer = MediaPlayer.create(CountDown.this, R.raw.shortbeep);
        bg = MediaPlayer.create(CountDown.this, R.raw.bg);
        if (isPlaying == false) {
            bg.start();
            bg.setLooping(true);
            isPlaying = true;
        }


        timer = new CountDownTimer(activePeriod, 1000) {

            int currentProgress = 0;

            @Override
            public void onTick(long l) {

                if (isPaused == false) {

                    long timeRemaining = (l / 1000) + 1;

                    progressBar.setProgress(progressBar.getProgress() + progress);
                    if (timeRemaining <= 3){
                        mediaplayer.start();

                    }
                    txtTime.setTextColor(Color.parseColor("#FF40C118"));
                    txtTime.setText(String.valueOf(timeRemaining));
                } else {
                    timer.cancel();
                    isPaused = true;

                }
            }


            @Override
            public void onFinish() {

                finishedProgress = progressBar.getProgress();
                txtTime.setTextColor(Color.LTGRAY);
                //activity end
                txtName.setText("Rest");
                img.setImageResource(R.drawable.rest);

                restTimerRunning = true;
                timerRunning = false;
                restTimer = new CountDownTimer(restPeriod, 1000) {

                    int currentProgress = 0;
                    @Override
                    public void onTick(long l) {

                        long timeRemaining = (l / 1000) + 1;

                        progressBar.setProgress(progressBar.getProgress() + progress);

                        if (timeRemaining <= 3){
                            mediaplayer.start();
                        }
                        txtTime.setText(String.valueOf(timeRemaining));

                        if (timeRemaining == 5 && n[0] < workoutList.size() ){
                            Toast.makeText(CountDown.this, "Next: " + workoutList.get(n[0]).getWorkoutName(), Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFinish() {

                        finishedProgress = progressBar.getProgress();

                        n[0]++;
                        btn.setEnabled(true);

                        if (n[0] > interval) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(CountDown.this);
                            builder.setTitle("Message");
                            builder.setMessage("Work out complete!");
                            builder.show();
                        }

                        if (n[0] <= interval) {
                            CountDown.this.setTitle("WORKOUT " + n[0] + "/" + workoutList.size());
                            txtName.setText(workoutList.get(n[0] - 1).getWorkoutName());
                            img.setImageResource(workoutList.get(n[0] - 1).getImg());
                            timer.start();
                            timerRunning = true;
                            restTimerRunning = false;

                        }


                    }
                }.start();


            }
        }.start();

    }

    public ArrayList<Workout> getData(){

        ArrayList<String> workoutName =  new ArrayList<>(), imgStr = new ArrayList<>(), description = new ArrayList<>();
        ArrayList<Integer> imgRes = new ArrayList<>();

        type = getIntent().getStringExtra("type");

            if (type.equals("arms") || type.equals("abs") || type.equals("thigh")) {
                workoutName = new DbCenter().getData(CountDown.this, "select workout_name from table_workout where type LIKE '%" + type + "%'");
                imgStr = new DbCenter().getData(CountDown.this, "select image from table_workout where type LIKE '%" + type + "%'");
                description = new DbCenter().getData(CountDown.this, "select description from table_workout where type LIKE '%" + type + "%'");
            } else if (type.equals("1")) { //10x3, total 15 min
                workoutName = new DbCenter().getData(CountDown.this, "select workout_name from table_workout where id in (1,3,5,11,13,14,21,22,23,25)");
                imgStr = new DbCenter().getData(CountDown.this, "select image from table_workout where id in (1,3,5,11,13,14,21,22,23,25)");
                description = new DbCenter().getData(CountDown.this, "select description from table_workout where id in (1,3,5,11,13,14,21,22,23,25)");
            } else if (type.equals("2")) { //abs, 5x3, tottal 7.5 min
                workoutName = new DbCenter().getData(CountDown.this, "select workout_name from table_workout where id in (1,3,2,5,7)");
                imgStr = new DbCenter().getData(CountDown.this, "select image from table_workout where id in (1,3,2,5,7)");
                description = new DbCenter().getData(CountDown.this, "select description from table_workout where id in (1,3,2,5,7)");
            } else if (type.equals("3")) { //10x3x2, total 30 min
                workoutName = new DbCenter().getData(CountDown.this, "select workout_name from table_workout where id in (12,28,7,24,13,11,26,25,23,8)");
                imgStr = new DbCenter().getData(CountDown.this, "select image from table_workout where id in (12,28,7,24,13,11,26,25,23,8)");
                description = new DbCenter().getData(CountDown.this, "select description from table_workout where id in (12,28,7,24,13,11,26,25,23,8)");
            } else if (type.equals("4")) { //arm, 5x3, tottal 7.5 min
                workoutName = new DbCenter().getData(CountDown.this, "select workout_name from table_workout where id in (18,17,19,16,15)");
                imgStr = new DbCenter().getData(CountDown.this, "select image from table_workout where id in (18,17,19,16,15)");
                description = new DbCenter().getData(CountDown.this, "select description from table_workout where id in (18,17,19,16,15)");
            } else if (type.equals("5")) {  //10x3, total 15 min
                workoutName = new DbCenter().getData(CountDown.this, "select workout_name from table_workout where id in (28,11,29,14,23,9,10,18,21,22)");
                imgStr = new DbCenter().getData(CountDown.this, "select image from table_workout where id in (28,11,29,14,23,9,10,18,21,22)");
                description = new DbCenter().getData(CountDown.this, "select description from table_workout where id in (28,11,29,14,23,9,10,18,21,22)");
            } else if (type.equals("6")) { //thigh, 5x3, tottal 7.5 min
                workoutName = new DbCenter().getData(CountDown.this, "select workout_name from table_workout where id in (28,29,30,23,26)");
                imgStr = new DbCenter().getData(CountDown.this, "select image from table_workout where id in (28,29,30,23,26)");
                description = new DbCenter().getData(CountDown.this, "select description from table_workout where id in (28,29,30,23,26)");
            } else if (type.equals("7")) { //10x3x2, total 30 min
                workoutName = new DbCenter().getData(CountDown.this, "select workout_name from table_workout where id in (12,28,30,25,7,8,24,27,4,5)");
                imgStr = new DbCenter().getData(CountDown.this, "select image from table_workout where id in (12,28,30,25,7,8,24,27,4,5)");
                description = new DbCenter().getData(CountDown.this, "select description from table_workout where id in (12,28,30,25,7,8,24,27,4,5)");
            }


            for (int i = 0; i < imgStr.size(); i++) {
                imgRes.add(getResources().getIdentifier(imgStr.get(i), "drawable", getPackageName()));
            }

            for (int i = 0; i < workoutName.size(); i++) {

                Workout workout = new Workout(i, workoutName.get(i), imgRes.get(i), description.get(i));

                if (type.equals("arms") || type.equals("abs") || type.equals("thigh")) {
                    workoutList.add(workout);
                } else {

                    workoutList.add(workout);
                    workoutList.add(workout);
                    workoutList.add(workout);
                }
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




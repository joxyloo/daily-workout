package com.example.user.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class pilihLatihan extends AppCompatActivity implements OnClickListener{

    private ImageButton btnProgram, btnArm, btnAbs, btnThigh;
//    private MenuItem itmCustomise;
    private MenuItem itmAlarm;
    private String type = "";
    private ArrayList<Workout> workoutList = new ArrayList<>();
    private int interval = 0;
//    private CountDown countDownActivity;
    TimePickerDialog timePickerDialog;

    final static int RQS_1 = 1;

    TextView textAlarmPrompt;
    private static String textAlarm = "No Set Alarm";
    private static String strAlarm = "No Alarm Set";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_latihan);
        this.setTitle("Home");

        btnProgram = findViewById(R.id.btnProgram);
        btnArm = findViewById(R.id.btnArm);
        btnAbs = findViewById(R.id.btnAbs);
        btnThigh = findViewById(R.id.btnThight);
        textAlarmPrompt = (TextView) findViewById(R.id.tv_reminder);

        btnProgram.setOnClickListener(this);
        btnArm.setOnClickListener(this);
        btnAbs.setOnClickListener(this);
        btnThigh.setOnClickListener(this);

//        textAlarmPrompt.setText("***\n" + "Alarm set on " + textAlarm
//                + "\n***");

        textAlarmPrompt.setText(strAlarm);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_alarm, menu);
        itmAlarm = menu.findItem(R.id.itm_alarm);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        openTimePickerDialog(false);
        return super.onOptionsItemSelected(item);
    }

    private void openTimePickerDialog(boolean is24r) {
        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(pilihLatihan.this,
                onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true);
        timePickerDialog.setTitle("Set Alarm Time");

        timePickerDialog.show();


    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);

            if (calSet.compareTo(calNow) <= 0) {
                // Today Set time passed, count to tomorrow
                calSet.add(Calendar.DATE, 1);
                Log.i("hasil", " =<0");
            } else if (calSet.compareTo(calNow) > 0) {
                Log.i("hasil", " > 0");
            } else {
                Log.i("hasil", " else ");
            }

            setAlarm(calSet);
        }
    };

    private void setAlarm(Calendar targetCal) {

        textAlarm = targetCal.getTime().toString();
        strAlarm = "***\n" + "Alarm set on " + targetCal.getTime()
                + "\n***";
        textAlarmPrompt.setText("***\n" + "Alarm set on " + targetCal.getTime()
                + "\n***");
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        ActivityCompat.finishAffinity(this);
    }

    public void onClick(View view) {


        switch (view.getId()){
            case (R.id.btnProgram):
                getData();
                Intent intentTutorial = new Intent(pilihLatihan.this, StartProgramActivity.class);
                startActivity(intentTutorial);
                break;

            case (R.id.btnArm):
                type = "arms";
                getData();
                Intent intentArm = new Intent(pilihLatihan.this, TutorialActivity.class);
                intentArm.putExtra("day",type);
                intentArm.putExtra("from", "programDay");
                intentArm.putExtra("currentWorkout", workoutList);
//                intentArm.putExtra("type", "arms");
                startActivity(intentArm);
                break;

            case (R.id.btnAbs):
                type = "abs";
                getData();
                Intent intentAbs = new Intent(pilihLatihan.this, TutorialActivity.class);
//                intentAbs.putExtra("type", "abs");
                intentAbs.putExtra("day",type);
                intentAbs.putExtra("from", "programDay");
                intentAbs.putExtra("currentWorkout", workoutList);

                startActivity(intentAbs);
                break;

            case (R.id.btnThight):
                type = "thigh";
                getData();
                Intent intentThigh = new Intent(pilihLatihan.this, TutorialActivity.class);
//                intentThigh.putExtra("type", "thigh");'
                intentThigh.putExtra("day",type);
                intentThigh.putExtra("from", "programDay");
                intentThigh.putExtra("currentWorkout", workoutList);

                startActivity(intentThigh);
                break;

        }

    }

    public ArrayList<Workout> getData(){

        ArrayList<String> workoutName =  new ArrayList<>(), imgStr = new ArrayList<>(), description = new ArrayList<>();
        ArrayList<Integer> imgRes = new ArrayList<>();

            workoutName = new DbCenter().getData(pilihLatihan.this, "select workout_name from table_workout where type LIKE '%" + type + "%'");
            imgStr = new DbCenter().getData(pilihLatihan.this, "select image from table_workout where type LIKE '%" + type + "%'");
            description = new DbCenter().getData(pilihLatihan.this, "select description from table_workout where type LIKE '%" + type + "%'");


        for (int i = 0; i < imgStr.size(); i++) {
            imgRes.add(getResources().getIdentifier(imgStr.get(i), "drawable", getPackageName()));
        }

        for (int i = 0; i < workoutName.size(); i++) {

            Workout workout = new Workout(i, workoutName.get(i), imgRes.get(i), description.get(i));

                workoutList.add(workout);

        }


        interval = workoutList.size();

        return  workoutList;


    }

}
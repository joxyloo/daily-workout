package com.example.user.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>{

    ArrayList<Workout> workoutList = new ArrayList<>();

    public WorkoutAdapter(ArrayList<Workout> workoutList){
        this.workoutList = workoutList;
    }
    @NonNull
    @Override
    public WorkoutAdapter.WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.row_all_workout, viewGroup, false);

        return new WorkoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutAdapter.WorkoutViewHolder workoutViewHolder, int i) {
        workoutViewHolder.txtName.setText(workoutList.get(i).getWorkoutName());
        workoutViewHolder.img.setImageResource(workoutList.get(i).getImg());

    }

    @Override
    public int getItemCount() {
        return (workoutList != null) ? workoutList.size() : 0;
    }

    public class WorkoutViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private ImageView img;

        public WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            img = itemView.findViewById(R.id.img);
        }
    }
}

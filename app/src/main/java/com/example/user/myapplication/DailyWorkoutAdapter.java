package com.example.user.myapplication;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DailyWorkoutAdapter extends RecyclerView.Adapter<DailyWorkoutAdapter.DailyWorkoutViewHolder> {
    private ArrayList<DailyWorkout> dataList;

    public DailyWorkoutAdapter(ArrayList<DailyWorkout> dataList){
        this.dataList=dataList;
    }
    @Override
    public DailyWorkoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.row_dailyworkout, parent, false);
        return new DailyWorkoutViewHolder(view);
    }
    @Override
    public void onBindViewHolder(DailyWorkoutViewHolder holder, int position){
        holder.txtNumber.setText(dataList.get(position).getNumber());
        holder.txtName.setText(dataList.get(position).getName());
        holder.imgImages.setImageResource(dataList.get(position).getImages());
    }
    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class DailyWorkoutViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNumber, txtName;
        private ImageView imgImages;

        public DailyWorkoutViewHolder(View itemView) {
            super(itemView);
            txtNumber = (TextView) itemView.findViewById(R.id.txt_Number);
            txtName = (TextView) itemView.findViewById(R.id.txt_Name);
            imgImages = (ImageView) itemView.findViewById(R.id.img_images);
        }
    }
}

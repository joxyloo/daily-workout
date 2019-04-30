package com.example.user.myapplication;

import java.io.Serializable;

public class Workout implements Serializable{

    private int id, img;
    private String workoutName,description;

    public Workout(int id, String workoutName, int img, String description){
        this.id = id;
        this.workoutName = workoutName;
        this.img = img;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.example.user.myapplication;

public class DailyWorkout {

    private String number;
    private String name;
    private int images;

    public DailyWorkout(String number, String name, int images){
        this.number = number;
        this.name = name;
        this.images = images;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }
}

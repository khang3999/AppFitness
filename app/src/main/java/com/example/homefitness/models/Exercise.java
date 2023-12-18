package com.example.homefitness.models;

public class Exercise {
    private String gifName;
    private int calorie;

    private int time  = 30;
    private int indexGifInDrawable;

    public String getGifName() {
        return gifName;
    }

    public void setGifName(String gifName) {
        this.gifName = gifName;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public int getIndexGifInDrawable() {
        return indexGifInDrawable;
    }

    public void setIndexGifInDrawable(int indexGifInDrawable) {
        this.indexGifInDrawable = indexGifInDrawable;
    }

    public Exercise() {
        this.gifName = "";
        this.calorie = 0;
        this.time = 30;
        this.indexGifInDrawable = 0;
    }

    public Exercise(String gifName, int time, int calorie, int indexGifInDrawable) {
        this.gifName = gifName;
        this.calorie = calorie;
        this.time = time;
        this.indexGifInDrawable = indexGifInDrawable;
    }

}

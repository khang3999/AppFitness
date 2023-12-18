package com.example.homefitness.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


public class Exercise {

    private int id;

    private String gifName;

    private int time  = 30;

    private int calorie;

    private int indexGifInDrawable;

    private String categoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Exercise() {
        this.gifName = "";
        this.calorie = 0;
        this.time = 30;
        this.indexGifInDrawable = 0;
        this.categoryId = "";
    }

    public Exercise(String gifName, int time, int calorie, int indexGifInDrawable, String categoryId) {
        this.gifName = gifName;
        this.calorie = calorie;
        this.time = time;
        this.indexGifInDrawable = indexGifInDrawable;
        this.categoryId = categoryId;
    }
    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}

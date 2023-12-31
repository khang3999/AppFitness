package com.example.homefitness.models;


import java.io.Serializable;

public class Exercise implements Serializable {

    private int id;
    private String gifName;

    private int time  = 30;

    private int calorie;

    private int indexGifInDrawable;

    private String categoryId;
    private int favorite;

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

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
        this.favorite = 0;
    }

    public Exercise(String gifName, int time, int calorie, int indexGifInDrawable, String categoryId, int favorite) {
        this.gifName = gifName;
        this.calorie = calorie;
        this.time = time;
        this.indexGifInDrawable = indexGifInDrawable;
        this.categoryId = categoryId;
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "gifName='" + gifName + '\'' +
                ", calorie=" + calorie +
                ", time=" + time +
                ", indexGifInDrawable=" + indexGifInDrawable +
                '}';
    }

}

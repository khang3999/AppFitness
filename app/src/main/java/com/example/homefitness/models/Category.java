package com.example.homefitness.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

public class Category {
    //Dinh nghia thuoc tinh cua bang



    private String categoryId;

    private String categoryName;

    private int indexCategoryInDrawable;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getIndexCategoryInDrawable() {
        return indexCategoryInDrawable;
    }

    public void setIndexCategoryInDrawable(int indexCategoryInDrawable) {
        this.indexCategoryInDrawable = indexCategoryInDrawable;
    }

    public Category() {
    }

    public Category(String categoryId, String categoryName, int indexCategoryInDrawable) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.indexCategoryInDrawable = indexCategoryInDrawable;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}

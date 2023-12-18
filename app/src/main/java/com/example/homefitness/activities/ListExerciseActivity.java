package com.example.homefitness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.homefitness.R;
import com.example.homefitness.models.Exercise;

import java.util.ArrayList;

public class ListExerciseActivity extends AppCompatActivity {
    // List du lieu lay tu database
    // Can lay du lieu tren database do vao list nay
    private ArrayList<Exercise> listExercise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_exercise_layout);

        //Khoi tao array
        listExercise = new ArrayList<Exercise>();

        // Lay du lieu goi di tu intent cua HomeActivity
        Intent intent = getIntent();
        // Lấy dữ liệu kiểu chuỗi (ví dụ: getStringExtra)
        String categoryName = intent.getStringExtra("categoryName");

        // Lay du lieu tu database voi key la categoryName; bo ham api vao day

    }
}
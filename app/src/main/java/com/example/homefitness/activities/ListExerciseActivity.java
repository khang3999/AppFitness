package com.example.homefitness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.homefitness.R;
import com.example.homefitness.adapters.ExerciseAdapter;
import com.example.homefitness.models.Exercise;

import java.util.ArrayList;

public class ListExerciseActivity extends AppCompatActivity {
    // List du lieu lay tu database
    // Can lay du lieu tren database do vao list nay
    private ArrayList<Exercise> listExercises;
    private ListView lvExercises;
    private TextView totalWorkouts;
    private TextView totalMinutes;
    private ExerciseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_exercise_layout);

        //Khoi tao array
        listExercises = new ArrayList<Exercise>();
        lvExercises = findViewById(R.id.lvExercise);
        totalWorkouts = findViewById(R.id.txtWorkouts);
        totalMinutes = findViewById(R.id.txtMinutes);

        // Lay du lieu goi di tu intent cua HomeActivity
        Intent intent = getIntent();
        // Lấy dữ liệu kiểu chuỗi (ví dụ: getStringExtra)
        String categoryName = intent.getStringExtra("categoryName");


        //Set apdater
        listExercises = (ArrayList<Exercise>) intent.getSerializableExtra("selectedExercises");
        lvExercises.setAdapter(adapter);

        //set tong so bai tap
        totalWorkouts.setText(listExercises.size()+"");
        //set tong thoi gian
        totalMinutes.setText((listExercises.size() * 30) +"");


        // Lay du lieu tu database voi key la categoryName; bo ham api vao day

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("OnStart", "onStart: ");
        adapter = new ExerciseAdapter(this,R.layout.my_listview_layout,listExercises);
        lvExercises.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        Log.d("OnResume", "OnResume: ");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.d("onRestart", "onRestart: ");
        super.onRestart();
    }
}
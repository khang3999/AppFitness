package com.example.homefitness.activities;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homefitness.R;
import com.example.homefitness.models.Exercise;

import java.util.ArrayList;

public class ResultExerciseActivity extends AppCompatActivity {

    private ArrayList<Exercise> listExercises;
    private TextView txtCalories;
    private TextView txtExercises;

    private TextView txtTimes;

    private Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_exercise_layout);
        int totalCalories = 0;
        Intent intent = getIntent();

        listExercises = new ArrayList<>();
        listExercises = (ArrayList<Exercise>) intent.getSerializableExtra("listExercises");
        txtCalories = findViewById(R.id.txtTotalCalories);
        txtExercises = findViewById(R.id.txtTotalExercises);
        txtTimes = findViewById(R.id.txtTotalTimes);
        btnFinish = findViewById(R.id.btnFinish);

        for (Exercise ex :
                listExercises) {
            totalCalories += ex.getCalorie();
        }

        txtCalories.setText(totalCalories + "");
        txtTimes.setText((listExercises.size() * 30) +"");
        txtExercises.setText(listExercises.size() + "");



        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentFinish = new Intent(ResultExerciseActivity.this, AppDrawerActivity.class);
                startActivity(intentFinish);
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intentReturnHome = new Intent(ResultExerciseActivity.this,AppDrawerActivity.class);
                startActivity(intentReturnHome);
            }
        };

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentReturnHome = new Intent(ResultExerciseActivity.this, AppDrawerActivity.class);
        startActivity(intentReturnHome);
        finish();
    }
}


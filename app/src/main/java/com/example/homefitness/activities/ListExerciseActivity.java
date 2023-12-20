package com.example.homefitness.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;

import android.view.MenuItem;

import android.widget.ListView;
import android.widget.TextView;

import com.example.homefitness.R;
import com.example.homefitness.adapters.ExerciseAdapter;
import com.example.homefitness.models.Exercise;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ListExerciseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
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


        //Khoi tao


        // Get action bar back to previous
        // khoi tao cho drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //Khoi tao array

        listExercises = new ArrayList<Exercise>();
        lvExercises = findViewById(R.id.lvExercise);
        totalWorkouts = findViewById(R.id.txtWorkouts);
        totalMinutes = findViewById(R.id.txtMinutes);
        Button btnStart = findViewById(R.id.btnStart);

        // Lay du lieu goi di tu intent cua HomeActivity
        Intent intent = getIntent();
        // Lấy dữ liệu kiểu chuỗi (ví dụ: getStringExtra)
        String categoryName = intent.getStringExtra("categoryName");


        //Set apdater


        listExercises = (ArrayList<Exercise>) intent.getSerializableExtra("selectedExercises");
        adapter = new ExerciseAdapter(this,R.layout.my_listview_layout,listExercises);

        if(intent.getSerializableExtra("selectedExercises") == null){
            listExercises =(ArrayList<Exercise>) intent.getSerializableExtra("listExerciseByCategory");
        }else {
            listExercises = (ArrayList<Exercise>) intent.getSerializableExtra("selectedExercises");
        }

        adapter = new ExerciseAdapter(this,R.layout.my_listview_layout, listExercises);

        lvExercises.setAdapter(adapter);

        //set tong so bai tap
        totalWorkouts.setText(listExercises.size()+"");
        //set tong thoi gian
        totalMinutes.setText((listExercises.size() * 30) +"");

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentStartExercise = new Intent(ListExerciseActivity.this, StartExerciseActivity.class);
                intentStartExercise.putExtra("listExercises",listExercises);
                intentStartExercise.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intentStartExercise);
            }
        });




    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        // Điều này sẽ tự động kết thúc hiện tại Activity và quay lại Activity trước đó (nếu có).
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

}
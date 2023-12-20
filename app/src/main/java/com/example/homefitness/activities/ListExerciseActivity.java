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
    private TextView tvDesc;
    private ExerciseAdapter adapter;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_exercise_layout);

<<<<<<< HEAD

        //Khoi tao


=======
>>>>>>> a3a7f823aa6aefc6e0e25979eedf9f21a714ba31
        // Get action bar back to previous
        // khoi tao cho drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //Khoi tao array

        listExercises = new ArrayList<Exercise>();
        lvExercises = findViewById(R.id.lvExercise);
        totalWorkouts = findViewById(R.id.txtWorkouts);
        totalMinutes = findViewById(R.id.txtMinutes);
<<<<<<< HEAD
        Button btnStart = findViewById(R.id.btnStart);
=======
        tvDesc = findViewById(R.id.tvDesc);

>>>>>>> a3a7f823aa6aefc6e0e25979eedf9f21a714ba31

        // Lay du lieu goi di tu intent cua HomeActivity
        Intent intent = getIntent();


        // Lấy dữ liệu kiểu chuỗi (ví dụ: getStringExtra)
        update();

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

<<<<<<< HEAD
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentStartExercise = new Intent(ListExerciseActivity.this, StartExerciseActivity.class);
                intentStartExercise.putExtra("listExercises",listExercises);
                intentStartExercise.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intentStartExercise);
            }
        });




=======
>>>>>>> a3a7f823aa6aefc6e0e25979eedf9f21a714ba31
    }

    protected void update(){
        // Lay du lieu goi di tu intent cua HomeActivity
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        setTitle(title);
        if (title.equals("Recent")){
            tvDesc.setText("This is list exercises recent.");
        }else if (title.equals("Favorite")){
            tvDesc.setText("This is list exercises favorite.");
        }else if (title.equals("Chest")){
            tvDesc.setText("This is list exercises chest.");
        }else if (title.equals("Shoulder")){
            tvDesc.setText("This is list exercises shoulder.");
        }else if (title.equals("Biceps")){
            tvDesc.setText("This is list exercises biceps.");
        }else if (title.equals("Triceps")){
            tvDesc.setText("This is list exercises triceps.");
        }else if (title.equals("Abs")){
            tvDesc.setText("This is list exercises abs.");
        }else if (title.equals("Glutes")){
            tvDesc.setText("This is list exercises glutes.");
        }
    }

<<<<<<< HEAD
=======
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("OnStart", "onStart: ");
        update();
        adapter = new ExerciseAdapter(this,R.layout.my_listview_layout,listExercises);
        lvExercises.setAdapter(adapter);
    }
>>>>>>> a3a7f823aa6aefc6e0e25979eedf9f21a714ba31


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
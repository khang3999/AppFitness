package com.example.homefitness.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.Menu;
import android.view.View;
import android.widget.Button;

import android.view.MenuItem;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homefitness.R;
import com.example.homefitness.adapters.ExerciseAdapter;
import com.example.homefitness.databases.MyDatabase;
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
    private Intent intent;
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

        tvDesc = findViewById(R.id.tvDesc);

        listExercises = new ArrayList<>();
        // Lay du lieu goi di tu intent cua HomeActivity
//        Intent intent = getIntent();
//
//
//        // Lấy dữ liệu kiểu chuỗi (ví dụ: getStringExtra)
//        update();
//
//        //Set apdater
//        if(intent.getSerializableExtra("selectedExercises") == null){
//            listExercises =(ArrayList<Exercise>) intent.getSerializableExtra("listExerciseByCategory");
//        }else {
//            listExercises = (ArrayList<Exercise>) intent.getSerializableExtra("selectedExercises");
//        }
//        adapter = new ExerciseAdapter(this,R.layout.my_listview_layout,listExercises);
//        lvExercises.setAdapter(adapter);
//        //set tong so bai tap
//        totalWorkouts.setText(listExercises.size()+"");
//        //set tong thoi gian
//        totalMinutes.setText((listExercises.size() * 30) +"");
//

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!listExercises.isEmpty()){
                    Intent intentStartExercise = new Intent(ListExerciseActivity.this, StartExerciseActivity.class);
                    intentStartExercise.putExtra("listExercises",listExercises);
                    intentStartExercise.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intentStartExercise);

                }else{
                    Toast.makeText(ListExerciseActivity.this, "Your exercise list is empty.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    // Hien thi option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (title.equals("Favorite")){
            // dung menuInflater de dan menu vao: voi tham so thu nhat la menu
            getMenuInflater().inflate(R.menu.menu, menu);

        }
        return true;
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
        } else{
            tvDesc.setText("This is list exercises customize.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        intent = new Intent();
        intent = getIntent();
        update();
        //Set apdater
//        listExercises.clear();

        if(intent.getSerializableExtra("selectedExercises") == null){
            listExercises =(ArrayList<Exercise>) intent.getSerializableExtra("listExerciseByCategory");
        }else if(intent.getSerializableExtra("listExerciseByCategory") == null){
//            listExercises = (ArrayList<Exercise>) intent.getSerializableExtra("selectedExercises");
            listExercises= (ArrayList<Exercise>) intent.getSerializableExtra("selectedExercises");
        }


        adapter = new ExerciseAdapter(this,R.layout.my_listview_layout,listExercises);
        lvExercises.setAdapter(adapter);

//        Log.d("listExercises_1", listExercises.toString());
        //set tong so bai tap
        totalWorkouts.setText(listExercises.size()+"");
        //set tong thoi gian
        totalMinutes.setText((change(listExercises.size() * 30)));
//        listExercises.clear();


    }

    public String change(int n){
        //khai báo 3 biến hour, minute, second đại diện cho giờ phút giây
        int hour, minute, second;
        //1h = 3600s -> hour = n / 3600
        hour = n / 3600;
        //1p = 60s, vì ở trên ta đã chia 3600 để lấy giờ
        //vậy nên ta cần lấy phần dư của nó chia cho 60
        minute = n % 3660 / 60;
        //phần dư còn lại chính là số giây
        second = n % 3600 % 60;
        String rs = hour+"h " + minute+"m "+second+"s";
        return rs;

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
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
        }else if (item.getItemId() == R.id.menuDelete){
            // ham xoa database
            MyDatabase myDatabase = new MyDatabase(this);
            // Cap nhat database
            myDatabase.updateExercisesIntoFavoriteByListId(adapter.getListId());
            // Lay lai listview moi
            listExercises = myDatabase.getExerciseFavorite();
            adapter = new ExerciseAdapter(this,R.layout.my_listview_layout,listExercises);
            lvExercises.setAdapter(adapter);


            totalWorkouts.setText(listExercises.size()+"");
            //set tong thoi gian
            totalMinutes.setText((change(listExercises.size() * 30)));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

}
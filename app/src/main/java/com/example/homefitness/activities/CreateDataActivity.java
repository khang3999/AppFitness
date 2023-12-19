package com.example.homefitness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.homefitness.R;
import com.example.homefitness.databases.MyDatabase;
import com.example.homefitness.databinding.AppDrawerLayoutBinding;
import com.example.homefitness.databinding.CreateDataLayoutBinding;
import com.example.homefitness.models.Account;
import com.example.homefitness.models.Category;
import com.example.homefitness.models.Exercise;

public class CreateDataActivity extends AppCompatActivity {
    private MyDatabase myDatabase;
    private CreateDataLayoutBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.create_data_layout);

        binding = CreateDataLayoutBinding.inflate(getLayoutInflater());
        // Gán view cho binding
        setContentView(binding.getRoot());
        myDatabase = new MyDatabase(CreateDataActivity.this);
        binding.btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int calorie = Integer.parseInt(binding.calorieData.getText().toString());
                String idCate = binding.idCateData.getText().toString();
                String name = binding.nameGifData.getText().toString();
                int time = 30;
                int favorite = 0;
                int index = getResources().getIdentifier(name,"drawable", getPackageName() );

                Exercise ex = new Exercise(name, time, calorie,index, idCate, 0);
                myDatabase.createExercise(ex);

                clearData();
                binding.nameGifData.requestFocus();
                Log.d("test", "onClick: " + ex.getGifName());
            }
        });
        binding.btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // id cate
                String idCate = binding.idCateData1.getText().toString();
                // cate name
                String nameCate = binding.nameCateData.getText().toString();
                int index = getResources().getIdentifier(nameCate,"drawable", getPackageName() );

                Category cate = new Category(idCate, nameCate, index);
                myDatabase.createCategory(cate);
                clearData();
                binding.idCateData1.requestFocus();
                Log.d("test", "cate : " + cate.getIndexCategoryInDrawable());
            }
        });
    }
    public void clearData(){
        binding.nameGifData.setText("");
        binding.idCateData.setText("");
        binding.calorieData.setText("");
    }
}
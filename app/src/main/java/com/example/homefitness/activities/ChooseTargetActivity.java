package com.example.homefitness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.homefitness.R;
import com.example.homefitness.databases.MyDatabase;
import com.example.homefitness.databinding.ChooseTargetLayoutBinding;
import com.example.homefitness.models.Account;

public class ChooseTargetActivity extends AppCompatActivity {
    private ChooseTargetLayoutBinding binding;
    private String name;
    private String gender;
    private String height;
    private String weight;
    private String target;
    private MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ChooseTargetLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        myDatabase = new MyDatabase(this);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        gender = intent.getStringExtra("gender");
        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");

        //Xu ly xu kien cho button
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseTargetActivity.this, InputWeightActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        //Chinh sua lai class khi click vao Continue se sang MainActivity
        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.rad1.isChecked()||binding.rad2.isChecked()||binding.rad3.isChecked()){

                //Set du lieu cho account
                if (binding.rad1.isChecked()){
                    target = binding.rad1.getText().toString();
                }
                else if (binding.rad2.isChecked()){
                    target =binding.rad2.getText().toString();
                }
                else if (binding.rad3.isChecked()){
                    target =binding.rad3.getText().toString();
                }
                double heightD = Double.parseDouble(height);
                double weightD = Double.parseDouble(weight);
                Account acc = new Account(name,gender,heightD,weightD,target,"");
                //Dua account len co so du lieu
                    myDatabase.createAccount(acc);
                    Intent intent = new Intent(ChooseTargetActivity.this, AppDrawerActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);}
                else {
                    String messBMI = "Please choose your target!";
                    Toast.makeText(ChooseTargetActivity.this, messBMI, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
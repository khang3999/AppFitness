package com.example.homefitness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.homefitness.R;
import com.example.homefitness.databinding.ChooseGenderLayoutBinding;

public class ChooseGenderActivity extends AppCompatActivity {
    private ChooseGenderLayoutBinding binding;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ChooseGenderLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
         name = intent.getStringExtra("name");

        //Xu ly su kien cho button
        binding.btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseGenderActivity.this,InputNameActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        binding.btnContinue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (binding.rad1.isChecked()||binding.rad2.isChecked()||binding.rad3.isChecked()){
                Intent intent = new Intent(ChooseGenderActivity.this,InputHeightActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                //Set du lieu cho account
                if (binding.rad1.isChecked()){
                    intent.putExtra("gender",binding.rad1.getText().toString());
                }
                else if (binding.rad2.isChecked()){
                    intent.putExtra("gender",binding.rad2.getText().toString());
                }
                else if (binding.rad3.isChecked()){
                    intent.putExtra("gender",binding.rad3.getText().toString());
                }
                intent.putExtra("name",name);
                startActivity(intent);}
                else {
                    String messBMI = "Please choose your gender!";
                    Toast.makeText(ChooseGenderActivity.this, messBMI, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
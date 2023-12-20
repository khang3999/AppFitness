package com.example.homefitness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.homefitness.R;
import com.example.homefitness.databinding.InputHeightLayoutBinding;

public class InputHeightActivity extends AppCompatActivity {
    private InputHeightLayoutBinding binding;
    private String name;
    private String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = InputHeightLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        gender = intent.getStringExtra("gender");

        //Xu ly su kien cho button
        binding.btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputHeightActivity.this,ChooseGenderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        binding.btnContinue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!binding.edtHeight.getText().toString().isEmpty()){
                Intent intent = new Intent(InputHeightActivity.this, InputWeightActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    intent.putExtra("height",binding.edtHeight.getText().toString());
                    intent.putExtra("name",name);
                    intent.putExtra("gender",gender);
                startActivity(intent);}
                else {
                    binding.edtHeight.requestFocus();
                    String messBMI = "Please enter your height!";
                    Toast.makeText(InputHeightActivity.this, messBMI, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
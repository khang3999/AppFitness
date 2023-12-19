package com.example.homefitness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.homefitness.R;
import com.example.homefitness.databinding.InputWeightLayoutBinding;

public class InputWeightActivity extends AppCompatActivity {
    private InputWeightLayoutBinding binding;
    private String name;
    private String gender;
    private String height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = InputWeightLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        gender = intent.getStringExtra("gender");
        height = intent.getStringExtra("height");
        //Xu ly du lieu cho button
        binding.btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputWeightActivity.this,InputHeightActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        binding.btnContinue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!binding.edtWeight.getText().toString().isEmpty()) {
                    Intent intent = new Intent(InputWeightActivity.this, ChooseTargetActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    intent.putExtra("weight", binding.edtWeight.getText().toString());
                    intent.putExtra("name",name);
                    intent.putExtra("gender",gender);
                    intent.putExtra("height",height);
                    startActivity(intent);
                }
                else {
                    binding.edtWeight.requestFocus();
                    String messBMI = "Please enter your weight!";
                    Toast.makeText(InputWeightActivity.this, messBMI, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
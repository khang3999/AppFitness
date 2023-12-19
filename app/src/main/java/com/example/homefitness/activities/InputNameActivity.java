package com.example.homefitness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.homefitness.R;
<<<<<<< HEAD
import com.example.homefitness.databinding.InputNameLayoutBinding;
=======
import com.example.homefitness.models.Account;
>>>>>>> origin/khang

public class InputNameActivity extends AppCompatActivity {

    private String name = "unknown";
    private InputNameLayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        binding = InputNameLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //Xu ly su kien cho button
        binding.btnContinue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!binding.edtName.getText().toString().isEmpty()){
                    name = binding.edtName.getText().toString();
                Intent intent = new Intent(InputNameActivity.this,ChooseGenderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    //Gui du lieu
                    intent.putExtra("name",name);
                startActivity(intent);
                }
                else {
                    binding.edtName.requestFocus();
                    String messBMI = "Please enter your name!";
                    Toast.makeText(InputNameActivity.this, messBMI, Toast.LENGTH_SHORT).show();
                }
            }
        });
=======
        setContentView(R.layout.input_name_layout);

        Account account = new Account();

>>>>>>> origin/khang
    }
}
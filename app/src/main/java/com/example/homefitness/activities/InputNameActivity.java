package com.example.homefitness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.homefitness.R;
import com.example.homefitness.models.Account;

public class InputNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_name_layout);

        Account account = new Account();

    }
}
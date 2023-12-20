package com.example.homefitness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.homefitness.R;
import com.example.homefitness.databases.MyDatabase;
import com.example.homefitness.models.Account;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {
    private static final long DELAY_TIME = 5000; // Đơn vị: milliseconds


    private MyDatabase myDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);

        // Khoi tao database
        myDatabase = new MyDatabase(WelcomeActivity.this);
//        Account a = new Account("Khang","Male", 165, 80, "aaa", "b,b");
//        myDatabase.createAccount(a);
        Log.d("test", myDatabase.getAccount().size()+"");

        if(myDatabase.getAccount().size() != 0){
            //handler
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                        // Tạo Intent để chuyển sang Activity mới
                        Intent intent = new Intent(WelcomeActivity.this, AppDrawerActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                }
            }, 5000);
        }
        else {
            //handler
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Tạo Intent để chuyển sang Activity mới
                    Intent intent = new Intent(WelcomeActivity.this, InputNameActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
            }, 5000);
        }


    }

}
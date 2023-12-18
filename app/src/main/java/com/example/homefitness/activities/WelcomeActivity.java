package com.example.homefitness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.homefitness.R;

public class WelcomeActivity extends AppCompatActivity {
    private static final long DELAY_TIME = 5000; // Đơn vị: milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);

        new CountDownTimer(DELAY_TIME, 1000) {
            public void onTick(long millisUntilFinished) {
                // Có thể thực hiện một số hành động trong quá trình đếm ngược (nếu cần)
            }

            public void onFinish() {
            // Chuyển màn hình ở đây (ví dụ: sử dụng Intent)
                //Intent intent = new Intent(WelcomeActivity.this, InforQuestionActivity.class);
                //startActivity(intent);
            }
        }.start();

    }
}
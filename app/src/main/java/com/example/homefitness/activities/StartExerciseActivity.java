package com.example.homefitness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.homefitness.R;
import com.example.homefitness.models.Exercise;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class StartExerciseActivity extends AppCompatActivity {

    private ArrayList<Exercise> listExercises;
    private ArrayList<Exercise> renderingExercises;
    private int currentGifIndex = 0;
    private static final long COUNTDOWN_INTERVAL = 1000; // 1 second
    private static final long TOTAL_COUNTDOWN_TIME = 3000; // 10 seconds
    private TextView timer ;
    private TextView title ;
    private ImageView imageViewGIF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_exercise_layout);

        //Khoi tao
        title = findViewById(R.id.title);
        timer = findViewById(R.id.timer);
        imageViewGIF = findViewById(R.id.imageViewGIF);
        renderingExercises = new ArrayList<>();
        listExercises = new ArrayList<>();
        Intent intent = getIntent();
        listExercises = (ArrayList<Exercise>) intent.getSerializableExtra("listExercises");

//        //set up array render exercise
         Exercise restGIF = new Exercise("rest",10,0,R.drawable.gif_rest_5);
        renderingExercises.addAll(listExercises);
        //them 1 gif rest sau moi 3 bai tap
        for (int i = 0; i < renderingExercises.size() ; i++) {
            if (i % 3 == 0 && i != 0){
                renderingExercises.add(i,restGIF);
            }
        }



        startCountdownTimer();

    }
    private void startCountdownTimer() {
//        changeGif();
        Glide.with(this).asGif().load(renderingExercises.get(currentGifIndex).getIndexGifInDrawable()).into(imageViewGIF);
        new CountDownTimer(TOTAL_COUNTDOWN_TIME, COUNTDOWN_INTERVAL) {

            @Override
            public void onTick(long millisUntilFinished) {
                NumberFormat f = new DecimalFormat("00");
                timer.setText(f.format(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                // The timer has finished, change the GIF and restart the timer
                if ((currentGifIndex + 1) == renderingExercises.size()){
                    changeGif();
                    cancel();
                    Intent intent = new Intent(StartExerciseActivity.this, ResultExerciseActivity.class);
                    intent.putExtra("listExercises",listExercises);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);

                }else {
                    changeGif();
                    startCountdownTimer();
                }
            }
        }.start();

    }
    private void changeGif() {
        // Load the next GIF
        if ((currentGifIndex + 1) % 3 == 0 && currentGifIndex != 0){
            title.setText("REST TIME");
            Glide.with(this).asGif().load(renderingExercises.get(currentGifIndex).getIndexGifInDrawable()).into(imageViewGIF);
            currentGifIndex = (currentGifIndex + 1) % renderingExercises.size();//
        }else {
            title.setText("GO!");
            Glide.with(this).asGif().load(renderingExercises.get(currentGifIndex).getIndexGifInDrawable()).into(imageViewGIF);
            currentGifIndex = (currentGifIndex + 1) % renderingExercises.size();//
        }
    }
}
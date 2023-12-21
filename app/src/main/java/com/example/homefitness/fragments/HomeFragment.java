package com.example.homefitness.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.homefitness.R;
import com.example.homefitness.activities.AppDrawerActivity;
import com.example.homefitness.activities.ListExerciseActivity;
import com.example.homefitness.activities.WelcomeActivity;
import com.example.homefitness.databases.MyDatabase;
import com.example.homefitness.models.Account;
import com.example.homefitness.models.Exercise;

import java.util.ArrayList;


public class HomeFragment extends AbstractFragment   {
    private MyDatabase myDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View fragment = null;
        // lay giao dien tuong ung dua vao fragment, 3 tham so: layout tuong ung, container , false
        fragment = inflater.inflate(R.layout.home_fragment, container, false);

        // Khoi tao database
        myDatabase = new MyDatabase(getActivity());
        // Lay layout
        ImageView imageRecent = fragment.findViewById(R.id.imageRecent);
        ImageView imageFavorite = fragment.findViewById(R.id.imageFavorite);
        ImageView imageChest = fragment.findViewById(R.id.imageChest);
        ImageView imageShoulder = fragment.findViewById(R.id.imageShoulder);
        ImageView imageBiceps = fragment.findViewById(R.id.imageBiceps);
        ImageView imageTriceps = fragment.findViewById(R.id.imageTriceps);
        ImageView imageAbs = fragment.findViewById(R.id.imageAbs);
        ImageView imageGlutes = fragment.findViewById(R.id.imageGlutes);


        // Bat su kien cho cac nut trong fragment
        imageRecent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListExerciseActivity.class);
                // Lay du lieu tu database
                Account ac = myDatabase.getAccount().get(0);

                String listIdRecentExercise = ac.getListIdRecentExercise();
                ArrayList<Exercise> listExerciseByCategory = new ArrayList<Exercise>();
                if (myDatabase.getListRecentExercise(listIdRecentExercise).size() != 0) {
                    listExerciseByCategory = myDatabase.getListRecentExercise(listIdRecentExercise);
                }
                // chuyển thêm dữ liệu
                intent.putExtra("title", "Recent");
                intent.putExtra("listExerciseByCategory", listExerciseByCategory);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        imageFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListExerciseActivity.class);
                ArrayList<Exercise> listExerciseByCategory = new ArrayList<Exercise>();
                listExerciseByCategory = myDatabase.getExerciseFavorite();
                // chuyển thêm dữ liệu
                intent.putExtra("title", "Favorite");
                intent.putExtra("listExerciseByCategory", listExerciseByCategory);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        imageChest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListExerciseActivity.class);
                String categoryId = "ch";
                ArrayList<Exercise> listExerciseByCategory = new ArrayList<Exercise>();
                listExerciseByCategory = myDatabase.getExerciseByCategoryId(categoryId);
                // chuyển thêm dữ liệu
                intent.putExtra("title", "Chest");
                intent.putExtra("listExerciseByCategory", listExerciseByCategory);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        imageShoulder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListExerciseActivity.class);
                String categoryId = "sh";
                ArrayList<Exercise> listExerciseByCategory = new ArrayList<Exercise>();
                listExerciseByCategory = myDatabase.getExerciseByCategoryId(categoryId);
                // chuyển thêm dữ liệu

                intent.putExtra("title", "Shoulder");
                intent.putExtra("listExerciseByCategory", listExerciseByCategory);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        imageBiceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListExerciseActivity.class);
                String categoryId = "bi";
                ArrayList<Exercise> listExerciseByCategory = new ArrayList<Exercise>();
                listExerciseByCategory = myDatabase.getExerciseByCategoryId(categoryId);
                // chuyển thêm dữ liệu

                intent.putExtra("title", "Biceps");
                intent.putExtra("listExerciseByCategory", listExerciseByCategory);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        imageTriceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListExerciseActivity.class);
                String categoryId = "tr";
                ArrayList<Exercise> listExerciseByCategory = new ArrayList<Exercise>();
                listExerciseByCategory = myDatabase.getExerciseByCategoryId(categoryId);
                // chuyển thêm dữ liệu

                intent.putExtra("title", "Triceps");
                intent.putExtra("listExerciseByCategory", listExerciseByCategory);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        imageAbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListExerciseActivity.class);
                String categoryId = "abs";
                ArrayList<Exercise> listExerciseByCategory = new ArrayList<Exercise>();
                listExerciseByCategory = myDatabase.getExerciseByCategoryId(categoryId);
                // chuyển thêm dữ liệu

                intent.putExtra("title", "Abs");
                intent.putExtra("listExerciseByCategory", listExerciseByCategory);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        imageGlutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListExerciseActivity.class);
                String categoryId = "gl";
                ArrayList<Exercise> listExerciseByCategory = new ArrayList<Exercise>();
                listExerciseByCategory = myDatabase.getExerciseByCategoryId(categoryId);
                // chuyển thêm dữ liệu

                intent.putExtra("title", "Glutes");
                intent.putExtra("listExerciseByCategory", listExerciseByCategory);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        return fragment;
    }
}
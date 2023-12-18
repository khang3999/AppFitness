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
import com.example.homefitness.databinding.AppDrawerLayoutBinding;
import com.example.homefitness.databinding.HomeFragmentBinding;
import com.example.homefitness.models.Exercise;

import java.util.ArrayList;


public class HomeFragment extends AbstractFragment   {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment = null;
        // lay giao dien tuong ung dua vao fragment, 3 tham so: layout tuong ung, container , false
        fragment = inflater.inflate(R.layout.home_fragment, container, false);

        // Lay layout
        ImageView imageChest = fragment.findViewById(R.id.imageChest);
        ImageView imageShoulder = fragment.findViewById(R.id.imageShoulder);
        ImageView imageBiceps = fragment.findViewById(R.id.imageBiceps);
        ImageView imageTriceps = fragment.findViewById(R.id.imageTriceps);
        ImageView imageAbs = fragment.findViewById(R.id.imageAbs);
        ImageView imageGlutes = fragment.findViewById(R.id.imageGlutes);


        // Bat su kien cho cac nut trong fragment
        imageChest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListExerciseActivity.class);
                String categoryName = "chest";
                // Có thể chuyển thêm dữ liệu nếu cần
                intent.putExtra("categoryName", categoryName);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        imageShoulder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListExerciseActivity.class);
                String categoryName = "shoulder";
                // Có thể chuyển thêm dữ liệu nếu cần
                intent.putExtra("categoryName", categoryName);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        imageBiceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListExerciseActivity.class);
                String categoryName = "biceps";
                // Có thể chuyển thêm dữ liệu nếu cần
                intent.putExtra("categoryName", categoryName);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        imageTriceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListExerciseActivity.class);
                String categoryName = "triceps";
                // Có thể chuyển thêm dữ liệu nếu cần
                intent.putExtra("categoryName", categoryName);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        imageAbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListExerciseActivity.class);
                String categoryName = "abs";
                // Có thể chuyển thêm dữ liệu nếu cần
                intent.putExtra("categoryName", categoryName);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        imageGlutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListExerciseActivity.class);
                String categoryName = "glutes";
                // Có thể chuyển thêm dữ liệu nếu cần
                intent.putExtra("categoryName", categoryName);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });


        return fragment;
    }
}
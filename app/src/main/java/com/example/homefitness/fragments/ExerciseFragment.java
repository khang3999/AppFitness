package com.example.homefitness.fragments;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.homefitness.R;
import com.example.homefitness.activities.ListExerciseActivity;
import com.example.homefitness.adapters.ExerciseAdapter;
import com.example.homefitness.models.Exercise;

import java.util.ArrayList;


public class ExerciseFragment extends AbstractFragment {
    private ArrayList<Exercise> listExerciseFromDatabase;
    private ExerciseAdapter adapter;

    private ArrayList<Integer> selectedRows = new ArrayList<>();
    private ArrayList<Exercise> selectedExercises = new ArrayList<>();
    private int backColor;
    private View prev;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment = null;
        // lay giao dien tuong ung dua vao fragment, 3 tham so: layout tuong ung, container , false
        fragment = inflater.inflate(R.layout.exercise_fragment, container, false);

        // Khoi tao mang chua gia tri
        listExerciseFromDatabase = new ArrayList<Exercise>();

        //Doc du lieu tu database
        //databaseAPIs.getAllExercise();
        ListView lvExercise = fragment.findViewById(R.id.lvExercise);
        Button btnGetStarted = fragment.findViewById(R.id.btnGetStarted);
        Button btnAddFavorite = fragment.findViewById(R.id.btnAddFavoriteList);

        //Xu ly su kien gui list bai tap da chon
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clear truoc khi them de k trung du lieu
                selectedExercises.clear();
                if (selectedRows.size() != 0){
                    //add tat ca exercise da chon de gui di
                    for (int i : selectedRows) {
                        selectedExercises.add(listExerciseFromDatabase.get(i));
                    }
                    Intent intent = new Intent(getActivity(), ListExerciseActivity.class);
                    intent.putExtra("selectedExercises",selectedExercises);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
            }
        });

        //Xu ly su kien gui list bai tap da chon vao yeu thich
        btnAddFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clear truoc khi them de k trung du lieu
                selectedExercises.clear();
                if (selectedRows.size() != 0){
                    //add tat ca exercise da chon de gui di
                    for (int i : selectedRows) {
                        selectedExercises.add(listExerciseFromDatabase.get(i));
                    }
                    Intent intent = new Intent(getActivity(), ListExerciseActivity.class);
                    intent.putExtra("selectedExercises",selectedExercises);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
            }
        });



        //Xu ly su kien chon item trong list view
        lvExercise.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(selectedRows.size() == 0){
                    backColor = view.getSolidColor();
                    view.setBackgroundColor(getResources().getColor(R.color.selectedRow));
                    selectedRows.add(i);
                    prev = view;
                }else{
                    if (selectedRows.contains(i)){
                        view.setBackgroundColor(backColor);
                        selectedRows.remove(selectedRows.indexOf(i));
                    }else{
                        view.setBackgroundColor(getResources().getColor(R.color.selectedRow));
                        selectedRows.add(i);

                    }
                }
            }
        });

        // TAO MANG TAM THOI

        Exercise exe1 = new Exercise("chest", 30, 100,R.drawable.gif_1);
        Exercise exe2 = new Exercise("Shoulder", 30, 200,R.drawable.gif_2);
        Exercise exe3 = new Exercise("Push Up", 30, 100,R.drawable.gif_3);
        Exercise exe4 = new Exercise("Alternating Superman", 30, 100,R.drawable.gif_4);



        listExerciseFromDatabase.add(exe1);
        listExerciseFromDatabase.add(exe2);
        listExerciseFromDatabase.add(exe3);
        listExerciseFromDatabase.add(exe4);

        adapter = new ExerciseAdapter(this.getActivity(), R.layout.my_listview_layout, listExerciseFromDatabase);
        lvExercise.setAdapter(adapter);
        return fragment;
    }
}
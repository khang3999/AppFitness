package com.example.homefitness.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.example.homefitness.R;
import com.example.homefitness.adapters.ExerciseAdapter;
import com.example.homefitness.databinding.MyListviewLayoutBinding;
import com.example.homefitness.models.Exercise;

import java.util.ArrayList;


public class ExerciseFragment extends AbstractFragment {
    private ArrayList<Exercise> listExerciseFromDatabase;
    private ExerciseAdapter adapter;

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


        // TAO MANG TAM THOI
        Exercise exe1 = new Exercise("chest", 30, 100,R.drawable.a);
        Exercise exe2 = new Exercise("Shoulder", 30, 200,R.drawable.b);

        listExerciseFromDatabase.add(exe1);
        listExerciseFromDatabase.add(exe2);

        adapter = new ExerciseAdapter(this.getActivity(), R.layout.my_listview_layout, listExerciseFromDatabase);
        lvExercise.setAdapter(adapter);
        return fragment;
    }
}
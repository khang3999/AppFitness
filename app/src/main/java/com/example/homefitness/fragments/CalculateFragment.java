package com.example.homefitness.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homefitness.R;


public class CalculateFragment extends AbstractFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = null;
        // lay giao dien tuong ung dua vao fragment, 3 tham so: layout tuong ung, container , false
        fragment = inflater.inflate(R.layout.calculate_fragment, container, false);



        return fragment;
    }
}
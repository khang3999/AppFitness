package com.example.homefitness.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homefitness.R;
import com.example.homefitness.databases.MyDatabase;
import com.example.homefitness.models.Account;

import java.text.DecimalFormat;


public class CalculateFragment extends AbstractFragment {
    private EditText edtHeight;
    private EditText edtWeight;
    private TextView tvResult;
    private TextView lbResult;
    private TextView lbConclude;
    private Button btnClear;
    private Button  btnUpdateInfo;
    private MyDatabase myDatabase;
    private  double height;
    private  double weight;
    View fragment = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // lay giao dien tuong ung dua vao fragment, 3 tham so: layout tuong ung, container , false
        fragment = inflater.inflate(R.layout.calculate_fragment, container, false);

        Button btnCalculate = fragment.findViewById(R.id.btnSubmit);
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

        btnUpdateInfo = fragment.findViewById(R.id.btnUpdateInfor);
        btnUpdateInfo.setEnabled(false);
        btnUpdateInfo.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_blue_grey_800));
        edtHeight = fragment.findViewById(R.id.edtHeight);
        edtWeight = fragment.findViewById(R.id.edtWeight);
        myDatabase = new MyDatabase(getActivity());;
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get cac thanh phan cua layout can su li
                if (!edtWeight.getText().toString().equals("") && !edtHeight.getText().toString().equals("")) {
                     height = Double.parseDouble(edtHeight.getText().toString()) / 100;
                     weight = Double.parseDouble(edtWeight.getText().toString());
                    tvResult = fragment.findViewById(R.id.tvResult);
                    lbResult = fragment.findViewById(R.id.lbResult);
                    lbConclude = fragment.findViewById(R.id.lbConclude);
                    btnClear = fragment.findViewById(R.id.btnClear);
                    double bmi = weight / (height * height);
                    tvResult.setText(decimalFormat.format(bmi));
                    tvResult.setVisibility(View.VISIBLE);
                    lbResult.setVisibility(View.VISIBLE);
                    btnUpdateInfo.setBackgroundColor(getResources().getColor(R.color.btnColor));
                    btnUpdateInfo.setEnabled(true);
                    lbConclude.setText("You' re " + getConclude(bmi));
                    lbConclude.setVisibility(View.VISIBLE);
                    btnClear.setVisibility(View.VISIBLE);

                    //Bat su kien cho UpdateInfo
                    btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Account account = myDatabase.getAccount().get(0);
                            int id = account.getId();
                            String name =  account.getName();
                            myDatabase.updateAccount(id,name,height*100,weight);
                            clearData();
                            account = myDatabase.getAccount().get(0);
                            String messBMI = "Update success";
                            Toast.makeText(getActivity(), messBMI, Toast.LENGTH_SHORT).show();

                            updateDataNavigationbar(account.getHeight(), account.getWeight());
                        }
                    });
                    //bat su kien clear data
                    btnClear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clearData();
                        }
                    });
                }
                else {
                    edtHeight.requestFocus();
                    String messBMI = "Please enter information!";
                    Toast.makeText(getActivity(), messBMI, Toast.LENGTH_SHORT).show();
                }
            }
        });


        return fragment;
    }
    private void updateDataNavigationbar(double height, double weight){
        TextView slideHeight = getActivity().findViewById(R.id.navigationView).findViewById(R.id.tvHeightData);
        TextView slideWeight = getActivity().findViewById(R.id.navigationView).findViewById(R.id.tvWeightData);
        TextView slideBmi = getActivity().findViewById(R.id.navigationView).findViewById(R.id.tvBmiData);

        slideHeight.setText(height + " Cm");
        slideWeight.setText(weight + " Kg");
        double bmi = weight/(height/100*height/100);
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        slideBmi.setText(decimalFormat.format(bmi) + "");
    }

    @Override
    public void onResume() {
        super.onResume();
        Account account = myDatabase.getAccount().get(0);
        updateDataNavigationbar(account.getHeight(), account.getWeight());
        TextView headerName = getActivity().findViewById(R.id.navigationView).findViewById(R.id.headerFullname);
        headerName.setText(account.getName());
    }

    public void clearData() {
        edtHeight.setText("");
        edtWeight.setText("");
        tvResult.setVisibility(View.INVISIBLE);
        lbResult.setVisibility(View.INVISIBLE);
        btnUpdateInfo.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_blue_grey_800));
        btnUpdateInfo.setEnabled(false);
        lbConclude.setText("");
        lbConclude.setVisibility(View.INVISIBLE);
        btnClear.setVisibility(View.INVISIBLE);
        edtHeight.requestFocus();
    }

    public static String getConclude(double bmi) {
        String conclude = "";
        if (bmi < 18.5) {
            conclude = "Underweight";
        } else if (bmi <= 24.9) {
            conclude = "Balance weight";
        } else if (bmi <= 29.9) {
            conclude = "Overweight";
        } else if (bmi <= 34.9) {
            conclude = "Fat";
        } else {
            conclude = "Dangerous obesity";
        }
        return conclude;
    }
}
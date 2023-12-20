package com.example.homefitness.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homefitness.R;

import java.text.DecimalFormat;


public class CalculateFragment extends AbstractFragment {
    private EditText edtHeight;
    private EditText edtWeight;
    private TextView tvResult;
    private TextView lbResult;
    private TextView lbConclude;
    private Button btnClear;
    private Button  btnUpdateInfo;
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

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get cac thanh phan cua layout can su li
                if (!edtWeight.getText().toString().equals("") && !edtHeight.getText().toString().equals("")) {
                    double height = Double.parseDouble(edtHeight.getText().toString()) / 100;
                    double weight = Double.parseDouble(edtWeight.getText().toString());
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
//                            Account.account.setWeight(weight);
//                            Account.account.setHeight(height * 100);
//                            clearData();
//                            String messBMI = "Update success";
//                            Toast.makeText(CalculatorBMIActivity.this, messBMI, Toast.LENGTH_SHORT).show();
//
//                            Intent intent = new Intent(getActivity(), ProfileAccountActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                            startActivity(intent);
//                            //Update info
//                            Log.d("w new", "onClick: " + Account.account.getWeight());
//                            Log.d("h new", "onClick: " + Account.account.getHeight());
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
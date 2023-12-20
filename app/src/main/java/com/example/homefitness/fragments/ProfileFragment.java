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
import com.example.homefitness.activities.InputNameActivity;
import com.example.homefitness.activities.ListExerciseActivity;
import com.example.homefitness.activities.WelcomeActivity;
import com.example.homefitness.databases.MyDatabase;
import com.example.homefitness.models.Account;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProfileFragment extends AbstractFragment {

    private MyDatabase myDatabase;
    private  Account account;
    private EditText lbName;
    private EditText tvHeight;
    private EditText tvWeight;
    private TextView tvBMI;
    private TextView tvConclude;
    private TextView tvTarget;
    private View fragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         fragment = null;
        // lay giao dien tuong ung dua vao fragment, 3 tham so: layout tuong ung, container , false
        fragment = inflater.inflate(R.layout.profile_fragment, container, false);

        //Lay account
        myDatabase = new MyDatabase(getActivity());
        account  =new Account();
        account  = myDatabase.getAccount().get(0);
        Log.d("test", "h pro: "+account.getHeight());
        Log.d("test", "w pro: "+account.getWeight());

        updateSetText(fragment);

        Button btnEdit = fragment.findViewById(R.id.btnEdit);
        Button btnSubmit = fragment.findViewById(R.id.btnSubmit);


        fragment.findViewById(R.id.btnEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lbName.setEnabled(true);
                tvHeight.setEnabled(true);
                tvWeight.setEnabled(true);

                btnEdit.setVisibility(View.INVISIBLE);
                btnSubmit.setVisibility(View.VISIBLE);
            }
        });

        fragment.findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lbName.getText().toString().isEmpty() && !tvHeight.getText().toString().isEmpty() && !tvWeight.getText().toString().isEmpty()){

                    double hei = Double.parseDouble(tvHeight.getText().toString());
                    double wei = Double.parseDouble(tvWeight.getText().toString());
                    boolean a = myDatabase.updateAccount(account.getId(),lbName.getText().toString(), hei, wei);
                    Log.d("test", "onClick: " + a);

                    lbName.setText(lbName.getText().toString());
                    tvHeight.setText(tvHeight.getText().toString());
                    tvWeight.setText(tvWeight.getText().toString());

                    DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                    tvBMI.setText(decimalFormat.format(calculateBMI(hei,wei)));

                    tvConclude.setText("You're "+CalculateFragment.getConclude(calculateBMI(hei,wei)));
                    lbName.setEnabled(false);
                    tvHeight.setEnabled(false);
                    tvWeight.setEnabled(false);


                    btnEdit.setVisibility(View.VISIBLE);
                    btnSubmit.setVisibility(View.INVISIBLE);
                }
                else{
                    Toast.makeText(getActivity(), "Please enter all the required data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fragment.findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabase.deleteAccount(account);
                getActivity().finishAffinity();
            }
        });

        return fragment;
    }

    public double calculateBMI(double height, double weight){
        double bmi = weight/(height/100*height/100);

        return bmi;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateSetText(fragment);
    }

    private void updateSetText(View fragment){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        lbName = fragment.findViewById(R.id.lbName);
        lbName.setText(account.getName());
        tvHeight = fragment.findViewById(R.id.tvHeight);
        tvHeight.setText(account.getHeight()+"");
        tvWeight = fragment.findViewById(R.id.tvWeight);
        tvWeight.setText(account.getWeight()+"");
         tvBMI = fragment.findViewById(R.id.tvBMI);

        double bmi = calculateBMI(account.getHeight(),account.getWeight());
        tvBMI.setText(decimalFormat.format(bmi));
         tvConclude = fragment.findViewById(R.id.tvConclude);
        tvConclude.setText("You're "+CalculateFragment.getConclude(bmi));

         tvTarget= fragment.findViewById(R.id.tvTarget);
        tvTarget.setText(account.getTarget());
    }
}
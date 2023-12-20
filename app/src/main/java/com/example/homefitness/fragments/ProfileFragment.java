package com.example.homefitness.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.homefitness.R;

import java.text.DecimalFormat;

public class ProfileFragment extends AbstractFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = null;
        // lay giao dien tuong ung dua vao fragment, 3 tham so: layout tuong ung, container , false
        fragment = inflater.inflate(R.layout.profile_fragment, container, false);

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        TextView lbName = fragment.findViewById(R.id.lbName);
        //lbName.setText(Account.account.getName());
        TextView tvHeight = fragment.findViewById(R.id.tvHeight);
        //tvHeight.setText(Account.account.getHeight()+" cm");
        TextView tvWeight = fragment.findViewById(R.id.tvWeight);

        //tvWeight.setText(Account.account.getWeight()+" kg");
        TextView tvBMI = fragment.findViewById(R.id.tvBMI);

       // double bmi = Account.account.getWeight()/(Account.account.getHeight()/100*Account.account.getHeight()/100);
        //tvBMI.setText(decimalFormat.format(bmi));
        TextView tvConclude = fragment.findViewById(R.id.tvConclude);
        //tvConclude.setText("You' re "+CalculatorBMIActivity.getConclude(bmi));

        TextView tvTarget= fragment.findViewById(R.id.tvTarget);
        //tvTarget.setText(Account.account.getTarget());


//        fragment.findViewById(R.id.btnEdit).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), InputNameActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                startActivity(intent);
//            }
//        });

        return fragment;
    }
}
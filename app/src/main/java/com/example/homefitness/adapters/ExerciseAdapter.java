package com.example.homefitness.adapters;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.Glide;
import com.example.homefitness.R;
import com.example.homefitness.databinding.MyListviewLayoutBinding;
import com.example.homefitness.models.Exercise;

import java.util.ArrayList;

public class ExerciseAdapter extends ArrayAdapter<Exercise> {
    //Fields
    private Activity context;
    private ArrayList<Exercise> listExercise;

    private ViewBinding binding;
    private ArrayList<Integer> selectedRows = new ArrayList<>();
    private ArrayList<Exercise> selectedExercises = new ArrayList<>();
    private ArrayList<Integer> listId = new ArrayList<>();
    private int backColor;
    private View prev;


    public ExerciseAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<Exercise> objects) {
        super(context, resource, objects);
        this.context = context;
        listExercise = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        //Chua co item nen can tao moi
        if (convertView == null) {
            binding = MyListviewLayoutBinding.inflate(context.getLayoutInflater(), parent, false);
            convertView = binding.getRoot();
            // Gan ket binding voi tung listView item
            convertView.setTag(binding);


        }
        // Tai su dung item
        else {
            binding = (MyListviewLayoutBinding) convertView.getTag();
        }

        //DO DATA
        // Do du lieu vao convertView
        Exercise exercise = listExercise.get(position);

        // Do gif vao
//        ((MyListviewLayoutBinding)binding).gifItem.setImageResource(exercise.getIndexGifInDrawable());

        int resourceId = getContext().getResources().getIdentifier(exercise.getGifName(), "drawable", getContext().getPackageName());


        Glide.with(context).asGif().load(resourceId).into(((MyListviewLayoutBinding) binding).gifItem);
//

        // Do name
        ((MyListviewLayoutBinding) binding).tvGifName.setText(exercise.getGifName());
        //Do time
        ((MyListviewLayoutBinding) binding).tvTime.setText("Time: " + exercise.getTime());
        //Do calorie
        ((MyListviewLayoutBinding) binding).tvCalorie.setText("Calorie out: " + exercise.getCalorie());

        //Doi mau cho item khi duoc chon
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleItemClick(view, position);
            }
        });

        if (selectedRows.contains(position)) {
            convertView.setBackgroundColor(context.getColor(R.color.selectedRow));
        } else {
            convertView.setBackgroundColor(context.getColor(android.R.color.transparent));
        }

        return convertView;
    }

    private void handleItemClick(View view, int position) {
        if (selectedRows.contains(position)) {
            view.setBackgroundColor(context.getColor(android.R.color.transparent));
            selectedRows.remove(Integer.valueOf(position));
        } else {
            view.setBackgroundColor(context.getColor(R.color.selectedRow));
            selectedRows.add(position);

            Exercise exercise = listExercise.get(position);
            listId.add(exercise.getId());
        }
    }
    public ArrayList<Integer> getSelectedRows(){
        return selectedRows;
    }
    public ArrayList<Integer> getListId(){
        return listId;
    }
}

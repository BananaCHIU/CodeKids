package com.edu.codekids.Learn;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.edu.codekids.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class JavaLearnFragment extends Fragment {


    public JavaLearnFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_java_learn, container, false);

        Button story = view.findViewById(R.id.button_story);
        story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StoryActivity.class);
                startActivity(intent);
            }
        });

        Button ch1 = view.findViewById(R.id.button_Ch1);
        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), T1Q1Activity.class);
                startActivity(intent);
            }
        });

        return view;
    }


}

package com.edu.codekids.Learn;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
        Button startButton = view.findViewById(R.id.java_ST_bytton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GameMainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


}

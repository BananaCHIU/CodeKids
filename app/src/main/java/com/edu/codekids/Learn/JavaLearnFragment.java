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
        /*ImageView lock2 = view.findViewById(R.id.lock_2);
        lock2.bringToFront();
        ImageView lock3 = view.findViewById(R.id.lock_3);
        lock3.bringToFront();
        ImageView lock4 = view.findViewById(R.id.lock_4);
        lock4.bringToFront();
        ImageView lock5 = view.findViewById(R.id.lock_5);
        lock5.bringToFront();
        ImageView lock6 = view.findViewById(R.id.lock_6);
        lock6.bringToFront();
        ImageView lock7 = view.findViewById(R.id.lock_7);
        lock7.bringToFront();*/

        return view;
    }


}

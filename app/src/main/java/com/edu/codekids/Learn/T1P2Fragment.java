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
public class T1P2Fragment extends Fragment {


    public T1P2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_t1_p2, container, false);
        Button game = (Button) view.findViewById(R.id.btn_game);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), T1Q1Activity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }

}

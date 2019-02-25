package com.edu.codekids;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class QuizFragment extends Fragment {
    private Button button;
    private static final int REQUEST_CODE_QUIZ = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        String[] chapters = {"Chapter 1", "Chapter 2", "Chapter 3" , "Chapter 4" , "Chapter 5"};

        ListView listView = (ListView) view.findViewById(R.id.chapters);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                chapters
        );

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    Intent intent = new Intent(getActivity(), quiz_start_screenActivity.class);
                    startActivity(intent);
                } else if (position >0){
                    Toast.makeText(getActivity(), "Complete the above quiz to unlock!", Toast.LENGTH_SHORT).show();

                }
            }
        });

       return view;

    }


}


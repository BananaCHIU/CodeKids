package com.edu.codekids.Learn;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.codekids.R;


public class StoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_story);

        ((ImageView)findViewById(R.id.story_animation)).setBackgroundResource(R.drawable.animation);
        ((AnimationDrawable)  ((ImageView) findViewById(R.id.story_animation)).getBackground()).start();
        getSupportActionBar().hide();


    }
}

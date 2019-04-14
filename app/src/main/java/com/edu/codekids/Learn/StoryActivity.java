package com.edu.codekids.Learn;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.codekids.R;


public class StoryActivity extends AppCompatActivity {

    MediaPlayer myMus = null; // a field of MediaPlayer

    @Override
    protected void onResume(){ // callback method, active: when interacting with user
        super.onResume(); // always call superclass
        if (myMus != null) myMus.start(); // start playing
    }
    @Override
    protected void onPause(){ // callback method, inactive: when no interacting
        super.onPause(); // always call superclass
        if (myMus != null) myMus.pause(); // pause playing
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myMus = MediaPlayer.create(this, R.raw.story_bgm); // bg sound file “bs” in raw folder
        myMus.setLooping(true); // set loop-playing mode
        setContentView(R.layout.activity_story);

        ((ImageView)findViewById(R.id.story_animation)).setBackgroundResource(R.drawable.animation);
        ((AnimationDrawable)  ((ImageView) findViewById(R.id.story_animation)).getBackground()).start();
        getSupportActionBar().hide();

        Button game = (Button)findViewById(R.id.btn_story);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoryActivity.this, T1Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

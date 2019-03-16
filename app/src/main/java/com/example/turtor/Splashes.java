package com.example.turtor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Splashes extends AppCompatActivity {
    private TextView tv,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashes);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashes);
        tv= (TextView) findViewById(R.id.text1);
        tv2=(TextView) findViewById(R.id.text2);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.transition);
        tv.startAnimation(animation);
        tv2.startAnimation(animation);
        final Intent intent = new Intent(this,Tutorial1.class);
        Thread timer =new Thread (){
            public void run () {
                try {
                    sleep(3000);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
                timer.start();
    }
}

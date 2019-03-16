package com.edu.codekids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Tutorial1 extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    private Button mNextBtn;
    private Button mBackBtn;
    private Button mQuizBtn;
    private int mCurrentPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial1);


        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);
        mQuizBtn = (Button) findViewById(R.id.quizButton);
        mNextBtn = (Button) findViewById(R.id.nextButton);
        mBackBtn = (Button) findViewById(R.id.previousButton);

        sliderAdapter = new SliderAdapter( this);

        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSlideViewPager.setCurrentItem(mCurrentPage+1);
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage-1);
            }
        });
    }

    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        mDotLayout.removeAllViews();
        for(int i =0; i<mDots.length;i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(i+1 +"  ");
            mDots[i].setTextColor(getResources().getColor(R.color.transWhite));

            mDotLayout.addView(mDots[i]);
        }

        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.white));
        }

    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            mCurrentPage = i;
            if(i == 0 ){
                mQuizBtn.setEnabled(false);
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(false);
                mBackBtn.setVisibility(View.INVISIBLE);
                mQuizBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("Next");
                mQuizBtn.setText("");
                mBackBtn.setText("");

            } else if(i==mDots.length -1){

                mNextBtn.setEnabled(false);
                mBackBtn.setEnabled(true);
                mQuizBtn.setEnabled(true);

                mBackBtn.setVisibility(View.VISIBLE);
                mNextBtn.setVisibility(View.INVISIBLE);
                mQuizBtn.setVisibility(View.VISIBLE);

                mQuizBtn.setText("Quiz");
                mNextBtn.setText("");
                mBackBtn.setText("Back");

                mQuizBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(Tutorial1.this, quiz_start_screenActivity.class));

                    }
                });

            }else{
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mQuizBtn.setEnabled(false);

                mNextBtn.setVisibility(View.VISIBLE);
                mBackBtn.setVisibility(View.VISIBLE);
                mQuizBtn.setVisibility(View.INVISIBLE);

                mQuizBtn.setText("");
                mNextBtn.setText("Next");
                mBackBtn.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}

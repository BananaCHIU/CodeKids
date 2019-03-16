package com.edu.codekids;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    //Arrays
    public String[] slide_title = {
            "Basic function",
            "Title",
            "Title"
    };

    public String[] slide_content= {
            "content goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes here",
            "content goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes here",
            "content goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes herecontent goes here"
    };


    @Override
    public int getCount() {
        return slide_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position){

        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        TextView slideTitle = (TextView) view.findViewById(R.id.slide_title);
        TextView slideContent = (TextView) view.findViewById(R.id.slide_content);

        slideTitle.setText(slide_title[position]);
        slideContent.setText(slide_content[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){

        container.removeView((RelativeLayout)object);
    }



}
package com.edu.codekids;

import java.util.Date;

public class Post {
    private User PuId, PuName, PuType;
    private String pTitle, pContent;
    private Date pTime;

    public Post(){}

    public Post(String title, String content, Date time){
        pTitle =title; pContent=content; pTime=time;
    }

    public Date getpTime() {
        return pTime;
    }

    public String getpContent() {
        return pContent;
    }

    public String getpTitle() {
        return pTitle;
    }

}

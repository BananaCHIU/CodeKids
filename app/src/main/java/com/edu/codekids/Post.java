package com.edu.codekids;

import java.util.Date;

public class Post {
    private User PuId, PuName, PuType;
    private String pTitle, pContent;
    private Date pTime;
    private int commentCount;

    public Post(){}

    public Post(String title, String content, Date time, int comcount){
        pTitle =title; pContent=content; pTime=time; commentCount=comcount;
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

    public int getCommentCount() {
        return commentCount;
    }

}

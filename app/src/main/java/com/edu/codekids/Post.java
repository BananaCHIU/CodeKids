package com.edu.codekids;

import java.util.Date;

public class Post {
    private User PuId, PuName, PuType;
    private String pTitile, pContent;
    private Date pTime;
    private int commentCount;

    public Post(){}

    public Post(String title, String content, Date time, int comcount){
        pTitile=title; pContent=content; pTime=time; commentCount=comcount;
    }

    public Date getpTime() {
        return pTime;
    }

    public String getpContent() {
        return pContent;
    }

    public String getpTitile() {
        return pTitile;
    }

    public int getCommentCount() {
        return commentCount;
    }

}

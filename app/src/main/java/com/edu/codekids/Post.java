package com.edu.codekids;

import java.util.Date;

public class Post {
    private User pUser;
    private String pTitle, pContent;
    private Date pTime;

    public Post(){}

    public Post(User user,String title, String content, Date time){
        pUser = user; pTitle =title; pContent=content; pTime=time;
    }

    public User getUser() { return pUser; }

    public String getUserID () { return pUser.getuId();}

    public String getUserName() { return pUser.getuName();}

    public String getUserType() { return pUser.getuType();}

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

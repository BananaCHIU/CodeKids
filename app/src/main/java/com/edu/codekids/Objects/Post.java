package com.edu.codekids.Objects;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Post implements Serializable {
    private User pUser;
    private String pId, pTitle, pContent, pLang;
    private Date pTime;
    private List<Comment> pComments;

    public Post(){}

    public Post(User user,String id, String title, String content, String lang, Date time, List<Comment> comments){
        pUser = user; pId = id; pTitle =title; pContent=content; pLang=lang; pTime=time; pComments = comments;
    }

    public User getUser() { return pUser; }

    public void setpUser(User pUser) {
        this.pUser = pUser;
    }

    public Date getpTime() {
        return pTime;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpContent() {
        return pContent;
    }

    public String getpLang() {
        return pLang;
    }

    public String getpTitle() {
        return pTitle;
    }

    public List<Comment> getpComments() { return pComments; }

    public void setpComments(List<Comment> comments) { pComments = comments; }

}

package com.edu.codekids;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private User cUser;
    private String cContent;
    private Date cTime;
    private int cVote;


    public Comment(){}

    public Comment(User user, String content, Date time, int vote){
        cUser=user; cContent=content; cTime=time; cVote=vote;
    }

    public String getCuId() {
        return cUser.getuId();
    }

    public String getCuName() {
        return cUser.getuName();
    }

    public String getCuType() { return cUser.getuType(); }

    public String getcContent() {
        return cContent;
    }

    public Date getcTime() {
        return cTime;
    }

    public int getcVote() { return cVote; }

    public void setcVote(int newVote) { cVote = newVote; }

}

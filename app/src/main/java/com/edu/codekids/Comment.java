package com.edu.codekids;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable, Comparable<Comment>{
    private User cUser;
    private String cContent;
    private Date cTime;
    private int cVote;


    public Comment(){}

    public Comment(User user, String content, Date time, int vote){
        cUser=user; cContent=content; cTime=time; cVote=vote;
    }

    public User getcUser() {
        return cUser;
    }

    public String getcContent() {
        return cContent;
    }

    public Date getcTime() {
        return cTime;
    }

    public int getcVote() { return cVote; }

    public void setcVote(int newVote) { cVote = newVote; }

    @Override
    public int compareTo(Comment cm) {
        return getcTime().compareTo(cm.getcTime());
    }

}

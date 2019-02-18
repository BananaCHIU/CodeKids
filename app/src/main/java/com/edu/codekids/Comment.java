package com.edu.codekids;

import java.util.Date;

public class Comment extends Post {
    private User CuId, CuName, CuType;
    private String cContent;
    private Date cTime;
    private int cVote;

    public Comment(){}

    public Comment(User uid, User name, User type, String content, Date time, int vote){
        CuId=uid; CuName=name; CuType=type; cContent=content; cTime=time; cVote=vote;
    }

    public User getCuId() {
        return CuId;
    }

    public User getCuName() {
        return CuName;
    }

    public User getCuType() {
        return CuType;
    }

    public String getcContent() {
        return cContent;
    }

    public Date getcTime() {
        return cTime;
    }

    public int getcVote() {
        return cVote;
    }

}

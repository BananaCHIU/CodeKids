package com.edu.codekids;

public class User {
    private String uId, uName ,uType;
    public User(){ }

    public User(String uid, String name, String type){
        uId=uid; uName=name; uType=type;
    }

    public String getuId() {
        return uId;
    }

    public String getuName() {
        return uName;
    }

    public String getuType() {
        return uType;
    }
}

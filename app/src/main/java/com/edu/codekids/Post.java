package com.edu.codekids;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static List<Post> samplePosts()
    {
        ArrayList<Post> posts= new ArrayList<Post>();

        User[] u = new User[8];
        u[0] = new User("001", "I", "Student");
        u[1] = new User("002", "love", "Teacher");
        u[2] = new User("003", "Project", "Student");
        u[3] = new User("004", "on", "Student");
        u[4] = new User("005", "knowledge", "Teacher");
        u[5] = new User("006", "Projects", "Teacher");
        u[6] = new User("007", "Development", "Student");
        u[7] = new User("008", "!!!", "Teacher");

        for (int i = 0; i < 8; i++)
        {
            posts.add(new Post(u[i], "Sample Post title" + (i + 1), "Sample Post Content" + (i + 1), new Date()));
        }

        return posts;
    }
}

package com.edu.codekids;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment {
    private Post parentPost;
    private User cUser;
    private String cContent;
    private Date cTime;
    private int cVote, cDislike;


    public Comment(){}

    public Comment(User user, String content, Date time, int vote, int dislike){
        cUser=user; cContent=content; cTime=time; cVote=vote; cDislike = dislike;
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

    public int getcDislike() { return cDislike; }

    public Post getParentPost() { return parentPost; }

    public void setParentPost(Post post) { parentPost = post; }

    public static List<Comment> sampledata()
    {
        ArrayList<Comment> sample = new ArrayList<Comment>();

        User pU, cU1, cU2, cU3, cU4, cU5, cU6, cU7;
        pU = new User("009", "This", "Student");
        cU1 = new User("010", "is", "Teacher");
        cU2 = new User("011", "a", "Student");
        cU3 = new User("012", "sample", "Student");
        cU4 = new User("013", "list", "Teacher");
        cU5 = new User("014", "of", "Teacher");
        cU6 = new User("015", "comment", "Student");
        cU7 = new User("016", "data", "Teacher");

        Post post = new Post(pU, "Sample Title", "Sample Post Content", new Date());

        Comment c1 = new Comment(cU1, "Sample first comment", new Date(), 10, 9);
        sample.add(c1);
        Comment c2 = new Comment(cU2, "Sample second comment", new Date(), 8, 7);
        sample.add(c2);
        Comment c3 = new Comment(cU3, "Sample third comment", new Date(), 6, 5);
        sample.add(c3);
        Comment c4 = new Comment(cU4, "Sample fourth comment", new Date(), 4, 3);
        sample.add(c4);
        Comment c5 = new Comment(cU5, "Sample fifth comment", new Date(), 2, 1);
        sample.add(c5);
        Comment c6 = new Comment(cU6, "Sample sixth comment", new Date(), 0, 1);
        sample.add(c6);
        Comment c7 = new Comment(cU7, "Sample seventh comment", new Date(), 2, 3);
        sample.add(c7);

        for (int i = 0; i < sample.size(); i++)
        {
            sample.get(i).setParentPost(post);
        }

        return sample;
    }
}

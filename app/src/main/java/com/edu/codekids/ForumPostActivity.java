package com.edu.codekids;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import org.ocpsoft.prettytime.PrettyTime;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class ForumPostActivity extends AppCompatActivity
{

    public static Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_post);

        post = (Post) getIntent().getSerializableExtra("post");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textView = (TextView)toolbar.findViewById(R.id.toolbarTextView);
        textView.setText(post.getpTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final CardView pCV;
        final TextView pUserName;
        final TextView pDate;
        final TextView pContent;
        final FloatingActionButton newComment;
        PrettyTime prettyTime = new PrettyTime(Locale.getDefault());

        pCV = (CardView)findViewById(R.id.post_CardView);
        pUserName = (TextView)findViewById(R.id.post_UserName);
        pDate = (TextView)findViewById(R.id.post_Date);
        pContent = (TextView)findViewById(R.id.post_Content);
        newComment = (FloatingActionButton) findViewById(R.id.btn_new_comment);

        pUserName.setText(post.getUser().getuName());
        pDate.setText(prettyTime.format(post.getpTime()));
        pContent.setText(post.getpContent());

        RecyclerView rv = (RecyclerView)findViewById(R.id.comment_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setNestedScrollingEnabled(false);
        CommentRVAdapter adapter = new CommentRVAdapter(post.getpComments());
        rv.setAdapter(adapter);

    }

    public void newCommentBtnClicked(View v){
        Intent intent = new Intent(this, NewCommentActivity.class);
        intent.putExtra("post", post);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        //Need to refresh after posting new comment

    }
}

package com.edu.codekids;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.ocpsoft.prettytime.PrettyTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ForumPostActivity extends AppCompatActivity
{

    private static final String TAG = "Message: ";
    public static Post post;
    private CommentRVAdapter adapter;

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
        final TextView pUserType;
        final TextView pDate;
        final TextView pContent;
        final FloatingActionButton newComment;
        PrettyTime prettyTime = new PrettyTime(Locale.getDefault());

        pCV = (CardView)findViewById(R.id.post_CardView);
        pUserName = (TextView)findViewById(R.id.post_UserName);
        pUserType = (TextView)findViewById(R.id.post_uType);
        pDate = (TextView)findViewById(R.id.post_Date);
        pContent = (TextView)findViewById(R.id.post_Content);
        newComment = (FloatingActionButton) findViewById(R.id.btn_new_comment);

        pUserName.setText(post.getUser().getuName());
        String tempType = post.getUser().getuType();
        String output = tempType.substring(0, 1).toUpperCase() + tempType.substring(1);
        pUserType.setText(output);
        pDate.setText(prettyTime.format(post.getpTime()));
        pContent.setText(post.getpContent());

        RecyclerView rv = (RecyclerView)findViewById(R.id.comment_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setNestedScrollingEnabled(false);
        List<Comment> cm = post.getpComments();
        Collections.sort(cm, Collections.reverseOrder());
        adapter = new CommentRVAdapter(cm);
        rv.setAdapter(adapter);

    }

    public void newCommentBtnClicked(View v){
        Intent intent = new Intent(this, NewCommentActivity.class);
        intent.putExtra("post", post);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        //Need to refresh after posting new comment

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //Update final vote number of all comments
        List<Comment> comments = adapter.getComments();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String lang = null;
        if(post.getpLang().equals("Java")) lang = "javaPost";
        else if (post.getpLang().equals("Pascal")) lang = "pascalPost";
        db.collection(lang).document(post.getpId())
                .update("pComments", comments).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully updated!");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }
}

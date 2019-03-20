package com.edu.codekids;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.ocpsoft.prettytime.PrettyTime;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ForumPostActivity extends AppCompatActivity
{

    private static final String TAG = "Message: ";
    private Post post;
    private String lang = null;
    private CommentRVAdapter adapter;
    private SwipeRefreshLayout mySwipeRefreshLayout;
    private FirebaseFirestore db;

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

        mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mySwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i(TAG, "onRefresh called from SwipeRefreshLayout");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        refresh();

                    }
                }
        );
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
        Collections.sort(cm, new Comparator<Comment>(){
            public int compare(Comment obj1, Comment obj2) {
                return Integer.compare(obj2.getcVote(), obj1.getcVote());
            }
        });
        adapter = new CommentRVAdapter(cm,lang,post.getpId());
        rv.setAdapter(adapter);
    }

    public void newCommentBtnClicked(View v){
        Intent intent = new Intent(this, NewCommentActivity.class);
        intent.putExtra("post", post);
        startActivity(intent);
    }

    public void refreshBtnClicked(View v){
        refresh();
    }

    private void refresh(){
        db = FirebaseFirestore.getInstance();
        if (post.getpLang().equals("Java")) lang = "javaPost";
        else if(post.getpLang().equals("Pascal")) lang = "pascalPost";
        final DocumentReference docRef = db.collection(lang).document(post.getpId());

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        HashMap map  = (HashMap) document.get("user");
                        User temp = new User (map.get("uId").toString(), map.get("uName").toString(), map.get("uType").toString());
                        post = document.toObject(Post.class);
                        post.setpUser(temp);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                    RecyclerView rv = (RecyclerView)findViewById(R.id.comment_RecyclerView);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ForumPostActivity.this);
                    rv.setLayoutManager(linearLayoutManager);
                    rv.setNestedScrollingEnabled(false);
                    List<Comment> cm = post.getpComments();
                    Collections.sort(cm, new Comparator<Comment>(){
                        public int compare(Comment obj1, Comment obj2) {
                            return Integer.compare(obj2.getcVote(), obj1.getcVote());
                        }
                    });
                    adapter = new CommentRVAdapter(cm,lang,post.getpId());
                    rv.setAdapter(adapter);
                    mySwipeRefreshLayout.setRefreshing(false);
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }

    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        //Need to refresh after posting new comment
        refresh();
    }

}

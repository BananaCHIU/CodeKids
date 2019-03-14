package com.edu.codekids;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

public class NewCommentActivity extends AppCompatActivity {

    Post post;
    User user = SignedInActivity.getCurrentuser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comment);

        Toolbar toolbar = findViewById(R.id.toolbar);

        post = (Post) getIntent().getSerializableExtra("post");
        EditText title = (EditText) findViewById(R.id.cmTitle);
        EditText content = (EditText) findViewById(R.id.inputCm);

        title.setText(post.getpTitle());
    }

    public void postBtnClicked(View v){

    }

    public void cancelBtnClicked(View v){
        finish();
    }

}

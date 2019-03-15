package com.edu.codekids;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class NewCommentActivity extends AppCompatActivity {

    private static final String TAG = "Message: ";
    Post post;
    User user = SignedInActivity.getCurrentuser();
    List<Comment> comments = new ArrayList<Comment>();
    EditText title, content;
    ImageButton btn_cancel;
    Button btn_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comment);
        post = (Post) getIntent().getSerializableExtra("post");
        Toolbar toolbar = findViewById(R.id.toolbar);
        btn_post = (Button) findViewById(R.id.button_post_cm);
        btn_cancel = (ImageButton) findViewById(R.id.button_cancel_cm);
        title = (EditText) findViewById(R.id.cmTitle);
        content = (EditText) findViewById(R.id.inputCm);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelBtnClicked();
            }
        });

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postBtnClicked();
            }
        });
        title.setText(post.getpTitle());
    }

    public void postBtnClicked(){
        uploadComment();
    }

    public void cancelBtnClicked(){
        finish();
    }

    private void uploadComment(){
        String lang = null;
        if (post.getpLang().equals("Java")) lang = "javaPost";
        else if(post.getpLang().equals("Pascal")) lang = "pascalPost";

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection(lang).document(post.getpId());
        downloadComment(docRef);
    }

    private void addDocument(List<Comment> cm, DocumentReference docRef){
        docRef.update("pComments", cm)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }

    private void downloadComment(final DocumentReference ref){
        final List<Comment> cm = new ArrayList<Comment>();

        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        cm.clear();
                        ArrayList temp =(ArrayList) document.get("pComments");
                        for (int i=0; i<temp.size(); i++){
                            HashMap map = (HashMap) temp.get(i);
                            HashMap uMap = (HashMap) map.get("cUser");
                            User user = new User(uMap.get("uId").toString(),uMap.get("uName").toString(),uMap.get("uType").toString());
                            Timestamp time = (Timestamp) map.get("cTime");
                            cm.add(new Comment(user, map.get("cContent").toString(), time.toDate(), (int) (long) map.get("cVote")));
                        }
                        Date newTime = new Date();
                        Comment add = new Comment(user, content.getText().toString(), newTime, 0 );
                        cm.add(add);
                        addDocument(cm,ref);
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
}

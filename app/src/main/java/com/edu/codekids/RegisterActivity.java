package com.edu.codekids;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "Error: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        MaterialButton button = findViewById(R.id.save_button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText name = findViewById(R.id.name);
                RadioGroup type = findViewById(R.id.type);
                switch(type.getCheckedRadioButtonId()){
                    case R.id.teacher:
                        addDocument(name.getText().toString(),"teacher");
                        break;
                    case R.id.student:
                        addDocument(name.getText().toString(),"student");
                        break;
                }
                Intent intent = new Intent(view.getContext(), SignedInActivity.class);
                RegisterActivity.this.startActivity(intent);
                finish();
            }
        });
    }

    private void addDocument(String name, String type){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser fbuser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = fbuser.getUid();
        Map<String, Object> user = new HashMap<>();
        user.put("uid", uid);
        user.put("name", name);
        user.put("type", type);

        db.collection("users").document(uid)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please input your information to continue", Toast.LENGTH_SHORT).show();
    }


}

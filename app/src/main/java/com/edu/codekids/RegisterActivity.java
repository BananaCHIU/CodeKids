package com.edu.codekids;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
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
    private static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final MaterialButton button = findViewById(R.id.save_button);
        RadioButton student = (RadioButton) findViewById(R.id.student);
        student.setChecked(true);

        button.setOnClickListener(new View.OnClickListener() {
            private boolean textValid = false;
            @Override
            public void onClick(View view) {
                TextInputLayout nameLayout = findViewById(R.id.InputLayout);
                TextInputEditText name = findViewById(R.id.name);
                FirebaseUser fbuser = FirebaseAuth.getInstance().getCurrentUser();
                String uid = fbuser.getUid();
                if(name.length() == 0){
                    nameLayout.setError("Nickname Cannot Be Empty");
                    textValid= false;
                }
                else{
                    nameLayout.setError(null);
                    textValid = true;
                }
                RadioGroup type = findViewById(R.id.type);

                if (type.getCheckedRadioButtonId() == R.id.teacher) {
                    user = new User(uid, name.getText().toString(), "teacher");
                    addDocument(user);
                } else if (type.getCheckedRadioButtonId() == R.id.teacher) {
                    user = new User(uid, name.getText().toString(), "student");
                    addDocument(user);
                }
                if (textValid) {
                    Intent intent = new Intent(view.getContext(), SignedInActivity.class);
                    intent.putExtra("currentuser", user);
                    RegisterActivity.this.startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void addDocument(User user){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(user.getuId()).set(user);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please input your information to continue", Toast.LENGTH_SHORT).show();
    }


}

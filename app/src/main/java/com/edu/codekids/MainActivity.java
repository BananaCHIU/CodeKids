package com.edu.codekids;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            // already signed in
            startActivity(new Intent(this, SignedInActivity.class));
            finish();
        } else {
            // not signed in
            startActivity(new Intent(this, AuthActivity.class));
            finish();
        }

        Button buttonQuiz = findViewById(R.id.button_quiz);
        buttonQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quiz();
            }
        });
    }

    private void Quiz() {
        Intent intent = new Intent(MainActivity.this, quiz_start_screenActivity.class);

    }

}

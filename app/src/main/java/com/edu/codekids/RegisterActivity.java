package com.edu.codekids;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

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
                        User user = new User();
                        break;
                    case R.id.student:

                        break;
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please input your information to continue", Toast.LENGTH_SHORT).show();
    }


}

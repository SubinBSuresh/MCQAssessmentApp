package com.example.assessmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FacultySignup_4 extends AppCompatActivity {

    private ImageView imgBack;
    private Button btnSignup;
    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_signup_4);
        imgBack = findViewById(R.id.backArrow);
        btnSignup = findViewById(R.id.btn_facultySignUp);
        etName = findViewById(R.id.et_faculty_signUpName);
        etEmail = findViewById(R.id.et_faculty_signUpEmail);
        etPassword = findViewById(R.id.et_faculty_signUpPassword);
        etConfirmPassword = findViewById(R.id.et_faculty_signUpConfirmPassword);

        //Go back
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FacultyLogin_2.class));
            }
        });

        //Signup
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check if both passwords match
                //Add data to database

                startActivity(new Intent(getApplicationContext(), FacultyLogin_2.class));

            }
        });
    }
}
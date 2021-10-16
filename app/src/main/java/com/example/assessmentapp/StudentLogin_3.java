package com.example.assessmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class StudentLogin_3 extends AppCompatActivity {
    private Button btnSignUp;
    private Button btnLogin;
    private ImageView imgBack;

    private EditText etStudentEmail;
    private EditText getEtStudentPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login_3);
        imgBack = findViewById(R.id.backArrow);
        btnSignUp = findViewById(R.id.btn_student_signUp);
        btnLogin = findViewById(R.id.btn_student_login);

        etStudentEmail =findViewById(R.id.et_student_loginEmail);
        getEtStudentPassword = findViewById(R.id.et_student_loginPassword);

        //Go to Signup page
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StudentSignup_5.class));
            }
        });

        //Go Back
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),LandingPage_1.class));
            }
        });

        //Go to student Dashboard
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if the email and password matches in database
                //If true only, go to question page
                startActivity(new Intent(getApplicationContext(),StudentDashboard_11.class));
            }
        });

    }
}
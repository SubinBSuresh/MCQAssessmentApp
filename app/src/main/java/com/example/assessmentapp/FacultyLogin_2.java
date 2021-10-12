package com.example.assessmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FacultyLogin_2 extends AppCompatActivity {
    private ImageView imgBack;
    private Button btnLogin;
    private Button btnSignup;
    private EditText etFacultyEmail;
    private EditText etEtFacultyPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_login_2);
        imgBack = findViewById(R.id.backArrow);
        btnLogin = findViewById(R.id.btn_faculty_login);
        btnSignup = findViewById(R.id.btn_faculty_signUp);

        etFacultyEmail = findViewById(R.id.et_faculty_loginEmail);
        etEtFacultyPassword = findViewById(R.id.et_faculty_loginPassword);

        //Go back
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), LandingPage_1.class));

            }
        });

        //Go to Faculty Dashboard
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check if the email and password matches in database
                //If true only, go to question page

                startActivity(new Intent(getApplicationContext(), QuestionSection_6.class));

            }
        });

        //Go to Faculty SignUp
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FacultyLogin_2.this, FacultySignup_4.class));
            }
        });
    }
}
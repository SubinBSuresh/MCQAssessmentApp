package com.example.assessmentapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentSignup_5 extends AppCompatActivity {

    private ImageView imgBack;
    private Button btnSignUp;
    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etRepassword;

    SharedPreferences sp1;
    private TheHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_signup_5);
        db = new TheHelper(this);
        imgBack = findViewById(R.id.backArrow);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sp1 = getSharedPreferences("Registered", MODE_PRIVATE);
       /* if (sp1.getBoolean("registered",false)){
            goToMain();
        }*/


        etName = findViewById(R.id.et_student_signUpName);
        etEmail = findViewById(R.id.et_student_signUpEmail);
        etPassword = findViewById(R.id.et_student_signUpPassword);
        etRepassword = findViewById(R.id.et_student_signUpConfirmPassword);
        btnSignUp = findViewById(R.id.btn_studentSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String repassword = etRepassword.getText().toString();

                // Password Checking here
                if (user.equals("") || email.equals("") || password.equals("") || repassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill all the Fields", Toast.LENGTH_LONG).show();
                } else {
                    if (password.equals(repassword)) {
                        Boolean userCheckResult = db.checkStudentUsername(user);
                        if (userCheckResult == false) {
                            Boolean regResult = db.insertStudentData(user, email, password);
                            if (regResult == true) {
                                Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), StudentLogin_3.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "User already exists Please LOGIN", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Passwords Do not Match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void goToMain() {
        startActivity(new Intent(getApplicationContext(), StudentDashboard_11.class));
        finish();
    }
}
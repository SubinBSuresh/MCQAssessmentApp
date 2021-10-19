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


public class FacultySignup_4 extends AppCompatActivity {

    private ImageView imgBack;
    private Button btnSubmit;
    private EditText etFName;
    private EditText etFEmail;
    private EditText etFPassword;
    private EditText etRepassword;


    SharedPreferences sp;
    private TheHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_signup_4);
        sp = getSharedPreferences("Registered", MODE_PRIVATE);

        /*if (sp.getBoolean("registered",false)){
            goToMain();
        }*/

        imgBack = findViewById(R.id.backArrow);
        btnSubmit = findViewById(R.id.btn_facultySignUp);
        etFName = findViewById(R.id.et_faculty_signUpName);
        etFEmail = findViewById(R.id.et_faculty_signUpEmail);
        etFPassword = findViewById(R.id.et_faculty_signUpPassword);
        etRepassword = findViewById(R.id.et_faculty_signUpConfirmPassword);

        db = new TheHelper(this);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etFName.getText().toString();
                String email = etFEmail.getText().toString();
                String password = etFPassword.getText().toString();
                String repassword = etRepassword.getText().toString();

                if (user.equals("") || email.equals("") || password.equals("") || repassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill all the Fields", Toast.LENGTH_LONG).show();
                } else {
                    if (password.equals(repassword)) {
                        Boolean userCheckResult = db.checkFacultyUsername(user);
                        if (userCheckResult == false) {
                            Boolean regResult = db.insertFacultyData(user, email, password);
                            if (regResult == true) {
                                Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_LONG).show();
                                sp.edit().putBoolean("registered", true).apply();
                                Intent intent = new Intent(getApplicationContext(), FacultyLogin_2.class);
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
        startActivity(new Intent(getApplicationContext(), QuestionSection_6.class));
        finish();
    }
}
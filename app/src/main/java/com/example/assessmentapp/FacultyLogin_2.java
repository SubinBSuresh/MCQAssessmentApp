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

public class FacultyLogin_2 extends AppCompatActivity {

    ImageView imgBack;
    Button tvSignUp;
    EditText useremail;
    EditText passwordLogin;
    Button btnLogin;
    SharedPreferences sp;
    String facultyName;
    TheHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_login_2);
        db = new TheHelper(this);

        sp = getSharedPreferences("login", MODE_PRIVATE);
        /*if(sp.getBoolean("logged",false)){
            goToMain();
        }*/
        useremail = findViewById(R.id.et_faculty_loginEmail);
        passwordLogin = findViewById(R.id.et_faculty_loginPassword);
        btnLogin = findViewById(R.id.btn_faculty_login);
        imgBack = findViewById(R.id.backArrow);
        tvSignUp = findViewById(R.id.btn_faculty_signUp);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LandingPage_1.class));
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FacultySignup_4.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String facultyemail = useremail.getText().toString();
                String facultypassword = passwordLogin.getText().toString();
                if (facultyemail.equals("") || facultypassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter credentials", Toast.LENGTH_LONG).show();
                } else {
                    Boolean resultLogin = db.checkFacultyUsernamePassword(facultyemail, facultypassword);
                    if (resultLogin == true) {

                        facultyName = db.getSFacultyName(facultyemail);
                        goToMain();
                        sp.edit().putBoolean("logged", true).apply();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid user", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void goToMain() {
        Intent intent = new Intent(getApplicationContext(), FacultyDashboard_12.class);
        intent.putExtra("FacultyName", facultyName);
        startActivity(intent);
        finish();
    }

}
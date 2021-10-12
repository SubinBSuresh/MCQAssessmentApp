package com.example.assessmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LandingPage_1 extends AppCompatActivity {
    private Button btnFacultyLoginPage;
    private Button btnStudentLoginPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page_1);
        btnStudentLoginPage =findViewById(R.id.btn_studentLoginPage);
        btnFacultyLoginPage =findViewById(R.id.btn_facultyLoginPage);

        //Go to Student Login Page
        btnStudentLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StudentLogin_3.class));
            }
        });

        //Go to Faculty Login Page
        btnFacultyLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FacultyLogin_2.class));
            }
        });

    }
}
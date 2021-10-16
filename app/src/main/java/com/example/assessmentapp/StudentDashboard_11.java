package com.example.assessmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentDashboard_11 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_dashboard_11);
        //list on click move to instruction Page
        ListView assessmentlist=findViewById(R.id.upcominglist);



        //back button for moving to student page
        Button btnback =findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add intent to move to student page
                startActivity(new Intent(getApplicationContext(),StudentLogin_3.class));
            }
        });
    }
}
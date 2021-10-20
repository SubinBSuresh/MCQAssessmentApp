package com.example.assessmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class FacultyDashboard_12 extends AppCompatActivity {

    ListView listView;
    Button btnNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_dashboard_12);

        btnNew = findViewById(R.id.btn_new);

        TheHelper dbHelper=new TheHelper(this);
        ArrayList<HashMap<String,String>> AssessmentList=dbHelper.getAssessmentList();
        ListView listView=findViewById(R.id.lv_faculty_assessmentList);
        ListAdapter listAdapter=new SimpleAdapter(getApplicationContext(),AssessmentList,R.layout.custom_faculty_listview,new String[]{"assessment_name_input","due_date","duration"},new int[]{R.id.tv_assessmentName_facultyDashboard,R.id.tv_assessmentDueDate_facultyDashboard,R.id.tv_assessmentTime_facultyDashboard} );
        listView.setAdapter(listAdapter);


        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), QuestionSection_6.class));
            }
        });
    }
}
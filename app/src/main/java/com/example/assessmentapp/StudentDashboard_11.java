package com.example.assessmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentDashboard_11 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_dashboard_11);
        //list on click move to instruction Page
//        ListView assessmentlist=findViewById(R.id.assessmentList);

        AssessmentDetailsDatabaseHelper dbHelper=new AssessmentDetailsDatabaseHelper(this);
        ArrayList<HashMap<String,String>> AssessmentList=dbHelper.getAssessmentList();
        ListView listView=findViewById(R.id.lv_assessmentList);
        ListAdapter listAdapter=new SimpleAdapter(StudentDashboard_11.this,AssessmentList,R.layout.student_dashboard_listview,new String[]{"assessment_name_input","due_date","duration"},new int[]{R.id.tv_student_dashboardAssessmentName,R.id.tv_student_dashboardAssessmentDueDate,R.id.tv_student_dashboardAssessmentDuration} );
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StudentDashboard_11.this, InstuctionPage_8.class);
                intent.putExtra("Assessment", position);
                startActivity(intent);
            }
        });

        //back button for moving to student page
        ImageButton btnback =findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add intent to move to student page
                startActivity(new Intent(getApplicationContext(),StudentLogin_3.class));
            }
        });
    }
}
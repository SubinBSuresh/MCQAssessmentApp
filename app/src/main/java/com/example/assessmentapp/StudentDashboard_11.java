package com.example.assessmentapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentDashboard_11 extends AppCompatActivity {

    String studentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_dashboard_11);
        //list on click move to instruction Page
//        ListView assessmentlist=findViewById(R.id.assessmentList);

        TextView tvStudentName = findViewById(R.id.tv_studentNameDisplay);

        Bundle extras = getIntent().getExtras();

        studentName = extras.getString("StudentName");
        tvStudentName.setText(studentName);



        TheHelper dbHelper = new TheHelper(this);
        ArrayList<HashMap<String, String>> AssessmentList = dbHelper.getAssessmentList();
        ListView listView = findViewById(R.id.lv_student_assessmentList);
        ListAdapter listAdapter = new SimpleAdapter(StudentDashboard_11.this, AssessmentList, R.layout.student_dashboard_listview, new String[]{"assessment_name_input", "due_date", "duration"}, new int[]{R.id.tv_student_dashboardAssessmentName, R.id.tv_student_dashboardAssessmentDueDate, R.id.tv_student_dashboardAssessmentDuration});
        listView.setAdapter(listAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String eligibility = dbHelper.eligibility(studentName,AssessmentList.get(position).get("assessment_name_input"));
                if(!eligibility.equals("")){
                    Toast.makeText(getApplicationContext(), "You have already done this test", Toast.LENGTH_SHORT).show();

                    Dialog submitDialog = new Dialog(StudentDashboard_11.this);
                    submitDialog.setContentView(R.layout.custom_mark_dialog);
                    submitDialog.setCancelable(false);
                    submitDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    submitDialog.show();

                    Button btnOk = submitDialog.findViewById(R.id.btn_student_markView);
                    TextView tvMarkDisplay = submitDialog.findViewById(R.id.tv_markDisplayDialog);
                    tvMarkDisplay.setText(eligibility);
                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            submitDialog.cancel();
                        }
                    });


                }else{
                    Intent intent = new Intent(StudentDashboard_11.this, InstuctionPage_8.class);
                    intent.putExtra("Assessment", AssessmentList.get(position).get("assessment_name_input"));
                    intent.putExtra("AssessmentDuration", AssessmentList.get(position).get("duration"));
                    intent.putExtra("StudentName", studentName);
                    startActivity(intent);
                }
            }
        });

        //back button for moving to student page
        ImageButton btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add intent to move to student page
                startActivity(new Intent(getApplicationContext(), StudentLogin_3.class));
            }
        });
    }
}
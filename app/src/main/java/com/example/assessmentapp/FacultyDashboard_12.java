package com.example.assessmentapp;

import android.app.Dialog;
import android.content.Context;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class FacultyDashboard_12 extends AppCompatActivity {

    ImageButton facultyLogout;
    Button btnNew;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_dashboard_12);

        facultyLogout = findViewById(R.id.ib_faculty_logout);
        btnNew = findViewById(R.id.btn_new);

        TheHelper dbHelper = new TheHelper(this);
        ArrayList<HashMap<String, String>> AssessmentList = dbHelper.getAssessmentList();
        ListView listView = findViewById(R.id.lv_faculty_assessmentList);
        ListAdapter listAdapter = new SimpleAdapter(getApplicationContext(), AssessmentList, R.layout.custom_faculty_listview, new String[]{"assessment_name_input", "due_date", "duration"}, new int[]{R.id.tv_assessmentName_facultyDashboard, R.id.tv_assessmentDueDate_facultyDashboard, R.id.tv_assessmentTime_facultyDashboard});
        listView.setAdapter(listAdapter);



        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), QuestionSection_6.class));
            }
        });
    //logout implementations
        facultyLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Logging out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), FacultyLogin_2.class));
            }
        });

        //onclick
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getApplicationContext(), StudentMarks_13.class);
                intent.putExtra("Assessment", AssessmentList.get(position).get("assessment_name_input"));
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Dialog submitDialog = new Dialog(FacultyDashboard_12.this);
                submitDialog.setContentView(R.layout.custom_delete_dialog);
                Button btnDeleteConfirm = submitDialog.findViewById(R.id.btn_faculty_deleteConfirm);
                Button btnDeleteCancel = submitDialog.findViewById(R.id.btn_faculty_deleteCancel);
                submitDialog.setCancelable(false);
                submitDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                submitDialog.show();

                btnDeleteCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        submitDialog.cancel();
                    }
                });
                btnDeleteConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbHelper.deleteAssessment(AssessmentList.get(position).get("assessment_name_input"));
                        startActivity(new Intent(getApplicationContext(),FacultyDashboard_12.class));
                    }
                });
                return false;
            }
        });
    }
}
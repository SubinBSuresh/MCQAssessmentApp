package com.example.assessmentapp;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentMarks_13 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_marks_13);

        TextView tvNoOfQuestions = findViewById(R.id.tv_noOfQuestions_markList);

        Bundle extras = getIntent().getExtras();
        String assessmentName = extras.getString("Assessment");

        TheHelper dbHelper = new TheHelper(this);
        ArrayList<HashMap<String, String>> markList = dbHelper.getStudentMarklist(assessmentName);
        Integer noOfQuestions = dbHelper.getQuestionCount(assessmentName);
        tvNoOfQuestions.setText("Total Marks : "+ noOfQuestions.toString());
        ListView markListView = findViewById(R.id.lv_markList);

        ListAdapter listAdapter = new SimpleAdapter(getApplicationContext(), markList, R.layout.custom_student_mark_listview, new String[]{"student_name", "student_marks"}, new int[]{R.id.tv_studentName_markList, R.id.tv_studentMark_markList});
        markListView.setAdapter(listAdapter);
    }
}
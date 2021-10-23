package com.example.assessmentapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultPage_10 extends AppCompatActivity {

    private TextView tvResult;
    private Button btnGoBack;
    private Integer marks;
    private Integer numberOfQuestions;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_page_10);

        tvResult = findViewById(R.id.tv_result);
        btnGoBack = findViewById(R.id.btn_goBack);

        TheHelper dbHelper = new TheHelper(this);
        Bundle extras = getIntent().getExtras();
        marks = extras.getInt("Marks");
        numberOfQuestions = extras.getInt("TotalQuestions");

        tvResult.setText(marks + " / " + numberOfQuestions);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.insertStudentMarklist(extras.getString("StudentName"), extras.getString("AssessmentName"), Integer.toString(marks));
                Intent intent = new Intent(getApplicationContext(), StudentDashboard_11.class);
                intent.putExtra("StudentName", extras.getString("StudentName"));
                startActivity(intent);
            }
        });
    }
}
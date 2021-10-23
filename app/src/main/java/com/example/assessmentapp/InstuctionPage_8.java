package com.example.assessmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InstuctionPage_8 extends AppCompatActivity {

    private Button btnstart;
    private TextView tvNoOfQuestions;
    private TextView tvTotalTime;
    private TextView tvTotalMarks;
    private TextView tvPassMarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instuction_page_8);
        //start Button
        btnstart = findViewById(R.id.btnstart);

        tvNoOfQuestions = findViewById(R.id.tv_noOfQuestions);
        tvTotalTime = findViewById(R.id.tv_totalTime);
        tvTotalMarks = findViewById(R.id.tv_totalMark);
        tvPassMarks = findViewById(R.id.tv_passMark);

        Bundle extras = getIntent().getExtras();
        String assessmentName = extras.getString("Assessment");
        String time = extras.getString("AssessmentDuration");
        TheHelper dbHelper = new TheHelper(this);
        Integer questionCount = dbHelper.getQuestionCount(assessmentName);

        tvNoOfQuestions.setText(Integer.toString(questionCount));
        tvTotalTime.setText(time + " Minutes");
        tvTotalMarks.setText(Integer.toString(questionCount));
        Integer passMark = (int)( questionCount*0.7) ;

        tvPassMarks.setText(Integer.toString(passMark));
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Starting assessment", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), MainAssessmentPage_9.class);
                intent.putExtra("AssessmentName", assessmentName);
                intent.putExtra("StudentName",extras.getString("StudentName"));
                startActivity(intent);
            }
        });
    }
}
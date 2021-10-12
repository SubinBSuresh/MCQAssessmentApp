package com.example.assessmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InstuctionPage_8 extends AppCompatActivity {

    private Button  btnstart;
    private TextView tvNoOfQuestions;
    private TextView tvTotalTime;
    private TextView tvTotalMarks;
    private TextView tvPassMarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instuction_page_8);
        //start Button
        btnstart =findViewById(R.id.btnstart);

        tvNoOfQuestions =findViewById(R.id.tv_noOfQuestions);
        tvTotalTime = findViewById(R.id.tv_totalTime);
        tvTotalMarks = findViewById(R.id.tv_totalMark);
        tvPassMarks = findViewById(R.id.tv_passMark);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Your Assessment Starts",Toast.LENGTH_LONG).show();

                startActivity(new Intent(getApplicationContext(),MCQQuestionSection_9.class));
            }
        });
    }
}
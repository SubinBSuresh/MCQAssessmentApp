package com.example.assessmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MCQQuestionSection_9 extends AppCompatActivity {

    private Button btnMcqnext;
    private Button btnMcqPrevious;
    private Button btnSubmit;
    private RadioButton option1;
    private RadioButton option2;
    private RadioButton option3;
    private RadioButton option4;
    private TextView question;
    private TextView questionNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcq_question_section_9);


        //Next question
        btnMcqnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Display new questions
                //After all question, make the Button text as submit
                //Go to result page
                startActivity(new Intent(getApplicationContext(),ResultPage_10.class));
            }
        });

        //Previous question
        btnMcqPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //display previous question
            }
        });


    }


}
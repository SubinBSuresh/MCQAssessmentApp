package com.example.assessmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MCQQuestionSection_9 extends AppCompatActivity {

    private Button btnSubmit;
    private RadioButton option1;
    private RadioButton option2;
    private RadioButton option3;
    private RadioButton option4;
    private TextView question;
    private TextView questionNo;

    Integer questionCount=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcq_question_section_9);

        Button btnSubmit = findViewById(R.id.btn_saveAssessment);
        RadioGroup radioGroup = findViewById(R.id.answer);
        RadioButton radioButton1 = findViewById(R.id.answer1);
        RadioButton radioButton2 = findViewById(R.id.answer2);
        RadioButton radioButton3 = findViewById(R.id.answer3);
        RadioButton radioButton4 = findViewById(R.id.answer4);

        Bundle extras = getIntent().getExtras();
        String assessmentMCQName = extras.getString("AssessmentName");

        questionNo = findViewById(R.id.QuestionNumber);

        TheHelper dbHelper = new TheHelper(this);
        ArrayList<HashMap<String,String>> mcqQuestions = dbHelper.getMCQData(assessmentMCQName);

        ListView questionListView = findViewById(R.id.lv_faculty_assessmentList);
        ListAdapter listAdapter = new SimpleAdapter(getApplicationContext(),mcqQuestions, R.layout.mcq_question_listview,new String[]{
                "mcq_question",
                "option1",
                "option2",
                "option3",
                "option4"
        }, new int[]{R.id.question,R.id.answer1,R.id.answer2,R.id.answer3,R.id.answer4});

        questionListView.setAdapter(listAdapter);
    }
}
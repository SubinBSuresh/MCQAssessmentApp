package com.example.assessmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class QuestionSection_6 extends AppCompatActivity {

    ImageButton imgback;
    EditText testName;
    EditText testDuration;
    Button btnNext;
    DatePicker datePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_section_6);

        imgback = findViewById(R.id.img_previous);
        testName =findViewById(R.id.et_testName);
        testDuration = findViewById(R.id.et_duration);
        btnNext = findViewById(R.id.btn_goToQuestionInput);
        datePicker = findViewById(R.id.date_picker);



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Add assessment details to database
                //Sent assessment name to the next page
                startActivity(new Intent(getApplicationContext(),MCQQuestionInput_7.class));
            }
        });
    }
}
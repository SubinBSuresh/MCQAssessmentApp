package com.example.assessmentapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

    String assessmentName;
    String duration;
    String dueDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_section_6);

        imgback = findViewById(R.id.img_previous);
        testName =findViewById(R.id.et_testName);
        testDuration = findViewById(R.id.et_duration);
        btnNext = findViewById(R.id.btn_goToQuestionInput);
        datePicker = findViewById(R.id.date_picker);

        //previous page imgback.setOn.....
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FacultyLogin_2.class));
            }
        });



        //Database Helper
        final AssessmentDetailsDatabaseHelper assessmentDetailsdbHelper = new AssessmentDetailsDatabaseHelper(this);



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                //Adding Custom dialog
                Dialog submitDialog = new Dialog(QuestionSection_6.this);
                submitDialog.setContentView(R.layout.custom_question_section_dialog);
                submitDialog.setCancelable(false);
                submitDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                submitDialog.show();

                Button btnAssessDetailsCancel = submitDialog.findViewById(R.id.btn_faculty_AsseDetailsCancel);
                Button btnAssessDetailsConfirm = submitDialog.findViewById(R.id.btn_faculty_AsseDetailsConfirm);

                //Dialog Confirm
                btnAssessDetailsConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Add assessment details to database
                        //Extractng assessment name and duration from the fields
                        assessmentName = testName.getText().toString();
                        duration = testDuration.getText().toString();
                        //extracting duedate
                        String day = ""+datePicker.getDayOfMonth();
                        int month = datePicker.getMonth();
                        String year = ""+datePicker.getYear();
                        dueDate= day+"/"+(month+1)+"/"+year;

                        //Checks, if there are any empty fields
                        if (assessmentName.equals("") || duration.equals("")) {
                            Toast.makeText(getApplicationContext(), "Check again", Toast.LENGTH_SHORT).show();

                        }

                        //insert data in database
                        assessmentDetailsdbHelper.insertAssessmentDetails(assessmentName, dueDate, duration);

                        Toast.makeText(getApplicationContext(), "Assessment Details Saved", Toast.LENGTH_LONG).show();

                        //clear fields
                        testName.setText("");
                        testDuration.setText("");

                        //Sent assessment name to the next page
                        Intent intent = new Intent(new Intent(getApplicationContext(),MCQQuestionInput_7.class));
                        intent.putExtra("AssessmentName",assessmentName);
                        startActivity(intent);
                    }
                });

                //Dialog Cancel
                btnAssessDetailsCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        submitDialog.cancel();
                    }
                });


            }
        });
    }
}
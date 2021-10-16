package com.example.assessmentapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MCQQuestionInput_7 extends AppCompatActivity {

    Integer mcqQuestionNumber;
    String mcqQuestionNumberString;
    String mcqQuestion;
    String mcqOption1;
    String mcqOption2;
    String mcqOption3;
    String mcqOption4;
    String mcqCorrectAnswer;
    String assessmentName;

    int mcqQuestionCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcq_question_input_7);

        TextView tvFacultyMCQNumber = findViewById(R.id.tv_faculty_MCQNumber);
        EditText etFacultyMCQQuestion = findViewById(R.id.et_faculty_MCQQuestion);
        EditText etMCQOption1 = findViewById(R.id.et_MCQ_Option1);
        EditText etMCQOption2 = findViewById(R.id.et_MCQ_Option2);
        EditText etMCQOption3 = findViewById(R.id.et_MCQ_Option3);
        EditText etMCQOption4 = findViewById(R.id.et_MCQ_Option4);
        EditText etMCQCorrectAnswer = findViewById(R.id.et_MCQ_CorrectAnswer);
        Button btnFacultyMCQOneQuestion = findViewById(R.id.btn_faculty_MCQOneQuestion);
        Button btnFacultyMCQAllQuestions = findViewById(R.id.btn_faculty_MCQAllQuestions);

        //Database Helper
        final MCQInputDatabaseHelper mcqdbHelper = new MCQInputDatabaseHelper(this);


        //Converting the question number to display on textview
        mcqQuestionNumberString = String.valueOf(mcqQuestionCount);
        tvFacultyMCQNumber.setText(mcqQuestionNumberString);


        tvFacultyMCQNumber.setText(String.valueOf(mcqQuestionCount));

        //Getting the database name from previous page


        // Saving MCQ Questions one by one
        btnFacultyMCQOneQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Getting all the values
                mcqQuestion = etFacultyMCQQuestion.getText().toString();
                mcqOption1 = etMCQOption1.getText().toString();
                mcqOption2 = etMCQOption2.getText().toString();
                mcqOption3 = etMCQOption3.getText().toString();
                mcqOption4 = etMCQOption4.getText().toString();
                mcqCorrectAnswer = etMCQCorrectAnswer.getText().toString();

                Bundle extras = getIntent().getExtras();
                assessmentName = extras.getString("AssessmentName");

                //Checks, if there are any empty fields
                if (mcqQuestion.equals("") || mcqOption1.equals("") || mcqOption2.equals("") || mcqOption3.equals("") || mcqOption4.equals("")) {
                    Toast.makeText(getApplicationContext(), "Check again", Toast.LENGTH_SHORT).show();

                }
                //Checks if the correct answer matches any of the option
                else if ((mcqOption1.equals(mcqCorrectAnswer)) || (mcqOption2.equals(mcqCorrectAnswer)) || (mcqOption3.equals(mcqCorrectAnswer)) || (mcqOption4.equals(mcqCorrectAnswer))) {

                    //Adding questions to database
                    mcqdbHelper.insertMCQData(assessmentName, mcqQuestionNumberString, mcqQuestion, mcqOption1, mcqOption2, mcqOption3, mcqOption4, mcqCorrectAnswer);

                    //Incrementing question number
                    mcqQuestionCount++;
                    mcqQuestionNumberString = String.valueOf(mcqQuestionCount);
                    tvFacultyMCQNumber.setText(mcqQuestionNumberString);

                    //Clearing out fields
                    etFacultyMCQQuestion.setText("");
                    etMCQOption1.setText("");
                    etMCQOption2.setText("");
                    etMCQOption3.setText("");
                    etMCQOption4.setText("");
                    etMCQCorrectAnswer.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot input", Toast.LENGTH_SHORT).show();
                }
            }

        });


        //Submitting all MCQ Questions
        btnFacultyMCQAllQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Resetting question number to 1
                mcqQuestionCount = 1;

                //Adding Custom dialog
                Dialog submitDialog = new Dialog(MCQQuestionInput_7.this);
                submitDialog.setContentView(R.layout.custom_mcq_dialog);
                submitDialog.setCancelable(false);
                submitDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                submitDialog.show();

                Button btnMCQCancel = submitDialog.findViewById(R.id.btn_faculty_MCQCancel);
                Button btnMCQConfirm = submitDialog.findViewById(R.id.btn_faculty_MCQConfirm);

                //Dialog Confirm
                btnMCQConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(), QuestionSection_6.class));
                    }
                });

                //Dialog Cancel
                btnMCQCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        submitDialog.cancel();
                    }
                });
            }
        });


    }
}
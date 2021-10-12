package com.example.assessmentapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MCQQuestionInput_7 extends AppCompatActivity {

    String mcqQuestion;
    String mcqOption1;
    String mcqOption2;
    String mcqOption3;
    String mcqOption4;
    String mcqCorrectAnswer;

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

        final MCQDBHelper mcqdbHelper = new MCQDBHelper(this);

        tvFacultyMCQNumber.setText(String.valueOf(mcqQuestionCount));
        // Saving MCQ Questions one by one
        btnFacultyMCQOneQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mcqQuestion = etFacultyMCQQuestion.getText().toString();
                mcqOption1 = etMCQOption1.getText().toString();
                mcqOption2 = etMCQOption2.getText().toString();
                mcqOption3 = etMCQOption3.getText().toString();
                mcqOption4 = etMCQOption4.getText().toString();
                mcqCorrectAnswer = etMCQCorrectAnswer.getText().toString();

                mcqdbHelper.insertMCQData(mcqQuestion, mcqOption1, mcqOption2, mcqOption3, mcqOption4, mcqCorrectAnswer);

                mcqQuestionCount++;

                etFacultyMCQQuestion.setText("");
                etMCQOption1.setText("");
                etMCQOption2.setText("");
                etMCQOption3.setText("");
                etMCQOption4.setText("");
                etMCQCorrectAnswer.setText("");

            }
        });


        //Submitting all MCQ Questions
        btnFacultyMCQAllQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog submitDialog = new Dialog(getApplicationContext());
                submitDialog.setContentView(R.layout.custom_mcq_dialog);
                submitDialog.setCancelable(true);
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
package com.example.assessmentapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainAssessmentPage extends AppCompatActivity {

    private Button btnPrevious;
    private Button btnNext;
    private RadioButton option1;
    private RadioButton option2;
    private RadioButton option3;
    private RadioButton option4;
    private RadioGroup radioGroup;
    private TextView question;
    private TextView questionNo;

    private ProgressBar progressBar;
    private TextView questionProgress;
    private TextView assessmentName;

    Integer questionNumber = 0;
    Integer marksCount = 0;
    Integer noOfQuestions;

    ArrayList<HashMap<String, String>> mcqQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_assessment_page);

        btnPrevious = findViewById(R.id.btn_previousQuestion);
        btnNext = findViewById(R.id.btn_nextQuestion);
        radioGroup = findViewById(R.id.answer);
        option1 = findViewById(R.id.answer1);
        option2 = findViewById(R.id.answer2);
        option3 = findViewById(R.id.answer3);
        option4 = findViewById(R.id.answer4);
        question = findViewById(R.id.mcqQuestionPrint);
        questionNo = findViewById(R.id.questionNumber);

        progressBar = findViewById(R.id.progressBar);
        questionProgress = findViewById(R.id.tv_questionProgress);
        assessmentName = findViewById(R.id.tv_assessmentName_display);
        Bundle extras = getIntent().getExtras();
        String assessmentMCQName = extras.getString("AssessmentName");
        assessmentName.setText(assessmentMCQName);


        TheHelper dbHelper = new TheHelper(this);
        mcqQuestions = dbHelper.getMCQData(assessmentMCQName);
        noOfQuestions = mcqQuestions.size();
        Toast.makeText(getApplicationContext(), Integer.toString(noOfQuestions), Toast.LENGTH_SHORT).show();


        questionPrinter(questionNumber);
        progressBar.setProgress(100 / noOfQuestions);

//        BTN PREVIOUS
        btnPrevious.setEnabled(false);
        btnPrevious.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Decrement question number and fetch call question printer method
                while(marksCount>0) {
                    marksCount--;
                }
                questionNumber--;
                questionPrinter(questionNumber);
                //Update progress bar
                progressBar.setProgress(progressBar.getProgress() - (progressBar.getMax() / noOfQuestions));
            }
        });


        //BTN NEXT
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Update progress bar
                progressBar.incrementProgressBy(100 / noOfQuestions);
                //Increment question number and fetch call question printer method
                questionNumber++;


                //Submit Quiz
                if (btnNext.getText() == "Submit") {
                    btnPrevious.setEnabled(false);
                    Toast.makeText(getApplicationContext(), Integer.toString(marksCount), Toast.LENGTH_SHORT).show();

                    //Custom Dialog
                    Dialog submitDialog = new Dialog(MainAssessmentPage.this);
                    submitDialog.setContentView(R.layout.custom_quiz_submit);
                    submitDialog.setCancelable(false);
                    submitDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    submitDialog.show();

                    Button btnMCQCancel = submitDialog.findViewById(R.id.btn_student_MCQCancel);
                    Button btnMCQConfirm = submitDialog.findViewById(R.id.btn_student_MCQSubmit);

                    //Dialog Cancel
                    btnMCQCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            submitDialog.cancel();
                        }
                    });
                    //Dialog Confirm
                    btnMCQConfirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), ResultPage_10.class);
                            intent.putExtra("Marks", marksCount);
                            startActivity(intent);
                        }
                    });
                }
                questionPrinter(questionNumber);
                btnPrevious.setEnabled(true);
            }
        });
    }

    //Method to push questions
    @SuppressLint("SetTextI18n")
    public void questionPrinter(Integer qNo) {

        if (qNo < noOfQuestions) {
            questionNo.setText(Integer.toString(qNo + 1));
            question.setText(mcqQuestions.get(qNo).get("mcq_question"));
            option1.setText(mcqQuestions.get(qNo).get("option1"));
            option2.setText(mcqQuestions.get(qNo).get("option2"));
            option3.setText(mcqQuestions.get(qNo).get("option3"));
            option4.setText(mcqQuestions.get(qNo).get("option4"));
            questionProgress.setText((qNo + 1) + " of " + noOfQuestions + " questions");

            //Get checked answer from RadioGroup
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(selectedId);
                    if (radioButton.getText().equals(mcqQuestions.get(qNo).get("correct_answer"))) {
                        marksCount++;
                    }
                }
            });
            //Condition for setting button texts
            if (qNo + 1 == noOfQuestions) {
                btnNext.setText("Submit");
            } else {
                btnNext.setText("Next");
            }
            // Condition for enabling previous button
            if (qNo == 0) {
                btnPrevious.setEnabled(false);
            }
        }

    }
}
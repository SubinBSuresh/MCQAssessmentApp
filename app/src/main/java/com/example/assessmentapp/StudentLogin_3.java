package com.example.assessmentapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentLogin_3 extends AppCompatActivity {


    private Button tvSignUp;
    private ImageView imgBack;
    private EditText StEmail;
    private EditText StPassword;
    private Button btnLogin;
    private TheHelper db1;

    SharedPreferences sp1;

    String studentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login_3);
        imgBack=findViewById(R.id.backArrow);
        StEmail=findViewById(R.id.et_student_loginEmail);
        StPassword=findViewById(R.id.et_student_loginPassword);
        btnLogin=findViewById(R.id.btn_student_login);
        db1 = new TheHelper(this);
        sp1 = getSharedPreferences("login",MODE_PRIVATE);
        /*if(sp1.getBoolean("logged",false)){
            goToMain();
        }*/

        tvSignUp=findViewById(R.id.btn_student_signUp);
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),StudentSignup_5.class));
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = StEmail.getText().toString();
                String password = StPassword.getText().toString();
                if(email.equals("")||password.equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter credentials", Toast.LENGTH_LONG).show();
                }else{
                    Boolean resultLogin = db1.checkStudentUsernamePassword(email,password);
                    if (resultLogin==true){
                        studentName = db1.getStudentName(email);
                        goToMain(studentName);
                        sp1.edit().putBoolean("logged",true).apply();
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Invalid user", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
    private void goToMain(String name) {
        Intent intent = new Intent(getApplicationContext(),StudentDashboard_11.class);
        intent.putExtra("StudentName",name);
        startActivity(intent);
        finish();
    }
}
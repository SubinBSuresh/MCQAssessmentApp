package com.example.assessmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class SplashPage_0 extends AppCompatActivity {
    private final static int SPLASH_TIME_OUT = 1900;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_page_0);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashPage_0.this, LandingPage_1.class));

                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}


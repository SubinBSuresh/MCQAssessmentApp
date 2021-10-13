package com.example.assessmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class Splash extends AppCompatActivity {
    private final static int SPLASH_TIME_OUT = 1900;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);


        Animation splashAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_in);


        ImageView imageView = findViewById(R.id.img_splash);
        imageView.setVisibility(View.VISIBLE);
        imageView.setAnimation(splashAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, LandingPage_1.class));

                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}


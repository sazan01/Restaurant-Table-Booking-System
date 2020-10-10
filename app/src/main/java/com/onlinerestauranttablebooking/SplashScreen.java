package com.onlinerestauranttablebooking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        final Intent splash= new Intent(SplashScreen.this,MainActivity.class);

           new Handler().postDelayed(new Runnable() {
               @Override
               public void run() {
                   startActivity(splash);
                   finish();
               }
           },2000);



        }
    }


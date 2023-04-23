package com.example.quizgame;

import static com.example.quizgame.AboutActivity.MyPREFERENCES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener {

    ImageView imageView;
    Animation animation;
    public static int checkhed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        imageView = findViewById(R.id.splash_logo);
        animation = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.splash_anim);
        animation.setAnimationListener(this);
        imageView.startAnimation(animation);


        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        int default1;
        switch (Configuration.UI_MODE_NIGHT_MASK & AppCompatDelegate.getDefaultNightMode()){
            case AppCompatDelegate.MODE_NIGHT_NO:
                default1=0;
                break;

            case AppCompatDelegate.MODE_NIGHT_YES:
                default1=1;
                break;

            default:
                default1=2;
        }

        checkhed=sharedPreferences.getInt("checked",default1);
        switch (checkhed){
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;

            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;

            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;

            case 3:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                break;
        }
        Thread mSplashThread;
        mSplashThread = new Thread(){
            @Override public void run() {
                 try {
                     synchronized (this){
                         wait(2000);
                     }
                 }catch (InterruptedException ignored){
                 }
                 finally {
                     startActivity(new Intent(getApplicationContext(), MainActivity.class));
                     finish();
                 }
            }
        };

        mSplashThread.start();


    }

    @Override
    public void onAnimationStart(Animation animation) {


    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
package com.awizomtech.bastararts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.awizomtech.bastararts.R;
import com.awizomtech.bastararts.SharedPreference.SharedPrefManager;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       /* new Timer().schedule(new TimerTask() {
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();

            }
        }, 2000);*/
        String userid = SharedPrefManager.getInstance(this).getUser().getUserID().toString();
        if (userid != "") {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    startActivity(new Intent(SplashActivity.this, HomePageActivity.class));
                    finish();

                }
            }, 3000);

        }
        else {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();

                }
            }, 2000);
        }
    }
}
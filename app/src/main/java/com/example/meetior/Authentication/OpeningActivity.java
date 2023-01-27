package com.example.meetior.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.meetior.IntroActivity;
import com.example.meetior.R;


public class OpeningActivity extends AppCompatActivity {
    private int prg = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
                if (isFirstRun) {
                    showanimation();
                    startActivity(new Intent(OpeningActivity.this, IntroActivity.class));
                    checkconnectivity();
                } else {
                    showanimation();
                    checkconnectivity();
                    startapp();
                }
                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).commit();
            }
        });
        thread.start();
    }

    public void checkconnectivity() {

    }

    private void showanimation() {
        for (prg = 0; prg <= 125; prg += 25) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void startapp() {
        startActivity(new Intent(OpeningActivity.this, LoginActivity.class));
        finish();
    }
}
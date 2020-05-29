package com.mohfajar.gantara.Signup;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mohfajar.gantara.Login.LoginActivity;
import com.example.mohfajar.gantara.R;

public class SignActivity extends AppCompatActivity implements View.OnClickListener {

    boolean doubleBackToExitPressedOnce = false;

    private Button btnNext;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        btnNext = (Button) findViewById(R.id.buttonNext);
        btnNext.setOnClickListener(this);
        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonNext:
                Intent bukaSignupActivity = new Intent(this, SignupActivity.class);
                startActivity(bukaSignupActivity);
                break;
            case R.id.buttonBack:
                Intent bukaLoginActivity = new Intent(this, LoginActivity.class);
                startActivity(bukaLoginActivity);
                break;
        }
    }

    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}

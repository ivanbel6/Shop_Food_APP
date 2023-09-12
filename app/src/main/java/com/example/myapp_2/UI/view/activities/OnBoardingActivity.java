package com.example.myapp_2.UI.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapp_2.R;
import com.example.myapp_2.UI.view.fragments.onbooarding.SplashFragment;

public class OnBoardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        getSupportFragmentManager().beginTransaction().replace(R.id.onBoarding, new SplashFragment()).addToBackStack(null).commit();
    }
}
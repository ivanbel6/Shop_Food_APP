package com.example.myapp_2.UI.view.fragments.onbooarding;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.myapp_2.R;

public class SplashFragment extends Fragment {

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getFragmentManager().beginTransaction().replace(R.id.onBoarding, new ViewPagerFragment()).addToBackStack(null).commit();
            }
        }, 1000);

        return inflater.inflate(R.layout.fragment_splash, container, false);
    }
}
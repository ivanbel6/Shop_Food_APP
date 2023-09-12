package com.example.myapp_2.UI.view.fragments.onbooarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.fragment.app.Fragment;

import com.example.myapp_2.R;
import com.example.myapp_2.UI.view.activities.MainActivity;
import com.example.myapp_2.databinding.FragmentOnboarding3Binding;

public class OnBoardingFragment3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_onboarding3, container, false);
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fragment_transition_animation);
        anim.setDuration(200);
        view.startAnimation(anim);
        FragmentOnboarding3Binding binding = FragmentOnboarding3Binding.inflate(inflater, container, false); // подключение view binding
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }
}
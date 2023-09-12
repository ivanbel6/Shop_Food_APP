package com.example.myapp_2.UI.view.fragments.onbooarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.myapp_2.databinding.FragmentOnboarding2Binding;

public class OnBoardingFragment2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentOnboarding2Binding binding = FragmentOnboarding2Binding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}
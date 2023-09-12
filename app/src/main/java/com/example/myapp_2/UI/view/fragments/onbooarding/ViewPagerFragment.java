package com.example.myapp_2.UI.view.fragments.onbooarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapp_2.R;
import com.example.myapp_2.UI.view.adapters.ViewPagerAdapter;

import java.util.ArrayList;

public class ViewPagerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new OnBoardingFragment1());
        fragmentList.add(new OnBoardingFragment2());
        fragmentList.add(new OnBoardingFragment3());

        ViewPagerAdapter adapter = new ViewPagerAdapter(
                fragmentList,
                requireActivity().getSupportFragmentManager(),
                getLifecycle()
        );

        ViewPager2 viewPager = (ViewPager2)view.findViewById(R.id.onBordingViewPager);
        viewPager.setAdapter(adapter);

        return view;
    }
}
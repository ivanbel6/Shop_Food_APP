package com.example.myapp_2.Data.Discount_Get_table_Pofile;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.myapp_2.R;

import com.example.myapp_2.UI.view.fragments.FirstFragment;
import com.example.myapp_2.UI.view.fragments.SecondFragment;
import com.example.myapp_2.UI.view.fragments.ThirdFragment;
import com.example.myapp_2.databinding.FragmentRestaurantsBinding;

import java.util.ArrayList;

public class RestaurantsFragment extends Fragment implements RecyclerViewInterface {
    ArrayList<RestaurantModel> restaurantModels = new ArrayList<>();
    int[] restaurantsImages = {R.drawable.first_1, R.drawable.first_2, R.drawable.first_3, R.drawable.first_4};

    public RestaurantsFragment(){
        Activity a = getActivity();
        int b = 0;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentRestaurantsBinding binding = FragmentRestaurantsBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_restaurants, container, false);
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fragment_transition_animation);
        anim.setDuration(170);
        view.startAnimation(anim);
        setUpRestaurants();

        RestaurantsRecyclerViewAdapter adapter = new RestaurantsRecyclerViewAdapter(getActivity(), restaurantModels, this);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return binding.getRoot();
    }

    private void setUpRestaurants(){
        String[] restaurauntsNames = getResources().getStringArray(R.array.restaurants_names);
        String[] restaurauntsAdresses = getResources().getStringArray(R.array.restaurants_adresses);
        String[] restaurauntsWorkTime = getResources().getStringArray(R.array.restaurants_work_time);
        String[] restaurantsKitchenTypes = getResources().getStringArray(R.array.kitchenTypes);

        for(int i=0;i<restaurauntsNames.length;i++){
            restaurantModels.add(new RestaurantModel(restaurauntsNames[i], restaurauntsAdresses[i], restaurauntsWorkTime[i], restaurantsKitchenTypes[i], restaurantsImages[i]));
        }
    }
    @Override
    public void onItemClick(int position) { // надо чуток переделать
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        switch (position){
            case 0:
                transaction.replace(R.id.nav_container, new FirstFragment()).addToBackStack(null).commit();
                break;
            case 1:
                transaction.replace(R.id.nav_container, new SecondFragment()).addToBackStack(null).commit();
                break;
            case 2:
                transaction.replace(R.id.nav_container, new ThirdFragment()).addToBackStack(null).commit();
                break;
            case 3:
               // transaction.replace(R.id.nav_container, new FourthRestaurantFragment()).addToBackStack(null).commit();
                break;
        }
    }
}
package com.example.myapp_2.UI.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapp_2.R;

import java.util.ArrayList;
import java.util.List;


public class ListFragment extends Fragment{

    ListView listView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");
        arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");
        arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");
        arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");
        arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");
        arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");
        arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");
        arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");
        arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");
        arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");
        arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");
        arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");
        arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");
        arrayList.add("androidfdfdf");
        arrayList.add("sdaf");
        arrayList.add("dsf");
        arrayList.add("asdf");






        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,arrayList);
        ListView asdf = (ListView) view.findViewById(R.id.ListView);
        asdf.setAdapter(arrayAdapter);
        asdf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), "Вы нажали на элемент списка", Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       // ArrayList<String> arrayList = new ArrayList<>();




        //listView.setAdapter(arrayAdapter);
    }


}
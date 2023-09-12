package com.example.myapp_2.Data.Discount_Get_table_Pofile;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapp_2.R;

import java.util.ArrayList;

public class StocksAdapter extends PagerAdapter{

    private Context context;
    private ArrayList<StocksModel> stocksModelArrayList;
    private LayoutInflater layoutInflater;

    public StocksAdapter(Context context, ArrayList<StocksModel> stocksModelArrayList) {
        this.context = context;
        this.stocksModelArrayList = stocksModelArrayList;
    }

    @Override
    public int getCount() {
        return stocksModelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.stock_item, container, false);

        ImageView imageView;
        TextView title, dascription;

        imageView = view.findViewById(R.id.img);


        imageView.setImageResource(stocksModelArrayList.get(position).getImage());


        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

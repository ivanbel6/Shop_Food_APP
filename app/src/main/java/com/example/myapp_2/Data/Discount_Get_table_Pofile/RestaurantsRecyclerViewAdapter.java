package com.example.myapp_2.Data.Discount_Get_table_Pofile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp_2.R;


import java.util.ArrayList;

public class RestaurantsRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantsRecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    ArrayList<RestaurantModel> restaurantModels;

    public RestaurantsRecyclerViewAdapter(Context context, ArrayList<RestaurantModel> restaurantModels, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.restaurantModels = restaurantModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public RestaurantsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_restaurant,parent,false);

        return new RestaurantsRecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.restaurantName.setText(restaurantModels.get(position).getName());
        holder.restaurantAdress.setText(restaurantModels.get(position).getAdress());
        holder.restaurantWorkTime.setText(restaurantModels.get(position).getWorkTime());
        holder.restaurantKitchenType.setText(restaurantModels.get(position).getKitchenType());
        holder.imageView.setImageResource(restaurantModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return restaurantModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView restaurantName, restaurantAdress, restaurantWorkTime, restaurantKitchenType;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageRestaurant);
            restaurantName = itemView.findViewById(R.id.name);
            restaurantAdress = itemView.findViewById(R.id.adress);
            restaurantWorkTime = itemView.findViewById(R.id.worktime);
            restaurantKitchenType = itemView.findViewById(R.id.kitchenType);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null){
                        int pos = getAbsoluteAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}

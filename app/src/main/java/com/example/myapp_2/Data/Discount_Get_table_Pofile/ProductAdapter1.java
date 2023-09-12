package com.example.myapp_2.Data.Discount_Get_table_Pofile;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp_2.Data.List_1.Product;
import com.example.myapp_2.Data.List_1.ProductAdapter;
import com.example.myapp_2.Data.cart.OnProductClickListener;
import com.example.myapp_2.R;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductAdapter1 extends RecyclerView.Adapter<ProductAdapter1.ProductViewHolder> {
    private List<Product> products;
    private ProductAdapter.OnAddToCartClickListener addToCartClickListener;

    private Context context;

    private OnProductClickListener listener;
    public ProductAdapter1(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    public ProductAdapter1(List<Product> products, OnProductClickListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product3, parent, false);
        ProductViewHolder holder = new ProductViewHolder(view);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.productImage.setImageResource(product.getImageResource());
        holder.productName.setText(product.getName());
        holder.productDescription.setText(product.getDescription());
        holder.oldPriceTextView.setText(context.getString(R.string.old_price, String.format("%.2f", product.getPrice())));
        holder.newPriceTextView.setText(context.getString(R.string.price, String.format("%.2f", product.getNew_price())));
        holder.newPriceTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onAddToCartClick(product);
                }
            }
        });



// Добавлена проверка на null перед вызовом метода
        if (holder.addToCartButton != null) {
            holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (addToCartClickListener != null) {
                        addToCartClickListener.onAddToCartClick(product);
                    }
                }
            });
        }



    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        Product product1 = new Product("Product 1", "Товар подешевел на 30%", R.drawable.food_1, 0.0f);
        products.add(product1);

        Product product2 = new Product("Product 2", "Лучшее предложение", R.drawable.food_2, 0.0f);
        products.add(product2);

        Product product3 = new Product("Product 3", "Товар подешевел на 10%", R.drawable.food_1_3, 0.0f);
        products.add(product3);

        Product product4 = new Product("Product 4", "Товар подешевел на 15%", R.drawable.food_1_2, 0.0f);
        products.add(product4);

        Product product5 = new Product("Product 5", "Товар не подешевел на 30%", R.drawable.food_3_4, 0.0f);
        products.add(product5);

        return products;
    }

    public void sortProductsByPrice() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return Double.compare(o1.getPrice(), o2.getPrice());
            }
        });
        notifyDataSetChanged();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public Button addToCartButton;
        ImageView productImage;
        TextView productName;
        TextView productDescription;
        TextView oldPriceTextView;
        TextView newPriceTextView;

        public ProductViewHolder(View view) {
            super(view);
            productImage = view.findViewById(R.id.product_image);
            productName = view.findViewById(R.id.product_name);
            productDescription = view.findViewById(R.id.product_description);
            addToCartButton = itemView.findViewById(R.id.goRestaurantButton);
            oldPriceTextView = itemView.findViewById(R.id.old_price);
            newPriceTextView = itemView.findViewById(R.id.new_price);


        }
    }
    public interface OnAddToCartClickListener {
        void onAddToCartClick(Product product);
    }
    public void setOnAddToCartClickListener(ProductAdapter.OnAddToCartClickListener listener) {
        this.addToCartClickListener = listener;
    }


}
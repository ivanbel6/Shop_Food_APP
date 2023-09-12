package com.example.myapp_2.Data.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp_2.Data.List_1.Product;
import com.example.myapp_2.Data.List_1.ProductAdapter;
import com.example.myapp_2.R;

import java.util.List;

public class ProductsFragment extends Fragment {
    private List<Product> productList;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        adapter = new ProductAdapter(productList, new OnProductClickListener() {
            @Override
            public void onAddToCartClick(Product product) {
                addToCart(product);
            }
        });

// Здесь инициализируем список товаров и recyclerView

        adapter = new ProductAdapter(productList, new OnProductClickListener() {
            @Override
            public void onAddToCartClick(Product product) {
                addToCart(product);
            }
        }); // передаём метод добавления товара в корзину

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    private void addToCart(Product product) {
// метод добавления товара в корзину
        Cart cart = Cart.getInstance();
        cart.addItem(product);
    }
}

package com.example.myapp_2.Data.cart;

import com.example.myapp_2.Data.List_1.Product;

import java.util.ArrayList;
import java.util.List;



import com.example.myapp_2.Data.List_1.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart instance;
    private List<CartItem> items;
    private List<OnCartChangedListener> listeners;

    private Cart() {
        items = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addItem(Product product) {
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(item.getQuantity() + 1);
                notifyCartChanged();
                return;
            }
        }
        items.add(new CartItem(product, 1));
        notifyCartChanged();
    }

    public void updateItem(CartItem item, int newQuantity) {
        if (newQuantity <= 0) {
            removeItem(item);
        } else {
            item.setQuantity(newQuantity);
            notifyCartChanged();
        }
    }

    public void removeItem(CartItem item) {
        items.remove(item);
        notifyCartChanged();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
        notifyCartChanged();
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += (double) item.getTotalPrice();
        }
        return totalPrice;
    }

    private void notifyCartChanged() {
        for (OnCartChangedListener listener : listeners) {
            listener.onCartChanged();
        }
    }

    public void addOnCartChangedListener(OnCartChangedListener listener) {
        listeners.add(listener);
    }

    public interface OnCartChangedListener {
        void onCartChanged();
    }



}

package com.example.myapp_2.Data.cart;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp_2.Data.List_1.Product;
import com.example.myapp_2.R;

public  class CartItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView productImageView;
    private TextView productNameTextView;
    private TextView productPriceTextView;
    private TextView productTotalPriceTextView;
    private TextView productQuantityTextView;
    private Button decreaseButton;
    private Button increaseButton;
    private Button removeButton;
    private int quantity;

    public CartItemViewHolder(@NonNull View itemView) {
        super(itemView);

        productImageView = itemView.findViewById(R.id.cart_item_image_view);
        productNameTextView = itemView.findViewById(R.id.cart_item_name_text_view);
        productPriceTextView = itemView.findViewById(R.id.cart_item_price_text_view);
        productTotalPriceTextView = itemView.findViewById(R.id.cart_item_total_price_text_view);
        productQuantityTextView = itemView.findViewById(R.id.cart_item_quantity_text_view);
        decreaseButton = itemView.findViewById(R.id.cart_item_decrease_button);
        increaseButton = itemView.findViewById(R.id.cart_item_increase_button);
        removeButton = itemView.findViewById(R.id.cart_item_remove_button);
    }

    public void bind(CartItem item, OnCartItemListener listener) {
        Product product = item.getProduct();
        quantity = item.getQuantity();

        productImageView.setImageResource(product.getImageResource());
        productNameTextView.setText(product.getName());
        productPriceTextView.setText(itemView.getContext().getString(R.string.product_price, product.getPrice()));
        productTotalPriceTextView.setText(itemView.getContext().getString(R.string.cart_item_total_price, item.getTotalPrice()));
        productQuantityTextView.setText(String.valueOf(quantity));

        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity -= 1;
                productQuantityTextView.setText(String.valueOf(quantity));
                listener.onQuantityChanged(item, quantity);
            }
        });

        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity += 1;
                productQuantityTextView.setText(String.valueOf(quantity));
                listener.onQuantityChanged(item, quantity);
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRemoveButtonClick(item);
            }
        });
    }
}

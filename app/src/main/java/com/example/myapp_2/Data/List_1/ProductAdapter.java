package com.example.myapp_2.Data.List_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp_2.Data.cart.OnProductClickListener;
import com.example.myapp_2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private OnAddToCartClickListener addToCartClickListener;
    private List<Product> products;
    private Context context;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }
    private OnProductClickListener listener;
    public ProductAdapter(List<Product> products, OnProductClickListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        ProductViewHolder holder = new ProductViewHolder(view);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.productImage.setImageResource(product.getImageResource());
        holder.productName.setText(product.getName());
        holder.productDescription.setText(product.getDescription());
        holder.productRating.setText(String.format("%.1f", product.getRating()));
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
        holder.priceTextView.setText(context.getString(R.string.price, String.format("%.2f", product.getPrice())));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        Product product1 = new Product("Product 11", "Description 11", R.drawable.food_1, 0.0f);
        products.add(product1);

        Product product2 = new Product("Product 12", "Description 12", R.drawable.food_2, 0.0f);
        products.add(product2);

        Product product3 = new Product("Product 13", "Description 13", R.drawable.food_1_3, 0.0f);
        products.add(product3);

        Product product4 = new Product("Product 14", "Description 14", R.drawable.food_1_2, 0.0f);
        products.add(product4);

        Product product5 = new Product("Product 15", "Description 15", R.drawable.food_3_4, 0.0f);
        products.add(product5);

        Product product6 = new Product("Product 16", "Description 16", R.drawable.food_1_4, 0.0f);
        products.add(product6);

        Product product7 = new Product("Product 17", "Description 17", R.drawable.food_1_1, 0.0f);
        products.add(product7);

        Product product8 = new Product("Product 18", "Description 18", R.drawable.food_1_2, 0.0f);
        products.add(product8);

        Product product9 = new Product("Product 19", "Description 19", R.drawable.food_3, 0.0f);
        products.add(product9);

        Product product10 = new Product("Product 20", "Description 20", R.drawable.food_3_4, 0.0f);
        products.add(product10);



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
        TextView productRating;
        // Добавлено поле priceTextView
        TextView priceTextView;

        public ProductViewHolder(View view) {
            super(view);
            productImage = view.findViewById(R.id.product_image);
            productName = view.findViewById(R.id.product_name);
            productDescription = view.findViewById(R.id.product_description);
            productRating = view.findViewById(R.id.product_rating);
            addToCartButton = itemView.findViewById(R.id.add_button);
// Присвоен элемент с id price_text_view переменной priceTextView
            priceTextView = itemView.findViewById(R.id.price_text_view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showRatingDialog(getAdapterPosition());
                }
            });

        }
    }

    private void showRatingDialog(final int position) {
        final RatingDialog ratingDialog = new RatingDialog(context);
        ratingDialog.setRating(products.get(position).getRating());
        ratingDialog.setOnRatingChangedListener(new RatingDialog.OnRatingChangedListener() {
            @Override
            public void onRatingChanged(float newRating) {
                products.get(position).setRating(newRating);
                notifyDataSetChanged();
            }
        });
        ratingDialog.show();
    }
    public interface OnAddToCartClickListener {
        void onAddToCartClick(Product product);
    }
    public void setOnAddToCartClickListener(OnAddToCartClickListener listener) {
        this.addToCartClickListener = listener;
    }


}
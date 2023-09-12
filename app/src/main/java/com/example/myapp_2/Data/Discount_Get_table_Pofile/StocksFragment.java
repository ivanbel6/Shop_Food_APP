package com.example.myapp_2.Data.Discount_Get_table_Pofile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myapp_2.Data.List_1.Product;
import com.example.myapp_2.Data.List_1.ProductAdapter;
import com.example.myapp_2.Data.cart.Cart;
import com.example.myapp_2.Data.cart.CartFragment;
import com.example.myapp_2.R;

import java.util.ArrayList;
import java.util.List;

public class StocksFragment extends Fragment {

    ViewPager viewPager;
    StocksAdapter adapter;
    private ImageButton backButton;
    private ImageButton cartButton;
    ArrayList<StocksModel> stocksModelArrayList;
    ProductAdapter1 productAdapter;
    List<Product> products;

    private List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        Product product1 = new Product("Product 1", "Товар подешевел на 30%", R.drawable.food_1_4, 150.00,100.00);
        products.add(product1);

        Product product2 = new Product("Product 1", "Лучшая цена!", R.drawable.food_1_1, 5000.00,1050.00);
        products.add(product2);

        Product product3 =new Product("Product 1", "Товар подешевел на 15%", R.drawable.food_1_2, 510.00,350.00);
        products.add(product3);

        Product product4 = new Product("Product 1", "Товар подешевел на 2%", R.drawable.food_1, 225.00,199.00);
        products.add(product4);

        Product product5 = new Product("Product 1", "Товар подешевел на 5%", R.drawable.food_3_3, 700.00,650.00);
        products.add(product5);

        Product product6 = new Product("Product 1", "Товар подешевел на 10%", R.drawable.food_3_4, 600.00,450.00);
        products.add(product6);

        Product product7 =new Product("Product 1", "Товар подешевел на 15%", R.drawable.food_1_3, 550.00,225.00);
        products.add(product7);

        Product product8 = new Product("Product 1", "Товар подешевел на 36%", R.drawable.first_3, 400.00,300.00);
        products.add(product8);

        Product product9 =  new Product("Product 1", "Товар подешевел на 24%", R.drawable.food_3_2, 200.00,150.00);
        products.add(product9);

        Product product10 = new Product("Product 1", "Товар подорожал ,осторожно мошенники!", R.drawable.food_3_3, 100.00,150.00);
        products.add(product10);

        return products;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stocks, container, false);
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fragment_transition_animation);
        anim.setDuration(200);
        view.startAnimation(anim);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        stocksModelArrayList = new ArrayList<>();
        stocksModelArrayList.add(new StocksModel("Title1", "Description1", R.drawable.food_3));
        stocksModelArrayList.add(new StocksModel("Title2", "Description2", R.drawable.food_4));
        stocksModelArrayList.add(new StocksModel("Title2", "Description2", R.drawable.food_1_2));
        backButton = view.findViewById(R.id.backButton);
        cartButton = view.findViewById(R.id.cartButton);

        // устанавливаем обработчик нажатия на кнопку “назад”
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // возвращаемся на предыдущий фрагмент
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_container, new RestaurantsFragment()).addToBackStack(null).commit();
            }
        });

        // устанавливаем обработчик нажатия на кнопку “корзина”
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // возвращаемся на предыдущий фрагмент
                FragmentManager fragmentManager = requireFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_container, new CartFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productAdapter = new ProductAdapter1(getContext(), products);
        products = getProducts();
        productAdapter = new ProductAdapter1(getContext(), products);
        recyclerView.setAdapter(productAdapter);


        productAdapter.setOnAddToCartClickListener(new ProductAdapter.OnAddToCartClickListener() {
            @Override
            public void onAddToCartClick(Product product) {
                Toast.makeText(getActivity(),"Товар успешно добавлен!",Toast.LENGTH_SHORT).show();

                addToCart(product);
            }
        });

        return view;
    }
    private void addToCart(Product product) {
        Cart cart = Cart.getInstance();
        cart.addItem(product);
    }
}
package com.example.myapp_2.UI.view.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.example.myapp_2.UI.view.activities.ReserveTableActivity;
import com.example.myapp_2.UI.view.adapters.SliderAdapter;

import java.util.ArrayList;
import java.util.List;


public class SecondFragment extends Fragment implements View.OnClickListener {


    private Button buttonRegistre_table;
    private static final String MY_PREFS_NAME = "MyPrefs_2";


    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> products = getProducts();

    private float rating1 = 0.0f;
    private float rating2 = 0.0f;
    private float rating3 = 0.0f;
    private float rating4 = 0.0f;
    private float rating5 = 0.0f;
    private float rating6 = 0.0f;
    private float rating7 = 0.0f;
    private float rating8 = 0.0f;
    private float rating9 = 0.0f;
    private float rating10 = 0.0f;

    //private static final String MY_PREFS_NAME = "MyPrefs";

    public SecondFragment() {

    }

    private ViewPager viewPager;
    private int[] imageUrls = {R.drawable.second_1, R.drawable.second_3, R.drawable.second_4, R.drawable.second_5, R.drawable.second_22};

    private TextView textView;
    private EditText editText;
    private Button applyTextButton;
    private Button saveButton;
    private Switch switch1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";

    private String text;
    private boolean switchOnOff;

    //private String text;
    //private boolean switchOnOff;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_second, container, false);
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fragment_transition_animation);
        anim.setDuration(570);
        v.startAnimation(anim);




















        recyclerView = v.findViewById(R.id.recycler_view_products);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productAdapter = new ProductAdapter(getContext(), products);
        recyclerView.setAdapter(productAdapter);
        productAdapter.setOnAddToCartClickListener(new ProductAdapter.OnAddToCartClickListener() {
            @Override
            public void onAddToCartClick(Product product) {
                Toast.makeText(getActivity(),"Товар успешно добавлен!",Toast.LENGTH_SHORT).show();
                addToCart(product);
            }
        });
        viewPager = v.findViewById(R.id.view_pager);
        SliderAdapter adapter = new SliderAdapter(getActivity(), imageUrls);
        viewPager.setAdapter(adapter);

// Получаем оценки из SharedPreferences
        SharedPreferences prefs = getContext().getSharedPreferences("MyPrefs_2", Context.MODE_PRIVATE);
        rating1 = prefs.getFloat("rating1", 0.0f);
        rating2 = prefs.getFloat("rating2", 0.0f);
        rating3 = prefs.getFloat("rating3", 0.0f);
        rating4 = prefs.getFloat("rating4", 0.0f);
        rating5 = prefs.getFloat("rating5", 0.0f);
        rating6 = prefs.getFloat("rating6", 0.0f);
        rating7 = prefs.getFloat("rating7", 0.0f);
        rating8 = prefs.getFloat("rating8", 0.0f);
        rating9 = prefs.getFloat("rating9", 0.0f);
        rating10 = prefs.getFloat("rating10", 0.0f);


// Устанавливаем оценки в соответствующие товары
        products.get(0).setRating(rating1);
        products.get(1).setRating(rating2);
        products.get(2).setRating(rating3);
        products.get(3).setRating(rating4);
        products.get(4).setRating(rating5);
        products.get(5).setRating(rating6);
        products.get(6).setRating(rating7);
        products.get(7).setRating(rating8);
        products.get(8).setRating(rating9);
        products.get(9).setRating(rating10);

        products.get(0).setPrice(500.99);
        products.get(1).setPrice(720.99);
        products.get(2).setPrice(140.99);
        products.get(3).setPrice(220.99);
        products.get(4).setPrice(380.99);
        products.get(5).setPrice(105.99);
        products.get(6).setPrice(230.99);
        products.get(7).setPrice(170.99);
        products.get(8).setPrice(295.99);
        products.get(9).setPrice(235.99);


        viewPager = v.findViewById(R.id.view_pager);
       // SliderAdapter_2 adapter = new SliderAdapter_2(getActivity(), imageUrls);
        viewPager.setAdapter(adapter);

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int totalItems = viewPager.getAdapter().getCount();
                int nextItem = currentItem == totalItems - 1 ? 0 : currentItem + 1;
                viewPager.setCurrentItem(nextItem);
                handler.postDelayed(this, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);

        return v;
    }


    private NotificationManager notificationManager;
    private static final int NOTIFY_ID = 1;
    private static final String CHANNEL_ID = "CHANNEL_ID";
    Button b1;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fragment_transition_animation);
        anim.setDuration(500);
        view.startAnimation(anim);
        buttonRegistre_table = view.findViewById(R.id.reserveTable);

        buttonRegistre_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReserveTableActivity.class);
                intent.putExtra("restaurantName", "MyRestaurant");
                startActivityForResult(intent, 2);
            }
        });

        Button button = view.findViewById(R.id.cart_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = requireFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_container, new CartFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    public void saveData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, textView.getText().toString());
        editor.putBoolean(SWITCH1, switch1.isChecked());

        editor.apply();

        Toast.makeText(getActivity(), "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
        switchOnOff = sharedPreferences.getBoolean(SWITCH1, false);
    }

    public void updateViews() {
        //textView.setText(text);
      //  switch1.setChecked(switchOnOff);
    }

    private void initView(View view) {
        Button btnClick = (Button) view.findViewById(R.id.btn_1_second_fragment);
        btnClick.setOnClickListener(this);
        Button btnClick2 = (Button) view.findViewById(R.id.btn_3_second_fragment);
        btnClick2.setOnClickListener(this);
    }

    private SharedPreferences.Editor editor;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editor = getContext().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int myInt = bundle.getInt("hello world", 123);
            String strI = Integer.toString(myInt);
            Toast.makeText(getActivity(), strI, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onStart() {
        super.onStart();
// Загружаем данные из SharedPreferences
        loadData();
// Обновляем Views на экране
        updateViews();


    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = getContext().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putFloat("rating1", products.get(0).getRating());
        editor.putFloat("rating2", products.get(1).getRating());
        editor.putFloat("rating3", products.get(2).getRating());
        editor.putFloat("rating4", products.get(3).getRating());
        editor.putFloat("rating5", products.get(4).getRating());
        editor.putFloat("rating6", products.get(5).getRating());
        editor.putFloat("rating7", products.get(6).getRating());
        editor.putFloat("rating8", products.get(7).getRating());
        editor.putFloat("rating9", products.get(8).getRating());
        editor.putFloat("rating10", products.get(9).getRating());
        editor.apply();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private void changeFragment() {
        getFragmentManager().beginTransaction().replace(R.id.nav_container, new FirstFragment()).addToBackStack(null).commit();
    }

    private void changeFragment2() {
        getFragmentManager().beginTransaction().replace(R.id.nav_container, new ThirdFragment()).addToBackStack(null).commit();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_1_second_fragment)
            changeFragment();
        if(view.getId() == R.id.btn_3_second_fragment)
            changeFragment2();
    }

    public static void createChannelIfNeeded(NotificationManager manager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }
    }



    @Override
    public void onPause() {
        super.onPause();
// Сохраняем оценки в SharedPreferences
        SharedPreferences.Editor editor = getContext().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putFloat("rating1", products.get(0).getRating());
        editor.putFloat("rating2", products.get(1).getRating());
        editor.putFloat("rating3", products.get(2).getRating());
        editor.putFloat("rating4", products.get(3).getRating());
        editor.putFloat("rating5", products.get(4).getRating());
        editor.putFloat("rating6", products.get(5).getRating());
        editor.putFloat("rating7", products.get(6).getRating());
        editor.putFloat("rating8", products.get(7).getRating());
        editor.putFloat("rating9", products.get(8).getRating());
        editor.putFloat("rating10", products.get(9).getRating());
        editor.apply();

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Сохраняем оценки в Bundle
        outState.putFloat("rating1", rating1);
        outState.putFloat("rating2", rating2);
        outState.putFloat("rating3", rating3);
        outState.putFloat("rating4", rating4);
        outState.putFloat("rating5", rating5);
        outState.putFloat("rating6", rating6);
        outState.putFloat("rating7", rating7);
        outState.putFloat("rating8", rating8);
        outState.putFloat("rating9", rating9);
        outState.putFloat("rating10", rating10);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            // Восстанавливаем оценки из Bundle
            rating1 = savedInstanceState.getFloat("rating1");
            rating2 = savedInstanceState.getFloat("rating2");
            rating3 = savedInstanceState.getFloat("rating3");
            rating4 = savedInstanceState.getFloat("rating4");
            rating5 = savedInstanceState.getFloat("rating5");
            rating6 = savedInstanceState.getFloat("rating6");
            rating7 = savedInstanceState.getFloat("rating7");
            rating8 = savedInstanceState.getFloat("rating8");
            rating9 = savedInstanceState.getFloat("rating9");
            rating10 = savedInstanceState.getFloat("rating10");

            // Устанавливаем оценки в соответствующие товары
            products.get(0).setRating(rating1);
            products.get(1).setRating(rating2);
            products.get(2).setRating(rating3);
            products.get(3).setRating(rating4);
            products.get(4).setRating(rating5);
            products.get(5).setRating(rating6);
            products.get(6).setRating(rating7);
            products.get(7).setRating(rating8);
            products.get(8).setRating(rating9);
            products.get(9).setRating(rating10);
            products.get(0).setRating(rating1);
            products.get(1).setRating(rating2);
            products.get(2).setRating(rating3);
            products.get(3).setRating(rating4);
            products.get(4).setRating(rating5);
            products.get(5).setRating(rating6);
            products.get(6).setRating(rating7);
            products.get(7).setRating(rating8);
            products.get(8).setRating(rating9);
            products.get(9).setRating(rating10);

        }

    }
    private List<Product> getProducts() {
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



    private void addToCart(Product product) {
        Cart cart = Cart.getInstance();
        cart.addItem(product);
    }
}


package com.example.myapp_2.UI.view.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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
import com.example.myapp_2.Data.cart.OnProductClickListener;
import com.example.myapp_2.Data.register.RegistrationFragment;
import com.example.myapp_2.Data.PR_9_10.MyTask2;
import com.example.myapp_2.R;
import com.example.myapp_2.UI.view.activities.MainActivity;
import com.example.myapp_2.UI.view.activities.ReserveTableActivity;
import com.example.myapp_2.UI.view.adapters.SliderAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class FirstFragment extends Fragment implements View.OnClickListener ,RatingBar.OnRatingBarChangeListener {
    private Button buttonRegister;
    private Button buttonRegistre_table;
    private Button mShareButton;
    private Button cartButton;
    private BroadcastReceiver broadcastReceiver;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;


    private RatingBar ratingBar;
    private TextView ratingAverageTextView;


    private ViewPager viewPager;
    private int[] imageUrls = {R.drawable.first_1, R.drawable.first_2, R.drawable.first_3, R.drawable.first_4, R.drawable.first_5};
    private List<Product> products = getProducts();
    private static final String MY_PREFS_NAME = "MyPrefs";
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



    EditText mEditText;

    private static final String TAG = "FirstFragment";

    private long noteId;

    public FirstFragment(long noteId) {
        this.noteId = noteId;
    }
    public FirstFragment() {

    }


   // private ViewPager viewPager;


   // EditText mEditText;

    public void createFile(String fileName, String fileContent) {//Реализовать создание текстового файла в app-specific storage.
        try {
            FileOutputStream fos = requireContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write(fileContent);
            outputStreamWriter.close();
            String filePath = new File(requireContext().getFilesDir(), fileName).getAbsolutePath(); // получаем абсолютный путь
            Log.d(TAG, "File " + fileName + " created successfully at path:" + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Failed to create file " + fileName + ": " + e.getMessage());
        }
    }
    public void createTextFileInDirectory(String fileName) {
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File textFile = new File(directory, fileName);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(textFile);
            fileOutputStream.write("Hello World".getBytes());
            fileOutputStream.close();

            Toast.makeText(getActivity(), "File created at: " + textFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error creating file", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first, container, false);
        getActivity().findViewById(R.id.bottomNavigationView).setVisibility(View.VISIBLE);
        buttonRegistre_table = v.findViewById(R.id.reserveTable);

        buttonRegistre_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReserveTableActivity.class);
                intent.putExtra("restaurantName", "MyRestaurant");
                startActivityForResult(intent, 2);
            }
        });


        buttonRegister = v.findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).startRegistration(); // вызов метода startRegistration()
                Fragment fragment = new RegistrationFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_container, fragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });












        mShareButton = v.findViewById(R.id.btn_3);

        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareText();
            }
        });


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
        SharedPreferences prefs = getContext().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
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

        products.get(0).setPrice(300);
        products.get(1).setPrice(120);
        products.get(2).setPrice(140);
        products.get(3).setPrice(420);
        products.get(4).setPrice(380);
        products.get(5).setPrice(105);
        products.get(6).setPrice(200);
        products.get(7).setPrice(150);
        products.get(8).setPrice(190);
        products.get(9).setPrice(299.99);





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


        productAdapter = new ProductAdapter(products, new OnProductClickListener() {
            @Override
            public void onAddToCartClick(Product product) {
                addToCart(product);
            }
        });






















        


        return v;
    }

    private List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        Product product1 = new Product("Product 1", "Description 1", R.drawable.food_3_1jpg, 0.0f);
        products.add(product1);

        Product product2 = new Product("Product 2", "Description 2", R.drawable.food_1_1, 0.0f);
        products.add(product2);

        Product product3 = new Product("Product 3", "Description 3", R.drawable.food_1_4, 0.0f);
        products.add(product3);

        Product product4 = new Product("Product 4", "Description 4", R.drawable.food_1_1, 0.0f);
        products.add(product4);

        Product product5 = new Product("Product 5", "Description 5", R.drawable.food_1_1, 0.0f);
        products.add(product5);

        Product product6 = new Product("Product 6", "Description 6", R.drawable.food_1_1,0.0f);
        products.add(product6);

        Product product7 = new Product("Product 7", "Description 7", R.drawable.food_3_2,0.0f);
        products.add(product7);

        Product product8 = new Product("Product 8", "Description 8", R.drawable.food_3_3,0.0f);
        products.add(product8);

        Product product9 = new Product("Product 9", "Description 9", R.drawable.food_1_1,0.0f);
        products.add(product9);

        Product product10 = new Product("Product 10", "Description 10", R.drawable.food_1_1,0.0f);
        products.add(product10);

        return products;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {//После того как отрисовались лементы
        super.onViewCreated(view,savedInstanceState);
        initView(view);
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fragment_transition_animation);
        anim.setDuration(200);
        view.startAnimation(anim);























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









        ExecutorService executorService = Executors.newFixedThreadPool(3);// количество потоков в пуле --> 3:
        // Создание задач с разными параметрами
        Runnable task1 = new MyTask2("Hello");
        Runnable task2 = new MyTask2("World");
        Runnable task3 = new MyTask2("From my task");

        executorService.execute(task1);
        executorService.execute(task2);
        executorService.execute(task3);

        //создается пул с двумя потоками, создаются две задачи и запускаются через пул.
        // Каждая задача выполняется в своем потоке, и в консоль выводится имя каждого потока.

    }
    private void initView(View view){
        Button btnClick = (Button) view.findViewById(R.id.btn_2);
        Button btnClick2 = (Button) view.findViewById(R.id.btn_3);
        btnClick.setOnClickListener(this);
        btnClick2.setOnClickListener(this);

        Button btnClick3 = (Button) view.findViewById(R.id.cart_button);
        btnClick3.setOnClickListener(this);

        Button btnClick4 = (Button) view.findViewById(R.id.change_btn);
        btnClick4.setOnClickListener(this);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public void onStart() {
        super.onStart();



    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause(){
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
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    private void  changeFragment(){
        getFragmentManager().beginTransaction().replace(R.id.nav_container,new SecondFragment()).addToBackStack(null).commit();
    }
    private void  changeFragment2(){
        getFragmentManager().beginTransaction().replace(R.id.nav_container,new ThirdFragment()).addToBackStack(null).commit();
    }
    private void  changeFragment3(){
        getFragmentManager().beginTransaction().replace(R.id.nav_container,new ListFragment()).addToBackStack(null).commit();
    }
    private void  changeFragment4(){
        getFragmentManager().beginTransaction().replace(R.id.nav_container,new RecycleFragment()).addToBackStack(null).commit();
    }
    Context context;
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_2){
            changeFragment4();
            sendBroadcast();
        }
        if (view.getId() == R.id.cart_button){
            openCartFragment();
        }
        if (view.getId() == R.id.change_btn){
            changeFragment4();
        }
    }


    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

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

        }
    }

    private void shareText() {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello, world!");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }
    private void sendBroadcast() {
        Intent intent = new Intent("com.example.myapp_2.ACTION_RECEIVE_MESSAGE");
        intent.putExtra("message", "Hello, world!");
        requireContext().sendBroadcast(intent);
    }
    private void openCartFragment() {
        CartFragment cartFragment = new CartFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_2, cartFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void addToCart(Product product) {
        Cart cart = Cart.getInstance();
        cart.addItem(product);
    }


    // Метод, который создает список товаров

}


package com.example.myapp_2.UI.view.activities;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapp_2.Data.Discount_Get_table_Pofile.ProfileFragment;
import com.example.myapp_2.Data.Discount_Get_table_Pofile.RestaurantsFragment;
import com.example.myapp_2.Data.Discount_Get_table_Pofile.StocksFragment;
import com.example.myapp_2.Data.register.RegistrationFragment;
import com.example.myapp_2.Data.register.User;
import com.example.myapp_2.Data.register.UserDAO;
import com.example.myapp_2.R;
import com.example.myapp_2.UI.view.fragments.FirstFragment;
import com.example.myapp_2.ViewModel.ViewModels.NoteViewModel;
import com.example.myapp_2.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private NoteViewModel noteViewModel;

    private BroadcastReceiver broadcastReceiver;

    static boolean isRegister = false;
    Button button1;
    Button button3;
    ActivityMainBinding binding;
    private Button btn_fragment1,btn_fragment2,btn_fragment3;

    @SuppressLint("NonConstantResourceId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new FirstFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
                    if (item.getItemId() == R.id.restaurants) {
                        replaceFragment(new RestaurantsFragment());
                    }
                    if (item.getItemId() == R.id.discounts) {
                        replaceFragment(new StocksFragment());
                    }
                    if (item.getItemId() == R.id.profile) {
                        replaceFragment(new ProfileFragment());
                    }
                    return true;
                });

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_container, new FirstFragment()).addToBackStack(null).commit();

        if (isRegister) {
            binding.bottomNavigationView.setVisibility(View.GONE);
        } else {
            binding.bottomNavigationView.setVisibility(View.VISIBLE);
        }

// получение данных пользователя при каждой авторизации
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey("user_id")) {
                int userId = getIntent().getExtras().getInt("user_id");
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_container, new ProfileFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
    }

    private void registerBroadcastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
// Получение сообщения
                String action = intent.getAction();
                if(action.equals("com.example.myapplication_2.SEND_MESSAGE")) {
                    String message = intent.getStringExtra("MESSAGE");
// Вывод сообщения в лог
                    Log.d("MyApp2", "Received message: " + message);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.myapplication_2.SEND_MESSAGE");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onClick(View view) {//2 способ

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
// Удаление приемника
        unregisterReceiver(broadcastReceiver);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_container,fragment);
        String A = "HELLO";


        Bundle bundle = new Bundle();
        bundle.putInt("hello world", 4344);
        fragment.setArguments(bundle);


        fragmentTransaction.commit();

    }

// вызов фрагмента регистрации с передачей кода запроса

    public void startRegistration() {
        isRegister = true;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_container, new RegistrationFragment()).addToBackStack(null).commit();
    }

    // обработка полученного результата (кода запроса)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            isRegister = true; // установка значения переменнойisRegister в true
        }
// обновление данных пользователя после закрытия фрагмента профиля
        if (requestCode == 2) {
            if (data != null && data.getBooleanExtra("profile_updated", false)) {
// получение текущего пользователя из базы данных
                UserDAO userDAO = new UserDAO(this);
                userDAO.open();
                int userId = data.getIntExtra("user_id", 0);
                User user = userDAO.getUserById(userId);
                userDAO.close();

// обновление отображаемых данных в editText
                EditText editTextName = findViewById(R.id.editTextName);
                EditText editTextEmail = findViewById(R.id.editTextEmail);
                EditText editTextPassword = findViewById(R.id.editTextPassword);
                editTextName.setText(user.getName());
                editTextEmail.setText(user.getEmail());
                editTextPassword.setText(user.getPassword());
            }
        }
    }
    public interface FragmentHost {
        void switchFragment(Fragment fragment);
    }

    public void switchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_container, fragment)
                .commit();
    }

}
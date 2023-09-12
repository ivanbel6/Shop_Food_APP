package com.example.myapp_2.Data;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.example.myapp_2.R;
import com.example.myapp_2.databinding.ActivityMainBinding;

public class MyService extends Service{

    private WindowManager windowManager;
    private View overlayView;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("InflateParams")
    @Override
    public void onCreate() {
        super.onCreate();

        // Инициализируем WindowManager
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // Создаем LayoutParams для нашего View
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        // Создаем наш View из layout-файла overlaylayout.xml
        overlayView = LayoutInflater.from(this).inflate(R.layout.overlay_layout, null);

        // Добавляем View на экран
        int LAYOUT_FLAG;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
        }


        windowManager.addView(overlayView, params);

         //Устанавливаем слушатель кликов на кнопку в overlaylayout.xml
        Button button = overlayView.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Открываем приложение
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setClassName("com.example.myapp_2", "com.example.myapp_2.UI.view.activities.MainActivity");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                onDestroy();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Удаляем View из экрана при остановке сервиса
        if (overlayView != null) {
            windowManager.removeView(overlayView);
            overlayView = null;
        }
    }


}
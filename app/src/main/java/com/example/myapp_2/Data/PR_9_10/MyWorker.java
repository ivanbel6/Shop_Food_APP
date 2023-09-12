package com.example.myapp_2.Data.PR_9_10;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {
    private static final String TAG = "MyWorker";

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
// Выполнение основной работы в потоках
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep((long) (Math.random() * 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setProgressAsync(new Data.Builder().putInt("progress", i * 10).build());
        }

// Возвращаем финальный результат
        return Result.success();
    }
}
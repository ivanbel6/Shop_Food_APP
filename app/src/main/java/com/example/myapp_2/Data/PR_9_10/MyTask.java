package com.example.myapp_2.Data.PR_9_10;

public class MyTask implements Runnable {

    @Override
    public void run() {
        // Логика
        System.out.println("Task executed by thread " + Thread.currentThread().getName());
    }
}

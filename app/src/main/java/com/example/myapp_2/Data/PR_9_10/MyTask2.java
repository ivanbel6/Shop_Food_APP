package com.example.myapp_2.Data.PR_9_10;

public class MyTask2 implements Runnable {
    private String message;

    public MyTask2(String message) {
        this.message = message;
    }

    @Override
    public void run() {

        //Внутри этого метода осуществляется вывод сообщения в консоль с
        // именем потока, который выполняет задачу, и сообщением, которое задается через конструктор MyTask2.
        // Затем выполнение потока замедляется на секунду с помощью метода Thread.sleep(1000) в блоке try-catch.
        System.out.println("Task executed by thread " + Thread.currentThread().getName() + ", message: " + message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
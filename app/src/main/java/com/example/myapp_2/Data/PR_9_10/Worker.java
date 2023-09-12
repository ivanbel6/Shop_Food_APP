package com.example.myapp_2.Data.PR_9_10;

public abstract class Worker {
    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    protected void progressChanged(int progress) {
        if (listener != null) {
            listener.onProgressChanged(progress);
        }
    }

    protected void onResult(String result) {
        if (listener != null) {
            listener.onResult(result);
        }
    }

    protected abstract void execute();

    public interface Listener {
        void onProgressChanged(int progress);
        void onResult(String result);
    }
}

    //В этом абстрактном классе определены методы для уведомления о прогрессе выполнения и о результате работы.
// Теперь тебе нужно имплементировать этот интерфейс в фрагменте (или другом классе) и вызвать MyWorker:
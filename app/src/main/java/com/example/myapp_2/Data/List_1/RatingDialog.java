package com.example.myapp_2.Data.List_1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myapp_2.R;

public class RatingDialog extends AlertDialog implements RatingBar.OnRatingBarChangeListener {
    private RatingBar ratingBar;
    private TextView ratingMessage;
    private float rating;
    private OnRatingChangedListener listener;

    public RatingDialog(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_rating, null);
        ratingBar = view.findViewById(R.id.rating_bar);
        ratingMessage = view.findViewById(R.id.rating_message);
        ratingBar.setOnRatingBarChangeListener(this);
        setView(view);
    }

    public void setRating(float rating) {
        this.rating = rating;
        ratingBar.setRating(rating);
    }

    public void setOnRatingChangedListener(OnRatingChangedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        this.rating = rating;
        String message = String.format(getContext().getString(R.string.rating_message), String.valueOf(rating));
        ratingMessage.setText(message);

        // Сохраняем оценку в SharedPreferences
        SharedPreferences prefs = getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat("rating", rating);
        editor.apply();
    }

    @Override
    public void onStart() {
        super.onStart();
        onRatingChanged(ratingBar, rating, true);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        rating = savedInstanceState.getFloat("rating");
        ratingBar.setRating(rating);
    }


    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState();
        outState.putFloat("rating", rating);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (listener != null) {
            listener.onRatingChanged(rating);
        }
    }

    public interface OnRatingChangedListener {
        void onRatingChanged(float newRating);
    }
}

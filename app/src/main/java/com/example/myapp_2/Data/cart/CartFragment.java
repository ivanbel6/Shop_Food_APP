package com.example.myapp_2.Data.cart;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp_2.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp_2.R;

public class CartFragment extends Fragment implements Cart.OnCartChangedListener {

    private TextView totalPriceTextView;
    private Button placeOrderButton;
    private RecyclerView recyclerView;
    private Button backButton;
    private Toolbar myToolbar;

    private CartAdapter adapter;
    private Cart cart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        totalPriceTextView = view.findViewById(R.id.total_price_text_view);
        placeOrderButton = view.findViewById(R.id.place_order_button);
        recyclerView = view.findViewById(R.id.cart_recycler_view);

        cart = Cart.getInstance();
        cart.addOnCartChangedListener(this);

        adapter = new CartAdapter(cart.getItems(), new OnCartItemListener() {
            @Override
            public void onQuantityChanged(CartItem item, int newQuantity) {
                cart.updateItem(item, newQuantity);
            }

            @Override
            public void onRemoveButtonClick(CartItem item) {
                cart.removeItem(item);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        updateTotalPrice();

        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создание уведомления

                String orderPriceStr = getString(R.string.cart_item_total_price_2, cart.getTotalPrice());
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "channel_id")
                        .setSmallIcon(R.drawable.baseline_fastfood_24)
                        .setContentTitle("Заказ оформлен")
                        .setContentText("Ваш заказ успешно оформлен"+ " "+ orderPriceStr+"!")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                notificationManager.notify(1, builder.build());
                // Закрытие текущего экрана
                getActivity().onBackPressed();

                // Очистка списка заказов
                adapter.clear();
            }
        });
        NotificationHelper.createNotificationChannel(getContext());
        return view;
    }

    private void updateTotalPrice() {
        double totalPrice = cart.getTotalPrice();

        if (getContext() != null) {
        totalPriceTextView.setText(getString(R.string.cart_item_total_price, totalPrice));
    }
    }

    @Override
    public void onCartChanged() {
        adapter.setItems(cart.getItems());
        updateTotalPrice();
    }
}

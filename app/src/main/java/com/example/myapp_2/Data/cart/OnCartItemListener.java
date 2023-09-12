package com.example.myapp_2.Data.cart;

public interface OnCartItemListener {
    void onQuantityChanged(CartItem item, int newQuantity);//вызывается при изменении количества товара в корзине
    void onRemoveButtonClick(CartItem item);//вызывается при нажатии на кнопку удаления товара из корзины
}

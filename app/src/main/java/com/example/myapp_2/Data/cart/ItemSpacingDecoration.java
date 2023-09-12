package com.example.myapp_2.Data.cart;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class ItemSpacingDecoration extends RecyclerView.ItemDecoration {

    private int mSpacing; // промежуток между элементами

    public ItemSpacingDecoration(int spacing) {
        this.mSpacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

// Добавляем отступ снизу элемента
        outRect.bottom = mSpacing;
    }
}
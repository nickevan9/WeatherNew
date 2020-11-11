package com.netviet.weathernew.app;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SimpleItemDecorator extends RecyclerView.ItemDecoration {

    int space;
    boolean isHorizontalLayout;
    public SimpleItemDecorator(int space) {
        this.space = space;
    }

    public SimpleItemDecorator(int space, boolean isHorizontalLayout) {
        this.space = space;
        this.isHorizontalLayout = isHorizontalLayout;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        final int itemPosition = parent.getChildAdapterPosition(view);


        if (isHorizontalLayout) {
            if (itemPosition == state.getItemCount()) {

            } else {
                outRect.right = space;
            }


        } else {
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == 0)
                outRect.top = space;
            else
                outRect.top = 0;

        }


    }
}
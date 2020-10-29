package com.netviet.weathernew.widget.customview;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.netviet.weathernew.app.TypefaceProvider;


public class CustomTextviewBold extends AppCompatTextView {
    public CustomTextviewBold(Context context) {
        super(context);
        initView();
    }

    public CustomTextviewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomTextviewBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    protected void initView() {
        Typeface myTypeface = TypefaceProvider.getTypeFace(getContext(), "UTM-HelveBold");
        setTypeface(myTypeface);

    }

    @Override
    public boolean isInEditMode() {
        return true;
    }
}
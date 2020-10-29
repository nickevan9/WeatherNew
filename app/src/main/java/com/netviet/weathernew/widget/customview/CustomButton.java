package com.netviet.weathernew.widget.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import com.netviet.weathernew.app.TypefaceProvider;

public class CustomButton extends AppCompatButton {
    public CustomButton(Context context) {
        super(context, null, android.R.attr.borderlessButtonStyle);
        initView();
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.borderlessButtonStyle);
        initView();
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, android.R.attr.borderlessButtonStyle);
        initView();
    }

    protected void initView (){
        Typeface myTypeface = TypefaceProvider.getTypeFace(getContext(), "UTM-Helve");
        setTypeface(myTypeface);

        setTransformationMethod(null);
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }
}

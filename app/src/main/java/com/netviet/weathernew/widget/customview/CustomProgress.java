package com.netviet.weathernew.widget.customview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;

import com.netviet.weathernew.R;

public class CustomProgress extends ConstraintLayout {
    private Guideline guidelineChange;


    public CustomProgress(@NonNull Context context) {
        super(context);
    }

    public CustomProgress(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomProgress(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void initView() {
        guidelineChange = findViewById(R.id.guideline_change);
    }

    public void applyData(int process) {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) guidelineChange.getLayoutParams();
        params.guidePercent = process % 5;

        guidelineChange.setLayoutParams(params);
    }
}

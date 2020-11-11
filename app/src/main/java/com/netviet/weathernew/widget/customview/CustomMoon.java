package com.netviet.weathernew.widget.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.netviet.weathernew.R;

import java.util.Objects;

public class CustomMoon extends View {

    private Paint mCirclePaint;
    private Paint mCirclePaintInside;
    private Paint mOvalPaint;
    private double mPercentMoon;
    private float mPercent;
    private String mColorStroke = "#F5EDED";
    private String mColorMoon = "#FFFFFF";


    public CustomMoon(Context context) {
        super(context);
        initView(context, null);
    }

    public CustomMoon(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public CustomMoon(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    protected void initView(Context context, AttributeSet attrs) {
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaintInside = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOvalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPercentMoon = 0.0;
        mPercent = 0f;

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.MoonCustomView);

        try {
            mPercentMoon = Double.parseDouble(Objects.requireNonNull(mTypedArray.getString(R.styleable.MoonCustomView_percent_moon)));
            mColorStroke = Objects.requireNonNull(mTypedArray.getString(R.styleable.MoonCustomView_strokeMoon));
            mColorMoon = Objects.requireNonNull(mTypedArray.getString(R.styleable.MoonCustomView_moonInsideColor));
        } catch (NullPointerException e) {

        } finally {
            mTypedArray.recycle();
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int mWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int mWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int mHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int mHeightSize = MeasureSpec.getSize(heightMeasureSpec);

        int mWidth = 0;
        int mHeight = 0;

        switch (mWidthMode) {
            case MeasureSpec.EXACTLY: {
                mWidth = mWidthSize;
                break;
            }

            case MeasureSpec.AT_MOST: {
                mWidth = 110;
                break;
            }

            default:
                mWidth = 110;
        }

        switch (mHeightMode) {
            case MeasureSpec.EXACTLY: {
                mHeight = mHeightSize;
                break;
            }

            case MeasureSpec.AT_MOST: {
                mHeight = 110;
                break;
            }

            default:
                mHeight = 110;
        }

        setMeasuredDimension(mWidth, mHeight);


    }

    @Override
    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setColor(Color.parseColor("#F5EDED"));
        mCirclePaint.setStrokeWidth(3f);

        mCirclePaintInside.setStyle(Paint.Style.FILL);
        mCirclePaintInside.setColor(Color.parseColor("#F5EDED"));

        mOvalPaint.setStyle(Paint.Style.FILL);
        mOvalPaint.setColor(Color.parseColor("#F5EDED"));

        if (mPercentMoon == 0.0) {
            RectF mCircleRectF = new RectF(2F, 2F, 102f, 102f);
            canvas.drawOval(mCircleRectF, mCirclePaint);
        } else if (mPercentMoon >= 0.5 && mPercentMoon <= 1) {
            mPercent = (float) (mPercentMoon * 100);

            RectF mCircleRectF = new RectF(2F, 2F, 102f, 102f);
            canvas.drawOval(mCircleRectF, mCirclePaint);

            mCirclePaint.setStyle(Paint.Style.FILL);
            RectF mOvalRecF = new RectF(102 - mPercent, 2F, mPercent, 102F);
            canvas.drawOval(mOvalRecF, mCirclePaint);

            RectF mArcRecF = new RectF(2F, 2F, 102F, 102F);
            canvas.drawArc(mArcRecF, 91F, 180F, false, mOvalPaint);
        } else {
            float mPercent = (float) (mPercentMoon * 100);

            RectF mCircleRectFInside = new RectF(2F, 2F, 102F, 102F);
            canvas.drawOval(mCircleRectFInside, mCirclePaintInside);

            RectF mCircleRectF =new RectF(2F, 2F, 102f, 102f);
            canvas.drawOval(mCircleRectF, mCirclePaint);
            mOvalPaint.setColor(Color.BLACK);

            RectF mOvalRecF =new RectF(mPercent, 2F, 102 - mPercent, 102F);
            canvas.drawOval(mOvalRecF, mOvalPaint);

            RectF mArcRecF =new RectF(2F, 2F, 102F, 102F);
            canvas.drawArc(mArcRecF, -91F, 180F, false, mOvalPaint);
        }

    }

    public void updateMoon(Double percent){
        this.mPercentMoon = percent;
        invalidate();
    }


}

package com.example.admin.customeviewsapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CustomRectangle extends View {

    private static final String TAG = "CustomRectangle";
    int color, width, height;

    public CustomRectangle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.custom_rectangle,
                0,
                0);

        width = typedArray.getColor(R.styleable.custom_rectangle_r_width, 30);
        height = typedArray.getColor(R.styleable.custom_rectangle_r_height, 10);
        color = typedArray.getColor(R.styleable.custom_rectangle_r_color, 0);

        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor( color );

//        canvas.drawRect( 0, 0, width, height, paint );
        canvas.drawRect( width*.25f, 0, width*.75f, height, paint );
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.d(TAG, "onMeasure: ");

        int desiredWidth = 300;
        int desiredHeight = 100;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
//            Log.d(TAG, "onMeasure: width mode == MeasureSpec.EXACTLY");
//            Log.d(TAG, "onMeasure: width is " + width);
//            Log.d(TAG, "onMeasure: widthSize is " + widthSize);
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
//            Log.d(TAG, "onMeasure: widthMode == MeasureSpec.AT_MOST");
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
//            Log.d(TAG, "onMeasure: width else");
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
//            Log.d(TAG, "onMeasure: heightMode == MeasureSpec.EXACTLY");
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
//            Log.d(TAG, "onMeasure: heightMode == MeasureSpec.AT_MOST");
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
//            Log.d(TAG, "onMeasure: height else");
            height = desiredHeight;
        }

        Log.d(TAG, "onMeasure: width: " + width);
        Log.d(TAG, "onMeasure: height: " + height);
        setMeasuredDimension(width, height);
    }
}

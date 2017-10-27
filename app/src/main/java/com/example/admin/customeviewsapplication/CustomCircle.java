package com.example.admin.customeviewsapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CustomCircle extends View {

    private static final String TAG = "CustomCircle";
    int radius, color, width, height;

    public CustomCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.custom_circle,
                0,
                0);

        radius = typedArray.getInt(R.styleable.custom_circle_radius, 20);
        color = typedArray.getColor(R.styleable.custom_circle_color, 0);

        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor( color );

        if( radius > width/2 )
            radius = width/2;

        canvas.drawCircle( width/2, height/2, radius, paint );
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.d(TAG, "onMeasure: ");

        int desiredWidth = 300;
        int desiredHeight = 300;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        Log.d(TAG, "onMeasure: width: " + width);
        Log.d(TAG, "onMeasure: height: " + height);
        setMeasuredDimension(width, height);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius += radius;
        Log.d(TAG, "setRadius: new radius: " + this.radius);
        invalidate();
    }
}

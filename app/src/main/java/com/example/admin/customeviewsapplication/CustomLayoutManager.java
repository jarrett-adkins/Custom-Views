package com.example.admin.customeviewsapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomLayoutManager extends ViewGroup implements View.OnClickListener {

    private static final String TAG = "CustomLayout";
    @BindView(R.id.btnLogin)
    Button btnLogin;

    public CustomLayoutManager(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(getContext()).inflate(R.layout.custom_login_view, this, true);
        ButterKnife.bind(this);
//        etUserName.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        Log.d(TAG, "onClick: clicked.");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        int curWidth, curHeight, curLeft, curTop, maxHeight;

        //get the available size of child view
        final int childLeft = this.getPaddingLeft();
        final int childTop = this.getPaddingTop();
        final int childRight = this.getMeasuredWidth() - this.getPaddingRight();
        final int childBottom = this.getMeasuredHeight() - this.getPaddingBottom();
        final int childWidth = childRight - childLeft;
        final int childHeight = childBottom - childTop;

        maxHeight = 0;
        curLeft = childLeft;
        curTop = childTop;

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);

            if (child.getVisibility() == GONE)
                return;

            //Get the maximum size of the child
            child.measure(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.AT_MOST),
                    MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.AT_MOST));
            curWidth = child.getMeasuredWidth();
            curHeight = child.getMeasuredHeight();

            curLeft = childLeft;
            curTop += maxHeight;
            maxHeight = 0;

            //do the layout
            child.layout(curLeft, curTop, this.getMeasuredWidth(), curTop + curHeight);
//            Log.d(TAG, "onLayout: curLeft: " + curLeft);
//            Log.d(TAG, "onLayout: curTop: " + curTop);
//            Log.d(TAG, "onLayout: right: " + this.getMeasuredWidth());
//            Log.d(TAG, "onLayout: bottom: " + (curTop + curHeight));
            //store the max height
            if (maxHeight < curHeight)
                maxHeight = curHeight;
            curLeft += curWidth;
        }
    }

//    @OnClick(R.id.btnLogin)
//    public void onViewClicked() {
//        Log.d(TAG, "onClick: clicked.");
//    }

    public Button getButton() {
        return btnLogin;
    }
}

package com.example.measuredemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/10/20.
 */

public class MyView extends View {

    private static final String TAG = "TAG";
    //给自定义view一个确定的初始值
    private int viewWidth = 100;
    private int viewHeight = 100;

    //半径
    private int mRadius;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthResult = 0;
        int heightResult = 0;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec); //取出宽度的测量模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec); //取出宽度的大小

        int heightMode = MeasureSpec.getMode(heightMeasureSpec); //取出高度的测量模式
        int heightSize = MeasureSpec.getSize(heightMeasureSpec); //取出高度的大小


        int minWidth = viewWidth;
        int minHeigth = viewHeight;

        if (widthMode == MeasureSpec.AT_MOST) {
            widthResult = Math.min(minWidth, widthSize);
        } else {
            widthResult = widthSize;
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            heightResult = Math.min(minHeigth, heightSize);
        } else {
            heightResult = heightSize;
        }
        //设置需要绘制的大小
        setMeasuredDimension(widthResult, heightResult);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#0000ff"));
//        mPaint.setAntiAlias(true);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius, mPaint);
    }

    /**
     * @param w    大小改变之后的宽度值
     * @param h    大小改变之后的高度值
     * @param oldw 大小改变之前的宽度值
     * @param oldh 大小改变之前的宽高度值
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


        Log.i(TAG, "new:" + w + "---" + h);
        Log.i(TAG, "old:" + oldw + "---" + oldh);
        mRadius = mRadius + 2;


    }

    public void changeView(int w, int h) {

        viewWidth = viewWidth + w;
        viewHeight = viewHeight + h;

        requestLayout();

    }

}

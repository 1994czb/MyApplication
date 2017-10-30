package com.example.ondrawdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/10/20.
 */

public class MyView extends View {
    private Paint mPaint;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL); //充满绘制区域
//        mPaint.setStyle(Paint.Style.STROKE);//绘制线条
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE); //同时充满并绘制线条
        mPaint.setStrokeWidth(5); //线条宽度
        mPaint.setDither(true);  //设置抖动

        mPaint.setStrokeCap(Paint.Cap.BUTT);//画点的时候绘制点的形状


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = mPaint;

//        Rect rect = new Rect() //int类型矩形
//        RectF rect = new RectF() //float类型矩形


//        canvas.drawRect(0,0,100,100,paint);//画矩形
//        canvas.drawPoint(100,100,paint);



//        float[] li = {20, 20, 120, 20,
//                70, 20, 70, 120,
//                20, 120, 120, 120,
//                150, 20, 250, 20,
//                150, 20, 150, 120,
//                250, 20, 250, 120,
//                150, 120, 250, 120};
//        canvas.drawLines(li,4,8,paint);//绘制多个线条

//        canvas.drawRoundRect(100,100,500,500,50,50,paint);//绘制圆角矩形


//        canvas.drawArc(100,100,500,500,10,100,true,paint); //绘制弧形
//        canvas.drawArc(500,500,600,600,40,180,false,paint);


//        Path path = new Path();
//
//        path.lineTo(200,200);
//        path.lineTo(500,100);
//        path.rLineTo(100,200);
////        path.moveTo(600,600);
////        path.rLineTo(100,100);
////        path.close();
//
//
//        canvas.drawPath(path,paint);  //画Path



    }
}

package com.example.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.exercise1.R;

/**
 * 类描述：
 * 创建时间：2018/3/16
 *
 * @author chaochao
 */

public class GraphView extends View {

//    <!--线-->
//            <enum name="line" value="1001"/>
//
//            <!--三角形-->
//            <enum name="triangle" value="1002"/>
//
//            <!--多边形-->
//            <enum name="polygon" value="1003"/>
//
//            <!--矩形-->
//            <enum name="rectangle" value="1004"/>
//
//            <!--圆角矩形-->
//            <enum name="round_rectangle" value="1005"/>
//
//            <!--圆-->
//            <enum name="circle" value="1006"/>
//
//            <!--弧形-->
//            <enum name="arc" value="1007"/>
    private static final int TYPE_LINE = 1001;
    private static final int TYPE_TRIANGLE = 1002;
    private static final int TYPE_POLYGON = 1003;
    private static final int TYPE_RECTANGLE = 1004;
    private static final int TYPE_ROUND_RECTANGLE = 1005;
    private static final int TYPE_CIRCLE = 1006;
    private static final int TYPE_ARC = 1007;

    private int type;

    public GraphView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.GraphView);
        type = array.getInt(array.getIndex(R.styleable.GraphView_type),TYPE_LINE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();

        //设置画笔颜色
        paint.setColor(Color.RED);

        //设置画笔样式
        //FILL全部填充   STROKE只画轮廓
        paint.setStyle(Paint.Style.STROKE);
        //设置画笔宽度
        paint.setStrokeWidth(2);

        //设置是否抗锯齿
        paint.setAntiAlias(true);


        switch (type){
            case TYPE_LINE:
                //画一条线
                canvas.drawLine(0,0,100,100,paint);
                break;
            case TYPE_TRIANGLE:
                //画一个三角形
                Path path = new Path();
                path.moveTo(10,0);
                path.lineTo(100,100);
                path.lineTo(5,9);
                path.close();
                canvas.drawPath(path,paint);
                break;
            case TYPE_POLYGON:
                //画一个多边形
                Path path1 = new Path();
                path1.moveTo(10,0);
                path1.lineTo(100,100);
                path1.lineTo(200,150);
                path1.lineTo(200,100);
                path1.lineTo(100,50);
                path1.close();
                canvas.drawPath(path1,paint);
                break;
            case TYPE_RECTANGLE:
                //画一个矩形
                Rect rect = new Rect(0,0,100,100);
                canvas.drawRect(rect,paint);
                break;
            case TYPE_ROUND_RECTANGLE:
                //画一个圆角矩形
                RectF rectf = new RectF(0,0,100,100);
                canvas.drawRoundRect(rectf,20,10,paint);
                break;
            case TYPE_CIRCLE:
                //画一个圆
                canvas.drawCircle(50,50,40,paint);
                break;
            case TYPE_ARC:
                //画一个弧形
                RectF rect3 = new RectF(0,0,100,100);
                canvas.drawArc(rect3,0,270,true,paint);
                break;
        }
    }
}

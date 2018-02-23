package com.example.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.exercise1.R;

/**
 * Created by Leovo on 2018/1/12.
 */
public class MyTextView extends View {

    private static final String TAG = "MyTextView";
    private String title;
    private int titleSize;
    private int titleColor;

    private Paint paint;
    public MyTextView(Context context) {
        this(context,null);
        Log.i(TAG,"MyTextView1");
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        Log.i(TAG,"MyTextView2");
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG,"MyTextView3");

        //1.在attrs.xml文件中自定义所需的属性
        //2.在引用自定义view的地方给自定义的属性设置值
        //3.自定义View中取出这些值
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,R.styleable.MyTextView,defStyleAttr,0);
        int n = typedArray.getIndexCount();
        for (int i=0;i<n;i++){
            int attr = typedArray.getIndex(i);
            switch (attr){
                case R.styleable.MyTextView_title:
                    title = typedArray.getString(attr);
                    break;
                case R.styleable.MyTextView_titleColor:
                    titleColor = typedArray.getColor(attr,0);
                    break;
                case R.styleable.MyTextView_titleSize:
                    titleSize = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }
        }
        typedArray.recycle();

        paint = new Paint();
        paint.setTextSize(titleSize);//设置字体
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Rect rect = new Rect(0,0,getMeasuredWidth(),getMeasuredHeight());
        paint.setColor(Color.YELLOW);
        canvas.drawRect(rect,paint);

        Log.i(TAG,"tilteSize--->" + titleSize);
        Log.i(TAG,"title---->" + title);

        Rect mBound = new Rect();
        paint.getTextBounds(title, 0, title.length(), mBound);
        paint.setColor(titleColor);
//        canvas.drawText(title,0 , mBound.height(),paint);
        //当设置为（0,0）时，描绘字体原点在左下角
        canvas.drawText(title,getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2,paint);
    }
}

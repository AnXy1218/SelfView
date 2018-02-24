package com.example.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.exercise1.R;

/**
 * 类描述：自定义View：认识OnMeasure和OnDraw函数
 * 参考文档：http://blog.csdn.net/huachao1001/article/details/51577291
 * 创建时间：2018/2/23
 *
 * @author An xy
 */
public class MyView extends View{
    private static final String TAG = "MyView";
    private String content;

    private Paint mPaint;

    private Rect mBound;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyView,defStyleAttr,0);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.MyView);
        content = array.getString(R.styleable.MyTextView_title);

        array.recycle();//获取到值以后，将array回收掉


        mPaint = new Paint();

        mPaint.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        mBound = new Rect();

        mPaint.getTextBounds(content,0,content.length(),mBound);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int myWidth = mySize(100,widthMeasureSpec,0);
        int myHeight = mySize(100,heightMeasureSpec,1);
//        Log.i(TAG,"width---->" + myWidth);
        setMeasuredDimension(myWidth,myHeight);
    }

    /**
     * 该方法用来获取不同情况下，我们需要设置的最终值
     * @param defaultSize
     * @param measureSpec
     * @param widthOrHeight 0为width，其他为height
     * @return
     */
    private int mySize(int defaultSize,int measureSpec,int widthOrHeight){
        int mySize = defaultSize;
        /**
         * 1.获取测量模式：通常有三种，如下
         * UNSPECIFIED  父容器没有对当前View有任何限制，当前View可以任意取尺寸
         * EXACTLY  当前的尺寸就是当前View应该取的尺寸
         * AT_MOST  当前尺寸是当前View能取得最大尺寸
         * 2.xml中设置的尺寸对应
         * match_parent--->EXACTLY
         * wrap_content--->AT_MOST
         * 固定尺寸（ex：100dp）---->EXACTLY
         */
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode){
            case MeasureSpec.UNSPECIFIED:
                mySize = defaultSize;
                break;
            case MeasureSpec.AT_MOST:
                //测量最大值为size
//                mySize = size;
                if (widthOrHeight == 0){
                    mySize = getPaddingLeft() + mBound.width() + getPaddingRight();
                }else{
                    mySize = getPaddingTop() + mBound.height() + getPaddingBottom();
                }
                break;
            case MeasureSpec.EXACTLY:
                mySize = size;
                break;
        }

        return mySize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLACK);

        canvas.drawText(content,getWidth()/2-mBound.width()/2,getHeight()/2+mBound.height()/2,mPaint);
    }
}

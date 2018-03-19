package com.example.viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.exercise1.R;

/**
 * 类描述：自定义ViewGroup,实现一个LinearLayout
 * 创建时间：2018/2/24
 *
 * @author An xy
 */
public class MyLinearLayout extends ViewGroup{
    public static final int ORIENTATION_VERTICAL = 101;//垂直方向
    public static final int ORIENTATION_HORIZONTAL = 102;//水平方向

    public static final int TYPE_WIDTH = 0;//宽度标识
    public static final int TYPE_HEIGHT = 1;//高度标识
    private int orientation = 0;
    public MyLinearLayout(Context context) {
        this(context,null);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyLinearLayout);

        orientation = array.getInt(R.styleable.MyLinearLayout_orientation,ORIENTATION_VERTICAL);

        array.recycle();
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //测量所有子View的尺寸
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //当外层嵌套一个ScrollView时，一般模式是UNSPECIFIED
        if (widthMode == MeasureSpec.UNSPECIFIED){
            widthMode = MeasureSpec.AT_MOST;
        }

        if (heightMode == MeasureSpec.UNSPECIFIED){
            heightMode = MeasureSpec.AT_MOST;
        }

        if (orientation == ORIENTATION_VERTICAL){
            //垂直方向排列
            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
                //宽高测量方式都是wrap_content
                setMeasuredDimension(getMaxValue(TYPE_WIDTH),getTotalValue(TYPE_HEIGHT));
            }else if (widthMode == MeasureSpec.AT_MOST){
                //仅仅宽是wrap_content
                setMeasuredDimension(getMaxValue(TYPE_WIDTH),heightSize);
            }else{
                setMeasuredDimension(widthSize,getTotalValue(TYPE_HEIGHT));
            }
        }else if (orientation == ORIENTATION_HORIZONTAL){
            //水平方向排列
            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
                //宽高测量方式都是wrap_content
                int width = getTotalValue(TYPE_WIDTH);
                int height = getMaxValue(TYPE_HEIGHT);
                setMeasuredDimension(width,height);
            }else if (widthMode == MeasureSpec.AT_MOST){
                //仅仅宽是wrap_content
                setMeasuredDimension(getTotalValue(TYPE_WIDTH),heightSize);
            }else{
                setMeasuredDimension(widthSize,getMaxValue(TYPE_HEIGHT));
            }
        }

    }

    /**
     * 获取所有子View的高度或宽度之和
     * @param type TYPE_WIDTH、TYPE_HEIGHT
     * @return
     */
    private int getTotalValue(int type){
        int total = 0;
        int n = getChildCount();
        if (n == 0){
            return total;
        }

        for (int i=0;i<n;i++){
            View child = getChildAt(i);
            if(type == TYPE_WIDTH){
                total += child.getMeasuredWidth();
            }else if (type == TYPE_HEIGHT){
                total += child.getMeasuredHeight();
            }
        }
        return total;
    }

    /**
     * 获取所有子View的高度或宽度最大值
     * @param type TYPE_WIDTH、TYPE_HEIGHT
     * @return
     */
    private int getMaxValue(int type){
        int max = 0;
        int n = getChildCount();
        if (n == 0){
            return max;
        }

        for (int i = 0;i<n;i++){
            View child = getChildAt(i);
            if (type == TYPE_WIDTH && child.getMeasuredWidth() > max){
                max = child.getMeasuredWidth();
            }else if (type == TYPE_HEIGHT && child.getMeasuredHeight() > max){
                max = child.getMeasuredHeight();
            }
        }
        return max;
    }

    /**
     *
     * @param changed 当前View的大小和位置改变了
     * @param left 左边位置（相对于父视图）
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int count = getChildCount();
        int curSize = 0;
        for (int i = 0;i<count;i++){
            View child = getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            if (orientation == ORIENTATION_VERTICAL){
                //垂直方向排列
                child.layout(0,curSize,width,curSize+height);
                curSize += height;
            }else if (orientation == ORIENTATION_HORIZONTAL){
//                child.layout(curSize,top,curSize+width,top+height);
                child.layout(curSize,0,curSize+width,height);
                curSize += width;
            }
        }
    }
}

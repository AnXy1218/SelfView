package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 类描述：针对Path类的自定义View
 * 创建时间：2018/3/19
 *
 * @author An xy
 */

public class PathView extends View {
    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pathText(canvas);
    }

    private void pathText(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(10,10);
        path.addCircle(200,200,150, Path.Direction.CW);
        canvas.drawTextOnPath("中华人民共和国",path,10,10,paint);
        paint.setColor(Color.BLUE);
        canvas.drawPath(path,paint);
    }
}

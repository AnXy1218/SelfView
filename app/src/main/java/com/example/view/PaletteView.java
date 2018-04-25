package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 画板
 */
public class PaletteView extends View {
    private static final String TAG = "SelfView";

    private List<Path> pathList;
    private Path path;
    public PaletteView(Context context) {
        super(context);
    }

    public PaletteView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        pathList = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        RectF rectF = new RectF();
        rectF.set(0,0,getWidth(),getHeight());
        canvas.drawRect(rectF,paint);
        setClickable(true);

        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        for (int i = 0;i<pathList.size();i++){
            canvas.drawPath(pathList.get(i),paint);
        }

        if (path != null){
            canvas.drawPath(path,paint);
        }
    }

    public void revoke(){
        path = null;
        if (pathList.size() > 0){
            pathList.remove(pathList.size()-1);
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path = new Path();
                path.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_UP:
                pathList.add(path);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(),event.getY());
                break;
        }

        invalidate();
        return super.onTouchEvent(event);
    }
}

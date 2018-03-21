package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        pathText(canvas);
//        paintBezier(canvas);
        pathOp(canvas);
    }

    /**
     * 贝塞尔曲线
     * @param canvas
     */
    private void paintBezier(Canvas canvas){
        PointF startPoint,controlPoint,endPoint;

        startPoint = new PointF(50,100);
        controlPoint = new PointF(100,400);
        endPoint = new PointF(300,150);

        PointF control2 = new PointF(200,50);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(20);
//        float[] pts = new float[]{startPoint.x,startPoint.y,controlPoint.x,controlPoint.y,endPoint.x,endPoint.y};
        float[] pts = new float[]{startPoint.x,startPoint.y,controlPoint.x,controlPoint.y,control2.x,control2.y,endPoint.x,endPoint.y};
        canvas.drawPoints(pts,paint);

        paint.setStrokeWidth(4);
        paint.setColor(Color.GRAY);
//        Draw a series of lines. Each line is taken from 4 consecutive values in the pts array. Thus
//        to draw 1 line, the array must contain at least 4 values. This is logically the same as
//     * drawing the array as follows: drawLine(pts[0], pts[1], pts[2], pts[3]) followed by
//     * drawLine(pts[4], pts[5], pts[6], pts[7]) and so on.
//                *
        //drawLines中pts，每4个值画一条直线，这个是必选要
        canvas.drawLine(startPoint.x,startPoint.y,controlPoint.x,controlPoint.y,paint);
//        canvas.drawLine(controlPoint.x,controlPoint.y,endPoint.x,endPoint.y,paint);
        canvas.drawLine(controlPoint.x,controlPoint.y,control2.x,control2.y,paint);
        canvas.drawLine(control2.x,control2.y,endPoint.x,endPoint.y,paint);
        Path path = new Path();
        path.moveTo(startPoint.x,startPoint.y);
//        path.quadTo(controlPoint.x,controlPoint.y,endPoint.x,endPoint.y);
        path.cubicTo(controlPoint.x,controlPoint.y,control2.x,control2.y,endPoint.x,endPoint.y);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        canvas.drawPath(path,paint);
    }

    /**
     * 在一个路径上边写文字
     * @param canvas
     */
    private void pathText(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(10,10);
        path.addCircle(200,200,150, Path.Direction.CW);
        canvas.drawTextOnPath("这是一个美好的一天，我们高高兴兴地去上学",path,300,10,paint);
        paint.setColor(Color.BLUE);
        canvas.drawPath(path,paint);
    }

    /**
     * Path的Op探索
     * @param canvas
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void pathOp(Canvas canvas){
        drawPathOp(canvas,new PointF(0,0), Path.Op.DIFFERENCE,"DIFFERENCE");
        //注：第二次的坐标是相对于第一次移动之后的
        drawPathOp(canvas,new PointF(500,0), Path.Op.INTERSECT,"INTERSECT");

        drawPathOp(canvas,new PointF(-500,500), Path.Op.UNION,"UNION");

        drawPathOp(canvas,new PointF(500,0), Path.Op.XOR,"XOR");

        drawPathOp(canvas,new PointF(-500,500), Path.Op.REVERSE_DIFFERENCE,"REVERSE_DIFFERENCE");
    }

    /**
     * 绘制每一个圆
     * @param canvas
     * @param start
     * @param op
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void drawPathOp(Canvas canvas, PointF start, Path.Op op,String title){
        //1.将坐标系原点移到start位置
        canvas.translate(start.x,start.y);

        //2.定义圆的圆心和半径
        PointF circle1  = new PointF(200,200);
        PointF cicile2  = new PointF(250,250);
        int radius = 100;

        //3.定义Path1和Path2的区域
        Paint paint = new Paint();
        Path path1 = new Path();
        path1.addCircle(circle1.x,circle1.y,radius, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(cicile2.x,cicile2.y,radius, Path.Direction.CW);

        //注：Path3和Path4是Path1和Path2拷贝
        Path path3 = new Path();
        path3.addCircle(circle1.x,circle1.y,radius, Path.Direction.CW);
        Path path4 = new Path();
        path4.addCircle(cicile2.x,cicile2.y,radius, Path.Direction.CW);


        //4.画Op后的区域
        path3.op(path4, op);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path3,paint);


        //5.画path1
        paint.setPathEffect(new DashPathEffect(new float[]{10,5},10));//设置虚线
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLUE);
        canvas.drawPath(path1,paint);

        //6.画path2
        paint.setColor(Color.RED);
        canvas.drawPath(path2,paint);

        //7.绘制文字
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        Rect mBound = new Rect();
        paint.getTextBounds(title, 0, title.length(), mBound);
        canvas.drawText(title,225-mBound.width()/2,430,paint);
    }
}

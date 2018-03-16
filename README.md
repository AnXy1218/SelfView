# 自定义View
### 1. 项目介绍
 SelfView项目是用来提交平时感觉比较好的自定义View的。
### 2. 各个自定义View中的知识点
 #### 2.1 MyTextView
 #### 2.2 MyView(主要是OnMeasure方法的理解)
 onMeasure(int widthMeasureSpec, int heightMeasureSpec)方法中，widthMeasureSpec和heightMeasureSpec看起来是个int值，但它也包含了View的测量模式和View的大小值。其中测量模式（用measureSpec的32位中前两位记录）有三种，三种测量模式如下：
 
 
 | 测量模式 | 表示意思 | 对应的xml中设置尺寸
 | ------  | ------ |------|
 | UNSPECIFIED  | 父容器没有对当前的View有任何限制，当前View可以任意取尺寸 | |
 |EXACTLY|当前尺寸就是当前View应该取得尺寸 | match_parent、固定尺寸（ex:100dp） |
 |AT_MOST|当前尺寸是当前View能取到的最大值 | wrap_content|

 #### 2.3 MyLinearLayout
 相比于自定义View，自定义ViewGroup更为复杂一些，主要是自定义ViewGroup除了绘制自己外，还得兼顾自己子View的排列。子View的排列是在onLayout方法中实现：
 ```
 onLayout(boolean changed, int left, int top, int right, int bottom)
 ```
其中，changed表示该ViewGroup的大小和位置是否改变了，left表示该ViewGroup离父视图左边的距离。

**注意：**
 当ScrollView嵌套一个自定义控件时，该控件的MeasureSpec是UNSPECIFIED，这时要记得设置控件的大小，不然控件显示不全且无法实现滚动。
 #### 2.4 GraphView(基本图形的绘制)
 ##### 2.4.1 绘制线
 ```
 canvas.drawLine(0,0,100,100,paint);
 ```
 ##### 2.4.2 绘制三角形和多边形
 canvas没有提供专门画三角形的方法，但可以运用画多边形的方法来画三角形。画多边形方法如下：
 ```
 //1.先定义一个Path对象，用来记录多边形各顶点信息
 Path path = new Path();
 path.moveTo(10,0);//起点位置
 path.lineTo(100,100);
 path.lineTo(200,150);
 path.lineTo(200,100);
 path.lineTo(100,50);
 path.close();//设置Path是闭合的
 //2.调用canvas的drawPath方法
 canvas.drawPath(path,paint);
 ```
 ##### 2.4.3 绘制矩形和圆角矩形
 1 ) 矩形
 ```
 Rect rect = new Rect(0,0,100,100);
 canvas.drawRect(rect,paint);
 ```
 2 ) 圆角矩形
 ```
 RectF rectf = new RectF(0,0,100,100);
 canvas.drawRoundRect(rectf,20,10,paint);
 ```
 **其中**
 ，drawRoundRect(RectF rect, float rx, float ry, Paint paint)的rx、ry表示如下:
 ![](img/rectround.png)
 ##### 2.4.4 绘制圆
 ```
 canvas.drawCircle(50,50,40,paint);
 ```
 **其中**，前两个参数为圆心位置，第三个参数为半径。
 ##### 2.4.5 弧形
 ```
 canvas.drawArc(rectf,0,270,true,paint);
 ```
 **其中**，第二个参数为起始角度，第三个参数为从起始角度扫过的角度（eg：起始角度为90度，扫过为80度，那么最后的图形是90到170度之间的区域），第四个参数为是否需要带圆心


 ### 参考文档：
 1. [Android 自定义View (一)](http://blog.csdn.net/lmj623565791/article/details/24252901)
 2. [自定义View，有这一篇就够了](http://blog.csdn.net/huachao1001/article/details/51577291)
 3. [手把手教你写一个完整的自定义View](http://blog.csdn.net/carson_ho/article/details/62037696)
 4. [安卓自定义View进阶 - 贝塞尔曲线](http://blog.csdn.net/u013831257/article/details/51281136)
 5. [ScrollView和HorizontalScrollView中添加的自定义View控件无法显示问题](http://blog.csdn.net/qq_25929547/article/details/53142161)

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

 ### 参考文档：
 1. [Android 自定义View (一)](http://blog.csdn.net/lmj623565791/article/details/24252901)
 2. [自定义View，有这一篇就够了](http://blog.csdn.net/huachao1001/article/details/51577291)
 3. [手把手教你写一个完整的自定义View](http://blog.csdn.net/carson_ho/article/details/62037696)
 4. [安卓自定义View进阶 - 贝塞尔曲线](http://blog.csdn.net/u013831257/article/details/51281136)

# 深入掌握 Animation 动画实现

## applyTransformation

传入两个参数 void applyTransformation (float interpolatedTime, Transformation t)

interpolatedTime：时间比例系数，由0变化到1；
t：包含两个部分，一个Alpha值和一个Matrix矩阵，Matrix默认是一个3X3的单位矩阵

https://developer.android.com/reference/android/view/animation/Animation.html#applyTransformation(float, android.view.animation.Transformation)

## 画图

用 Python 的 MatPlotLib 来绘制一些正弦或者余弦的坐标图

## Animation

Animation动画只能对View起作用，能对View实现动画效果，但是View实际的触摸区域却没有发生改变

偏移动画：TranslateAnimation，为 View 制造偏移的动画效果

https://developer.android.com/reference/android/view/animation/TranslateAnimation.html

TranslateAnimation动画的坐标值有以下三种类型

- Animation.ABSOLUTE：绝对值，偏移量直接为像素数
- Animation.RELATIVE_TO_SELF：相对值，偏移量为百分比，这个百分比相对于控件自身
- Animation.RELATIVE_TO_PARENT：相对值，偏移量为百分比，这个百分比相对于控件的Parent

缩放动画： ScaleAnimation，为 View 制造缩放的动画效果

https://developer.android.com/reference/android/view/animation/ScaleAnimation.html

构造函数有以下三类

- ScaleAnimation (float fromX, float toX, float fromY, float toY)：fromX、fromY是动画开始时的比例，toX、toY是动画结束时的比例。比例都是基于控件自身尺寸。
- ScaleAnimation(float fromX, float toX, float fromY, float toY, float pivotX, float pivotY)：在一的基础上可以指定缩放的轴点坐标
- ScaleAnimation(float fromX, float toX, float fromY, float toY, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue)：在二的基础上可以指定轴点的类型

旋转动画：RotateAnimation

https://developer.android.com/reference/android/view/animation/RotateAnimation.html

构造函数参数分为三种，角度，旋转轴点，比例系数

透明度动画：AlphaAnimation

https://developer.android.com/reference/android/view/animation/AlphaAnimation.html

很简单的参数，只有透明度的比例系数

动画合集：AnimationSet

https://developer.android.com/reference/android/view/animation/AnimationSet.html

## Animation API小结

可以在xml中做的，也可以在代码里面做到

动画的构造函数参数要么是绝对的像素值，要么是相对某个View的比例值。

## Animation 实现原理

研究动画，如何实现，应该对其原理进行分析才对。

## 自定义 Animation

[Android之自定义Animation动画](http://www.apkbus.com/blog-104974-52806.html)


为什么一定是重写 initialize 和 applyTransformation 两个函数？从何处得知

先搞定如何继承那两个函数来实现自定义动画效果的问题

再通过 加断点 和 打Log 来调试，验证整个动画的实现流程。

矩阵知识，以及矩阵生成的XY轴的图如何展示（有什么工具可以画出来吗）

## 关于 Matrix

Matrix 是一个 3 X 3 的矩阵

不懂矩阵的乘法，可以查看阮一峰先生的博文 [理解矩阵乘法](http://www.ruanyifeng.com/blog/2015/09/matrix-multiplication.html)，或者去维基百科。

详细介绍 Android Matrix 矩阵的文章：

Android Matrix理论与应用详解 http://zensheno.blog.51cto.com/2712776/513652

Android Matrix：http://www.cnblogs.com/qiengo/archive/2012/06/30/2570874.html


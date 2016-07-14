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

跟 View 的绘制原理有关，需要了解。

Android 动画框架详解：https://www.ibm.com/developerworks/cn/opensource/os-cn-android-anmt1/

## 自定义 Animation

http://www.cnblogs.com/wondertwo/p/5295976.html

矩阵知识，以及矩阵生成的XY轴的图如何展示（有什么工具可以画出来吗）

这些动画效果遵循的数学公式是什么？？？？？ http://blog.csdn.net/linmiansheng/article/details/18820599

例子：http://www.apkbus.com/blog-104974-52806.html


## 关于 Matrix

Matrix 是一个 3 X 3 的矩阵

不懂矩阵的乘法，可以查看阮一峰先生的博文 [理解矩阵乘法](http://www.ruanyifeng.com/blog/2015/09/matrix-multiplication.html)，或者去维基百科。

详细介绍 Android Matrix 矩阵的文章：

Android Matrix矩阵详解：http://www.maplejaw.com/2016/06/21/Android-Matrix%E7%9F%A9%E9%98%B5%E8%AF%A6%E8%A7%A3/index.html

Android Matrix理论与应用详解 http://zensheno.blog.51cto.com/2712776/513652

Android Matrix：http://www.cnblogs.com/qiengo/archive/2012/06/30/2570874.html （透视那块，既然文档没有提，那就稍作解释然后忽略即可）

矩阵的左乘右乘法概念：http://www.cnblogs.com/ylwn817/archive/2011/12/15/2288921.html

2D平面中关于矩阵（Matrix）跟图形变换的讲解： http://blog.csdn.net/linmiansheng/article/details/18801947

Android中关于矩阵（Matrix）前乘后乘的一些认识：http://blog.csdn.net/linmiansheng/article/details/18820599

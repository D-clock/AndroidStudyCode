# 深入理解 Android 中的 Matrix

在 Android 开发中，矩阵是一个功能强大并且应用广泛的神器，例如：用它来制作动画效果、改变图片大小、给图片加各类滤镜等。对于矩阵，Android 官方 SDK 为我们提供了一个强大的类 Matrix （还有 ColorMatrix ）是一直困扰着我的问题，虽然大致能够调用相应的 API ，但却一直 get 不到其内在的梗。但是出来混总是别想着蒙混过关的，所以最近重新操起一年毕业的线性代数，再本着小事问老婆，大事问Google的心态，终于把多年不解的问题给破了。出于好记性不如烂笔头的原因，便有了本文。在此先感谢下面两篇令我茅舍顿开的文章：

- [齐次坐标系入门级思考](https://oncemore2020.github.io/blog/homogeneous/)
- [仿射变换与齐次坐标](https://guangchun.wordpress.com/2011/10/12/affineandhomogeneous/)

## Matrix 的结构

Matrix 是 Android SDK 提供的一个矩阵类，它代表一个 3 X 3 的矩阵（不懂矩阵为何物的童鞋就要自行 Google 了）。 Matrix 提供了让我们获得 Matrix 值的 API —— **getValues** 

![](http://h.hiphotos.baidu.com/image/pic/item/43a7d933c895d1438a9f6d507bf082025baf07d3.jpg)

利用此 API 传入一个长度为 9 的 float 数组，即可获得矩阵中每个元素的值。那么这 9 个浮点数的作用和意义是什么呢，从 Android 官方文档上看，它为这个数组中的每一个元素都定义了一个下标常量

![](http://c.hiphotos.baidu.com/image/pic/item/f3d3572c11dfa9ece819dc646ad0f703918fc18a.jpg)

这个 9 个常量取值分别是 0 - 8

![](http://c.hiphotos.baidu.com/image/pic/item/908fa0ec08fa513d4cd5e6ad356d55fbb2fbd942.jpg)

如果我们将这个 float 排成直观的矩阵格式，那它将是下面这样子的

![](http://d.hiphotos.baidu.com/image/pic/item/2934349b033b5bb5425dd68d3ed3d539b600bc3f.jpg)

没错，我们平常利用 Matrix 来进行 Translate（平移）、Scale（缩放）、Rotate（旋转）的操作，实际上就是在操作着这个矩阵中元素的数值来达到我们想要的效果。但是现在问题来了，上面提到的平移、缩放、旋转操作中，旋转和缩放可以用乘法表示，而平移就只能用加法表示，而且 Matrix 是一个 3 X 3 的矩阵，实际上表示这些操作 2 X 2 的矩阵足矣！

![](http://e.hiphotos.baidu.com/image/pic/item/7acb0a46f21fbe096feccf6063600c338744ad17.jpg)

![](http://h.hiphotos.baidu.com/image/pic/item/bf096b63f6246b600bbcbe00e3f81a4c510fa217.jpg)

![](http://c.hiphotos.baidu.com/image/pic/item/d058ccbf6c81800a737a857ab93533fa828b4722.jpg)

如上，可以依次看到平移、缩放、旋转的矩阵，其中（x'，y'）表示执行操作后的点的坐标，（x，y）表示执行操作前的点的坐标。tx、ty 分别表示x轴、y轴上平移的距离，Sx、Sy 分别表示x轴、y轴上的缩放比例，θ 则表示旋转角度。至于上面矩阵的推导过程，Google一下其实很多，这里就不去赘述了。以前到了这里，我就会很纳闷，为什么 2 X 2 矩阵能干的事情，偏偏要用 3 X 3 矩阵去做，直到遇到前面提到的两篇文章才有所领悟。

其实在计算机图形应用涉及到几何变换，主要包括平移、旋转、缩放。以矩阵表达式来计算这些变换时，平移是矩阵相加，旋转和缩放则是矩阵相乘。那些数学大神们为了方便计算，所以引入了一样神器叫做**齐次坐标**（不懂得童鞋，老规矩自行搜索），将平移的加法合并用乘法表示。所以，2 X 2 的矩阵经过一番变换后，成了下面这样的。

![](http://h.hiphotos.baidu.com/image/pic/item/5d6034a85edf8db184610e100123dd54564e7472.jpg)

![](http://b.hiphotos.baidu.com/image/pic/item/c83d70cf3bc79f3d89569944b2a1cd11728b290a.jpg)

![](http://b.hiphotos.baidu.com/image/pic/item/5fdf8db1cb13495458519a105e4e9258d1094a72.jpg)

## 验证结果

## 总结



## applyTransformation

传入两个参数 void applyTransformation (float interpolatedTime, Transformation t)

interpolatedTime：时间比例系数，由0变化到1；
t：包含两个部分，一个Alpha值和一个Matrix矩阵，Matrix默认是一个3X3的单位矩阵

https://developer.android.com/reference/android/view/animation/Animation.html#applyTransformation(float, android.view.animation.Transformation)

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

例子：http://www.apkbus.com/blog-104974-52806.html


## 关于 Matrix

Matrix 是一个 3 X 3 的矩阵

不懂矩阵的乘法，可以查看阮一峰先生的博文 [理解矩阵乘法](http://www.ruanyifeng.com/blog/2015/09/matrix-multiplication.html)，或者去维基百科。

详细介绍 Android Matrix 矩阵的文章：

Android Matrix矩阵详解：http://www.maplejaw.com/2016/06/21/Android-Matrix%E7%9F%A9%E9%98%B5%E8%AF%A6%E8%A7%A3/index.html

Android Matrix：http://www.cnblogs.com/qiengo/archive/2012/06/30/2570874.html （透视那块，既然文档没有提，那就稍作解释然后忽略即可）

矩阵的左乘右乘法概念：

http://www.cnblogs.com/ylwn817/archive/2011/12/15/2288921.html

http://blog.csdn.net/linmiansheng/article/details/18820599

2D平面中关于矩阵（Matrix）跟图形变换的讲解： http://blog.csdn.net/linmiansheng/article/details/18801947

对于其次坐标的理解：https://oncemore2020.github.io/blog/homogeneous/




 
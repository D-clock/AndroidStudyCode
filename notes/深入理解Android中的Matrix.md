# 深入理解 Android 中的 Matrix

在 Android 开发中，矩阵是一个功能强大并且应用广泛的神器，例如：用它来制作动画效果、改变图片大小、给图片加各类滤镜等。对于矩阵，Android 官方 SDK 为我们提供了一个强大的类 Matrix （还有 ColorMatrix ）是一直困扰着我的问题，虽然大致能够调用相应的 API ，但却一直 get 不到其内在的梗。但是出来混总是别想着蒙混过关的，所以最近重新操起一年毕业的线性代数，再本着小事问老婆，大事问Google的心态，终于把多年不解的问题给破了。出于好记性不如烂笔头的原因，便有了本文。在此先感谢下面两篇令我茅舍顿开的文章：

- [齐次坐标系入门级思考](https://oncemore2020.github.io/blog/homogeneous/)
- [仿射变换与齐次坐标](https://guangchun.wordpress.com/2011/10/12/affineandhomogeneous/)

读完本文，相信你能够搞明白以下三个问题：

- 为什么 Matrix 是个 3 X 3 的矩阵
- Matrix 这个  3 X 3 的矩阵每个元素的作用
- Matrix 的 setXXX、preXXX、postXXX API 方法的工作原理

## Matrix 的结构

Matrix 是 Android SDK 提供的一个矩阵类，它代表一个 3 X 3 的矩阵（不懂矩阵为何物的童鞋就要自行 Google 了）。 Matrix 提供了让我们获得 Matrix 值的 API —— **getValues** 

![](http://h.hiphotos.baidu.com/image/pic/item/43a7d933c895d1438a9f6d507bf082025baf07d3.jpg)

利用此 API 传入一个长度为 9 的 float 数组，即可获得矩阵中每个元素的值。那么这 9 个浮点数的作用和意义是什么呢，从 Android 官方文档上看，它为这个数组中的每一个元素都定义了一个下标常量

![](http://c.hiphotos.baidu.com/image/pic/item/f3d3572c11dfa9ece819dc646ad0f703918fc18a.jpg)

这个 9 个常量取值分别是 0 - 8

![](http://c.hiphotos.baidu.com/image/pic/item/908fa0ec08fa513d4cd5e6ad356d55fbb2fbd942.jpg)

如果我们将这个 float 排成直观的矩阵格式，那它将是下面这样子的

![](http://d.hiphotos.baidu.com/image/pic/item/2934349b033b5bb5425dd68d3ed3d539b600bc3f.jpg)

实际上我们平常利用 Matrix 来进行 Translate（平移）、Scale（缩放）、Rotate（旋转）的操作，就是在操作着这个矩阵中元素的数值来达到我们想要的效果。但是现在问题来了，上面提到的平移、缩放、旋转操作中，旋转和缩放可以用乘法表示，而平移就只能用加法表示，而且 Matrix 是一个 3 X 3 的矩阵，实际上表示这些操作 2 X 2 的矩阵足矣！

![](http://e.hiphotos.baidu.com/image/pic/item/7acb0a46f21fbe096feccf6063600c338744ad17.jpg)

![](http://h.hiphotos.baidu.com/image/pic/item/bf096b63f6246b600bbcbe00e3f81a4c510fa217.jpg)

![](http://c.hiphotos.baidu.com/image/pic/item/d058ccbf6c81800a737a857ab93533fa828b4722.jpg)

如上，可以依次看到平移、缩放、旋转的矩阵，其中

- （x'，y'）表示执行操作后的点的坐标，（x，y）表示执行操作前的点的坐标
-  tx、ty 分别表示x轴、y轴上平移的距离，Sx、Sy 分别表示x轴、y轴上的缩放比例
-  θ 则表示旋转角度
 
至于上面矩阵的推导过程，网络上很多，这里就不去赘述了。以前到了这里，我就会很纳闷，为什么 2 X 2 矩阵能干的事情，偏偏要用 3 X 3 矩阵去做，直到遇到前面提到的两篇文章才有所领悟。

其实在计算机图形应用涉及到几何变换，主要包括平移、旋转、缩放。以矩阵表达式来计算这些变换时，平移是矩阵相加，旋转和缩放则是矩阵相乘。那些数学大神们为了方便计算，所以引入了一样神器叫做**齐次坐标**（不懂的童鞋，老规矩自行搜索），将平移的加法合并用乘法表示。所以，2 X 2 的矩阵经过一番变换后，成了下面这样的。

![](http://h.hiphotos.baidu.com/image/pic/item/5d6034a85edf8db184610e100123dd54564e7472.jpg)

![](http://b.hiphotos.baidu.com/image/pic/item/c83d70cf3bc79f3d89569944b2a1cd11728b290a.jpg)

![](http://b.hiphotos.baidu.com/image/pic/item/5fdf8db1cb13495458519a105e4e9258d1094a72.jpg)

至此，我们可以得知**为什么 Matrix 是一个 3 X 3 的矩阵**，其实 2 X 2 的矩阵是足以表示的，不过是为了方便计算而合并写成了 3 X 3 的格式。

## Matrix 元素的作用

一个 Matrix 共有 9 个元素，那么它每个元素的值发生改变会起到什么作用呢？按照前面所示的齐次坐标转换得到 3 X 3 的矩阵和 Android 文档提供的官方结构相对应，我们不难看出下面的对应关系（其实从 Matrix 中每个位置的常量命名也可以看出来）：

![](http://f.hiphotos.baidu.com/image/pic/item/aec379310a55b3193603a1034ba98226cefc17c9.jpg)

![](http://e.hiphotos.baidu.com/image/pic/item/562c11dfa9ec8a136f837496ff03918fa0ecc02e.jpg)

![](http://b.hiphotos.baidu.com/image/pic/item/aa64034f78f0f73676046f770255b319eac413c9.jpg)

从这我们可以看出这个 Matrix 结构中的每个参数发挥着如下作用：

- MTRANS_X、MTRANS_Y 同时控制着 Translate
- MSCALE_X、MSCALE_Y 同时控制着 Scale
- MSCALE_X、MSKEW_X、MSCALE_Y、MSKEW_Y 同时控制着 Rotate
- 从名称上看，我们可以顺带看出 MSKEW_X、MSKEW_Y 同时控制着 Skew

如果要进行代码验证的话，也非常简单，例如直接只对 Matrix 做 Translate 的 API 调用操作，再将 Matrix 的信息打印到控制台，你会发现整个 Matrix 确实只有 MTRANS_X、MTRANS_Y 两个位置的数字在发生变化。其他 Scale、Rotate、Skew 操作也是一样，感兴趣的童鞋可以自行代码验证一番。

至此，我们可以大致弄清矩阵每个元素的作用。至于 MPERSP_0、MPERSP_1、MPERSP_2 这三个参数，目前暂时不得而知，网上有文章说这三个参数控制着透视变换，但是文档和 API 上都没怎么提及，所以还是有待验证研究的，有明白的童鞋不妨留言赐教一下，不胜感激。

## 理解 Matrix API 调用

按照第一小节里面通过齐次坐标转换而来的矩阵方程可以知道，假设一根线执行了平移操作，相当于线上每个点的坐标被下方的矩阵左乘。（缩放和旋转操作也是同理）

![](http://f.hiphotos.baidu.com/image/pic/item/5882b2b7d0a20cf49c1f77a87e094b36acaf993e.jpg)

如果要进行同时缩放、平移之类的符合变化操作，也无非就是选取相应的矩阵做左乘操作。为了加深矩阵变换对应 Matrix API 调用的理解，直接通过下面的一个自定义的动画效果和代码来讲解好了。

```java

public class SimpleCustomAnimation extends Animation {

    private int mWidth, mHeight;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        this.mWidth = width;
        this.mHeight = height;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        Matrix matrix = t.getMatrix();
        matrix.preScale(interpolatedTime, interpolatedTime);//缩放
        matrix.preRotate(interpolatedTime * 360);//旋转
        //下面的Translate组合是为了将缩放和旋转的基点移动到整个View的中心，不然系统默认是以View的左上角作为基点
        matrix.preTranslate(-mWidth / 2, -mHeight / 2);
        matrix.postTranslate(mWidth / 2, mHeight / 2);
    }
}

```

熟悉动画这块的童鞋肯定知道，Animation 就是通过不断改变 applyTransformation 函数传入的 Matrix 来实现各种各样的动画效果的，通过上面 applyTransformation 寥寥的几行 Matrix 的复合变换操作可以得到如下效果

![](http://f.hiphotos.baidu.com/image/pic/item/9358d109b3de9c82048c06f86481800a18d843d8.jpg)

实际上这几行代码用矩阵来表示就相当于如下所示：

![](http://a.hiphotos.baidu.com/image/pic/item/a686c9177f3e67097989758633c79f3df8dc5539.jpg)

关于代码的作用上边已经给出了注释，这里就不多写了。主要还是要弄明白 Matrix 复合变换中 pre 、 post 等操作与其对应的矩阵发生的左乘、右乘变化。

## 总结

到此，整篇文章已经完结，相信已经能够让你明白开头提到的三个问题。其实我们也可以发现，Google 封装了 Matrix 已经是很完美了，几乎屏蔽了所有的数学细节，使得我这种数学水平一般的开发者也能够去调用相应的 API 实现一些简单的效果。虽然被封装得很完美，但掌握相应的一些原理，依旧可以帮你更好的理解一些技术实现，此次加深了对 Matrix 一些操作的理解，帮我自己解决了以前不少的困惑，不知道有没有帮你 get 到一些什么呢？

上面给的示例代码很简单，复制黏贴即可运行玩耍，实在需要直接运行源码的童鞋就到 https://github.com/D-clock/AndroidStudyCode 找吧！




 
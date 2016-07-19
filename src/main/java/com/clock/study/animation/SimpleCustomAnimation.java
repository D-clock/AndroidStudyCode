package com.clock.study.animation;

import android.graphics.Matrix;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Clock on 2016/7/9.
 */
public class SimpleCustomAnimation extends Animation {

    private final static String TAG = SimpleCustomAnimation.class.getSimpleName();

    private int mWidth, mHeight;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        Log.i(TAG, "-------------initialize-------------");
        Log.i(TAG, "width:" + width);
        Log.i(TAG, "height:" + height);
        Log.i(TAG, "parentWidth:" + parentWidth);
        Log.i(TAG, "parentHeight:" + parentHeight);
        Log.i(TAG, "-------------initialize-------------");
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
        Log.i(TAG, "-------------applyTransformation-------------");
        Log.i(TAG, "interpolatedTime:" + interpolatedTime);//动画持续的时间，时间比例系数（0.0 到 1.0）之间
        Log.i(TAG, "transformation:" + t);//控制动画效果，Transformation包含两个信息，一个Alpha值，一个Matrix矩阵，这里的Matrix默认是一个单位矩阵
        Log.i(TAG, "-------------applyTransformation-------------");

    }
}

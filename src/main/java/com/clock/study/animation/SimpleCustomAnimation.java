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
        setFillAfter(true);
        setDuration(15000);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        Matrix matrix = t.getMatrix();
        //matrix.setTranslate((float) Math.sin(interpolatedTime * 50), interpolatedTime * 350);
        //matrix.setRotate(interpolatedTime * 90);
        //matrix.setScale(interpolatedTime * 3, interpolatedTime * 3);
        //matrix.setSkew(interpolatedTime * 2, 0);
        //matrix.setSkew(0, interpolatedTime * 2);
        //matrix.setTranslate(0, (float) Math.sin(interpolatedTime * 80) * 10);
        //matrix.setScale(interpolatedTime * 1, interpolatedTime * 1, mWidth / 2, mHeight / 2);//线性缩放
        float[] data = new float[9];
        matrix.getValues(data);
        data[Matrix.MPERSP_0] = interpolatedTime * 1;
        matrix.setValues(data);
        Log.i(TAG, "-------------applyTransformation-------------");
        Log.i(TAG, "interpolatedTime:" + interpolatedTime);//动画持续的时间，时间比例系数（0.0 到 1.0）之间
        Log.i(TAG, "transformation:" + t);//控制动画效果，Transformation包含两个信息，一个Alpha值，一个Matrix矩阵，这里的Matrix默认是一个单位矩阵
        Log.i(TAG, "-------------applyTransformation-------------");

    }
}

package com.clock.study.animation;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * 自定义Android的Animation动画，仿QQ窗口抖动动画
 * <p/>
 * Created by Clock on 2016/7/5.
 */
public class CustomAnimation extends Animation {

    private final static String TAG = CustomAnimation.class.getSimpleName();

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        t.getMatrix().setTranslate(
                (float) Math.sin(interpolatedTime * 50) * 8,
                (float) Math.sin(interpolatedTime * 50) * 8
        );// 50越大频率越高，8越小振幅越小
        Log.i(TAG, "-------------applyTransformation-------------");
        Log.i(TAG, "interpolatedTime:" + interpolatedTime);//动画持续的时间，时间比例系数（0.0 到 1.0）之间
        Log.i(TAG, "transformation:" + t);//控制动画效果，Transformation包含两个信息，一个Alpha值，一个Matrix矩阵
        Log.i(TAG, "-------------applyTransformation-------------");
    }
}

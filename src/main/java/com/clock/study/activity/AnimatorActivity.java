package com.clock.study.activity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.clock.study.R;

/**
 * About Android Animator
 *
 * @author Clock
 * @since 2016-07-21
 */
public class AnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = AnimatorActivity.class.getSimpleName();

    private View mTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        mTarget = findViewById(R.id.anim_target);

        findViewById(R.id.btn_value_anim).setOnClickListener(this);
        findViewById(R.id.btn_object_anim_alpha).setOnClickListener(this);
        findViewById(R.id.btn_object_anim_rotation).setOnClickListener(this);
        findViewById(R.id.btn_object_anim_set).setOnClickListener(this);
        findViewById(R.id.btn_object_anim_xml).setOnClickListener(this);
        findViewById(R.id.btn_simple_value_animator).setOnClickListener(this);
        findViewById(R.id.btn_value_animator_argb).setOnClickListener(this);
        findViewById(R.id.btn_bounce_interpolator).setOnClickListener(this);
        findViewById(R.id.btn_simple_key_frame).setOnClickListener(this);
        findViewById(R.id.btn_shake_key_frame).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_value_anim) {
            ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
            valueAnimator.setDuration(3000);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int currentValue = (int) animation.getAnimatedValue();
                    Log.i(TAG, "currentValue: " + currentValue);
                }
            });
            valueAnimator.start();

        } else if (viewId == R.id.btn_object_anim_alpha) {
            ObjectAnimator alphaObjectAnimator = ObjectAnimator.ofFloat(mTarget, "alpha", 1f, 0f, 1f);
            alphaObjectAnimator.setDuration(3000);
            alphaObjectAnimator.start();

        } else if (viewId == R.id.btn_object_anim_rotation) {
            ObjectAnimator rotationObjectAnimator = ObjectAnimator.ofFloat(mTarget, "rotation", 0f, 360f);
            rotationObjectAnimator.setDuration(3000);
            rotationObjectAnimator.start();

        } else if (viewId == R.id.btn_object_anim_set) {
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator alphaObjectAnimator = ObjectAnimator.ofFloat(mTarget, "alpha", 1f, 0f, 1f);
            ObjectAnimator rotationObjectAnimator = ObjectAnimator.ofFloat(mTarget, "rotation", 0f, 360f);
            animatorSet.play(alphaObjectAnimator).with(rotationObjectAnimator);
            //animatorSet.play(alphaObjectAnimator).after(rotationObjectAnimator);
            //animatorSet.playTogether(alphaObjectAnimator, rotationObjectAnimator);
            animatorSet.setDuration(3000);
            animatorSet.start();

        } else if (viewId == R.id.btn_object_anim_xml) {
            AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.simple_animator);
            animatorSet.setTarget(mTarget);
            animatorSet.start();

        } else if (viewId == R.id.btn_simple_value_animator) {
            displayColorAnimation(mTarget, "#0000ff", "#ff0000");

        } else if (viewId == R.id.btn_value_animator_argb) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                int startColor = 0x00000000;
                int centerColor = 0xff00ff89;
                int endColor = 0x00000000;
                ValueAnimator valueAnimator = ValueAnimator.ofArgb(startColor, centerColor, endColor);
                valueAnimator.setDuration(6000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int color = (int) animation.getAnimatedValue();
                        mTarget.setBackgroundColor(color);
                    }
                });
                valueAnimator.start();
            }

        } else if (viewId == R.id.btn_bounce_interpolator) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTarget, "translationY", 0f, 1500f);
            objectAnimator.setInterpolator(new BounceInterpolator());
            objectAnimator.setDuration(4000);
            objectAnimator.start();

        } else if (viewId == R.id.btn_simple_key_frame) {
            Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
            Keyframe kf1 = Keyframe.ofFloat(.5f, 360f);
            Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
            PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
            ObjectAnimator rotationAnim = ObjectAnimator.ofPropertyValuesHolder(mTarget, pvhRotation);
            rotationAnim.setDuration(5000);
            rotationAnim.start();

        } else if (viewId == R.id.btn_shake_key_frame) {
            displayShakeAnimator(mTarget, 1f);

        }
    }

    /**
     * 创建一个旋转动画
     *
     * @param target
     * @param shakeFactor
     * @return
     */
    private void displayShakeAnimator(View target, float shakeFactor) {
        Keyframe scaleXkf0 = Keyframe.ofFloat(0f, 1f);
        Keyframe scaleXkf1 = Keyframe.ofFloat(0.1f, 0.9f);
        Keyframe scaleXkf2 = Keyframe.ofFloat(0.2f, 0.9f);
        Keyframe scaleXkf3 = Keyframe.ofFloat(0.3f, 0.9f);
        Keyframe scaleXkf4 = Keyframe.ofFloat(0.4f, 1.1f);
        Keyframe scaleXkf5 = Keyframe.ofFloat(0.5f, 1.1f);
        Keyframe scaleXkf6 = Keyframe.ofFloat(0.6f, 1.1f);
        Keyframe scaleXkf7 = Keyframe.ofFloat(0.7f, 1.1f);
        Keyframe scaleXkf8 = Keyframe.ofFloat(0.8f, 1.1f);
        Keyframe scaleXkf9 = Keyframe.ofFloat(0.9f, 1.1f);
        Keyframe scaleXkf10 = Keyframe.ofFloat(1f, 1f);

        PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofKeyframe("scaleX", scaleXkf0, scaleXkf1, scaleXkf2, scaleXkf3, scaleXkf4,
                scaleXkf5, scaleXkf6, scaleXkf7, scaleXkf8, scaleXkf9, scaleXkf10);

        Keyframe scaleYkf0 = Keyframe.ofFloat(0f, 1f);
        Keyframe scaleYkf1 = Keyframe.ofFloat(0.1f, 0.9f);
        Keyframe scaleYkf2 = Keyframe.ofFloat(0.2f, 0.9f);
        Keyframe scaleYkf3 = Keyframe.ofFloat(0.3f, 0.9f);
        Keyframe scaleYkf4 = Keyframe.ofFloat(0.4f, 1.1f);
        Keyframe scaleYkf5 = Keyframe.ofFloat(0.5f, 1.1f);
        Keyframe scaleYkf6 = Keyframe.ofFloat(0.6f, 1.1f);
        Keyframe scaleYkf7 = Keyframe.ofFloat(0.7f, 1.1f);
        Keyframe scaleYkf8 = Keyframe.ofFloat(0.8f, 1.1f);
        Keyframe scaleYkf9 = Keyframe.ofFloat(0.9f, 1.1f);
        Keyframe scaleYkf10 = Keyframe.ofFloat(1f, 1f);
        PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofKeyframe("scaleY", scaleYkf0, scaleYkf1, scaleYkf2, scaleYkf3, scaleYkf4,
                scaleYkf5, scaleYkf6, scaleYkf7, scaleYkf8, scaleYkf9, scaleYkf10);


        PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofKeyframe("rotation",
                Keyframe.ofFloat(0f, 0),
                Keyframe.ofFloat(0.1f, -3 * shakeFactor),
                Keyframe.ofFloat(0.2f, -3 * shakeFactor),
                Keyframe.ofFloat(0.3f, 3 * shakeFactor),
                Keyframe.ofFloat(0.4f, -3 * shakeFactor),
                Keyframe.ofFloat(0.5f, 3 * shakeFactor),
                Keyframe.ofFloat(0.6f, -3 * shakeFactor),
                Keyframe.ofFloat(0.7f, -3 * shakeFactor),
                Keyframe.ofFloat(0.8f, 3 * shakeFactor),
                Keyframe.ofFloat(0.9f, -3 * shakeFactor),
                Keyframe.ofFloat(1f, 0));


        ObjectAnimator.ofPropertyValuesHolder(target, scaleXHolder, scaleYHolder, rotationHolder).setDuration(1000).start();
    }

    /**
     * 显示颜色变化的动画
     *
     * @param target
     * @param startColor
     * @param endColor
     */
    private void displayColorAnimation(final View target, final String startColor, final String endColor) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 100f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB_MR1) {
                    float fraction = animation.getAnimatedFraction();
                    if (target != null) {
                        int startRed = Integer.parseInt(startColor.substring(1, 3), 16);
                        int startGreen = Integer.parseInt(startColor.substring(3, 5), 16);
                        int startBlue = Integer.parseInt(startColor.substring(5, 7), 16);
                        int endRed = Integer.parseInt(endColor.substring(1, 3), 16);
                        int endGreen = Integer.parseInt(endColor.substring(3, 5), 16);
                        int endBlue = Integer.parseInt(endColor.substring(5, 7), 16);

                        int redDiff = Math.abs(endRed - startRed);
                        int greenDiff = Math.abs(endGreen - startGreen);
                        int blueDiff = Math.abs(endBlue - startBlue);
                        int colorDiff = redDiff + greenDiff + blueDiff;

                        int currRed = getCurrentColor(startRed, endRed, colorDiff, fraction);
                        int currGreen = getCurrentColor(startGreen, endGreen, colorDiff, fraction);
                        int currBlue = getCurrentColor(startBlue, endBlue, colorDiff, fraction);

                        String colorString = "#" + getHexString(currRed) + getHexString(currGreen) + getHexString(currBlue);
                        int color = Color.parseColor(colorString);
                        target.setBackgroundColor(color);

                    }
                }

            }
        });
        valueAnimator.setDuration(3000);
        valueAnimator.start();

    }

    /**
     * 获取当前新颜色
     *
     * @param startColor
     * @param endColor
     * @param colorDiff
     * @param fraction
     * @return
     */
    private int getCurrentColor(int startColor, int endColor, int colorDiff, float fraction) {
        int currentColor = 0;
        if (startColor > endColor) {
            currentColor = (int) (startColor - fraction * colorDiff);
        } else {
            currentColor = (int) (startColor + fraction * colorDiff);
        }
        if (currentColor >= 0 && currentColor <= 256) {//最终的色值要确保在0到256之间
            return currentColor;
        } else {
            currentColor = endColor;
        }
        return currentColor;
    }

    /**
     * 将10进制颜色值转换成16进制。
     */
    private String getHexString(int value) {
        String hexString = Integer.toHexString(value);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString;
    }
}

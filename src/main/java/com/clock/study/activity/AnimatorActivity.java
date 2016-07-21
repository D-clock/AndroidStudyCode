package com.clock.study.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;

import com.clock.study.R;

/**
 * About Android Animator
 *
 * @author Clock
 * @since 2016-07-21
 */
public class AnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = AnimatorActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        findViewById(R.id.btn_value_anim).setOnClickListener(this);
        findViewById(R.id.btn_object_anim_alpha).setOnClickListener(this);
        findViewById(R.id.btn_object_anim_rotation).setOnClickListener(this);
        findViewById(R.id.btn_object_anim_set).setOnClickListener(this);
        findViewById(R.id.btn_object_anim_xml).setOnClickListener(this);

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
            ObjectAnimator alphaObjectAnimator = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f, 1f);
            alphaObjectAnimator.setDuration(3000);
            alphaObjectAnimator.start();
        } else if (viewId == R.id.btn_object_anim_rotation) {
            ObjectAnimator rotationObjectAnimator = ObjectAnimator.ofFloat(v, "rotation", 0f, 360f);
            rotationObjectAnimator.setDuration(3000);
            rotationObjectAnimator.start();
        } else if (viewId == R.id.btn_object_anim_set) {
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator alphaObjectAnimator = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f, 1f);
            ObjectAnimator rotationObjectAnimator = ObjectAnimator.ofFloat(v, "rotation", 0f, 360f);
            animatorSet.play(alphaObjectAnimator).with(rotationObjectAnimator);
            //animatorSet.play(alphaObjectAnimator).after(rotationObjectAnimator);
            //animatorSet.playTogether(alphaObjectAnimator, rotationObjectAnimator);
            animatorSet.setDuration(3000);
            animatorSet.start();
        } else if (viewId == R.id.btn_object_anim_xml) {
            AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.simple_animator);
            animatorSet.setTarget(v);
            animatorSet.start();
        }
    }
}

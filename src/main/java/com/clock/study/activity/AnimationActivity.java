package com.clock.study.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.clock.study.R;
import com.clock.study.animation.CustomAnimation;
import com.clock.study.animation.SimpleCustomAnimation;

/**
 * Android动画效果实现复习
 */
public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnTranslate;
    private Button mBtnScale;
    private Button mBtnRotate;
    private Button mBtnAlpha;
    private Button mBtnSet;
    private Button mBtnSimpleCustom;
    private Button mBtnCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_anim);

        mBtnTranslate = (Button) findViewById(R.id.btn_translate);
        mBtnTranslate.setOnClickListener(this);

        mBtnScale = (Button) findViewById(R.id.btn_scale);
        mBtnScale.setOnClickListener(this);

        mBtnRotate = (Button) findViewById(R.id.btn_rotate);
        mBtnRotate.setOnClickListener(this);

        mBtnAlpha = (Button) findViewById(R.id.btn_alpha);
        mBtnAlpha.setOnClickListener(this);

        mBtnSet = (Button) findViewById(R.id.btn_set);
        mBtnSet.setOnClickListener(this);

        mBtnSimpleCustom = (Button) findViewById(R.id.btn_simple_custom_anim);
        mBtnSimpleCustom.setOnClickListener(this);

        mBtnCustom = (Button) findViewById(R.id.btn_custom_anim);
        mBtnCustom.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_translate) {//偏移动画
            //TranslateAnimation translateAnim = new TranslateAnimation(0, 500, 0, 500);
            /*TranslateAnimation translateAnim = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 500, Animation.ABSOLUTE, 0,
                    Animation.ABSOLUTE, 500);*/
            TranslateAnimation translateAnim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0,
                    Animation.RELATIVE_TO_PARENT, 1.0f);
            translateAnim.setDuration(2000);
            mBtnTranslate.startAnimation(translateAnim);
            //translateAnim.setFillAfter(true);//保持动画效果

        } else if (viewId == R.id.btn_scale) {//缩放动画

            //ScaleAnimation scaleAnim = new ScaleAnimation(0.5f, 1, 0.5f, 1);
            //ScaleAnimation scaleAnim = new ScaleAnimation(0.5f, 1, 0.5f, 1, 300, 300);
            ScaleAnimation scaleAnim = new ScaleAnimation(0.5f, 1, 0.5f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnim.setDuration(1000);
            mBtnScale.startAnimation(scaleAnim);

        } else if (viewId == R.id.btn_rotate) {//旋转动画

            //RotateAnimation rotateAnim = new RotateAnimation(0, 360);
            //RotateAnimation rotateAnim = new RotateAnimation(0, 360, 100, 100);
            RotateAnimation rotateAnim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnim.setDuration(2000);
            mBtnRotate.startAnimation(rotateAnim);

        } else if (viewId == R.id.btn_alpha) {//透明度动画

            AlphaAnimation alphaAnim = new AlphaAnimation(0, 1);
            alphaAnim.setDuration(2000);
            mBtnAlpha.startAnimation(alphaAnim);

        } else if (viewId == R.id.btn_set) {//动画合集

            AnimationSet animSet = new AnimationSet(true);
            animSet.setDuration(2000);
            RotateAnimation rotateAnim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animSet.addAnimation(rotateAnim);
            AlphaAnimation alphaAnim = new AlphaAnimation(0, 1);
            animSet.addAnimation(alphaAnim);
            mBtnSet.startAnimation(animSet);

        } else if (viewId == R.id.btn_simple_custom_anim) {

            SimpleCustomAnimation simpleCustomAnim = new SimpleCustomAnimation();//简单的自定义动画效果
            simpleCustomAnim.setDuration(1000);
            mBtnSimpleCustom.startAnimation(simpleCustomAnim);

        } else if (viewId == R.id.btn_custom_anim) {

            CustomAnimation customAnim = new CustomAnimation();//仿QQ抖屏动画效果
            customAnim.setDuration(1000);
            customAnim.setRepeatCount(3);
            mBtnCustom.startAnimation(customAnim);

        }

    }
}

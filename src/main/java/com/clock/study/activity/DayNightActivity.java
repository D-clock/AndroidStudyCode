package com.clock.study.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.clock.study.R;

/**
 * 夜间模式实现方案
 *
 * @author Clock
 * @since 2016=08-11
 */
public class DayNightActivity extends AppCompatActivity {

    private final static String TAG = DayNightActivity.class.getSimpleName();

    private static boolean isNight = false;

    private View mRootView;
    /**
     * 切换模式时，保存界面效果用于
     */
    private ImageView mTransView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isNight) {
            setTheme(R.style.NightTheme);
        } else {
            setTheme(R.style.DayTheme);
        }
        setContentView(R.layout.activity_day_night);

        initView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.day_night_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.day_mode) {
            isNight = false;
            recreate();
        } else if (itemId == R.id.night_mode) {
            isNight = true;
            //initTransBitmap();
            //startAnimation(mTransView);
            new TypedValue();
            recreate();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        mRootView = findViewById(R.id.layout_main);
        mTransView = (ImageView) findViewById(R.id.iv_trans);
    }

    /**
     * 初始化起过渡效果用的Bitmap
     */
    private void initTransBitmap() {
        //开启绘制缓存，让View中的内容能够转换成为Bitmap
        mRootView.setDrawingCacheEnabled(true);
        Bitmap bitmap = mRootView.getDrawingCache();
        if (bitmap != null) {
            mTransView.setImageBitmap(bitmap);
            mTransView.setAlpha(1f);
            mTransView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 启动过度用的动画效果
     */
    private void startAnimation(final View transView) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(transView, "alpha", 1f, 0f);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                transView.setVisibility(View.GONE);
            }
        });
        animator.setDuration(1000);
        animator.start();

    }

}

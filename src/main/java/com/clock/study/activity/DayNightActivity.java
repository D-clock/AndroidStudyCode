package com.clock.study.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.clock.study.R;

/**
 * 夜间模式实现方案
 *
 * @author Clock
 * @since 2016=08-11
 */
public class DayNightActivity extends AppCompatActivity {

    private final static String TAG = DayNightActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_night);

    }

}

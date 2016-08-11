package com.clock.study.activity;

import android.app.UiModeManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.clock.study.R;

/**
 * 夜间模式实现方案
 *
 * @author Clock
 * @since 2016=08-11
 */
public class NightModeActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private final static String TAG = NightModeActivity.class.getSimpleName();

    private UiModeManager mUiModeManager;
    private RadioGroup mUiModeGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_mode);

        mUiModeManager = (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);

        mUiModeGroup = (RadioGroup) findViewById(R.id.rg_ui_mode);
        mUiModeGroup.setOnCheckedChangeListener(this);
        RadioButton normalMode = (RadioButton) findViewById(R.id.rb_normal);
        normalMode.setChecked(true);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (mUiModeGroup == group) {
            int currentModeType = mUiModeManager.getCurrentModeType();
            Log.i(TAG, "currentModeType: " + currentModeType);
            if (checkedId == R.id.rb_normal) {
                mUiModeManager.disableCarMode(0);
                mUiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);

            } else if (checkedId == R.id.rb_night_mode) {//夜间模式
                mUiModeManager.enableCarMode(0);
                mUiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);

            } else if (checkedId == R.id.rb_car_mode) {//车载模式
                mUiModeManager.disableCarMode(0);
                mUiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);

            }
        }
    }
}

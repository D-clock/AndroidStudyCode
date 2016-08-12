package com.clock.study.activity;

import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
            int currentModeType = mUiModeManager.getCurrentModeType();//获取 UI 模式
            Log.e(TAG, "currentModeType value is : " + currentModeType);
            if (checkedId == R.id.rb_normal) {
                if (currentModeType == Configuration.UI_MODE_TYPE_CAR) {
                    mUiModeManager.disableCarMode(0);//关闭车载模式
                    mUiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
                }

            } else if (checkedId == R.id.rb_night_mode) {//夜间模式
                if (currentModeType == Configuration.UI_MODE_TYPE_NORMAL) {
                    //Note: On API 22 and below, changes to the night mode are only effective when the car or desk mode is enabled on a device.
                    // Starting in API 23, changes to night mode are always effective.
                    mUiModeManager.enableCarMode(0);//启用车载模式
                    mUiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
                }

            }
        }
    }

}

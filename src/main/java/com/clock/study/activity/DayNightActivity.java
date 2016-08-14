package com.clock.study.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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

        } else if (itemId == R.id.night_mode) {

        }
        return super.onOptionsItemSelected(item);
    }
}

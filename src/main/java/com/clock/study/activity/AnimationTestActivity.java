package com.clock.study.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.clock.study.R;
import com.clock.study.animation.SimpleCustomAnimation;

/**
 * 测试Animation动画效果
 */
public class AnimationTestActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mTestListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test);

        mTestListView = (ListView) findViewById(R.id.list_view_test);
        mTestListView.setAdapter(new SimpleTestListAdapter());

        findViewById(R.id.btn_test_anim).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_test_anim) {
            Animation animation = new SimpleCustomAnimation();
            animation.setDuration(1000);
            mTestListView.startAnimation(animation);
        }
    }

    private class SimpleTestListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(parent.getContext(), R.layout.author_info_layout, null);
            }
            return convertView;
        }
    }
}

package com.clock.study.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.clock.study.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_camera_take_photo).setOnClickListener(this);
        findViewById(R.id.btn_animation).setOnClickListener(this);
        findViewById(R.id.btn_animator).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_camera_take_photo) {
            Intent takePhotoIntent = new Intent(this, CapturePhotoActivity.class);
            startActivity(takePhotoIntent);
        } else if (viewId == R.id.btn_animation) {
            Intent animationIntent = new Intent(this, AnimationActivity.class);
            startActivity(animationIntent);
        } else if (viewId == R.id.btn_animator) {
            Intent animatorIntent = new Intent(this, AnimatorActivity.class);
            startActivity(animatorIntent);
        }
    }
}

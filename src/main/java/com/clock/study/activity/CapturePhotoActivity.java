package com.clock.study.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.clock.study.R;
import com.clock.study.helper.CapturePhotoHelper;
import com.clock.study.manager.FolderManager;

import java.io.File;

/**
 * 调用系统相机进行拍照
 *
 * @author Clock
 * @since 2016-05-13
 */
public class CapturePhotoActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = CapturePhotoActivity.class.getSimpleName();
    private final static String EXTRA_RESTORE_PHOTO = "extra_restore_photo";

    private CapturePhotoHelper mCapturePhotoHelper;
    private File mRestorePhotoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_take_photo);

        mCapturePhotoHelper = new CapturePhotoHelper(this, FolderManager.getPhotoFolder());

        findViewById(R.id.iv_take_photo).setOnClickListener(this);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        mRestorePhotoFile = mCapturePhotoHelper.getPhoto();
        Log.i(TAG, "onSaveInstanceState , mRestorePhotoFile: " + mRestorePhotoFile);
        if (mRestorePhotoFile != null) {
            outState.putSerializable(EXTRA_RESTORE_PHOTO, mRestorePhotoFile);
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i(TAG, "onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        mRestorePhotoFile = (File) savedInstanceState.getSerializable(EXTRA_RESTORE_PHOTO);
        Log.i(TAG, "onRestoreInstanceState , mRestorePhotoFile: " + mRestorePhotoFile);
        mCapturePhotoHelper.setPhoto(mRestorePhotoFile);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.iv_take_photo) {
            mCapturePhotoHelper.capture();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "requestCode: " + requestCode + " resultCode: " + resultCode + " data: " + data);
        if (requestCode == CapturePhotoHelper.CAPTURE_PHOTO_REQUEST_CODE) {
            File photoFile = mCapturePhotoHelper.getPhoto();
            if (photoFile != null) {
                if (resultCode == RESULT_OK) {
                    PhotoPreviewActivity.preview(this, photoFile);
                    finish();
                } else {
                    if (photoFile.exists()) {
                        photoFile.delete();
                    }
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}

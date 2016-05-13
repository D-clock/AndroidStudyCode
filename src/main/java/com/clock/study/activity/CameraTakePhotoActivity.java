package com.clock.study.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.clock.study.R;
import com.clock.study.StudyApplication;
import com.clock.utils.bitmap.BitmapUtils;
import com.clock.utils.common.SystemUtils;

import java.io.File;
import java.io.IOException;

/**
 * 调用系统相机进行拍照
 *
 * @author Clock
 * @since 2016-05-13
 */
public class CameraTakePhotoActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = CameraTakePhotoActivity.class.getSimpleName();
    private final static int TAKE_PHOTO_REQUEST_CODE = 1000;

    private File mTempFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_take_photo);

        findViewById(R.id.iv_take_photo).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.iv_take_photo) {
            createTempFile();
            if (mTempFile != null) {
                startCamera();
            } else {
                Toast.makeText(this, R.string.start_camera_error, Toast.LENGTH_SHORT).show();
            }

        }
    }

    /**
     * 调用系统相机
     */
    private void startCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri fileUri = Uri.fromFile(mTempFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, TAKE_PHOTO_REQUEST_CODE);
    }

    /**
     * 创建临时文件用于存储图片
     */
    private void createTempFile() {
        if (SystemUtils.mountedSdCard()) {
            File appFolder = new File(Environment.getExternalStorageDirectory(), StudyApplication.APP_MAIN_FOLDER_NAME);
            if (!appFolder.exists()) {
                appFolder.mkdir();//创建app的主目录
            }

            File photoFolder = new File(appFolder, StudyApplication.PHOTO_FOLDER_NAME);
            if (!photoFolder.exists()) {
                photoFolder.mkdir();//创建存放图片的目录
            }

            if (mTempFile == null && photoFolder.exists()) {
                mTempFile = new File(photoFolder, BitmapUtils.TEMP_FILE_NAME + BitmapUtils.JPG_SUFFIX);
            }

            if (mTempFile == null) {
                return;
            }

            if (mTempFile.exists()) {
                mTempFile.delete();
            }
            try {
                mTempFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                mTempFile = null;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "requestCode: " + requestCode + " resultCode: " + resultCode + " data: " + data);
        if (requestCode == TAKE_PHOTO_REQUEST_CODE && resultCode == RESULT_OK) {
            PhotoPreviewActivity.preview(this, mTempFile);
            finish();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}

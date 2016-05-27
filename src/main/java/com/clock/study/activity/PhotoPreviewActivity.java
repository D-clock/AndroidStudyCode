package com.clock.study.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.clock.study.R;
import com.clock.utils.bitmap.BitmapUtils;
import com.clock.utils.common.RuleUtils;

import java.io.File;

/**
 * 预览图片界面
 *
 * @author Clock
 * @since 2016-05-13
 */
public class PhotoPreviewActivity extends AppCompatActivity implements View.OnClickListener {

    private final static float RATIO = 0.75f;

    private final static String EXTRA_PHOTO = "extra_photo";

    private ImageView mPhotoPreview;
    private File mPhotoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_preview);

        mPhotoPreview = (ImageView) findViewById(R.id.iv_preview_photo);

        mPhotoFile = (File) getIntent().getSerializableExtra(EXTRA_PHOTO);
        int requestWidth = (int) (RuleUtils.getScreenWidth(this) * RATIO);
        int requestHeight = (int) (RuleUtils.getScreenHeight(this) * RATIO);
        Bitmap bitmap = BitmapUtils.decodeBitmapFromFile(mPhotoFile, requestWidth, requestHeight);//按照屏幕宽高的3/4比例进行缩放显示
        if (bitmap != null) {
            int degree = BitmapUtils.getBitmapDegree(mPhotoFile.getAbsolutePath());//检查是否有被旋转，并进行纠正
            if (degree != 0) {
                bitmap = BitmapUtils.rotateBitmapByDegree(bitmap, degree);
            }
            mPhotoPreview.setImageBitmap(bitmap);
        }

        findViewById(R.id.btn_display_to_gallery).setOnClickListener(this);
    }

    public static void preview(Activity activity, File file) {
        Intent previewIntent = new Intent(activity, PhotoPreviewActivity.class);
        previewIntent.putExtra(EXTRA_PHOTO, file);
        activity.startActivity(previewIntent);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_display_to_gallery) {
            BitmapUtils.displayToGallery(this, mPhotoFile);
        }
    }
}

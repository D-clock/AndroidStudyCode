package com.clock.study.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
public class PhotoPreviewActivity extends AppCompatActivity {

    private final static float RATIO = 0.75f;

    private final static String EXTRA_IMAGE = "extra_image";

    private ImageView mPhotoPreview;
    private File mImageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_preview);

        mPhotoPreview = (ImageView) findViewById(R.id.iv_photo_preview);

        mImageFile = (File) getIntent().getSerializableExtra(EXTRA_IMAGE);
        int requestWidth = (int) (RuleUtils.getScreenWidth(this) * RATIO);
        int requestHeight = (int) (RuleUtils.getScreenHeight(this) * RATIO);
        Bitmap bitmap = BitmapUtils.decodeBitmapFromFile(mImageFile, requestWidth, requestHeight);//按照屏幕宽高的3/4比例进行缩放显示
        if (bitmap != null) {
            mPhotoPreview.setImageBitmap(bitmap);
        }
    }

    public static void preview(Activity activity, File file) {
        Intent previewIntent = new Intent(activity, PhotoPreviewActivity.class);
        previewIntent.putExtra(EXTRA_IMAGE, file);
        activity.startActivity(previewIntent);
    }
}

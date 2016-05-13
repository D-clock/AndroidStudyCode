package com.clock.study;

import android.app.Application;

import com.clock.utils.crash.CrashExceptionHandler;

/**
 * Created by Clock on 2016/5/13.
 */
public class StudyApplication extends Application {

    /**
     * app在sd卡的主目录
     */
    public final static String APP_MAIN_FOLDER_NAME = "AndroidStudy";
    /**
     * 本地存放闪退日志的目录
     */
    public final static String CRASH_FOLDER_NAME = "crash";
    /**
     * app在sd卡存放图片的目录
     */
    public final static String PHOTO_FOLDER_NAME = "photo";

    @Override
    public void onCreate() {
        super.onCreate();

        configCollectCrashInfo();
    }

    /**
     * 配置奔溃信息的搜集
     */
    private void configCollectCrashInfo() {
        CrashExceptionHandler crashExceptionHandler = new CrashExceptionHandler(this, APP_MAIN_FOLDER_NAME, CRASH_FOLDER_NAME);
        Thread.setDefaultUncaughtExceptionHandler(crashExceptionHandler);
    }
}

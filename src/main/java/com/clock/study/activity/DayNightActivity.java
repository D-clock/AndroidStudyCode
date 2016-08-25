package com.clock.study.activity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clock.study.R;
import com.clock.study.adapter.SimpleAuthorAdapter;
import com.clock.study.helper.DayNightHelper;
import com.clock.study.type.DayNight;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 夜间模式实现方案
 *
 * @author Clock
 * @since 2016-08-11
 */
public class DayNightActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private final static String TAG = DayNightActivity.class.getSimpleName();

    private DayNightHelper mDayNightHelper;

    private RecyclerView mRecyclerView;

    private LinearLayout mHeaderLayout;
    private List<RelativeLayout> mLayoutList;
    private List<TextView> mTextViewList;
    private List<CheckBox> mCheckBoxList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        initData();
        initTheme();
        setContentView(R.layout.activity_day_night);
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new SimpleAuthorAdapter());

        mHeaderLayout = (LinearLayout) findViewById(R.id.header_layout);

        mLayoutList = new ArrayList<>();
        mLayoutList.add((RelativeLayout) findViewById(R.id.jianshu_layout));
        mLayoutList.add((RelativeLayout) findViewById(R.id.zhihu_layout));

        mTextViewList = new ArrayList<>();
        mTextViewList.add((TextView) findViewById(R.id.tv_jianshu));
        mTextViewList.add((TextView) findViewById(R.id.tv_zhihu));

        mCheckBoxList = new ArrayList<>();
        CheckBox ckbJianshu = (CheckBox) findViewById(R.id.ckb_jianshu);
        ckbJianshu.setOnCheckedChangeListener(this);
        mCheckBoxList.add(ckbJianshu);
        CheckBox ckbZhihu = (CheckBox) findViewById(R.id.ckb_zhihu);
        ckbZhihu.setOnCheckedChangeListener(this);
        mCheckBoxList.add(ckbZhihu);

    }

    private void initData() {
        mDayNightHelper = new DayNightHelper(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int viewId = buttonView.getId();
        if (viewId == R.id.ckb_jianshu) {
            changeThemeByJianShu();

        } else if (viewId == R.id.ckb_zhihu) {
            changeThemeByZhiHu();

        }
    }

    private void initTheme() {
        if (mDayNightHelper.isDay()) {
            setTheme(R.style.DayTheme);
        } else {
            setTheme(R.style.NightTheme);
        }
    }

    /**
     * 切换主题设置
     */
    private void toggleThemeSetting() {
        if (mDayNightHelper.isDay()) {
            mDayNightHelper.setMode(DayNight.NIGHT);
            setTheme(R.style.NightTheme);
        } else {
            mDayNightHelper.setMode(DayNight.DAY);
            setTheme(R.style.DayTheme);
        }
    }

    /**
     * 使用简书的实现套路来切换夜间主题
     */
    private void changeThemeByJianShu() {
        toggleThemeSetting();

        final TypedValue typedValue = new TypedValue();
        Resources.Theme theme = getTheme();

        theme.resolveAttribute(R.attr.clockBackground, typedValue, true);
        mHeaderLayout.setBackgroundResource(typedValue.resourceId);
        //mRecyclerView.setBackgroundResource(typedValue.resourceId);
        for (RelativeLayout layout : mLayoutList) {
            layout.setBackgroundResource(typedValue.resourceId);
        }
        for (CheckBox checkBox : mCheckBoxList) {
            checkBox.setBackgroundResource(typedValue.resourceId);
        }
        for (TextView textView : mTextViewList) {
            textView.setBackgroundResource(typedValue.resourceId);
        }

        theme.resolveAttribute(R.attr.clockTextColor, typedValue, true);
        Resources resources = getResources();
        for (TextView textView : mTextViewList) {
            textView.setTextColor(resources.getColor(typedValue.resourceId));
        }

        Class<RecyclerView> recyclerViewClass = RecyclerView.class;
        try {
            Field declaredField = recyclerViewClass.getDeclaredField("mRecycler");
            declaredField.setAccessible(true);
            Method declaredMethod = Class.forName(RecyclerView.Recycler.class.getName()).getDeclaredMethod("clear", (Class<?>[]) new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(declaredField.get(mRecyclerView), new Object[0]);
            RecyclerView.RecycledViewPool recycledViewPool = mRecyclerView.getRecycledViewPool();
            recycledViewPool.clear();
            Context context = mRecyclerView.getContext();
            if (context instanceof ContextThemeWrapper) {
                if (mDayNightHelper.isDay()) {
                    context.setTheme(R.style.DayTheme);
                } else {
                    context.setTheme(R.style.NightTheme);
                }
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用知乎的实现套路来切换夜间主题
     */
    private void changeThemeByZhiHu() {
        toggleThemeSetting();
    }
}

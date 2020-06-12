package com.base.library.common.activity;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.base.library.common.AppCommonConstants;

/**
 * @author reber
 */
public abstract class AppBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        onInitCreate(savedInstanceState);
    }

    /**
     * 获取activity传递的参数bundle
     */
    protected Bundle getParamBundle() {
        return getIntent().getBundleExtra(AppCommonConstants.ARG_ACTIVITY_BUNDLE);
    }

    /**
     * 获取activity的布局Id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 在onCreate方法中，调用setContentView后的初始化，
     */
    protected abstract void onInitCreate(@Nullable Bundle savedInstanceState);
}

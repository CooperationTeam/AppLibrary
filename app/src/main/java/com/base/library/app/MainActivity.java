package com.base.library.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.base.library.app.font.AppFoutActivity;
import com.base.library.common.activity.AppBaseActivity;
import com.base.library.widget.listener.OnAppIntervalClickListener;

/**
 * @author reber
 */
public class MainActivity extends AppBaseActivity implements View.OnClickListener {

    private static final int APP_FONT = 1;   // Font

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitCreate(@Nullable Bundle savedInstanceState) {
        LinearLayout linearLayout = findViewById(R.id.container_layout);
        // 获取间隔的点击时间，防止重复点击
        OnAppIntervalClickListener clickListener = new OnAppIntervalClickListener(this);
        linearLayout.addView(ViewUtil.generationItemView(this, "Font的展示", APP_FONT, clickListener));
    }

    @Override
    public void onClick(View v) {
        int type = (int) v.getTag();
        Intent intent = null;
        switch (type) {
            case APP_FONT:
                intent = AppFoutActivity.getIntent(this);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}

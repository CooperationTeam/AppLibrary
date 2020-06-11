package com.base.library.widget.listener;

import android.view.View;
import android.view.ViewConfiguration;

import com.base.library.widget.AppWidgetConstants;
import com.base.library.widget.R;

/**
 * @author reber
 * <p>
 * 处理view的间隔点击事件
 */
public class OnAppIntervalClickListener implements View.OnClickListener {

    private View.OnClickListener mClickListener;
    /**
     * 点击的间隔时间设置
     */
    private long mIntervalTimeValue;

    public OnAppIntervalClickListener() {
        this(null, ViewConfiguration.getDoubleTapTimeout());
    }

    public OnAppIntervalClickListener(View.OnClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public OnAppIntervalClickListener(View.OnClickListener clickListener, long intervalTimeValue) {
        this.mClickListener = clickListener;
        this.mIntervalTimeValue = intervalTimeValue;
    }

    @Override
    public void onClick(View v) {
        if (isAppClickEnable(v)) {
            if (mClickListener != null) {
                mClickListener.onClick(v);
            }
        }
    }

    /**
     * 处理间隔时间,防止重复点击
     */
    private boolean isAppClickEnable(View view) {
        Object object = view.getTag(AppWidgetConstants.KEY_VIEW_INTERNAL_TIME);
        boolean intervalClickEnable = true;
        if (object != null) {
            // 获取上一次点击的时间
            long preClickTime = (long) object;
            if (mIntervalTimeValue == 0) {
                mIntervalTimeValue = view.getContext().getResources()
                        .getInteger(R.integer.VIEW_CLICKABLE_DEFAULT_TIME);
            }
            intervalClickEnable = preClickTime + mIntervalTimeValue >= System.currentTimeMillis();
        }
        if (intervalClickEnable) {
            // 记录当前点击的时间
            view.setTag(AppWidgetConstants.KEY_VIEW_INTERNAL_TIME, System.currentTimeMillis());
        }
        return intervalClickEnable;
    }
}

package com.base.library.widget.common;

import android.view.View;

import com.base.library.widget.AppWidgetConstants;

/**
 *
 */
public class OnAppClickListener implements View.OnClickListener {

    private View.OnClickListener mClickListener;

    public OnAppClickListener() {
    }

    public OnAppClickListener(View.OnClickListener clickListener) {
        mClickListener = clickListener;
    }

    public void setClickListener(View.OnClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    @Override
    public void onClick(View v) {
        if (isAppClickEnable(v)) {
            if (mClickListener != null) {
                mClickListener.onClick(v);
            }
        }
    }

    private boolean isAppClickEnable(View view) {
        Object object = view.getTag(AppWidgetConstants.TIME_KEY);
        if (object != null) {
            long preClickTime = (long) object;
            long currentTime = System.currentTimeMillis();
            boolean multiClickEnable = preClickTime + AppWidgetConstants.VIEW_CLICKABLE_TIME > currentTime;
            if (multiClickEnable) {
                view.setTag(AppWidgetConstants.TIME_KEY, System.currentTimeMillis());
            }
            return multiClickEnable;
        }
        return true;
    }
}

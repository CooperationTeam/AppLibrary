package com.base.library.widget.page;

import android.view.View;
import android.view.ViewGroup;

import com.base.library.widget.listener.OnAppPageLoadListener;


/**
 * @author reber
 */
public abstract class AppPageLoadControllerImpl implements AppPageLoadControllerDelegate {

    private OnAppPageLoadListener mOnAppPageLoadListener;

    @Override
    public View getViewWithPageState(@AppPageLoadState int pageState, ViewGroup parent, String message) {
        switch (pageState) {
            case AppPageLoadState.LOADING:
                return getLoadingView(parent, message);
            case AppPageLoadState.EMPTY:
                return getEmptyView(parent, message);
            case AppPageLoadState.FAILURE:
                return getFailureView(parent, message);
            case AppPageLoadState.SUCCESS:
            default:
                throw new IllegalStateException("not support pageState:" + pageState + " in this controller");
        }
    }

    /**
     * 如果{@link AppPageLoadWidgetImpl}也设置了OnPageLoadListener，以PageLoadWidget的为主，防止重复设置
     */
    @Override
    public void setOnPageLoadListener(OnAppPageLoadListener onAppPageLoadListener) {
        if (mOnAppPageLoadListener == null) {
            this.mOnAppPageLoadListener = onAppPageLoadListener;
        }
    }
}

package com.base.library.widget.page;

import android.view.View;
import android.view.ViewGroup;

import com.base.library.widget.listener.OnAppPageLoadListener;

/**
 * @author reber
 */
public interface AppPageLoadControllerDelegate {

    /**
     * 根据不同的页面状态返沪不同的页面
     */
    View getViewWithPageState(@AppPageLoadState int pageState, ViewGroup parent, String message);

    /**
     * 返回需要的loading状态的页面
     *
     * @param loadingMessage loading加载中的提示信息
     */
    View getLoadingView(ViewGroup parent, String loadingMessage);

    /**
     * 返回需要的数据为空的页面
     *
     * @param emptyMessage 数据为空后的提示信息
     */
    View getEmptyView(ViewGroup parent, String emptyMessage);

    /**
     * 返回需要的失败页面
     *
     * @param failureMessage 失败后的提示信息
     */
    View getFailureView(ViewGroup parent, String failureMessage);

    /**
     * 当页面加载为空或失败后的重试
     * <p>
     * 如果{@link AppPageLoadWidgetImpl}也设置了OnPageLoadListener，以PageLoadWidget的为主，
     * 处理判空防止重复设置
     */
    void setOnPageLoadListener(OnAppPageLoadListener onAppPageLoadListener);
}

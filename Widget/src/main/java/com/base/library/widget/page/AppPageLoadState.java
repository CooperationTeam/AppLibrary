package com.base.library.widget.page;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author reber
 * <p>
 * 页面的加载状态
 */
@IntDef({
        AppPageLoadState.LOADING,
        AppPageLoadState.SUCCESS,
        AppPageLoadState.FAILURE,
        AppPageLoadState.EMPTY
})
@Retention(RetentionPolicy.SOURCE)
public @interface AppPageLoadState {
    int LOADING = 0; // 加载中
    int SUCCESS = 1; // 加载成功
    int FAILURE = 2; // 加载失败
    int EMPTY = 3;   // 数据为空
}

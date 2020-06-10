package com.base.library.widget.page;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.library.util.AppViewUtil;
import com.base.library.widget.listener.OnAppPageLoadListener;

/**
 * @author reber
 */
public abstract class AppPageLoadWidgetImpl extends FrameLayout {

    private int mPageState = -1;
    private AppPageLoadControllerDelegate mAppPageLoadController;
    private View mLoadingView, mSuccessView, mFailureView, mEmptyView;

    public AppPageLoadWidgetImpl(@NonNull Context context) {
        this(context, null);
    }

    public AppPageLoadWidgetImpl(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppPageLoadWidgetImpl(@NonNull Context context, @Nullable AttributeSet attrs,
                                 int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mSuccessView = getChildAt(0);
        if (mSuccessView == null) {
            throw new ExceptionInInitializerError("please add a child view");
        }
    }

    /**
     * 如果想重新定义一套loading、empty、failure的页面，可以自定一个实现类
     * {@link AppPageLoadControllerDelegate}，或继承{@link AppPageLoadControllerImpl},
     * 再通过set方法更新
     */
    public void setPageLoadDelegate(AppPageLoadControllerDelegate appPageLoadControllerDelegate) {
        if (mAppPageLoadController != null) {
            throw new ExceptionInInitializerError("Please initialize the mAppPageLoadController " +
                    "object through setPageLoadDelegate before using the setPageState method");
        }
        this.mAppPageLoadController = appPageLoadControllerDelegate;
    }

    public void setOnPageLoadListener(OnAppPageLoadListener onAppPageLoadListener) {
        getAppPageLoadController().setOnPageLoadListener(onAppPageLoadListener);
    }

    /**
     * 返回当前正在展示的页面状态
     */
    public int getPageState() {
        return mPageState;
    }

    public void setPageState(@AppPageLoadState int pageState) {
        setPageState(pageState, null);
    }

    public void setPageState(@AppPageLoadState int pageState, String message) {
        if (pageState == mPageState) {
            return;
        }
        this.mPageState = pageState;
        switch (pageState) {
            case AppPageLoadState.LOADING:
                onUpdateWithPageState(pageState, true, false,
                        false, false, message);
                break;
            case AppPageLoadState.SUCCESS:
                onUpdateWithPageState(pageState, false, true,
                        false, false, message);
                break;
            case AppPageLoadState.EMPTY:
                onUpdateWithPageState(pageState, false, false,
                        true, false, message);
                break;
            case AppPageLoadState.FAILURE:
                onUpdateWithPageState(pageState, false, false,
                        false, true, message);
                break;
        }
    }

    /**
     * 根据pageState切换显示不同的页面
     *
     * @param loadingVisible 是显示loading页面
     * @param successVisible 是否显示成功页面（内容页面）
     * @param emptyVisible   是否显示空页面
     * @param failureVisible 是否显示失败页面
     */
    private void onUpdateWithPageState(@AppPageLoadState int pageState, boolean loadingVisible,
                                       boolean successVisible, boolean emptyVisible,
                                       boolean failureVisible, String message) {
        onUpdateLoadingView(pageState, loadingVisible, message);
        onUpdateSuccessView(successVisible);
        onUpdateEmptyView(pageState, emptyVisible, message);
        onUpdateFailureView(pageState, failureVisible, message);
    }

    /**
     * 显示或隐藏 loading页面
     *
     * @param loadingVisible 是显示loading页面
     * @param loadingMessage loading加载中的提示信息
     */
    private void onUpdateLoadingView(@AppPageLoadState int pageState, boolean loadingVisible,
                                     String loadingMessage) {
        if (loadingVisible && mLoadingView == null) {
            this.mLoadingView = getPageStateView(pageState, loadingMessage);
            addView(mLoadingView);
        } else {
            AppViewUtil.setVisible(mLoadingView, loadingVisible);
        }
    }

    /**
     * 显示或隐藏 内容页面
     *
     * @param successVisible 是否显示成功页面（内容页面）
     */
    private void onUpdateSuccessView(boolean successVisible) {
        AppViewUtil.setVisible(mSuccessView, successVisible);
    }

    /**
     * 显示或隐藏 empty页面
     *
     * @param emptyVisible 是否显示空页面
     * @param emptyMessage 数据为空后的提示信息
     */
    private void onUpdateEmptyView(@AppPageLoadState int pageState, boolean emptyVisible,
                                   String emptyMessage) {
        if (emptyVisible && mEmptyView == null) {
            this.mEmptyView = getPageStateView(pageState, emptyMessage);
            addView(mEmptyView);
        } else {
            AppViewUtil.setVisible(mEmptyView, emptyVisible);
        }
    }

    /**
     * 显示或隐藏 empty页面
     *
     * @param failureVisible 是否显示失败页面
     * @param failureMessage 失败后的提示信息
     */
    private void onUpdateFailureView(@AppPageLoadState int pageState, boolean failureVisible,
                                     String failureMessage) {
        if (failureVisible && mFailureView == null) {
            mFailureView = getPageStateView(pageState, failureMessage);
            addView(mFailureView);
        } else {
            AppViewUtil.setVisible(mFailureView, failureVisible);
        }
    }

    /**
     * 根据页面状态返回需要的页面
     *
     * @param pageState PageLoadState的页面的展示状态
     * @param message   需要展示页面信息
     */
    private View getPageStateView(@AppPageLoadState int pageState, String message) {
        return getAppPageLoadController().getViewWithPageState(pageState, this, message);
    }

    private AppPageLoadControllerDelegate getAppPageLoadController() {
        if (mAppPageLoadController == null) {
            mAppPageLoadController = getDefaultAppPageLoadController();
        }
        return mAppPageLoadController;
    }

    /**
     * 获取默认的页面切换的Controller
     * 可以继承{@link AppPageLoadControllerImpl}单独处理，或者实现{@link AppPageLoadControllerDelegate}
     */
    protected abstract AppPageLoadControllerDelegate getDefaultAppPageLoadController();
}

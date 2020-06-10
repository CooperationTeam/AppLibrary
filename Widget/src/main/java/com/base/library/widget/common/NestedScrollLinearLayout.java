package com.base.library.widget.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ViewCompat;

/**
 * @author reber
 * <p>
 * 实现header跟随手指触发整体滑动
 */
public class NestedScrollLinearLayout extends AppLinearLayout implements NestedScrollingChild {

    private final int[] offset = new int[2];
    private final int[] consumed = new int[2];

    private NestedScrollingChildHelper mScrollingChildHelper;
    private int lastY;

    public NestedScrollLinearLayout(Context context) {
        this(context, null);
    }

    public NestedScrollLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedScrollLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScrollingChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = (int) event.getY();
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
                break;
            case MotionEvent.ACTION_MOVE:
                int y = (int) (event.getY());
                //dy < 0 下滑， dy>0 上滑
                int dy = lastY - y;
                lastY = y;
                dispatchNestedPreScroll(0, dy, consumed, offset);
                break;
        }
        return true;
    }


    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        mScrollingChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return mScrollingChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return mScrollingChildHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        mScrollingChildHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return mScrollingChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed,
                                        int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return mScrollingChildHelper.dispatchNestedScroll(dxConsumed,
                dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed,
                                           int[] offsetInWindow) {
        return mScrollingChildHelper.dispatchNestedPreScroll(dx, dy,
                consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY,
                                       boolean consumed) {
        return mScrollingChildHelper.dispatchNestedFling(velocityX,
                velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return mScrollingChildHelper.dispatchNestedPreFling(velocityX,
                velocityY);
    }
}

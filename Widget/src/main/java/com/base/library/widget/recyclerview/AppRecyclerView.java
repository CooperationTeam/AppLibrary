package com.base.widget.recyclerview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

/**
 * @author reber
 */
public class AppRecyclerView extends RecyclerView {

    public AppRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public AppRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 当adapter调用notifyChange之后，移除列表中的item默认动画效果
     * <p>
     * remove the item default animator when recyclerView.adapter execute notifyChanged
     */
    public void removeDefaultItemAnimator() {
        if (getItemAnimator() != null) {
            SimpleItemAnimator simpleItemAnimator = (SimpleItemAnimator) getItemAnimator();
            simpleItemAnimator.setAddDuration(0);
            simpleItemAnimator.setChangeDuration(0);
            simpleItemAnimator.setMoveDuration(0);
            simpleItemAnimator.setRemoveDuration(0);
            simpleItemAnimator.setSupportsChangeAnimations(false);
        }
    }

    /**
     * 移除滑动产生的阴影
     * <p>
     * Remove shadows from sliding
     */
    public void removeScrollShadows() {
        setOverScrollMode(OVER_SCROLL_NEVER);
    }
}

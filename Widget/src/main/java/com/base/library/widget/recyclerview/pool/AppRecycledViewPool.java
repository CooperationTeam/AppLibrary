package com.base.library.widget.recyclerview.pool;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author reber
 */
public class AppRecycledViewPool extends RecyclerView.RecycledViewPool {

    private static final int DEFAULT_MIN_SCRAP_COUNT = 5;

    public AppRecycledViewPool() {
        super();
    }

    @Override
    public void clear() {
        super.clear();
    }

    /**
     * 设置RecyclerViewPool缓存最大数目
     *
     * @param viewType ViewHolder的ItemType
     * @param max      每viewType对应的最大缓存数目，
     *                 默认为5个{@link RecyclerView.RecycledViewPool DEFAULT_MAX_SCRAP}
     */
    @Override
    public void setMaxRecycledViews(int viewType, int max) {
        // 设置最低限制 5 个
        super.setMaxRecycledViews(viewType, Math.max(DEFAULT_MIN_SCRAP_COUNT, max));
    }

    /**
     * 返回对应viewType的已缓存数量
     */
    @Override
    public int getRecycledViewCount(int viewType) {
        return super.getRecycledViewCount(viewType);
    }

    @Nullable
    @Override
    public RecyclerView.ViewHolder getRecycledView(int viewType) {
        return super.getRecycledView(viewType);
    }

    @Override
    public void putRecycledView(RecyclerView.ViewHolder scrap) {
        super.putRecycledView(scrap);
    }
}

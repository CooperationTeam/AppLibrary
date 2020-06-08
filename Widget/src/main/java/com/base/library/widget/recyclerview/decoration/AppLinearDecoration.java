package com.base.library.widget.recyclerview.decoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author reber
 * <p>
 * RecyclerView的通用线性分割线
 * <p>
 * RecyclerView's general linear dividing line
 */
public class AppLinearDecoration extends RecyclerView.ItemDecoration {

    /**
     * 分割线的默认值为1px
     * <p>
     * The default value of the dividing line is 1px
     */
    private static final int DEFAULT_DIVIDER_VALUE = 1;
    /**
     * 如果直接设置了分割线的Drawable，将不重复设置分割线的颜色
     * <p>
     * If the Drawable of the dividing line is set directly, the color of the dividing line
     * will not be set repeatedly
     */
    private Drawable mDrawable;

    /**
     * RecyclerView的方向, 默认为纵向的
     * <p>
     * The direction of RecyclerView, the default is vertical
     */
    @RecyclerView.Orientation
    private int mOrientation = RecyclerView.VERTICAL;

    /**
     * 当没有设置drawable时，需要初始化一个画笔绘制矩形分割线
     * <p>
     * When drawable is not set, you need to initialize a brush to draw a rectangular dividing line
     */
    private Paint mDividerPaint;

    /**
     * 是否展示列表边界的分割线, 值都是RecyclerView的left，top，right和bottom
     * <p>
     * Whether to display the dividing line of the list boundary, the values are the left, top, right and bottom of RecyclerView
     */
    private final Rect mRVBoundaryValue = new Rect();

    /**
     * RecyclerView的列表边界值
     */
    private int mItemDivideValues;

    /**
     * 设置私有构造方法，并只能通过builder中新建
     * <p>
     * Set a private constructor, and can only be created through the builder
     */
    private AppLinearDecoration() {
    }

    /**
     * 在ItemView绘制之前调用
     * <p>
     * Called before ItemView draws
     */
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (state.isPreLayout()) {
            return;
        }
        if (mOrientation == RecyclerView.VERTICAL) {
            drawHorizontalDivider(c, parent, state);
        } else {
            drawVerticalDivider(c, parent, state);
        }
    }

    /**
     * 在itemView绘制之后调用
     * <p>
     * Called after itemView is drawn
     */
    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    /**
     * 绘制完一个ItemView之后，下一个Item需要预留多少空位
     * <p>
     * After drawing an ItemView, how much space should be reserved for the next Item
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (state.isPreLayout()) {
            return;
        }
        int childPosition = parent.getChildAdapterPosition(view);
        int lastPosition = state.getItemCount() - 1;
        if (mOrientation == RecyclerView.VERTICAL) {
            // 如果是列表中的第一个，取RecyclerView的top值
            // if it is the first item of list,will get the top value from recyclerview
            int topValue = 0;
            if (childPosition == 0) {
                topValue = mRVBoundaryValue.top;
            }
            // 如果是列表中的最后的一个取RecyclerView的bottom边界值
            // If it is the last one in the list, take the bottom boundary value of RecyclerView
            int bottomValue;
            if (childPosition == lastPosition) {
                bottomValue = mRVBoundaryValue.bottom;
            } else {
                bottomValue = mItemDivideValues;
            }
            outRect.set(mRVBoundaryValue.left, topValue, mRVBoundaryValue.right, bottomValue);
        } else {
            int leftValue = 0;
            // 如果RecyclerView的方向是横向的，第一个item的左边界值取RecyclerView的left值
            // If the direction of RecyclerView is horizontal, the left boundary value of
            // the first item takes the left value of RecyclerView
            if (childPosition == 0) {
                leftValue = mRVBoundaryValue.left;
            }
            // 如果是列表中的最后一个，右边界值取RecyclerView中的right值
            // If it is the last one in the list, the right boundary value takes the right value in RecyclerView
            int rightValue;
            if (childPosition == lastPosition) {
                rightValue = mRVBoundaryValue.right;
            } else {
                rightValue = mItemDivideValues;
            }
            outRect.set(leftValue, mRVBoundaryValue.top, rightValue, mRVBoundaryValue.bottom);
        }
    }

    /**
     * 如果RecyclerView是纵向的，绘制横向的分割线
     * <p>
     * If RecyclerView is vertical, draw a horizontal dividing line
     */
    private void drawHorizontalDivider(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        // 获取分割线的左边距离
        // Get the left distance of the dividing line
        final int dividerStart = recyclerView.getPaddingLeft() + mRVBoundaryValue.left;
        // 获取分割线到右边的距离
        // Get the distance from the dividing line to the right
        final int dividerEnd = recyclerView.getMeasuredWidth() - recyclerView.getPaddingRight() - mRVBoundaryValue.right;

        final int childCount = recyclerView.getChildCount();
        final int lastPosition = state.getItemCount() - 1;

        for (int i = 0; i < childCount; i++) {
            View childView = recyclerView.getChildAt(i);
            final int childRealPosition = recyclerView.getChildAdapterPosition(childView);

            // 如果是最后一个Item不展示分割线，底部是根据RecyclerView的底部边界值设置的
            // If it is the last Item that does not show the dividing line,
            // the bottom is set according to the bottom boundary value of RecyclerView
            if (childRealPosition < lastPosition) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
                int dividerTop = childView.getBottom() + layoutParams.bottomMargin;
                int dividerBottom = dividerTop + mItemDivideValues;

                if (mDrawable != null) {
                    mDrawable.setBounds(dividerStart, dividerTop, dividerEnd, dividerBottom);
                    mDrawable.draw(canvas);
                } else {
                    if (mDividerPaint != null) {
                        canvas.drawRect(dividerStart, dividerTop, dividerEnd, dividerBottom, mDividerPaint);
                    }
                }
            }
        }
    }

    /**
     * 如果RecyclerView是横向的，绘制纵向的分割线
     * <p>
     * If RecyclerView is horizontal, draw a vertical dividing line
     */
    private void drawVerticalDivider(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        final int dividerTop = recyclerView.getPaddingTop() + mRVBoundaryValue.top;
        final int dividerBottom = recyclerView.getMeasuredHeight() - recyclerView.getPaddingBottom() - mRVBoundaryValue.bottom;

        final int childCount = recyclerView.getChildCount();
        final int lastPosition = state.getItemCount() - 1;

        for (int i = 0; i < childCount; i++) {
            View childView = recyclerView.getChildAt(i);
            final int childRealPosition = recyclerView.getChildAdapterPosition(childView);

            if (childRealPosition < lastPosition) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
                int dividerStart = childView.getRight() + layoutParams.rightMargin;
                int dividerEnd = dividerStart + mItemDivideValues;

                if (mDrawable != null) {
                    mDrawable.setBounds(dividerStart, dividerTop, dividerEnd, dividerBottom);
                    mDrawable.draw(canvas);
                } else {
                    if (mDividerPaint != null) {
                        canvas.drawRect(dividerStart, dividerTop, dividerEnd, dividerBottom, mDividerPaint);
                    }
                }
            }
        }
    }

    public static class Builder {

        private final AppLinearDecoration mDecoration;

        /**
         * 是否初始化了分割线的颜色
         * Whether the color of the dividing line is initialized
         */
        private boolean isInitColor;
        @ColorRes
        private int mDividerColorResId;

        /**
         * 是否初始化了分割线的Drawable
         * Whether the Drawable of the dividing line is initialized
         */
        private boolean isInitDrawable;
        @DrawableRes
        private int mDrawableResId;

        /**
         * 是否初始化了分割线宽度
         * Whether the dividing line width is initialized
         */
        private boolean isInitItemDividerValue;
        @DimenRes
        private int mItemDividerValueResId;

        public Builder() {
            mDecoration = new AppLinearDecoration();
        }

        public Builder setOrientation(@RecyclerView.Orientation int orientation) {
            mDecoration.mOrientation = orientation;
            return this;
        }

        /**
         * RecyclerView的边界值
         * <p>
         * this is recyclerView boundary value
         */
        public Builder setRVBoundaryValue(@NonNull Rect rvBoundValues) {
            mDecoration.mRVBoundaryValue.set(rvBoundValues);
            return this;
        }

        public Builder setItemDivideValue(@DimenRes int itemDivideValueResId) {
            mItemDividerValueResId = itemDivideValueResId;
            isInitItemDividerValue = true;
            return this;
        }

        public Builder setDrawableResId(@DrawableRes int drawableResId) {
            mDrawableResId = drawableResId;
            isInitDrawable = true;
            return this;
        }

        public Builder setDividerColorResId(@ColorRes int dividerColorResId) {
            mDividerColorResId = dividerColorResId;
            isInitColor = true;
            return this;
        }

        /**
         * 根据设置的参数，初始化画笔或分割线的高度
         * <p>
         * According to the set parameters, initialize the height of the pen or dividing line
         */
        public AppLinearDecoration build(Context context) {
            Drawable divideDrawable = null;
            int divideValue = 0;
            try {
                if (isInitDrawable) {
                    divideDrawable = ContextCompat.getDrawable(context, mDrawableResId);
                }
                if (isInitItemDividerValue) {
                    divideValue = (int) context.getResources().getDimension(mItemDividerValueResId);
                }
            } catch (Resources.NotFoundException ignored) {
            }
            if (divideDrawable == null) {
                // 如果没有设置drawable就根据颜色值来设置分割线的背景
                // If no drawable is set, the background of the dividing line is set according to the color value
                if (isInitColor) {
                    mDecoration.mDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    mDecoration.mDividerPaint.setColor(ContextCompat.getColor(context, mDividerColorResId));
                    mDecoration.mDividerPaint.setStyle(Paint.Style.FILL);
                }
            } else {
                // drawable不为空时，如果没有设置分割线的高度，分割线的高度取drawable的默认高度
                // When the drawable is not empty, if the height of the dividing line is not set,
                // the height of the dividing line takes the default height of the drawable
                if (divideValue <= 0) {
                    divideValue = Math.max(divideDrawable.getIntrinsicHeight(), divideDrawable.getIntrinsicWidth());
                }
            }
            // 如果最后分割线的宽高值 <= 0时，设置默认值:1px
            // If the width and height of the last dividing line <= 0, set the default value is 1px
            mDecoration.mItemDivideValues = divideValue > 0 ? divideValue : DEFAULT_DIVIDER_VALUE;
            mDecoration.mDrawable = divideDrawable;
            return mDecoration;
        }
    }
}

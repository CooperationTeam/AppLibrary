package com.base.library.widget.shape.round;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import com.base.library.widget.R;
import com.base.library.widget.shape.AppShapeHelper;

/**
 * @author reber
 * <p>
 * this is order to add corner to background with all view, that only use this helper
 * at view constructor and set same attrs in view xml
 */
public class AppRoundHelper extends AppShapeHelper {

    private final View mView;
    private Path mClipPath;
    /**
     * @see AppRoundDelegate this is corner type
     */
    private int mShapeCornerType;
    /**
     * this is corner radius size(type is dimension)
     */
    private float mCornerRadius;
    /**
     * this is the background of shape solid
     */
    private Drawable mSolidDrawable;
    private final RectF mRectF = new RectF();

    AppRoundHelper(@NonNull View view) {
        this.mView = view;
    }

    final void loadFromShapeAttributes(AttributeSet attrs) {
        // init attrs params
        TypedArray a = mView.getContext()
                .obtainStyledAttributes(attrs, R.styleable.AppRoundLayout);
        try {
            if (a.getIndexCount() > 0) {
                if (a.hasValue(R.styleable.AppRoundLayout_shapeCornerType)) {
                    mShapeCornerType = a.getInt(R.styleable.AppRoundLayout_shapeCornerType,
                            AppRoundDelegate.MODE_NONE);
                }
                if (a.hasValue(R.styleable.AppRoundLayout_shapeCornerRadius)) {
                    mCornerRadius = a.getDimension(R.styleable.AppRoundLayout_shapeCornerRadius,
                            0f);
                }
                if (a.hasValue(R.styleable.AppRoundLayout_shapeSolidBackground)) {
                    mSolidDrawable = a.getDrawable(R.styleable.AppRoundLayout_shapeSolidBackground);
                }
                // init clip path
                if (isClipWithPathEnable()) {
                    //init path params
                    mClipPath = new Path();
                }
                // init background params
                Drawable drawable = mView.getBackground();
                if (drawable == null && mSolidDrawable != null) {
                    mView.setBackground(mSolidDrawable);
                }
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * whether view support clip when type != {@link AppRoundDelegate#MODE_NONE} and
     * mCornerRadius > 0 or not
     */
    final boolean isClipWithPathEnable() {
        return mShapeCornerType != AppRoundDelegate.MODE_NONE && mCornerRadius > 0;
    }

    /**
     * set the round rect of path after get layout width and height
     *
     * @param width  view width
     * @param height view height
     */
    final void onSizeChanged(float width, float height) {
        if (isClipWithPathEnable() && mClipPath != null) {
            float[] cornerRect = getFloatWithAllCorners(mShapeCornerType, mCornerRadius);
            mRectF.set(0, 0, width, height);
            mClipPath.reset();
            mClipPath.addRoundRect(mRectF, cornerRect, Path.Direction.CW);
        }
    }

    /**
     * @param canvas where execute is it that from inside of special view
     */
    final void executeDrawBeforeDraw(Canvas canvas) {
        if (isClipWithPathEnable() && mClipPath != null) {
            canvas.clipPath(mClipPath);
        }
    }
}

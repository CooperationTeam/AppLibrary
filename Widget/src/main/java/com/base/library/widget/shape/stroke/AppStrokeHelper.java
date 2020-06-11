package com.base.library.widget.shape.stroke;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.nfc.FormatException;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.base.library.widget.R;
import com.base.library.widget.shape.AppShapeHelper;

/**
 * @author reber
 * <p>
 * base on AppShapeHelper attrs {@see AppShapeHelper} corner type, corner radius and solid background
 * <p>
 * this is order to add more attributes strokeLine or dashLine
 */
public class AppStrokeHelper extends AppShapeHelper {

    private final View mView;
    /**
     * stroke paint path
     */
    private Path mPaintPath;
    /**
     * stroke paint
     */
    private Paint mPaint;
    /**
     * stroke paint color
     */
    private int mStrokeColor = -1;
    /**
     * this is the width of Paint, this is a px value
     */
    private float mStokeWidth = -1;
    private final RectF mPaintRectF = new RectF();

    public AppStrokeHelper(@NonNull View view) {
        this.mView = view;
    }

    public void loadFromStrokeAttributes(AttributeSet attrs) {
        float[] strokeDashWidthArray = null;
        TypedArray a = mView.getContext()
                .obtainStyledAttributes(attrs, R.styleable.AppStrokeLayout);
        try {
            if (a.getIndexCount() > 0) {
                if (a.hasValue(R.styleable.AppStrokeLayout_strokeColor)) {
                    mStrokeColor = a.getColor(R.styleable.AppStrokeLayout_strokeColor, -1);
                }
                if (a.hasValue(R.styleable.AppStrokeLayout_stokeWidth)) {
                    mStokeWidth = a.getDimension(R.styleable.AppStrokeLayout_stokeWidth, -1);
                }
                if (a.hasValue(R.styleable.AppStrokeLayout_stokeDashWidthArrayByDp)) {
                    String stokeIntervalWidth = a.getString(R.styleable.AppStrokeLayout_stokeDashWidthArrayByDp);
                    if (stokeIntervalWidth != null && stokeIntervalWidth.contains(",")) {
                        String[] stokeIntervalWidthArray = stokeIntervalWidth.split(",");
                        //the length must be even
                        if (stokeIntervalWidthArray.length % 2 != 0) {
                            throw new FormatException(
                                    "the attr of stokeDashWidthArrayByDp must be even, like:\"22,3\"");
                        }
                        strokeDashWidthArray = stringArrayToFloat(stokeIntervalWidthArray);
                    }
                }
                // init paint and path params
                if (isStrokeLineWithPathEnable()) {
                    // when view is ViewGroup that it will not execute the {executeDrawBeforeOnDraw()} method
                    // so it need set background to trigger this method
                    if (mView instanceof ViewGroup && mView.getBackground() == null) {
                        mView.setBackgroundColor(ContextCompat.getColor(mView.getContext(),
                                android.R.color.transparent));
                    }
                    //init paint and paint path
                    mPaintPath = new Path();
                    mPaint = new Paint();
                    mPaint.setStyle(Paint.Style.STROKE);
                    mPaint.setAntiAlias(true);
                    mPaint.setColor(mStrokeColor);
                    mPaint.setStrokeWidth(dp2px(mStokeWidth));
                    // whether the stroke dash line is enable or not
                    if (isStrokeDashWithPathEnable(strokeDashWidthArray)) {
                        mPaint.setPathEffect(new DashPathEffect(strokeDashWidthArray, 0));
                    }
                }
            }
        } catch (FormatException e) {
            e.printStackTrace();
        } finally {
            a.recycle();
        }
    }

    /**
     * set the round rect of path after get layout width and height
     *
     * @param width  view width
     * @param height view height
     */
    public float[] onSizeChanged(float width, float height) {
        if (isStrokeLineWithPathEnable() && mPaintPath != null) {

            float[] floats = getFloatWithAllCorners(0, 5f);
            float offset = mStokeWidth / 2;
            // update pain path
            mPaintPath.reset();
            mPaintRectF.set(offset, offset, width - offset, height - offset);
            mPaintPath.addRoundRect(mPaintRectF, floats, Path.Direction.CW);
            return floats;
        }
        return null;
    }

    /**
     * @param canvas this is from view to draw stroke line
     */
    public void executeDrawBeforeOnDraw(Canvas canvas) {
        if (isStrokeLineWithPathEnable() && mPaintPath != null) {
            canvas.drawPath(mPaintPath, mPaint);
        }
    }

    /**
     * @param dashArray this is a stroke dash line array,format is "22,2"
     * @return boolean whether the stroke dash line is enable or not
     */
    private boolean isStrokeDashWithPathEnable(float[] dashArray) {
        return dashArray != null && dashArray.length > 0;
    }

    /**
     * whether the stroke line will display or not
     */
    private boolean isStrokeLineWithPathEnable() {
        return mStrokeColor != -1 && mStokeWidth != -1;
    }

    /**
     * convert String array to float array
     *
     * @param stringArray this is stoke interval width array
     */
    private float[] stringArrayToFloat(String[] stringArray) {
        try {
            float[] floatArray = new float[stringArray.length];
            for (int i = 0; i < stringArray.length; i++) {
                floatArray[i] = dp2px(Float.parseFloat(stringArray[i]));
            }
            return floatArray;
        } catch (ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * convert dp value to px value
     */
    private float dp2px(float dpValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, mView.getContext()
                .getResources()
                .getDisplayMetrics());
    }
}

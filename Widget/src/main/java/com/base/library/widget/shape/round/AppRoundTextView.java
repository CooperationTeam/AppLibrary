package com.base.library.widget.shape.round;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.base.library.widget.common.AppTextView;

/**
 * @author reber
 */
public class AppRoundTextView extends AppTextView {

    private final AppRoundHelper mHelper;

    public AppRoundTextView(Context context) {
        this(context, null);
    }

    public AppRoundTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppRoundTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mHelper = new AppRoundHelper(this);
        mHelper.loadFromShapeAttributes(attrs);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        mHelper.onSizeChanged(width, height);
    }

    @Override
    public void draw(Canvas canvas) {
        if (mHelper.isClipWithPathEnable()) {
            int saveIndex = canvas.save();
            mHelper.executeDrawBeforeDraw(canvas);
            super.draw(canvas);
            canvas.restoreToCount(saveIndex);
        } else {
            super.draw(canvas);
        }
    }
}

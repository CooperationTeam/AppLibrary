package com.base.library.widget.shape.stroke;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.base.library.widget.common.AppTextView;

/**
 * @author reber
 */
class AppStrokeTextView extends AppTextView {

    private final AppStrokeHelper mHelper;

    public AppStrokeTextView(Context context) {
        this(context, null);
    }

    public AppStrokeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppStrokeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHelper = new AppStrokeHelper(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mHelper.executeDrawBeforeOnDraw(canvas);
        super.onDraw(canvas);
    }
}

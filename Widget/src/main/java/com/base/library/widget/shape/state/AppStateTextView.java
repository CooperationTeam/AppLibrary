package com.base.library.widget.shape.state;

import android.content.Context;
import android.util.AttributeSet;

import com.base.library.widget.common.AppTextView;

/**
 * @author reber
 */
public class AppStateTextView extends AppTextView {

    public AppStateTextView(Context context) {
        this(context, null);
    }

    public AppStateTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppStateTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}

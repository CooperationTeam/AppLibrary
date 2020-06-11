package com.base.library.widget.shape.state;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import com.base.library.widget.R;


/**
 * @author reber
 */
public class AppStateBackgroundHelper extends AppStateHelper {

    private final View mView;
    private StateListDrawable mStateListDrawable;

    public AppStateBackgroundHelper(@NonNull View view) {
        this.mView = view;
    }

    public void loadFromStateBackgroundAttributes(AttributeSet attrs) {
        TypedArray a = mView.getContext()
                .obtainStyledAttributes(attrs, R.styleable.AppStateBackgroundLayout);
        try {
            int indexCount = a.getIndexCount();
            if (indexCount > 0) {
                // i
                Drawable background = mView.getBackground();
                if (background instanceof StateListDrawable) {
                    mStateListDrawable = (StateListDrawable) background;
                } else {
                    mStateListDrawable = new StateListDrawable();
                }
                setStateSet(a);
                // Change the global fade duration when a new background is entering the scene.
                if (a.hasValue(R.styleable.AppStateBackgroundLayout_stateChangedFadeDuration)) {
                    setStateFadeDuration(a);
                }
                // update the view background.
                mView.setBackground(mStateListDrawable);
            }
        } finally {
            a.recycle();
        }
    }

    private void setStateSet(TypedArray typedArray) {
        setStateBackground(typedArray,
                R.styleable.AppStateBackgroundLayout_statePressedBackground,
                AppStateDelegate.TYPE_ENABLE_PRESSED);

        setStateBackground(typedArray,
                R.styleable.AppStateBackgroundLayout_stateSelectedBackground,
                AppStateDelegate.TYPE_ENABLE_SELECTED);

        setStateBackground(typedArray,
                R.styleable.AppStateBackgroundLayout_stateUnselectedBackground,
                AppStateDelegate.TYPE_ENABLE_UNSELECTED);

        setStateBackground(typedArray,
                R.styleable.AppStateBackgroundLayout_stateDisableBackground,
                AppStateDelegate.TYPE_DISABLE);

        setStateBackground(typedArray,
                R.styleable.AppStateBackgroundLayout_stateEnabledBackground,
                AppStateDelegate.TYPE_ENABLE);
    }

    private void setStateBackground(TypedArray typedArray, int styleableIndex, int stateType) {
        if (typedArray.hasValue(styleableIndex)) {
            Drawable drawable = typedArray.getDrawable(styleableIndex);
            if (drawable != null) {
                mStateListDrawable.addState(getStateCombination(stateType), drawable);
            }
        }
    }

    /**
     * 设置背景切换的褪色动画持续时间
     */
    private void setStateFadeDuration(TypedArray typedArray) {
        int fadeDefaultDuration = mView.getResources()
                .getInteger(R.integer.SHAPE_FADE_DEFAULT_DURATION);
        int fadeDuration = typedArray.getInt(
                R.styleable.AppStateBackgroundLayout_stateChangedFadeDuration, fadeDefaultDuration);
        mStateListDrawable.setEnterFadeDuration(fadeDuration);
        mStateListDrawable.setExitFadeDuration(fadeDuration);
    }
}

package com.base.library.widget.shape.state;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.base.library.widget.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author reber
 */
public class AppStateTextColorHelper extends AppStateHelper {

    private final View mView;
    private ArrayList<Integer> mColors;
    private ArrayList<int[]> mStates;

    public AppStateTextColorHelper(@NonNull View view) {
        this.mView = view;
    }

    public void loadFromTextColorAttributes(AttributeSet attrs) {
        TypedArray a = mView.getContext()
                .obtainStyledAttributes(attrs, R.styleable.AppStateTextColorLayout);
        try {
            if (a.getIndexCount() > 0 && mView instanceof TextView) {
                setStateSet(a);
                if (mColors != null && mColors.size() > 0) {
                    int[] colorArray = arrayIntegerToInt(mColors.toArray(new Integer[0]));
                    int[][] integerArray = mStates.toArray(new int[0][0]);
                    //add color state list to TextView
                    TextView tv = (TextView) mView;
                    tv.setTextColor(new ColorStateList(integerArray, colorArray));
                }
            }

        } finally {
            a.recycle();
        }
    }

    private void setStateSet(TypedArray typedArray) {
        setStateTextColor(typedArray, R.styleable.AppStateTextColorLayout_statePressedTextColor,
                AppStateDelegate.TYPE_ENABLE_PRESSED);

        setStateTextColor(typedArray, R.styleable.AppStateTextColorLayout_stateSelectedTextColor,
                AppStateDelegate.TYPE_ENABLE_SELECTED);

        setStateTextColor(typedArray, R.styleable.AppStateTextColorLayout_stateUnselectedTextColor,
                AppStateDelegate.TYPE_ENABLE_UNSELECTED);

        setStateTextColor(typedArray, R.styleable.AppStateTextColorLayout_stateDisableTextColor,
                AppStateDelegate.TYPE_DISABLE);

        setStateTextColor(typedArray, R.styleable.AppStateTextColorLayout_stateEnabledTextColor,
                AppStateDelegate.TYPE_ENABLE);

    }

    private void setStateTextColor(TypedArray typedArray, int styleableIndex, int stateType) {
        if (typedArray.hasValue(styleableIndex)) {
            int color = typedArray.getColor(styleableIndex, Color.TRANSPARENT);
            if (color != Color.TRANSPARENT) {
                if (mColors == null) mColors = new ArrayList<>();
                mColors.add(color);
                if (mStates == null) mStates = new ArrayList<>();
                mStates.add(getStateCombination(stateType));
            }
        }
    }

    /**
     * @param integers a array, this is a must to converted to int[]
     * @return int[]
     */
    private int[] arrayIntegerToInt(Integer[] integers) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Arrays.stream(integers)
                    .mapToInt(Integer::valueOf)
                    .toArray();
        } else {
            int[] array = new int[integers.length];
            for (int i = 0; i < integers.length; i++) {
                array[i] = integers[i];
            }
            return array;
        }
    }
}

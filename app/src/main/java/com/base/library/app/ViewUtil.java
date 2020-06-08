package com.base.library.app;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

/**
 * @author reber
 */
public class ViewUtil {

    public static View generationItemView(Context context, String text, int type, View.OnClickListener onClickListener) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 50);
        layoutParams.height = 200;
        layoutParams.topMargin = 20;
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        textView.setLayoutParams(layoutParams);
        textView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        textView.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        textView.setText(text);
        textView.setTag(type);
        textView.setOnClickListener(onClickListener);
        return textView;
    }
}

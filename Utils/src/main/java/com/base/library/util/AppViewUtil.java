package com.base.library.util;

import android.view.View;

public class AppViewUtil {

    public static void setVisible(View view, boolean visible) {
        if (view == null) {
            return;
        }
        if (visible) {
            if (view.getVisibility() != View.VISIBLE) {
                view.setVisibility(View.VISIBLE);
            }
        } else {
            if (view.getVisibility() != View.GONE) {
                view.setVisibility(View.GONE);
            }
        }
    }
}

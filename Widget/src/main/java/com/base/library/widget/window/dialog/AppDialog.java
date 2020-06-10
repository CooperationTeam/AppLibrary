package com.base.library.widget.window.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.DialogCompat;

public class AppDialog extends Dialog {

    public AppDialog(@NonNull Context context) {
        super(context);
        DialogCompat.requireViewById(this, 0);
    }

    public AppDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected AppDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}

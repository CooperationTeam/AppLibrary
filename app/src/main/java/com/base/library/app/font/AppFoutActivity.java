package com.base.library.app.font;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.base.library.app.R;

/**
 * Created by sunrise on 2020/6/15.
 */
public class AppFoutActivity extends AppCompatActivity {
    public static Intent getIntent(Context context) {
        return new Intent(context, AppFoutActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_font);
    }
}

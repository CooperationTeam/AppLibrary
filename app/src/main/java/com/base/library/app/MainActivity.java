package com.base.library.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.base.library.app.font.AppFontActivity;

/**
 * @author reber
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int APP_FONT = 1;   // Font

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = findViewById(R.id.container_layout);
        linearLayout.addView(ViewUtil.generationItemView(this, "Font的展示", APP_FONT, this));
    }

    @Override
    public void onClick(View v) {
        int type = (int) v.getTag();
        Intent intent = null;
        switch (type) {
            case APP_FONT:
                intent = AppFontActivity.getIntent(this);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}

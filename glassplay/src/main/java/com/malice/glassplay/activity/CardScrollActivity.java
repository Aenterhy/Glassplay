package com.malice.glassplay.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.glass.widget.CardScrollView;
import com.malice.glassplay.R;

/**
 * Base activity for cards
 */
public class CardScrollActivity extends Activity {

    protected CardScrollView vCardScroll;
    private TextView vLoading;
    private ProgressBar vProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_scroll);
        vCardScroll = (CardScrollView) findViewById(R.id.card_scroll);
        vLoading = (TextView) findViewById(R.id.loading);
        vProgress = (ProgressBar) findViewById(R.id.progress);
    }

    protected void showError(int resourceId) {
        vLoading.setText(resourceId);
        vProgress.setVisibility(View.GONE);
    }

    protected void hideProgress() {
        vLoading.setVisibility(View.GONE);
        vProgress.setVisibility(View.GONE);
    }
}

package com.malice.glassplay.activity;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;

import java.util.ArrayList;

/**
 * Activity receives voice recognition results array
 */
public class SearchActivity extends Activity {
    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<String> voiceResults = getIntent().getExtras().getStringArrayList(RecognizerIntent.EXTRA_RESULTS);
        String query = null;
        if (voiceResults != null && voiceResults.size() > 0) query = voiceResults.get(0);

        Intent intent = new Intent(this, StoreActivity.class);
        intent.putExtra(StoreActivity.EXTRA_QUERY, query);
        startActivity(intent);

        finish();
    }
}

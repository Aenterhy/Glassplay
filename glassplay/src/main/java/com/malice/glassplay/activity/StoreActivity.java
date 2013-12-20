package com.malice.glassplay.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.malice.glassplay.R;
import com.malice.glassplay.adapter.AppAdapter;
import com.malice.glassplay.api.GplayRestClient;
import com.malice.glassplay.model.App;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Search results activity
 */
public class StoreActivity extends CardScrollActivity {
    public static final String EXTRA_QUERY = "query";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchForApps();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void searchForApps() {
        String query = getIntent().getStringExtra(EXTRA_QUERY);
        if (TextUtils.isEmpty(query)) return;

        GplayRestClient.get("search", new RequestParams("keyword", URLEncoder.encode(query)), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONArray response) {
                hideProgress();
                List<App> list = new ArrayList<App>();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jo = response.getJSONObject(i);
                        list.add(new App(jo.getString("title"), jo.getString("img"), jo.getString("creator"), jo.getString("rating")));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (list.size() > 0) {
                    vCardScroll.setAdapter(new AppAdapter(list));
                    vCardScroll.activate();
                    vCardScroll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            openOptionsMenu();
                        }
                    });
                }
                hideProgress();
            }

            @Override
            public void onFailure(Throwable e, JSONObject errorResponse) {
                if (e != null) e.printStackTrace();
                hideProgress();
                showError(R.string.error_please_try_again);
            }
        });
    }
}
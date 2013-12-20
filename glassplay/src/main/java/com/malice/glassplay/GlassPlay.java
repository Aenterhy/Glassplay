package com.malice.glassplay;

import android.app.Application;

/**
 * Applications class
 */
public class GlassPlay extends Application {
    static GlassPlay sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static GlassPlay get() {
        return sInstance;
    }
}

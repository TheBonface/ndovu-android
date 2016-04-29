package com.equitygroupfoundation.databundleusage;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by sebichondo on 28/04/2016.
 */
public class DataBundleUsageApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/helvetica_neue-light.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}

package com.syncmedia.acr.demo;

import android.app.Application;

import com.testfairy.TestFairy;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TestFairy.begin(this, "SDK-k8dYoSuE");
    }
}
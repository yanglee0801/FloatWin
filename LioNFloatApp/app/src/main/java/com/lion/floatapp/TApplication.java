package com.lion.floatapp;

import android.app.Application;

import com.lion.floatwin.LioNSDK;

public class TApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LioNSDK.initApplication(this);
    }
}

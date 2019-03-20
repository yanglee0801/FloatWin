package com.lion.floatwin;

import android.app.Activity;
import android.app.Application;

public class Platform {

    private Activity mActivity;
    private Application mApplication;

    static volatile Platform mInstance;


    public  static Platform getInstance() {
        if (mInstance == null) {
            synchronized (Platform.class) {
                if (mInstance == null) {
                    mInstance = new Platform();
                }
            }
        }
        return mInstance;
    }

    public Activity getmActivity() {
        return mActivity;
    }

    public void setmActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public Application getmApplication() {
        return mApplication;
    }

    public void setmApplication(Application mApplication) {
        this.mApplication = mApplication;
    }
}

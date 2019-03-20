package com.lion.floatwin;

import android.app.Activity;
import android.app.Application;

import com.lion.floatwin.floatview.FloatOnClick;
import com.lion.floatwin.manager.FloatManager;

public class LioNSDK {


    public static Activity getMainActivity() {
        return Platform.getInstance().getmActivity();
    }


    public static Application getApplication() {
        return Platform.getInstance().getmApplication();
    }

    public static void initMainActivity(Activity activity){
        Platform.getInstance().setmActivity(activity);
    }

    public static void initApplication(Application application){
        Platform.getInstance().setmApplication(application);
    }

    public static void showToolbar(FloatOnClick onClick){
        FloatManager.getInstance().showToolbar(onClick);
    }


}

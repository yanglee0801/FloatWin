package com.lion.floatwin.floatview;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.WindowManager;

import com.lion.floatwin.LioNSDK;

public class ScreenUtils {

    public static int getScreenWidth() {
        WindowManager var0 = (WindowManager) LioNSDK.getApplication().getSystemService(Context.WINDOW_SERVICE);
        if (var0 == null) {
            return LioNSDK.getApplication().getResources().getDisplayMetrics().widthPixels;
        } else {
            Point var1 = new Point();
            if (Build.VERSION.SDK_INT >= 17) {
                var0.getDefaultDisplay().getRealSize(var1);
            } else {
                var0.getDefaultDisplay().getSize(var1);
            }

            return var1.x;
        }
    }

    public static int getScreenHeight() {
        WindowManager var0 = (WindowManager) LioNSDK.getApplication().getSystemService(Context.WINDOW_SERVICE);
        if (var0 == null) {
            return  LioNSDK.getApplication().getResources().getDisplayMetrics().heightPixels;
        } else {
            Point var1 = new Point();
            if (Build.VERSION.SDK_INT >= 17) {
                var0.getDefaultDisplay().getRealSize(var1);
            } else {
                var0.getDefaultDisplay().getSize(var1);
            }

            return var1.y;
        }
    }

}

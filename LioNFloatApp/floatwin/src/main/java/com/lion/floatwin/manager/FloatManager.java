package com.lion.floatwin.manager;



import com.lion.floatwin.LioNSDK;
import com.lion.floatwin.floatview.FloatOnClick;
import com.lion.floatwin.floatview.FloatView;
import com.lion.floatwin.floatview.FloatWindow;
import com.lion.floatwin.floatview.IFloatWindow;
import com.lion.floatwin.floatview.Screen;

public class FloatManager {

    private static final String TAG = FloatManager.class.getName();

    private static final Object object = new Object();

    private static FloatManager mInstance;

    private FloatManager() {
    }

    public static FloatManager getInstance() {
        if (mInstance == null) {
            synchronized (object) {
                if (mInstance == null) {
                    mInstance = new FloatManager();
                }
            }
        }
        return mInstance;
    }

    private IFloatWindow mFloatWindow;
    private FloatView mFloatView;

    /**
     * 显示悬浮窗
     */
    public void showToolbar(FloatOnClick floatOnClick) {
        if (mFloatWindow == null) {
            mFloatView = new FloatView(LioNSDK.getMainActivity());
            mFloatWindow = FloatWindow.with(LioNSDK.getMainActivity())
                    .setView(mFloatView)
                    .setWidth(Screen.WIDTH, 0.1f)
                    .setHeight(Screen.WIDTH, 0.1f)
                    .setX(Screen.WIDTH, 0)
                    .setY(Screen.HEIGHT, 0.3f)
                    .setMoveStyle(500)
                    .setOnClick(floatOnClick)
                    .build();
        }
        mFloatWindow.show();
    }

    /**
     * 关闭悬浮窗
     */
    public void hideToolbar() {
        if (mFloatWindow != null) {
            mFloatWindow.hide();
        }
    }


}

package com.lion.floatwin.floatview;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;


class FloatApp implements IFloatWindowView {

    private static final String TAG = FloatApp.class.getName();

    private final Context mContext;
    private final WindowManager mWindowManager;
    private final WindowManager.LayoutParams mLayoutParams;
    private View mView;
    private int mX, mY;

    FloatApp(Context appContext) {
        mContext = appContext;
        mWindowManager = (WindowManager) appContext.getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams();
    }

    @Override
    public void setSize(int width, int height) {
        mLayoutParams.width = width;
        mLayoutParams.height = height;
    }

    @Override
    public void setView(View view) {
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;
        mView = view;
    }

    @Override
    public void setGravity(int gravity, int xOffset, int yOffset) {
        mLayoutParams.gravity = gravity;
        mLayoutParams.x = xOffset;
        mLayoutParams.y = yOffset;
    }

    @Override
    public void init() {
        mLayoutParams.format = PixelFormat.RGBA_8888;
        mWindowManager.addView(mView, mLayoutParams);
        Log.d(TAG, "[init] start to add view to window ...");
    }

    @Override
    public void dismiss() {
        mWindowManager.removeView(mView);
    }

    @Override
    public void updateXY(int x, int y) {
        mLayoutParams.x = mX = x;
        mLayoutParams.y = mY = y;
        mWindowManager.updateViewLayout(mView, mLayoutParams);
    }

    @Override
    public void updateX(int x) {
        mLayoutParams.x = mX = x;
        mWindowManager.updateViewLayout(mView, mLayoutParams);
    }

    @Override
    public void updateY(int y) {
        mLayoutParams.y = mY = y;
        mWindowManager.updateViewLayout(mView, mLayoutParams);
    }

    @Override
    public int getX() {
        return mX;
    }

    @Override
    public int getY() {
        return mY;
    }

    @Override
    public void setOnClick(FloatOnClick floatOnClick) {

    }

    @Override
    public void hideOnEdge() {

    }
}

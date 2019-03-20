package com.lion.floatwin.floatview;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;


public class FloatWindow {

    private FloatWindow() {
    }

    private static IFloatWindow mFloatWindow;

    public static IFloatWindow getFloatWindow() {
        return mFloatWindow;
    }

    private static FloatBuilder mBuilder = null;

    public static FloatBuilder with(@NonNull Context appContext) {
        return mBuilder = new FloatBuilder(appContext);
    }

    public static void destory() {
        mFloatWindow.dismiss();
    }



    public static class FloatBuilder {
        Context mApplicationContext;
        View mView;
        int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        int mGravity = Gravity.TOP | Gravity.START;
        int mOffsetX;
        int mOffsetY;
        FloatOnClick floatOnClick;
        long mDuration = 300;
        TimeInterpolator mInterpolator;

        private FloatBuilder() {
        }

        FloatBuilder(Context applicationContext) {
            mApplicationContext = applicationContext;
        }

        public FloatBuilder setView(@NonNull View view) {
            mView = view;
            return this;
        }

        public FloatBuilder setWidth(int width) {
            mWidth = width;
            return this;
        }

        public FloatBuilder setWidth(@Screen.screenType int screentType, float ratio) {
            mWidth = (int) ((screentType == Screen.WIDTH ? ScreenUtils.getScreenWidth() : ScreenUtils.getScreenHeight()) * ratio);
            return this;
        }

        public FloatBuilder setHeight(int height) {
            mHeight = height;
            return this;
        }

        public FloatBuilder setHeight(@Screen.screenType int screentType, float ratio) {
            mHeight = (int) ((screentType == Screen.WIDTH ? ScreenUtils.getScreenWidth() : ScreenUtils.getScreenHeight()) * ratio);
            return this;
        }

        public FloatBuilder setX(int x) {
            mOffsetX = x;
            return this;
        }

        public FloatBuilder setX(@Screen.screenType int screentType, float ratio) {
            mOffsetX = (int) ((screentType == Screen.WIDTH ? ScreenUtils.getScreenWidth() : ScreenUtils.getScreenHeight()) * ratio);
            return this;
        }


        public FloatBuilder setY(int y) {
            mOffsetY = y;
            return this;
        }


        public FloatBuilder setY(@Screen.screenType int screentType, float ratio) {
            mOffsetY = (int) ((screentType == Screen.WIDTH ? ScreenUtils.getScreenWidth() : ScreenUtils.getScreenHeight()) * ratio);
            return this;
        }

        public FloatBuilder setMoveStyle(long duration) {
            mDuration = duration;
            mInterpolator = new BounceInterpolator();
            return this;
        }

        public FloatBuilder setOnClick(FloatOnClick floatOnClick){
            this.floatOnClick = floatOnClick;
            return this;
        }

        public IFloatWindow build() {
            if(mView == null){
                throw new IllegalArgumentException("view has not been set !!!");
            }
            return new IFloatWindowImpl(this);
        }


    }


}

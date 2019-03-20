package com.lion.floatwin.floatview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.lion.floatwin.LioNSDK;


public class IFloatWindowImpl implements IFloatWindow {

    private static final String TAG = IFloatWindowImpl.class.getName();

    private FloatWindow.FloatBuilder mBuilder;
    private IFloatWindowView mFloatView;

    private boolean mFlagIsShow;
    private boolean mFlagOnce = true;
    private ValueAnimator mAnimator;
    private TimeInterpolator mInterpolator;

    float mStartX;
    float mStartY;

    private IFloatWindowImpl() {
    }

    IFloatWindowImpl(FloatWindow.FloatBuilder builder) {
        mBuilder = builder;
        mFloatView = new FloatApp(mBuilder.mApplicationContext);
        mFloatView.setOnClick(mBuilder.floatOnClick);
        mFloatView.setSize(mBuilder.mWidth, mBuilder.mHeight);
        mFloatView.setGravity(mBuilder.mGravity, mBuilder.mOffsetX, mBuilder.mOffsetY);
        mFloatView.setView(mBuilder.mView);

        initTouchEvent();
    }

    /**
     * 处理滑动事件
     */
    private void initTouchEvent() {
        getView().setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getRawX();
                float y = event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mStartX = x;
                        mStartY = y;
                        cancelAnimator();

                        break;
                    case MotionEvent.ACTION_MOVE:
                        int newX = (int) (event.getRawX() - mBuilder.mWidth / 2);
                        int newY = (int) (event.getRawY() - mBuilder.mHeight / 2);
                        mFloatView.updateXY(newX, newY);
                        break;
                    case MotionEvent.ACTION_UP:
                        int startX = mFloatView.getX();
                        int endX = (startX * 2 + v.getWidth()) > ScreenUtils.getScreenWidth() ? ScreenUtils.getScreenWidth() - v.getWidth() : 0;
                        mAnimator = ObjectAnimator.ofInt(startX, endX);
                        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                int x = (int) animation.getAnimatedValue();
                                mFloatView.updateX(x);
                            }
                        });
                        startAnimator();

                        onTouchEnd(Math.abs(x - mStartX) > TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, LioNSDK.getMainActivity().getResources().getDisplayMetrics())
                                || Math.abs(y - mStartY) > TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, LioNSDK.getMainActivity().getResources().getDisplayMetrics()));

                        break;
                    default:
                        break;
                }

                return false;
            }
        });
    }

    private void onTouchEnd(boolean hasMoved) {
        if (!hasMoved){
            mBuilder.floatOnClick.onClick();
//            Toast.makeText(LioNSDK.getMainActivity(),"你又点我了~",Toast.LENGTH_SHORT).show();
        }
    }


    private void startAnimator() {
        Log.d(TAG, "start animation  .......");
        if (mBuilder.mInterpolator == null) {
            if (mInterpolator == null) {
                mInterpolator = new DecelerateInterpolator();
            }
            mBuilder.mInterpolator = mInterpolator;
        }
        mAnimator.setInterpolator(mBuilder.mInterpolator);
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                mAnimator.removeAllUpdateListeners();
                mAnimator.removeAllListeners();
                mAnimator = null;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
//                Toast.makeText(LioNSDK.getMainActivity(),"靠边隐藏...",Toast.LENGTH_SHORT).show();
            }
        });
        mAnimator.setDuration(mBuilder.mDuration).start();
    }

    private void cancelAnimator() {
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.cancel();
        }
    }

    @Override
    public void show() {
        if (mFlagOnce) {
            mFloatView.init();
            mFlagOnce = false;
            mFlagIsShow = true;
            return;
        }
        if (mFlagIsShow) {
            return;
        }
        getView().setVisibility(View.VISIBLE);
        mFlagIsShow = true;
    }

    @Override
    public void hide() {
        if (mFlagOnce || !mFlagIsShow) {
            return;
        }
        getView().setVisibility(View.INVISIBLE);
        mFlagIsShow = false;
    }

    @Override
    public int getX() {
        return mFloatView.getX();
    }

    @Override
    public int getY() {
        return mFloatView.getY();
    }

    @Override
    public void updateX(int x) {
        mBuilder.mOffsetX = x;
        mFloatView.updateX(x);
    }

    @Override
    public void updateX(int screenType, float ratio) {
        mBuilder.mOffsetX = (int) (((screenType == Screen.WIDTH ? ScreenUtils.getScreenWidth() : ScreenUtils.getScreenHeight())) * ratio);
        mFloatView.updateX(mBuilder.mOffsetX);
    }

    @Override
    public void updateY(int y) {
        mBuilder.mOffsetY = y;
        mFloatView.updateY(y);
    }

    @Override
    public void updateY(int screenType, float ratio) {
        mBuilder.mOffsetY = (int) (((screenType == Screen.WIDTH ? ScreenUtils.getScreenWidth() : ScreenUtils.getScreenHeight())) * ratio);
        mFloatView.updateY(mBuilder.mOffsetY);
    }

    @Override
    public View getView() {
        return mBuilder.mView;
    }

    @Override
    public void dismiss() {
        mFloatView.dismiss();
        mFlagIsShow = false;
    }

    @Override
    public void hideOnEdge(boolean isAtEdge) {

    }

}

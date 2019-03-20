package com.lion.floatwin.floatview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lion.floatwin.R;


public class FloatView extends RelativeLayout implements View.OnClickListener{

    private Context mContext;
    private ImageView mImgView;
    private View view;
    // 悬浮窗口上的按钮

    private ImageView floating_front;
    private RelativeLayout floatFrontContainer_RL;

    public FloatView(Context context) {
        super(context);
        init(context);
    }

    public FloatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FloatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        mContext = context;
        view = inflate(context, R.layout.layout_float_window,null);
        initComponents();
        addView(view);
    }
    protected void initComponents() {
        floating_front = (ImageView)view.findViewById(R.id.floating_front);
        floatFrontContainer_RL = (RelativeLayout) view.findViewById(R.id.rl_floating_front_tips_container);

        int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(measureSpec, measureSpec);

        // 设置悬浮窗按钮的监听
//        floatFrontContainer_RL.setOnClickListener(this);
    }



    public void hideOnEdge(boolean isAtEdge){
        if (!isAtEdge) {
            hideControllers();
        } else {
            showControllers();
        }
    }

    private void hideControllers() {
        floating_front.setVisibility(View.INVISIBLE);
    }

    private void showControllers() {
        floating_front.setVisibility(View.VISIBLE);
        showCertainTipsImage();
    }

    private void showCertainTipsImage() {

    }

    @Override
    public void onClick(View v) {

    }
}

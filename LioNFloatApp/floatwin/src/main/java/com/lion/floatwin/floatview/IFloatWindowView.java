package com.lion.floatwin.floatview;

import android.view.View;


interface IFloatWindowView {

    void setSize(int width, int height);

    void setView(View view);

    void setGravity(int gravity, int xOffset, int yOffset);

    void init();

    void dismiss();

    void updateXY(int x, int y);

    void updateX(int x);

    void updateY(int y);

    int getX();

    int getY();

    void setOnClick(FloatOnClick floatOnClick);

    void hideOnEdge();
}

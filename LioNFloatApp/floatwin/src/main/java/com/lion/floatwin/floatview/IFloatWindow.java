package com.lion.floatwin.floatview;

import android.view.View;


public interface IFloatWindow {

    public void show();

    public void hide();

    public int getX();

    public int getY();

    public void updateX(int X);

    public void updateX(@Screen.screenType int screenType, float ratio);

    public void updateY(int Y);

    public void updateY(@Screen.screenType int screenType, float ratio);

    public View getView();

    public void dismiss();


    public void hideOnEdge(boolean isAtEdge);


}

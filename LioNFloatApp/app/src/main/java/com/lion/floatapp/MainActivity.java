package com.lion.floatapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lion.floatwin.LioNSDK;
import com.lion.floatwin.floatview.FloatOnClick;

public class MainActivity extends Activity {

    TextView mTvShowFloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvShowFloat = findViewById(R.id.showFloat);
        LioNSDK.initMainActivity(MainActivity.this);
        mTvShowFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LioNSDK.showToolbar(new FloatOnClick() {
                    @Override
                    public void onClick() {
                        Toast.makeText(MainActivity.this,"xxxx",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

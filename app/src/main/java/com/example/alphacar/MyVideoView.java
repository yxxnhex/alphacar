package com.example.alphacar;

import android.content.Context;
import android.content.pm.Attribution;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;
import android.widget.VideoView;

public class MyVideoView extends VideoView {

    public MyVideoView(Context context, AttributeSet attrs){
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        Display dis = ((WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        setMeasuredDimension(dis.getWidth(), dis.getHeight());
    }
}
